/***********************************************************
 * @Description : 154.寻找旋转排序数组中的最小值II
 * https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array-ii/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/31 11:25
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C06_查找.T154_寻找旋转排序数组中的最小值II;

/**
 * 同 T153 类似， 要判断“断层”在左边还是右边。
 * 若 A[mid] < A[right] ， 则区间 [mid,right] 一定递增， 断层一定在左边
 * 若 A[mid] > A[right] ， 则区间 [left,mid] 一定递增， 断层一定在右边
 * 若 A[mid] == A[right] 确定不了， 这个时候， 断层既可能在左边， 也可能在右边， 所以我们不能扔
 * 掉一半， 不过这时， 我们可以 --right 扔掉一个
 * 本题还有另一种思路，
 * 若 A[left] < A[mid] ， 则区间 [left,mid] 一定递增， 断层一定在右边
 * 若 A[left] > A[mid] ， 则区间 [mid,right] 一定递增， 断层一定在左边
 * 若 A[left] == A[mid] 确定不了， 这个时候， 断层既可能在左边， 也可能在右边， 所以我们不能扔
 * 掉一半， 不过这时， 我们可以 ++left 扔掉一
 * 注意， 第三种情况， 我们认为可以 ++left 扔掉一个， 这个做法是不对的， 因为数组被分成两段后， 两段
 * 分别是递增的， left 这个元素有可能是全局最小值， 不能贸然扔掉。 而在前一种思路中， end 可以扔
 * 掉， 因为 end 在右边， 它的左边必然有小于或等于它的元素， 所以可以放心 --end
 * <p>
 * 时间复杂度O(logn)， 最坏 O(n)， 空间复杂度O(1)
 */
public class Solution {
    public int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < nums[right]) {
                right = mid;
            } else if (nums[mid] > nums[right]) {
                left = mid + 1;
            } else {
                --right;
            }
        }
        return nums[left];
    }
}
