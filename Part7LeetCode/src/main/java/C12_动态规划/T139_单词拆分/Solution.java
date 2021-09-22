/***********************************************************
 * @Description : 139. 单词拆分
 * https://leetcode-cn.com/problems/word-break/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/31 21:15
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C12_动态规划.T139_单词拆分;

import java.util.List;

/**
 * Word Break
 * 动规， 时间复杂度O(n^2)， 空间复杂度O(n)
 */
class Solution {
    public boolean wordBreak(String s, List<String> dict) {
        // 长度为n的字符串有n+1个隔板
        boolean[] memo = new boolean[s.length() + 1];
        // 空字符串
        memo[0] = true;
        for (int i = 1; i <= s.length(); ++i) {
            for (int j = i - 1; j >= 0; --j) {
                if (memo[j] && dict.contains(s.substring(j, i))) {
                    memo[i] = true;
                    break;
                }
            }
        }
        return memo[s.length()];
    }
}
