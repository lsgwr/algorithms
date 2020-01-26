package Chapter09DynamicAllocate.Section3IntegerBreak;

import java.util.Arrays;

/***********************************************************
 * @note      : 343. 整数拆分
 * 给定一个正整数 n，将其拆分为至少两个正整数的和，并使这些整数的乘积最大化。 返回你可以获得的最大乘积。
 * @author    : l00379880 梁山广
 * @version   : V1.0 at 2019/8/23 14:19
 ***********************************************************/
public class Solution3 {

    /**
     * 求三个数的最大值
     */
    private int max3(int a, int b, int c) {
        return Math.max(a, Math.max(b, c));
    }

    public int integerBreak(int n) {
        /**
         * 记忆数组，存储各个拆分元素的访问状态
         */
        int[] memo = new int[n + 1];
        Arrays.fill(memo, -1);
        memo[1] = 1;
        for (int i = 2; i <= n; i++) {
            // 求解memo[i]，求n的最大积问题转化为求子问题i的最大积问题
            for (int j = 0; j <= i - 1; j++) {
                // i = j + (i - j),第一种情况是i-j就不继续分割了，第二种情况是继续分割，因为i-j一定小于i，所以从memo数组里取即可.最后和memo[i]去最大值
                memo[i] = max3(memo[i], j * (i - j), j * memo[i - j]);
            }
        }
        return memo[n];
    }

    public static void main(String[] args) {
        int n = 10;
        int mulMax = new Solution3().integerBreak(n);
        System.out.println(n + "拆分后的整数的最大乘积是：" + mulMax);
        System.out.println("动态规划，不需要递归");
    }
}

/**
 * 输出为：
 * <p>
 * 0拆分后的整数的最大乘积是：36
 * 动态规划，不需要递归
 */
