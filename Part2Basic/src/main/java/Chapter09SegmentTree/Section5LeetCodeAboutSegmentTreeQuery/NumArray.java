/***********************************************************
 * @Description : 303. 区域和检索 - 数组不可变
 * 必须考虑传入的数组为空的情况
 * 本题只考察了线段树查询
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/1 23:27
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter09SegmentTree.Section5LeetCodeAboutSegmentTreeQuery;

import Chapter09SegmentTree.Section1to4SegmentTreeImpl.Merger;
import Chapter09SegmentTree.Section1to4SegmentTreeImpl.SegmentTree;

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

    public int sumRange(int i, int j) {
        // 线段树为空，一般是因为上面用户传入了空nums
        if (segmentTree == null) {
            throw new IllegalArgumentException("Segment Tree is null");
        }
        // [i, j]时闭区间
        return segmentTree.query(i, j);
    }

    public static void main(String[] args) {
        int[] nums = {-2, 0, 3, -5, 2, -1};
        NumArray obj = new NumArray(nums);
        System.out.println(obj.sumRange(0, 2));
        System.out.println(obj.sumRange(2, 5));
        System.out.println(obj.sumRange(0, 5));
    }
}
/***
 * 1
 * -1
 * -3
 */