/***********************************************************
 * @Description : 121.买卖股票的最佳时机
 * https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/31 18:21
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C11_贪心法.T121_买卖股票的最佳时机;

/**
 * Best Time to Buy and Sell Stock
 * 时间复杂度O(n)， 空间复杂度O(1)
 */
public class Solution {
    public int maxProfit(int[] prices) {
        if (prices.length < 2) {
            return 0;
        }
        // 差价， 也就是利润
        int profit = 0;
        // 当前最小
        int curMin = prices[0];
        for (int i = 1; i < prices.length; i++) {
            profit = Math.max(profit, prices[i] - curMin);
            curMin = Math.min(curMin, prices[i]);
        }
        return profit;
    }
}
