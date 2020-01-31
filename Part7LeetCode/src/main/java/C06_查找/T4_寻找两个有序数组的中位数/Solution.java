/***********************************************************
 * @Description : 4.寻找两个有序数组的中位数
 * https://leetcode-cn.com/problems/median-of-two-sorted-arrays/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/31 11:33
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C06_查找.T4_寻找两个有序数组的中位数;

/**
 * Median of Two Sorted Arrays
 * Time Complexity: O(log(m+n))， Space Complexity: O(log(m+n))
 */
public class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int total = nums1.length + nums2.length;
        if (total % 2 == 1) {
            return findKth(nums1, 0, nums2, 0, total / 2 + 1);
        } else {
            return (findKth(nums1, 0, nums2, 0, total / 2) + findKth(nums1, 0, nums2, 0, total / 2 + 1)) / 2.0;
        }
    }

    private static int findKth(final int[] nums1, int ai, final int[] nums2, int bi, int k) {
        //always assume that nums1 is shorter than nums2
        if (nums1.length - ai > nums2.length - bi) {
            return findKth(nums2, bi, nums1, ai, k);
        }
        if (nums1.length - ai == 0) {
            return nums2[bi + k - 1];
        }
        if (k == 1) {
            return Math.min(nums1[ai], nums2[bi]);
        }
        //divide k into two parts
        int k1 = Math.min(k / 2, nums1.length - ai), k2 = k - k1;
        if (nums1[ai + k1 - 1] < nums2[bi + k2 - 1]) {
            return findKth(nums1, ai + k1, nums2, bi, k - k1);
        } else if (nums1[ai + k1 - 1] > nums2[bi + k2 - 1]) {
            return findKth(nums1, ai, nums2, bi + k2, k - k2);
        } else {
            return nums1[ai + k1 - 1];
        }
    }
}
