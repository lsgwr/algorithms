/***********************************************************
 * @Description : 123.买卖股票的最佳时机III
 * https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iii/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/31 19:03
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C12_动态规划.T123_买卖股票的最佳时机III;

/**
 * Best Time to Buy and Sell Stock III
 * 时间复杂度O(n)， 空间复杂度O(n)
 */
public class Solution {
    public int maxProfit(int[] prices) {
        if (prices.length < 2) {
            return 0;
        }
        final int n = prices.length;
        int[] f = new int[n];
        int[] g = new int[n];
        for (int i = 1, valley = prices[0]; i < n; ++i) {
            valley = Math.min(valley, prices[i]);
            f[i] = Math.max(f[i - 1], prices[i] - valley);
        }
        for (int i = n - 2, peak = prices[n - 1]; i >= 0; --i) {
            peak = Math.max(peak, prices[i]);
            g[i] = Math.max(g[i], peak - prices[i]);
        }
        int maxProfit = 0;
        for (int i = 0; i < n; ++i) {
            maxProfit = Math.max(maxProfit, f[i] + g[i]);
        }
        return maxProfit;
    }
}
