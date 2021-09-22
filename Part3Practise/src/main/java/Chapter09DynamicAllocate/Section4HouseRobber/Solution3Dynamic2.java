/***********************************************************
 * @Description : 198.House Robber 动态递归问题详解
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2019/8/23 23:23
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter09DynamicAllocate.Section4HouseRobber;

public class Solution3Dynamic2 {

    public int rob(int[] nums) {
        int n = nums.length;
        if(n == 0){
            return 0;
        }
        if(n == 1){
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

    public static void main(String[] args) {
        int[] nums = {2, 7, 9, 3, 1, 4, 7, 9, 5};
        int robMax = new Solution3Dynamic2().rob(nums);
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
