/***********************************************************
 * @Description : 81.搜索旋转排序数组II
 * https://leetcode-cn.com/problems/search-in-rotated-sorted-array-ii/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/31 11:11
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C06_查找.T81_搜索旋转排序数组II;

/**
 * Search in Rotated Sorted Array II
 * Time Complexity: O(n)， Space Complexity: O(1)
 *
 * 允许重复元素， 则上一题(T33.搜索旋转排序数组)中如果 A[left] <= A[mid] ,那么 [left,mid] 为递增序列的假设就不能成立了， 比如 [1,3,1,1,1] 。
 * 既然 A[left] <= A[mid] 不能确定递增， 那就把它拆分成两个条件：
 * 1.若 A[left] < A[mid] ， 则区间 [left,mid] 一定递增
 * 2.若 A[left] == A[mid] 确定不了， 那就 left++ ， 往下看一步即可
 */
public class Solution {
    public boolean search(int[] nums, int target) {
        int first = 0, last = nums.length;
        while (first != last) {
            final int mid = first + (last - first) / 2;
            if (nums[mid] == target) {
                return true;
            }
            if (nums[first] < nums[mid]) {
                if (nums[first] <= target && target < nums[mid]) {
                    last = mid;
                } else {
                    first = mid + 1;
                }
            } else if (nums[first] > nums[mid]) {
                if (nums[mid] < target && target <= nums[last - 1]) {
                    first = mid + 1;
                } else {
                    last = mid;
                }
            } else {
                //skip duplicate one
                first++;
            }
        }
        return false;
    }
}
