/***********************************************************
 * @Description : 242. 有效的字母异位词
 * https://leetcode-cn.com/problems/valid-anagram/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/30 13:17
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C02_字符串.T242_有效的字母异位词;

class Solution {
    /**
     * 字母异位词：字母相同但是顺序不同的单词,，类似问题是438号问题
     */
    public boolean isAnagram(String s, String t) {
        if ("".equals(s) && "".equals(t)) {
            return true;
        }
        if ("".equals(s) || "".equals(t) || s.length() != t.length()) {
            return false;
        }
        int[] freq = new int[26];
        int len = s.length();
        for (int i = 0; i < len; i++) {
            int sInt = s.charAt(i) - 'a';
            int tInt = t.charAt(i) - 'a';
            freq[sInt] = freq[sInt] + 1;
            freq[tInt] = freq[tInt] - 1;
        }
        for (int i = 0; i < len; i++) {
            if (freq[s.charAt(i) - 'a'] != 0) {
                return false;
            }
        }
        return true;
    }
}
