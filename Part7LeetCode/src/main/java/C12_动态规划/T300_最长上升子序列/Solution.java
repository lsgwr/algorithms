/***********************************************************
 * @Description : 300.最长上升子序列
 * https://leetcode-cn.com/problems/longest-increasing-subsequence/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/31 18:53
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C12_动态规划.T300_最长上升子序列;

import java.util.Arrays;

class Solution {
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        int[] LIS = new int[n];
        Arrays.fill(LIS, 1);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                // nums[j] >= nums[i]才算上升
                if (nums[j] < nums[i]) {
                    // 不断用更大的LIS值来更新i处的LIS
                    LIS[i] = Math.max(LIS[i], LIS[j] + 1);
                }
            }
        }
        int max = 0;
        for (int lis : LIS) {
            if (lis > max) {
                max = lis;
            }
        }
        return max;
    }
}