/***********************************************************
 * @Description : 309.最佳买卖股票时机含冷冻期
 * https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/31 19:08
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C12_动态规划.T309_最佳买卖股票时机含冷冻期;

class Solution {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        if(n == 0){
            return 0;
        }
        // sell[i]表示截至第i天，最后一个操作是卖时的最大收益；
        int[] sell = new int[n];
        // buy[i]表示截至第i天，最后一个操作是买时的最大收益；
        int[] buy  = new int[n];
        // cool[i]表示截至第i天，最后一个操作是冷冻期时的最大收益；
        int[] cool = new int[n];
        // 第一天是买入，纯赔钱.sell[0]和cool[0]用默认的0作为初始值即可
        buy[0] = -prices[0];
        for(int i = 1; i < n; i++){
            // 第一项表示第i天卖出，第二项表示第i天冷冻
            sell[i] = Math.max(buy[i - 1]  + prices[i], sell[i - 1]);
            // 第一项表示第i天买进，第二项表示第i天冷冻
            buy[i]  = Math.max(cool[i - 1] - prices[i], buy[i - 1]);
            // 根据题目的状态转换形式，cool[i]=sell[i-1]。其不存在从buy状态和cold状态再到cold状态的过程，只存在sell状态到cold状态。因此cool[i]=sell[i-1]的物理意义才是正确的.状态方程的第三个是没有任何意义的。
            cool[i] = sell[i - 1];
        }
        return sell[n - 1];
    }
}
