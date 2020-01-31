/***********************************************************
 * @Description : 91.解码方法
 * https://leetcode-cn.com/problems/decode-ways/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/31 21:09
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C12_动态规划.T91_解码方法;

class Solution {
    public int numDecodings(String s) {
        int n = s.length();
        if (n == 0) {
            return 0;
        }
        int[] memo = new int[n + 1];
        memo[n] = 1;
        memo[n - 1] = s.charAt(n - 1) != '0' ? 1 : 0;
        for (int i = n - 2; i >= 0; i--) {
            if (s.charAt(i) != '0') {
                int num = Integer.parseInt(s.substring(i, i + 2));
                if (num <= 26) {
                    memo[i] = memo[i + 1] + memo[i + 2];
                } else {
                    memo[i] = memo[i + 1];
                }
            }
        }
        return memo[0];
    }
}
