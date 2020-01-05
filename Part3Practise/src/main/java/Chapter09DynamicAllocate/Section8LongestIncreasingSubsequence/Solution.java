/***********************************************************
 * @Description : 动态规划求解最长子序列,时间复杂度O(n^2)
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2019/8/26 07:53
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter09DynamicAllocate.Section8LongestIncreasingSubsequence;

import java.util.Arrays;

public class Solution {
    public int lengthOfLIS(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        // memo[i]表示0~i区间内以nums[i]为结尾的的最长上升子序列的长度
        int[] memo = new int[nums.length];
        // 初始化
        Arrays.fill(memo, 1);
        // 第1个元素不用考虑了，其最长子序列一定是1，已经初始化好了
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    // 如果i前面的值有大于i处的值的，更新子序列长度
                    // memo[i]表示当前i处的子序列长度，1+memo[i]表示考虑j的情况下最长子序列长度加1
                    // memo[i]在 `j in 0~i`的循环中可能已经被跟新多次了，不再是初始值1了
                    memo[i] = Math.max(memo[i], 1 + memo[j]);
                }
            }
        }
        int maxLIS = 1;
        // 从最长子序列数组中取最大地返回
        for (int i = 0; i < nums.length; i++) {
            maxLIS = Math.max(maxLIS, memo[i]);
        }
        return maxLIS;
    }

    public static void main(String[] args) {
        int[] nums = {10, 9, 2, 5, 3, 7, 101, 18};
        int maxLIS = new Solution().lengthOfLIS(nums);
        System.out.println(maxLIS);
    }
}
