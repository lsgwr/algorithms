/***********************************************************
 * @Description : 198.House Robber 动态递归问题详解,使用记忆数组
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2019/8/23 23:23
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter09DynamicAllocate.Section4HouseRobber;

public class Solution1Recur {
    public static int num;

    public int rob(int[] nums) {
        // 从起点0开始抢劫房子
        return tryRob(nums, 0);
    }

    private int tryRob(int[] nums, int index) {
        num++;
        if (index >= nums.length) {
            return 0;
        }
        int result = 0;
        // 从index指定的房子开始往后抢劫
        for (int i = index; i < nums.length; i++) {
            result = Math.max(result, nums[i] + tryRob(nums, i + 2));
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {2, 7, 9, 3, 1, 4, 7, 9, 5};
        int robMax = new Solution1Recur().rob(nums);
        System.out.println("抢劫到的最多的钱是：" + robMax);
        System.out.println("进入递归函数" + num + "次");
    }
}

/**
 * 输出结果是：
 * <p>
 * 抢劫到的最多的钱是：24
 * 进入递归函数89次
 */
