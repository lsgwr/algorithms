/***********************************************************
 * @Description : 198.House Robber 动态递归问题详解
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2019/8/23 23:23
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter09DynamicAllocate.Section4HouseRobber;

import java.util.Arrays;

public class Solution2Memo {
    public static int num;
    /**
     * 记忆数组，存储状态
     */
    public static int[] memo;

    public int rob(int[] nums) {
        memo = new int[nums.length + 1];
        Arrays.fill(memo, -1);
        // 从起点0开始抢劫房子
        return tryRob(nums, 0);
    }

    private int tryRob(int[] nums, int index) {
        num++;
        if (index >= nums.length) {
            return 0;
        }

        if (memo[index] != -1) {
            return memo[index];
        }

        int result = 0;
        // 从index指定的房子开始往后抢劫
        for (int i = index; i < nums.length; i++) {
            result = Math.max(result, nums[i] + tryRob(nums, i + 2));
        }
        memo[index] = result;
        return memo[index];
    }

    public static void main(String[] args) {
        int[] nums = {2, 7, 9, 3, 1, 4, 7, 9, 5};
        int robMax = new Solution2Memo().rob(nums);
        System.out.println("抢劫到的最多的钱是：" + robMax);
        System.out.println("进入递归函数" + num + "次");
    }
}

/**
 * 输出结果是：
 * <p>
 * 抢劫到的最多的钱是：24
 * 进入递归函数38次
 */
