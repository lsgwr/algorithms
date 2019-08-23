/***********************************************************
 * @Description : 198.House Robber 动态递归问题详解
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2019/8/23 23:23
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter09DynamicAllocate.Section4HouseRobber;

public class Solution {
    public int rob(int[] nums) {
        // 从起点0开始抢劫房子
        return tryRob(nums, 0);
    }

    private int tryRob(int[] nums, int index) {
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
        int[] nums = {2, 7, 9, 3, 1};
        int robMax = new Solution().rob(nums);
        System.out.println(robMax);
    }
}
