/***********************************************************
 * @Description : 3.无重复字符的最长子串
 * https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/31 18:26
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C11_贪心法.T3_无重复字符的最长子串;

import java.util.Arrays;

class Solution {
    public int lengthOfLongestSubstring(String s) {
        // freq表示所有的ASCII码字符对应的频率
        int[] freq = new int[256];
        Arrays.fill(freq, 0);
        // 滑动窗口为s[l...r)左闭右开区间
        int l = 0, r = -1;
        // 要求最大值，就得初始化为最小值
        int maxLen = 0;
        while (l < s.length()) {
            if (r + 1 < s.length() && freq[s.charAt(r + 1)] == 0) {
                // 最右侧的字符还没统计过频率，那么就把最右侧的字符放入统计数组freq
                r++;
                freq[s.charAt(r)]++;
            } else {
                // 已经到最右侧或者最右侧的字符已经统计过频率了非0了，那么就把最左侧的字符右移
                freq[s.charAt(l)]--;
                l++;
            }
            maxLen = Math.max(maxLen, r - l + 1);
        }
        return maxLen;
    }
}
