/***********************************************************
 * @Description : 153.寻找旋转排序数组中的最小值
 * https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/31 11:23
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C06_查找.T153_寻找旋转排序数组中的最小值;

/**
 * 从左向右扫描， 扫描到的第一个逆序的位置， 肯定是原始数组中第一个元素， 时间复杂度 O(n) 。
 * 不过本题依旧可以用二分查找， 最关键的是要判断那个“断层”是在左边还是右边。
 * 若 A[mid] < A[right] ， 则区间 [mid,right] 一定递增， 断层一定在左边
 * 若 A[mid] > A[right] ， 则区间 [left,mid] 一定递增， 断层一定在右边
 * nums[mid] == nums[right] ， 这种情况不可能发生， 因为数组是严格单调递增的， 不存在重复元素
 * <p>
 * 时间复杂度O(logn)， 空间复杂度O(1)
 */
public class Solution {
    public int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < nums[right]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return nums[left];
    }
}
