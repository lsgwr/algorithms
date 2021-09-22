/***********************************************************
 * @Description : 198.打家劫舍
 * https://leetcode-cn.com/problems/house-robber/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/31 21:28
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C12_动态规划.T198_打家劫舍;

class Solution {
    public int rob(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return nums[0];
        }
        int[] memo = new int[n + 1];
        memo[0] = nums[0];
        memo[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < n; i++) {
            memo[i] = Math.max(nums[i] + memo[i - 2], memo[i - 1]);
        }
        return memo[n - 1];
    }
}
