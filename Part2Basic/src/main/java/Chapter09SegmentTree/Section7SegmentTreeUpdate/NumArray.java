/***********************************************************
 * @Description : LeetCode题目307
 *  https://leetcode-cn.com/problems/verbal-arithmetic-puzzle/
 *  本问题同时用到了线段树好的查询和更新，所以需要本节完成SegmentTree的update函数
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/1 23:48
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter09SegmentTree.Section7SegmentTreeUpdate;

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