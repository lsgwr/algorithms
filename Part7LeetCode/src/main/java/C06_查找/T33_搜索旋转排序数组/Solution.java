/***********************************************************
 * @Description : 33.搜索旋转排序数组
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/31 11:04
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C06_查找.T33_搜索旋转排序数组;

/**
 * Search in Rotated Sorted Array
 * Time Complexity: O(log n)， Space Complexity: O(1)
 */
public class Solution {
    public int search(int[] nums, int target) {
        int first = 0, last = nums.length;
        while (first != last) {
            final int mid = first + (last - first) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            // 如果 nums[left] <= nums[mid] ,那么 [left,mid] 一定为单调递增序列
            if (nums[first] <= nums[mid]) {
                if (nums[first] <= target && target < nums[mid]) {
                    last = mid;
                } else {
                    first = mid + 1;
                }
            } else {
                if (nums[mid] < target && target <= nums[last - 1]) {
                    first = mid + 1;
                } else {
                    last = mid;
                }
            }
        }
        return -1;
    }
}
