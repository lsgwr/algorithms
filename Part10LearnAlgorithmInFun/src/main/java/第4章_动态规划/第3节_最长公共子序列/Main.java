/***********************************************************
 * @Description : 最长公共子序列
 * 参考：https://leetcode-cn.com/problems/longest-common-subsequence/solution/a-fei-xue-suan-fa-zhi-by-a-fei-8/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/3/29 15:49
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package 第4章_动态规划.第3节_最长公共子序列;

import java.util.Scanner;

public class Main {
    /**
     * 最长公共子序列
     *
     * @param str1 字符串1
     * @param str2 字符串2
     * @return 最长公共字符串的长度
     */
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
        // 给非第1行和第1列的中间元素赋值
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

    /**
     * 根据最长子序列的结果求得最长子序列的具体指
     *
     * @param str1   字符串1
     * @param str2   字符串2
     * @param maxLen 最长子序列的长度
     * @return 最长子序列的具体值
     */
    public String getResult(String str1, String str2, int maxLen) {
        for (int i = 0; i < str1.length() - maxLen; i++) {
            String tmp = str1.substring(i, i + maxLen);
            if (str2.contains(tmp)) {
                return tmp;
            }
        }
        return "";
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s1 = sc.next();
        String s2 = sc.next();
        Main main = new Main();
        int maxLen = main.longestCommonSubsequence(s1, s2);
        String result = main.getResult(s1, s2, maxLen);
        System.out.println("最长公共子序列长度为：" + maxLen + ", 值为：" + result);
    }
}
