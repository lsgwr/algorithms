/***********************************************************
 * @Description : 
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/27 23:33
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter10GreedyAlgorithms.Section01AssignCookies.LeetCode455分发饼干;

import java.util.Arrays;

class Solution {
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int gi = g.length - 1;
        int si = s.length - 1;
        int res = 0;
        while (gi >= 0 && si >= 0) {
            if (s[si] >= g[gi]) {
                si--;
                gi--;
                res++;
            } else {
                gi--;
            }
        }
        return res;
    }
}
