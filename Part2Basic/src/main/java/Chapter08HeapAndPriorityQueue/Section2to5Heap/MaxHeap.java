/***********************************************************
 * @Description : 这个课里的MaxHeap因为使用了前面自己封装的Array，更加灵活了，值得学习下
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/5/18 00:42
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter08HeapAndPriorityQueue.Section2to5Heap;

import Chapter02Arrays.Section7DynamicArray.Array;

public class MaxHeap<Element extends Comparable<Element>> {

    private Array<Element> data;

    public MaxHeap(int capacity) {
        data = new Array<>(capacity);
    }

    public MaxHeap() {
        data = new Array<>();
    }

    public MaxHeap(Element[] arr) {
        // 忘记传入arr了简直日了
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
    public void insert(Element element) {
        data.addLast(element);
        siftUp(data.getSize() - 1);
    }

    private void siftUp(int k) {

        while (k > 0 && data.get(parent(k)).compareTo(data.get(k)) < 0) {
            data.swap(k, parent(k));
            k = parent(k);
        }
    }

    /**
     * 看堆中的最大元素
     */
    public Element getMax() {
        if (data.getSize() == 0) {
            throw new IllegalArgumentException("Can not getMax when heap is empty.");
        }
        return data.get(0);
    }

    /**
     * 取出堆中最大元素
     */
    public Element popMax() {

        Element ret = getMax();

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
    public Element replace(Element e) {

        Element ret = getMax();
        data.set(0, e);
        siftDown(0);
        return ret;
    }
}

