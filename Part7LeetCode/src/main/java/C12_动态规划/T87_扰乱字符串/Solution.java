/***********************************************************
 * @Description : 87.扰乱字符串
 * https://leetcode-cn.com/problems/scramble-string/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/31 19:13
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C12_动态规划.T87_扰乱字符串;

/**
 * 参考：https://leetcode-cn.com/problems/scramble-string/solution/miao-dong-de-qu-jian-xing-dpsi-lu-by-sha-yu-la-jia/
 */
class Solution {
    public boolean isScramble(String s1, String s2) {
        char[] chs1 = s1.toCharArray();
        char[] chs2 = s2.toCharArray();
        int n = s1.length();
        int m = s2.length();
        if (n != m) {
            return false;
        }
        boolean[][][] dp = new boolean[n][n][n + 1];
        //初始化单个字符的情况
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j][1] = chs1[i] == chs2[j];
            }
        }

        //枚举长度2～n
        for (int len = 2; len <= n; len++) {
            //枚举S中的起点位置
            for (int i = 0; i <= n - len; i++) {

                //枚举T中的起点位置
                for (int j = 0; j <= n - len; j++) {

                    //枚举划分位置
                    for (int k = 1; k <= len - 1; k++) {

                        //第一种情况：S1->T1,S2->T2
                        if (dp[i][j][k] && dp[i + k][j + k][len - k]) {
                            dp[i][j][len] = true;
                            break;
                        }
                        //第二种情况：S1->T2,S2->T1
                        //S1起点i，T2起点j + 前面那段长度len-k，S2起点i+前面长度k
                        if (dp[i][j + len - k][k] && dp[i + k][j][len - k]) {
                            dp[i][j][len] = true;
                            break;
                        }
                    }
                }
            }
        }
        return dp[0][0][n];
    }
}
