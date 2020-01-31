/***********************************************************
 * @Description : 152.乘积最大子序列
 * https://leetcode-cn.com/problems/maximum-product-subarray/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/31 18:51
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C12_动态规划.T152_乘积最大子序列;

/**
 * maximum-product-subarray
 * 时间复杂度O(n)， 空间复杂度O(1)
 * <p>
 * 这题跟“最大连续子序列和”非常类似， 只不过变成了“最大连续子序列积”， 所以解决思路也很类似。
 * 仅仅有一个小细节需要注意， 就是负负得正， 两个负数的乘积是正数， 因此我们不仅要跟踪最大值， 还要
 * 跟踪最小值。
 */
public class Solution {
    public int maxProduct(int[] nums) {
        int maxLocal = nums[0];
        int minLocal = nums[0];
        int global = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int temp = maxLocal;
            maxLocal = Math.max(Math.max(nums[i] * maxLocal, nums[i]), nums[i] * minLocal);
            minLocal = Math.min(Math.min(nums[i] * temp, nums[i]), nums[i] * minLocal);
            global = Math.max(global, maxLocal);
        }
        return global;
    }
}
