/***********************************************************
 * @Description : 
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/27 23:59
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter10GreedyAlgorithms.Section01AssignCookies.LeetCode392判断子序列;

class Solution {
    public boolean isSubsequence(String s, String t) {
        int si = 0, ti = 0;
        int res = 0;
        while (si < s.length() && ti < t.length()) {
            if (s.charAt(si) == t.charAt(ti)) {
                res++;
                si++;
                ti++;
            } else {
                ti++;
            }
        }
        return res == s.length();
    }
}
