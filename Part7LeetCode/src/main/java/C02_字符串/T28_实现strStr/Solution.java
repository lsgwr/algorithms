/***********************************************************
 * @Description : T28_实现strStr()
 * 双指针法
 * https://leetcode-cn.com/problems/implement-strstr/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/30 10:59
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C02_字符串.T28_实现strStr;

/**
 * 暴力算法足够了， 一定要写得没有BUG.i、j、k的关系一定要滤清
 */
class Solution {
    public int strStr(String haystack, String needle) {
        if (needle.length() == 0) {
            return 0;
        }
        int N = haystack.length() - needle.length() + 1;
        for (int i = 0; i < N; i++) {
            int j = i;
            int k = 0;
            while (j < haystack.length() && k < needle.length() && haystack.charAt(j) == needle.charAt(k)) {
                j++;
                k++;
            }
            if (k == needle.length()) {
                return i;
            }
        }
        return -1;
    }
}
