/***********************************************************
 * @Description : 
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/30 12:14
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C02_字符串.T44_通配符匹配;

/**
 * Wildcard Matching
 * 迭代版， 时间复杂度O(n*m)， 空间复杂度O(1)
 * 和第10题_正则表达式匹配很类似。
 * 主要是 '*' 的匹配问题。 p 每遇到一个 '*' ， 就保留住当前 '*' 的坐标和 s 的坐标， 然后 s 从前往
 * 后扫描， 如果不成功， 则 s++ ， 重新扫描
 */
public class Solution {
    public boolean isMatch(String s, String p) {
        int i = 0, j = 0;
        int ii = -1, jj = -1;
        while (i < s.length()) {
            if (j < p.length() && p.charAt(j) == '*') {
                // skip continuous '*'
                while (j < p.length() && p.charAt(j) == '*') {
                    ++j;
                }
                if (j == p.length()) {
                    return true;
                }
                ii = i;
                jj = j;
            }
            if (j < p.length() && (p.charAt(j) == '?' || p.charAt(j) == s.charAt(i))) {
                ++i;
                ++j;
            } else {
                if (ii == -1) {
                    return false;
                }
                ++ii;
                i = ii;
                j = jj;
            }
        }
        // skip continuous '*'
        while (j < p.length() && p.charAt(j) == '*') {
            ++j;
        }
        return i == s.length() && j == p.length();
    }
}
