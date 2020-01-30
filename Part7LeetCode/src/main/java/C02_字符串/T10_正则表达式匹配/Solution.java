/***********************************************************
 * @Description : 10. 正则表达式匹配
 * https://leetcode-cn.com/problems/regular-expression-matching/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/30 12:03
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C02_字符串.T10_正则表达式匹配;

/**
 * Regular Expression Matching
 * Time complexity: O(n)
 * Space complexity: O(1)
 */
class Solution {
    public boolean isMatch(final String s, final String p) {
        return isMatch(s, 0, p, 0);
    }

    private static boolean matchFirst(String s, int i, String p, int j) {
        if (j == p.length()) {
            return i == s.length();
        }
        if (i == s.length()) {
            return j == p.length();
        }
        return p.charAt(j) == '.' || s.charAt(i) == p.charAt(j);
    }

    private static boolean isMatch(String s, int i, String p, int j) {
        if (j == p.length()) {
            return i == s.length();
        }
        // next char is not '*', then must match current character
        final char b = p.charAt(j);
        if (j == p.length() - 1 || p.charAt(j + 1) != '*') {
            if (matchFirst(s, i, p, j)) {
                return isMatch(s, i + 1, p, j + 1);
            } else {
                return false;
            }
        } else { // next char is '*'
            if (isMatch(s, i, p, j + 2)) {
                // try the length of 0
                return true;
            }
            // try all possible lengths
            while (matchFirst(s, i, p, j)) {
                if (isMatch(s, ++i, p, j + 2)) {
                    return true;
                }
            }
            return false;
        }
    }
}
