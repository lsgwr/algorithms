/***********************************************************
 * @Description : 115.不同的子序列
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/31 21:12
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C12_动态规划.T115_不同的子序列;

/**
 * Distinct Subsequences
 * 二维动规+滚动数组
 * 时间复杂度O(m*n)， 空间复杂度O(n)
 */
public class Solution {
    public int numDistinct(String s, String t) {
        int[] memo = new int[t.length() + 1];
        memo[0] = 1;
        for (int i = 0; i < s.length(); ++i) {
            for (int j = t.length() - 1; j >= 0; --j) {
                memo[j + 1] += s.charAt(i) == t.charAt(j) ? memo[j] : 0;
            }
        }
        return memo[t.length()];
    }
}
