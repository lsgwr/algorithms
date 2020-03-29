/***********************************************************
 * @Description : 最长公共子序列
 * 参考：https://leetcode-cn.com/problems/longest-common-subsequence/solution/a-fei-xue-suan-fa-zhi-by-a-fei-8/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/3/29 15:49
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package 第4章_动态规划.第3节_最长公共子序列;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private List<Character> result;

    /**
     * 最长公共子序列
     *
     * @param str1 字符串1
     * @param str2 字符串2
     * @return 最长公共字符串的长度
     */
    public int longestCommonSubsequence(String str1, String str2) {
        if (str1 == null || str2 == null) {
            return 0;
        }
        int m = str1.length();
        int n = str2.length();
        if (m == 0 || n == 0) {
            return 0;
        }
        // dp[i][j]代表chs1[0...i]和chs2[0...j]的最长公共子序列.注意此时的下标从1开始
        int[][] dp = new int[m + 1][n + 1];
        // 给非第1行和第1列的中间元素赋值
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    // 字符相等地话直接等于前一个字符串的dp值加1
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    // 比较相邻两种情况
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[m][n];
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s1 = sc.next();
        String s2 = sc.next();
        Main main = new Main();
        int maxLen = main.longestCommonSubsequence(s1, s2);
        System.out.println("最长公共子序列长度为：" + maxLen + ", 值为：" + main.result);
    }
}
