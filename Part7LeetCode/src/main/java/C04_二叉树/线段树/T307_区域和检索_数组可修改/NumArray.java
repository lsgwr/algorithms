/***********************************************************
 * @Description : 307. 区域和检索 - 数组可修改
 * https://leetcode-cn.com/problems/range-sum-query-mutable/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/30 22:32
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C04_二叉树.线段树.T307_区域和检索_数组可修改;

import java.util.Arrays;

interface Merger<E> {
    /**
     * 这里根据业务特点决定每个节点的值合并方式是sum、max、min、avg等.
     * 即综合两段子树来得到父节点的数据
     */
    E merge(E a, E b);
}

class SegmentTree<E> {
    /**
     * 线段树，开辟4个data的长度即可
     */
    private E[] tree;
    /**
     * 存储原始数据的数组
     */
    private E[] data;
    private Merger<E> merger;

    public SegmentTree(E[] arr, Merger<E> merger) {
        this.data = (E[]) new Object[arr.length];
        this.merger = merger;
        // 数组复制
        for (int i = 0; i < arr.length; i++) {
            data[i] = arr[i];
        }
        tree = (E[]) new Object[4 * arr.length];
        // 构建线段树
        buildSegmentTree(0, 0, data.length - 1);
    }

    /**
     * 创建线段树：在treeIndex的位置创建区间[l...r]的线段树
     *
     * @param treeIndex 要创建的线段树的根节点
     */
    private void buildSegmentTree(int treeIndex, int l, int r) {
        assert (l <= r);
        // 1.递归终止条件
        if (l == r) {
            // 当只有一个元素的时候，即到了线段树的最下层叶子节点的位置
            tree[treeIndex] = data[l];
            return;
        }

        // 2.递归具体逻辑
        // 获取左孩子的索引
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);
        // 确定左右区间的分界点，[leftTreeIndex, mid] [mid+1, rightTreeIndex]
        // 中间位置 当(l+r)/2会有溢出问题，，可以使用l+(r-l)/2来解决
        int mid = l + (r - l) / 2;
        buildSegmentTree(leftTreeIndex, l, mid);
        buildSegmentTree(rightTreeIndex, mid + 1, r);

        // 这里根据业务特点决定每个节点的值是sum、max、min、avg等.综合左右子树来得到父节点的数据
        tree[treeIndex] = merger.merge(tree[leftTreeIndex], tree[rightTreeIndex]);
    }

    /**
     * 查询指定区间[queryL,queryR]的merge结果
     *
     * @param queryL 区间左边界
     * @param queryR 区间右边界
     * @return 返回得到的merge结果
     */
    public E query(int queryL, int queryR) {
        assert queryL <= queryR;
        if (queryL < 0 || queryL >= data.length || queryR < 0 || queryR >= data.length) {
            throw new IllegalArgumentException("Index is illegal");
        }
        return query(0, 0, data.length - 1, queryL, queryR);
    }

    /**
     * 在以treeIndex为跟的线段树种[l...r]的范围内，搜索区间[queryL...queryR]的值
     *
     * @param treeIndex 开始查找的根节点
     * @param l         区间左边界
     * @param r         区间右边界
     * @param queryL    要查询的左边界
     * @param queryR    要查询的右边界
     * @return 查询并Merge后的结果
     */
    private E query(int treeIndex, int l, int r, int queryL, int queryR) {
        // 1.递归终止条件
        if (l == queryL && r == queryR) {
            // 当区间边界和指定的完全重合的时候，直接返回根节点的值就行
            return tree[treeIndex];
        }

        // 2.递归具体逻辑
        // 获取左孩子的索引
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);
        // 中间位置 当(l+r)/2会有溢出问题，，可以使用l+(r-l)/2来解决
        int mid = l + (r - l) / 2;
        // 只在右侧或者只在左侧，都可以直接计算结果后返回
        if (queryL >= mid + 1) {
            // 2.1 只在右侧找，因为左边界都比左右子树的中间位置大，所以一定在右侧找
            return query(rightTreeIndex, mid + 1, r, queryL, queryR);
        } else if (queryR <= mid) {
            // 2.2 只在左侧找，因为右边界都比左右子树的中间位置小，所以一定在左侧找
            return query(leftTreeIndex, l, mid, queryL, queryR);
        }

        // 2.3在左右两侧各有一部分,不会有一个区间完全满足我们的查找边界[queryL, queryR]，所以要分开查，然后用自定义的Merge计算最终的结果
        E leftResult = query(leftTreeIndex, l, mid, queryL, mid);
        E rightResult = query(rightTreeIndex, mid + 1, r, mid + 1, queryR);
        return merger.merge(leftResult, rightResult);
    }

    /**
     * 更新指定位置的元素
     */
    public void update(int index, E e) {
        if (index < 0 || index >= data.length) {
            throw new IllegalArgumentException("Index is illegal");
        }
        data[index] = e;
        // 下面是更新线段树
        update(0, 0, data.length - 1, index, e);
    }

    private void update(int treeIndex, int l, int r, int index, E e) {
        if (l == r) {
            tree[treeIndex] = e;
            return;
        }
        // 获取左孩子的索引
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);
        // 确定左右区间的分界点，[leftTreeIndex, mid] [mid+1, rightTreeIndex]
        int mid = l + (r - l) / 2;
        // 一定是大于等于，别只写成大于哈
        if (index >= mid + 1) {
            // 右子树中查找
            update(rightTreeIndex, mid + 1, r, index, e);
        } else {
            // 左子树中查找
            update(leftTreeIndex, l, mid, index, e);
        }

        tree[treeIndex] = merger.merge(tree[leftTreeIndex], tree[rightTreeIndex]);
    }

    /**
     * 获取E中指定下标对应的值
     */
    public E get(int index) {
        if (index < 0 || index >= data.length) {
            throw new IllegalArgumentException("Index is illegal");
        }
        return data[index];
    }

    /**
     * 获取数据的个数
     */
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
        return "SegmentTree { \n" +
                "  dataSize=" + data.length
                + ", \n  data=" + Arrays.asList(data) + ", \n  treeSize=" + tree.length +
                ", \n  tree=" + Arrays.toString(tree) +
                "\n}";
    }
}

public class NumArray {
    /**
     * 存储nums地线段树
     */
    private SegmentTree<Integer> segmentTree;

    public NumArray(int[] nums) {
        if (nums.length > 0) {
            // 1.必须要把基础类转成包装类，否则会在给new SegmentTree时出错
            Integer[] numsObj = new Integer[nums.length];
            for (int i = 0; i < nums.length; i++) {
                numsObj[i] = nums[i];
            }
            // 2.声明SegmentTree和自定义的合并规则
            segmentTree = new SegmentTree<>(numsObj, new Merger<Integer>() {
                @Override
                public Integer merge(Integer a, Integer b) {
                    return a + b;
                }
            });
        }
    }

    public void update(int i, int val) {
        // 线段树为空，一般是因为上面用户传入了空nums
        if (segmentTree == null) {
            throw new IllegalArgumentException("Segment Tree is null");
        }
        segmentTree.update(i, val);
    }

    public int sumRange(int i, int j) {
        // 线段树为空，一般是因为上面用户传入了空nums
        if (segmentTree == null) {
            throw new IllegalArgumentException("Segment Tree is null");
        }
        // [i, j]时闭区间
        return segmentTree.query(i, j);
    }

}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * obj.update(i,val);
 * int param_2 = obj.sumRange(i,j);
 */
