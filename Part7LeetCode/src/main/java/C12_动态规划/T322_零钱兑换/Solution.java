/***********************************************************
 * @Description : T322_零钱兑换
 * https://leetcode-cn.com/problems/coin-change/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/2/5 9:38
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C12_动态规划.T322_零钱兑换;

import java.util.Arrays;

class Solution {
    public int coinChange(int[] coins, int amount) {
        if (coins.length == 0) {
            return -1;
        }

        //dp[j]代表当钱包的总价值为j时，所需要的最少硬币的个数
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        // 钱包总价值为0时，需要的最少硬币数肯定是0，这是动态规划的开始
        dp[0] = 0;
        //i代表可以使用的硬币索引，i=2代表只在第0个，第1个，第2个这三个硬币中选择硬币
        for (int i = 0; i < coins.length; i++) {
            /**
             * 	当外层循环执行一次以后，说明在只使用前i-1个硬币的情况下，各个钱包的最少硬币个数已经得到，
             *  如果有些钱包的值还是无穷大，说明在仅使用前i-1个硬币的情况下，不能凑出钱包的价值
             * 	现在开始再放入第i个硬币，要想放如coins[i]，钱包的价值必须满足j>=coins[i]，所以在开始放入第i个硬币时，j从coins[i]开始
             */
            for (int j = coins[i]; j <= amount; j++) {
                /**
                 * 	如果钱包当前的价值j仅能允许放入一个coins[i]，那么就要进行权衡，以获得更少的硬币数
                 * 	  如果放入0个：此时钱包里面硬币的个数保持不变： v0=dp[j]
                 * 	  如果放入1个：此时钱包里面硬币的个数为：		v1=dp[j-coins[i]]+1
                 *   【前提是dp[j-coins[i]]必须有值，如果dp[j-coins[i]]是无穷大，说明无法凑出j-coins[i]价值的钱包，那么把coins[i]放进去以后，自然也凑不出dp[j]的钱包】
                 * 	所以，此时当钱包价值为j时，里面的硬币数目为 dp[j]=min{v0,v1}
                 * 	如果钱包当前价值j能够放入2个coins[i]，就要再进行一次权衡
                 * 		如果不放人第2个coins[i]，此时钱包里面硬币数目为，v1=dp[j]=min{v0,v1}
                 * 		如果放入第2个coins[i],  此时钱包里面硬币数目为，v2=dp[j-coins[i]]+1
                 * 	所以，当钱包的价值为j时，里面的硬币数目为dp[j]=min{v1,v2}=min{v0,v1,v2}
                 * 	......如此循环下去....
                 * 	钱包价值j能允许放入3个，4个.........w[i]，不断更新dp[j]，最后得到在仅使用前i个硬币(各个硬币的数目是不确定地)的时候，每个钱包里的最少硬币数目
                 *
                 * 	好好体会下随着j增加，j逐渐容纳下多个coins[i]的过程
                 */
                if (dp[j - coins[i]] != Integer.MAX_VALUE) {
                    dp[j] = Math.min(dp[j], dp[j - coins[i]] + 1);
                }
            }
        }
        if (dp[amount] != Integer.MAX_VALUE) {
            return dp[amount];
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] coins = {1, 2, 5};
        int amount = 11;
        System.out.println(new Solution().coinChange(coins, amount));
    }
}