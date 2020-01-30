/***********************************************************
 * @Description : 5.最长回文子串
 * 动态规划法，类似求最长上升子序列,https://gitee.com/lsgwr/algorithms/blob/master/Part3Practise/第09章_动态规划.md#98-lis最长子序列问题-300最长上升子序列longest-increasing-subsequence
 * https://leetcode-cn.com/problems/longest-palindromic-substring/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/30 11:40
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C02_字符串.T5_最长回文子串;

/**
 * Longest Palindromic Substring
 * 动规， 时间复杂度O(n^2)， 空间复杂度O(n^2)
 */
class Solution {
    public String longestPalindrome(final String s) {
        if ("".equals(s.trim())){
            return s;
        }
        final int N = s.length();
        // memo[i][j]表示表示区间[i,j]是否为回文串
        final boolean[][] memo = new boolean[N][N];
        // 最长回文子串的长度， 起点
        int maxLen = 1, start = 0;
        for (int i = 0; i < N; i++) {
            memo[i][i] = true;
            // memo[j][i]表示区间[j, i]是否是回文串
            for (int j = 0; j < i; j++) {
                // j和i位置的字符相等且区间(j,i)内的字符串时回文串。i-j<2表示i和j相邻或者时一个字符，区间[j, i]肯定是回文串
                memo[j][i] = (s.charAt(j) == s.charAt(i) && (i - j < 2 || memo[j + 1][i - 1]));
                if (memo[j][i] && maxLen < (i - j + 1)) {
                    maxLen = i - j + 1;
                    start = j;
                }
            }
        }
        return s.substring(start, start + maxLen);
    }
}
