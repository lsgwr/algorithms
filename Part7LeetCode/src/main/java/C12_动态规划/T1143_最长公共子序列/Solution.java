/***********************************************************
 * @Description : T1143_最长公共子序列 LCS问题
 * https://leetcode-cn.com/problems/longest-common-subsequence/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/2/5 20:49
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C12_动态规划.T1143_最长公共子序列;

class Solution {
    public int longestCommonSubsequence(String str1, String str2) {
        char[] chs1 = str1.toCharArray();
        char[] chs2 = str2.toCharArray();
        int M = chs1.length;
        int N = chs2.length;
        if (M == 0 || N == 0) {
            return 0;
        }
        // dp[i][j]代表chs1[0...i]和chs2[0...j]的最长公共子序列
        int[][] dp = new int[M][N];
        dp[0][0] = chs1[0] == chs2[0] ? 1 : 0;
        // 第1列赋值，即chs1[0...i]和chs2[0]的最长子序列，最长为1，而且一旦chs1[x][0]为1，x往后的dp[行][0]也为1
        for (int i = 1; i < M; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], chs1[i] == chs2[0] ? 1 : 0);
        }
        // 第1行赋值，与第一列同理，即chs1[0]和chs2[0...j]的最长子序列，最长为1，而且一旦chs2[0][x]为1，x往后的dp[0][列]也为1
        for (int j = 1; j < N; j++) {
            dp[0][j] = Math.max(dp[0][j - 1], chs1[0] == chs2[j] ? 1 : 0);
        }
        // 给非第1行和第1列的中间元素赋值，分三种情况：i前面一位、j前面一位、i和j所在的字符相等就取各自前一位的dp值然后加1，dp[i][j]取三种情况的最大值
        for (int i = 1; i < M; i++) {
            for (int j = 1; j < N; j++) {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                if (chs1[i] == chs2[j]) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + 1);
                }
            }
        }
        return dp[M - 1][N - 1];
    }
}
