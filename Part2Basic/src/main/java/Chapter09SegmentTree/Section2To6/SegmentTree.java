/***********************************************************
 * @Description : 线段树
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/5/18 23:02
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter09SegmentTree.Section2To6;

import java.util.Arrays;

public class SegmentTree<Element> {
    private Element[] tree;
    private Element[] data;
    private Merger<Element> merger;

    public SegmentTree(Element[] arr, Merger<Element> merger) {
        this.data = (Element[]) new Object[arr.length];
        this.merger = merger;
        // 数组复制
        System.arraycopy(arr, 0, data, 0, arr.length);
        tree = (Element[]) new Object[4 * arr.length];
        buildSegmentTree(0, 0, data.length - 1);
    }

    /**
     * 创建线段树：在treeIndex的位置创建区间[l...r]的线段树
     *
     * @param treeIndex 要创建的线段树的根节点
     */
    private void buildSegmentTree(int treeIndex, int l, int r) {
        // assert (l <= r);
        // 当只有一个元素的时候
        if (l == r) {
            tree[treeIndex] = data[l];
            return;
        }
        // 获取左孩子的索引
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);
        // 确定左右区间的分界点，[leftTreeIndex, mid] [mid+1, rightTreeIndex]
        int mid = l + (r - l) / 2;
        buildSegmentTree(leftTreeIndex, l, mid);
        buildSegmentTree(rightTreeIndex, mid + 1, r);

        // 这里根据业务特点决定每个节点的值是sum、max、min、avg等.综合两段子树来得到父节点的数据
        tree[treeIndex] = merger.merge(tree[leftTreeIndex], tree[rightTreeIndex]);
    }

    public Element get(int index) {
        if (index < 0 || index >= data.length) {
            throw new IllegalArgumentException("Index is illegal");
        }
        return data[index];
    }

    public int getSize() {
        return data.length;
    }

    /**
     * 返回二叉树的数组表示中的一个索引所表示的元素的左孩子节点的索引
     */
    public int leftChild(int index) {
        return 2 * index + 1;
    }

    /**
     * 返回二叉树的数组表示中的一个索引所表示的元素的右孩子节点的索引
     */
    public int rightChild(int index) {
        return 2 * index + 2;
    }

    @Override
    public String toString() {
        return "SegmentTree{" +
                "tree=" + Arrays.toString(tree) +
                '}';
    }
}
