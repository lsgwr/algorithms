/***********************************************************
 * @Description : 198.House Robber 动态递归问题详解
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2019/8/23 23:23
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter09DynamicAllocate.Section4HouseRobber;

import java.util.Arrays;

public class Solution3Dynamic {

    public int rob(int[] nums) {
        int n = nums.length;
        int[] memo = new int[n + 1];
        Arrays.fill(memo, -1);
        // 从起点0开始抢劫房子
        memo[n - 1] = nums[n - 1];

        // memo[i]表示考虑抢劫memo[i...n-i]所能获得的最大收益
        for (int i = n - 2; i >= 0; i--) {
            // memo[i]
            for (int j = i; j < n; j++) {
                memo[i] = Math.max(memo[i], nums[j] + (j + 2 < n ? memo[j + 2] : 0));
            }
        }
        return memo[0];
    }


    public static void main(String[] args) {
        int[] nums = {2, 7, 9, 3, 1, 4, 7, 9, 5};
        int robMax = new Solution3Dynamic().rob(nums);
        System.out.println("抢劫到的最多的钱是：" + robMax);
        System.out.println("动态规划不需要递归");
    }
}

/**
 * 输出结果是：
 * <p>
 * 抢劫到的最多的钱是：24
 * 动态规划不需要递归
 */
