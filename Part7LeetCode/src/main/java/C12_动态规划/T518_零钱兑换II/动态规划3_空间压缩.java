/***********************************************************
 * @Description : 518 零钱兑换II
 * 参考地左程云《算法面试》第4章 还钱的方法数
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/2/5 15:57
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C12_动态规划.T518_零钱兑换II;

/**
 * 执行用时 : 4 ms , 在所有 Java 提交中击败了 37.66% 的用户 内存消耗 : 34 MB , 在所有 Java 提交中击败了 91.30% 的用户
 */
class 动态规划3_空间压缩 {
    public int change(int amount, int[] coins) {
        int N = coins.length;
        if (N == 0) {
            if (amount == 0) {
                return 1;
            } else {
                return 0;
            }
        }
        // dp[j]表示表示用所有的货币组成钱数j的方法数
        int[] dp = new int[amount + 1];
        for (int j = 0; coins[0] * j <= amount; j++) {
            // 只用coins[0]一种货币组成目标钱数的方法只有一种
            dp[coins[0] * j] = 1;
        }
        // 除去第1行和第1列以外的位置，每个coins[i]都要尝试k次
        for (int i = 1; i < N; i++) {
            // j - coins[i]必须大于0，这里干脆直接从coins[i]开始
            for (int j = coins[i]; j <= amount; j++) {
                // 好好体会下面两行的动态规划的逻辑
                // coins[0...i]种货币组成目标值j的方法数 = coins[0...i-1]种货币组成目标j的方法数(上一轮循环时的dp[j]) + coins[0...i]组成目标值j-coins[i]的值
                dp[j] = dp[j] + dp[j - coins[i]];
            }
        }
        return dp[amount];
    }
}
