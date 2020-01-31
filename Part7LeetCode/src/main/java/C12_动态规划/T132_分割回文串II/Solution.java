/***********************************************************
 * @Description : 132.分割回文串II
 * https://leetcode-cn.com/problems/palindrome-partitioning-ii/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/31 18:55
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C12_动态规划.T132_分割回文串II;

/**
 * Palindrome Partitioning II
 * 时间复杂度O(n^2)， 空间复杂度O(n^2)
 * https://leetcode-cn.com/problems/palindrome-partitioning-ii/solution/zi-ding-xiang-xia-he-zi-di-xiang-shang-by-powcai-2/
 */
class Solution {
    public int minCut(String s) {
        int n = s.length();
        int[] min_s = new int[n];
        boolean[][] dp = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            min_s[i] = i;
            for (int j = 0; j <= i; j++) {
                if (s.charAt(i) == s.charAt(j) && (i - j < 2 || dp[j + 1][i - 1])) {
                    dp[j][i] = true;
                    min_s[i] = j == 0 ? 0 : Math.min(min_s[i], min_s[j - 1] + 1);
                }
            }
        }
        return min_s[n - 1];
    }
}
