/***********************************************************
 * @Description : 122.买卖股票的最佳时机II
 * https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/31 18:23
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C11_贪心法.T122_买卖股票的最佳时机II;

/**
 * Best Time to Buy and Sell Stock II
 * 时间复杂度O(n)， 空间复杂度O(1)
 * <p>
 * 贪心法， 低进高出， 把所有正的价格差价相加起来。
 * 把原始价格序列变成差分序列， 本题也可以做是最大 m 子段和， m= 数组长度
 */
public class Solution {
    public int maxProfit(int[] prices) {
        int sum = 0;
        for (int i = 1; i < prices.length; i++) {
            int diff = prices[i] - prices[i - 1];
            if (diff > 0) {
                sum += diff;
            }
        }
        return sum;
    }
}
