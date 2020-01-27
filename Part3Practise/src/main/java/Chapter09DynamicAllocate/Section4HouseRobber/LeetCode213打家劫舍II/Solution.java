/***********************************************************
 * @Description : 
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/26 22:57
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter09DynamicAllocate.Section4HouseRobber.LeetCode213打家劫舍II;

public class Solution {
    // 198号问题实现地打家劫舍
    public int rob198(int[] nums) {
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

    // 核心是：第1个和最后一个不能同时抢，分别把第一个和最后一个从数组剔除，按照198的方法分别去求最多抢劫的钱，然后取一下两者中的较大者即可
    public int rob(int[] nums) {
        int n = nums.length;
        // 去除第一个元素
        int[] nums1 = new int[n - 1];
        for (int i = 1; i < n; i++) {
            nums1[i - 1] = nums[i];
        }
        // 去除最后一个元素
        int[] nums2 = new int[n - 1];
        for (int i = 0; i < n - 1; i++) {
            nums2[i] = nums[i];
        }
        return Math.max(rob198(nums1), rob198(nums2));
    }
}
