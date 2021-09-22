/***********************************************************
 * @Description : LeetCode 76号问题：最小覆盖字符串
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/15 23:34
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter03.LeetCode76;

class Solution {
    public String minWindow(String s, String p) {
        int sLen = s.length();
        int pLen = p.length();
        if ("".equals(s) || "".equals(p) || sLen < pLen) {
            return "";
        }
        // s="ab" p="A
        if (pLen == 1) {
            if (s.contains(p)) {
                return p;
            } else {
                return "";
            }
        }
        int[] freq = new int[256];
        int[] freqTmp = new int[256];

        // 更新在p中的字符
        for (int i = 0; i < pLen; i++) {
            freq[p.charAt(i)] += 1;
            // 初始化窗口内的字符频率
            freqTmp[s.charAt(i)] += 1;
        }
        int l = 0;
        int r = pLen;
        int lMin = 0;
        int rMin = pLen;
        // 找最小时，初始应为最大值
        int min = Integer.MAX_VALUE;
        // 右边界还没滑动到末尾时就一直往后滑动
        while (r <= sLen) {
            boolean find = true;
            for (int i = 0; i < pLen; i++) {
                if (freqTmp[p.charAt(i)] == 0) {
                    // 有一个p中的字母在s中找不到，直接跳出
                    find = false;
                    break;
                } else {
                    // 能找到但是频率小，出现频率大于等于可以
                    if (freqTmp[p.charAt(i)] < freq[p.charAt(i)]) {
                        find = false;
                        break;
                    }
                }
            }
            if (find) {
                // 找到了符合条件的字符串
                if ((r - l) < min) {
                    // 新的区间小于初始化地最小区间才需要更新
                    min = r - l;
                    lMin = l;
                    rMin = r;
                }
            }

            if (freq[s.charAt(l)] == 0) {
                // 最左边的字母不在p中，此时窗口宽度必须必p的长度大，这样左边的l才可以右移
                if (r - l > pLen) {
                    // 在维持窗口宽度的前提下，左边界l可以向右移动地
                    freqTmp[s.charAt(l)] -= 1;
                    l++;
                } else {
                    // 长度不够，r必须先往右侧移动
                    // 必须放在这里~~否则会提前退出地
                    if (r + 1 > sLen) {
                        break;
                    }
                    freqTmp[s.charAt(r)] += 1;
                    r++;
                }

            } else {
                // 最左边的元素在p中，同时它的频率大于在p中的频率了，左边界l一定是可以向右移动地，需要更新freqTmp
                if (freqTmp[s.charAt(l)] > freq[s.charAt(l)]) {
                    freqTmp[s.charAt(l)] -= 1;
                    l++;
                } else {
                    // 必须放在这里~~否则会提前退出地
                    if (r + 1 > sLen) {
                        break;
                    }
                    // 其他情况下只能右侧元素往右移动，并更新频率
                    freqTmp[s.charAt(r)] += 1;
                    r++;
                }
            }
        }
        if (min == Integer.MAX_VALUE) {
            return "";
        } else {
            return s.substring(lMin, rMin);
        }
    }

    /**
     * 想到的异常用例：
     * S = "aa", T = "a"  ==> "a"
     * S = "abc", T = "acd"  ==> ""
     * S = "abc", T = "cba"  ==> abc
     * S = "bbaa", T = "aba"  ==>baa
     * S = "aaaaaaaaaaaabbbbbcdd", T = "abcdd"  ==> abbbbbcdd
     * S = "aabAA", T = "BB"
     */
    public static void main(String[] args) {
        String S = "aabAA";
        // cba、acd都测测
        String T = "BB";
        System.out.println(new Solution().minWindow(S, T));
    }
}
