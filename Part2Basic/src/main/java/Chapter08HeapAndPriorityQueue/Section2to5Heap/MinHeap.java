/***********************************************************
 * @Description : 基于索引从0开始的动态数组Array的最小堆
 * 这个课里的MinHeap因为使用了前面自己封装的Array，Array类很多
 * 常用的方法对构造最小堆很有用。比如addLast、addFirst、swap等
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2020/1/1 17:21
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter08HeapAndPriorityQueue.Section2to5Heap;

import Chapter02Arrays.Section7DynamicArray.Array;

public class MinHeap<E extends Comparable<E>> {

    /**
     * 存放节点的数组
     */
    private Array<E> data;

    public MinHeap(int capacity) {
        // 初始化时设置动态数组容量
        data = new Array<>(capacity);
    }

    public MinHeap() {
        // 初始化空数组，动态扩缩容
        data = new Array<>();
    }

    public MinHeap(E[] arr) {
        // 直接根据外面传入的数组对整理成最小堆
        data = new Array<>(arr);
        heapify();
    }

    /**
     * 返回堆中的元素个数
     */
    public int size() {
        return data.getSize();
    }

    /**
     * 返回一个布尔值, 表示堆中是否为空
     */
    public boolean isEmpty() {
        return data.isEmpty();
    }

    /**
     * 返回完全二叉树的数组表示中，一个索引所表示的元素的父亲节点的索引
     */
    private int parent(int index) {
        if (index == 0) {
            throw new IllegalArgumentException("index-0 doesn't have parent.");
        }
        return (index - 1) / 2;
    }

    /**
     * 返回完全二叉树的数组表示中，一个索引所表示的元素的左孩子节点的索引
     */
    private int leftChild(int index) {
        return index * 2 + 1;
    }

    /**
     * 返回完全二叉树的数组表示中，一个索引所表示的元素的右孩子节点的索引
     */
    private int rightChild(int index) {
        return index * 2 + 2;
    }

    /**
     * 向堆中添加元素
     */
    public void add(E e) {
        data.addLast(e);
        // 新加入的元素一定在末尾，所以上浮它即可
        shiftUp(data.getSize() - 1);
    }

    /**
     * 上浮data中索引为k的元素
     *
     * @param k 索引
     */
    private void shiftUp(int k) {
        // 只要k处节点小于其父亲节点，就交换两个节点继续上浮。不断更新k但要保证k>0
        while (k > 0 && data.get(k).compareTo(data.get(parent(k))) < 0) {
            // k和父亲节点交换
            data.swap(k, parent(k));
            // 交换会更新k为父亲节点的索引
            k = parent(k);
        }
    }

    /**
     * 看堆中的最小元素
     */
    public E getMin() {
        if (data.getSize() == 0) {
            throw new IllegalArgumentException("Can not getMax when heap is empty.");
        }
        // 0为根节点，根节点位置就是最小值
        return data.get(0);
    }

    /**
     * 取出堆中最小元素
     */
    public E popMin() {
        E ret = getMin();
        // 交换根节点和最后一个节点
        data.swap(0, data.getSize() - 1);
        // 删除最后一个节点，此时最小值就被正式删除了
        data.removeLast();
        // 调整新的二叉堆直到满足最小堆的性质
        siftDown(0);
        // 返回前面暂存的最小节点
        return ret;
    }

    private void siftDown(int k) {
        while (leftChild(k) < data.getSize()) {
            /* 在此轮循环中,data[k]和data[j]交换位置 */
            int j = leftChild(k);
            // j+1表右孩子，首先要在数组索引范围内，而且右孩子比左孩子小，则j取左右孩子中的较小值即右孩子的索引
            if (j + 1 < data.getSize() && data.get(j + 1).compareTo(data.get(j)) < 0) {
                j++;
            }
            // data[j] 是 k的左右孩子 中的较小者，当满足最小堆得性质时退出while循环
            if (data.get(k).compareTo(data.get(j)) <= 0) {
                break;
            }
            data.swap(k, j);
            k = j;
        }
    }

    /**
     * 取出堆中的最小元素，并且替换成元素e
     */
    public E replace(E e) {
        E ret = getMin();
        data.set(0, e);
        siftDown(0);
        return ret;
    }

    /**
     * 将任意数组整理成堆
     */
    public void heapify() {
        // 初始化的数组，靠上层的节点肯定索引小，所以不断循环进行下沉即可
        // parent(arr.length - 1)表示最后一个非叶子节点
        // i--的过程就是不断向最小堆上面遍历的过程
        int lastNotLeaf = parent(data.getSize() - 1);
        for (int i = lastNotLeaf; i >= 0; i--) {
            siftDown(i);
        }
    }
}

