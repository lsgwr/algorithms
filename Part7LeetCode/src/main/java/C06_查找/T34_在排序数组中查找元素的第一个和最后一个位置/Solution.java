/***********************************************************
 * @Description : 34.在排序数组中查找元素的第一个和最后一个位置
 * https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/31 10:11
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C06_查找.T34_在排序数组中查找元素的第一个和最后一个位置;

/**
 * 二分查找法分别找到上边界和下边界
 * Search for a Range
 * 重新实现 lower_bound 和 upper_bound
 * 时间复杂度O(logn)， 空间复杂度O(1)
 */
public class Solution {
    public int[] searchRange(int[] nums, int target) {
        int lower = lower_bound(nums, 0, nums.length, target);
        int upper = upper_bound(nums, 0, nums.length, target);
        if (lower == nums.length || nums[lower] != target) {
            return new int[]{-1, -1};
        } else {
            return new int[]{lower, upper - 1};
        }
    }

    int lower_bound(int[] nums, int first, int last, int target) {
        while (first != last) {
            int mid = first + (last - first) / 2;
            if (target > nums[mid]) {
                first = ++mid;
            } else {
                last = mid;
            }
        }
        return first;
    }

    int upper_bound(int[] nums, int first, int last, int target) {
        while (first != last) {
            int mid = first + (last - first) / 2;
            // 与 lower_bound 仅此不同
            if (target >= nums[mid]) {
                first = ++mid;
            } else {
                last = mid;
            }
        }
        return first;
    }
}
