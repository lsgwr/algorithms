/***********************************************************
 * @Description : 基于索引从0开始的动态数组Array的最大堆
 * 这个课里的MaxHeap因为使用了前面自己封装的Array，Array类很多
 * 常用的方法对构造最大堆很有用。比如addLast、addFirst、swap等
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2020/1/1 11:17
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter08HeapAndPriorityQueue.Section2to5Heap;

import Chapter02Arrays.Section7DynamicArray.Array;

public class MaxHeap<E extends Comparable<E>> {

    /**
     * 存放节点的数组
     */
    private Array<E> data;

    public MaxHeap(int capacity) {
        // 初始化时设置动态数组容量
        data = new Array<>(capacity);
    }

    public MaxHeap() {
        // 初始化空数组，动态扩缩容
        data = new Array<>();
    }

    public MaxHeap(E[] arr) {
        // 直接根绝外面传入的数组对动态数据进行初始化
        data = new Array<>(arr);
        for (int i = parent(arr.length - 1); i >= 0; i--) {
            siftDown(i);
        }
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
        siftUp(data.getSize() - 1);
    }

    /**
     * 上浮data中索引为k的元素
     *
     * @param k 索引
     */
    private void siftUp(int k) {
        // 索引k处的元素
        E kE = data.get(k);
        // k元素的父元素
        E parentE = data.get(parent(k));
        // 只要k处节点大于其父亲节点，就交换两个节点继续上浮
        while (k > 0 && kE.compareTo(parentE) > 0) {
            // k和父亲节点交换
            data.swap(k, parent(k));
            // 交换会更新k为父亲节点的索引
            k = parent(k);
        }
    }

    /**
     * 看堆中的最大元素
     */
    public E getMax() {
        if (data.getSize() == 0) {
            throw new IllegalArgumentException("Can not getMax when heap is empty.");
        }
        return data.get(0);
    }

    /**
     * 取出堆中最大元素
     */
    public E popMax() {

        E ret = getMax();

        data.swap(0, data.getSize() - 1);
        data.removeLast();
        siftDown(0);

        return ret;
    }

    private void siftDown(int k) {

        while (leftChild(k) < data.getSize()) {
            /* 在此轮循环中,data[k]和data[j]交换位置 */
            int j = leftChild(k);
            if (j + 1 < data.getSize() &&
                    data.get(j + 1).compareTo(data.get(j)) > 0) {
                j++;
            }
            // data[j] 是 leftChild 和 rightChild 中的最大值

            if (data.get(k).compareTo(data.get(j)) >= 0) {
                break;
            }

            data.swap(k, j);
            k = j;
        }
    }

    /**
     * 取出堆中的最大元素，并且替换成元素e
     */
    public E replace(E e) {

        E ret = getMax();
        data.set(0, e);
        siftDown(0);
        return ret;
    }
}

