/***********************************************************
 * @Description : 16.最接近的三数之和
 * https://leetcode-cn.com/problems/3sum-closest/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/28 18:03
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C01_线性表.数组.T16_最接近的三数之和;

import java.util.Arrays;

/**
 * 3Sum Closest
 * 先排序， 然后左右夹逼
 * Time Complexity: O(n^2), Space Complexity: O(1)
 */
public class Solution {
    public int threeSumClosest(int[] nums, int target) {
        final int N = nums.length;
        // 最接近target的nums中的三数之和
        int result = 0;
        // 遍历过程中result和target的差值的绝对值，当minGap最小是，result就是我们想要的值
        int minGap = Integer.MAX_VALUE;
        // 先排下序，方便找
        Arrays.sort(nums);
        for (int i = 0; i < N - 2; ++i) {
            int j = i + 1;
            int k = N - 1;
            // 夹逼的核心，j不断向右移，k向左移，直到j<k不再满足
            while (j < k) {
                final int sum = nums[i] + nums[j] + nums[k];
                // 当前的三数总和和目标值target的差值
                final int gap = Math.abs(sum - target);
                // 不断更新最小差值minGap
                if (gap < minGap) {
                    result = sum;
                    minGap = gap;
                }
                // 决定夹逼区间的移动方向
                if (sum < target) {
                    // 比目标值小，只能j右移动(j是从最左边移动过来地，不能再向左移动)
                    j++;
                    // 重复的元素不再判断
                    while (nums[j] == nums[j - 1] && j < k) {
                        j++;
                    }
                } else {
                    // 比目标值大，只能k左移动(k是从最右边移动过来地，不能再向右移动)
                    k--;
                    // 重复的元素不再判断
                    while (nums[k] == nums[k + 1] && j < k) {
                        k--;
                    }
                }
            }
        }
        return result;
    }
}
