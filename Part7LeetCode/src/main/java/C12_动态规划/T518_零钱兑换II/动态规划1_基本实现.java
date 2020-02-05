/***********************************************************
 * @Description : 518 零钱兑换II
 * 参考地左程云《算法面试》第4章 还钱的方法数
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/2/5 15:57
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C12_动态规划.T518_零钱兑换II;

/**
 * 执行用时 : 21 ms , 在所有 Java 提交中击败了 12.28% 的用户 内存消耗 : 36.9 MB , 在所有 Java 提交中击败了 11.03% 的用户
 */
class 动态规划1_基本实现 {
    public int change(int amount, int[] coins) {
        int N = coins.length;
        if (N == 0) {
            if (amount == 0) {
                return 1;
            } else {
                return 0;
            }
        }
        // dp[i][j]表示表示用coins[0~i]的货币组成钱数j的方法数
        int[][] dp = new int[N][amount + 1];
        for (int i = 0; i < coins.length; i++) {
            // 即组成钱数为0的方法数
            dp[i][0] = 1;
        }
        for (int j = 1; coins[0] * j <= amount; j++) {
            // 只用coins[0]一种货币组成目标钱数的方法只有一种
            dp[0][coins[0] * j] = 1;
        }
        // 除去第1行和第1列以外的位置，每个coins[i]都要尝试k次
        for (int i = 1; i < N; i++) {
            for (int j = 1; j <= amount; j++) {
                int num = 0;
                for (int k = 0; j - coins[i] * k >= 0; k++) {
                    num += dp[i - 1][j - coins[i] * k];
                }
                dp[i][j] = num;
            }
        }
        return dp[N - 1][amount];
    }
}
