/***********************************************************
 * @Description : 88.合并两个有序数组
 * https://leetcode-cn.com/problems/merge-sorted-array/
 * 给你两个有序整数数组 nums1 和 nums2，请你将 nums2 合并到 nums1 中，使 num1 成为一个有序数组。
 * 说明:
 * 初始化 nums1 和 nums2 的元素数量分别为 m 和 n 。
 * 你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/4/6 11:01
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C01_线性表.数组.T88_合并两个有序数组;

class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        // 1.递归终止条件
        if(n == 0){
            // nums2为空时，nums1还有元素，调整完毕，nums1既是想要的
            return;
        }
        if(m == 0){
            // nums1为空时，nums2还有元素，直接把nums2剩下的元素全都覆盖到nums1中即可
            for(int i = 0; i < n; i++){
                nums1[i] = nums2[i];
            }
            return;
        }

        // 2.递归的具体逻辑
        // nums2和nums1都不为空时，需要比较最后一个元素，决定nums1最后插入哪个元素
        if(nums2[n - 1] > nums1[m - 1]){
            // nums2的最后一个元素大于nums1的最后一个元素，则nums1最后的元素取nums2的
            nums1[m + n -1] = nums2[n - 1];
            // nums2少了一个元素，继续往下递归
            merge(nums1, m, nums2, n-1);
        }else {
            // nums2的最后一个元素小于nums1的最后一个元素，则nums1最后的元素取nums1的
            nums1[m + n -1] = nums1[m - 1];
            // nums1少了一个元素，继续往下递归
            merge(nums1, m-1, nums2, n);
        }
    }
}
