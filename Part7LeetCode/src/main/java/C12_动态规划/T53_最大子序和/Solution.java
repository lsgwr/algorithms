/***********************************************************
 * @Description : 53.最大子序和
 * https://leetcode-cn.com/problems/maximum-subarray/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/31 18:45
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C12_动态规划.T53_最大子序和;

/**
 * Maximum Subarray
 * 时间复杂度O(n)， 空间复杂度O(1)
 */
public class Solution {
    public int maxSubArray(int[] nums) {
        int maxLocal = nums[0];
        int global = nums[0];
        for (int i = 1; i < nums.length; ++i) {
            maxLocal = Math.max(nums[i], nums[i] + maxLocal);
            global = Math.max(global, maxLocal);
        }
        return global;
    }
}
