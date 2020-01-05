/***********************************************************
 * @Description : 滑动窗口问题
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2019/8/20 07:49
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter03.MinSubArrayLen;

/**
 * 时间复杂度O(n),空间复杂度O(1)
 */
class Solution {
    public int minSubArrayLen(int s, int[] nums) {
        // nums[l, r)为我们的滑动窗口，左闭右开区间，初始化应该不包含任何元素
        int l = 0, r = -1;
        int sum = 0;
        // 后面是求最小值，所以开始要设置成最大值
        int minLen = nums.length + 1;
        while (l < nums.length) {
            // 因为要r++，所以务必判断r的范围
            if ((r + 1) < nums.length && sum < s) {
                // 小于要求的目标和s了，右边元素下标右移，然后计算新的数组和sum
                r++;
                sum = sum + nums[r];
            } else {
                // 大于要求地目标和s了，先减去l位置的元素，然后左边元素下标右移
                sum = sum - nums[l];
                l++;
            }

            if (sum >= s) {
                // 满足要求了就取出窗口长度,
                minLen = Math.min(minLen, r - l + 1);
            }
        }
        if (minLen == nums.length + 1) {
            // minLen从未更新过，说明数组中不存在子数组满足其和大于等于s
            return 0;
        }
        return minLen;
    }

    public static void main(String[] args) {
        int s = 11;
        int[] nums = {1, 2, 3, 4, 5};
        int minLen = new Solution().minSubArrayLen(s, nums);
        System.out.println(minLen);
    }
}
