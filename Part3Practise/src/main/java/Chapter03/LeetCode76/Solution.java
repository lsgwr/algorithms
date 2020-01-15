/***********************************************************
 * @Description : LeetCode 76号问题：最小覆盖字符串
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/15 23:34
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter03.LeetCode76;

class Solution {
    public String minWindow(String s, String p) {
        if ("".equals(s) || "".equals(p) || s.length() < p.length()) {
            return "";
        }
        // s="ab" p="A
        if (p.length()==1){
            if (s.contains(p)){
                return p;
            }else {
                return "";
            }
        }
        int[] freq = new int[256];
        int[] freqTmp = new int[256];
        // 更新在p中的字符
        for (int i = 0; i < p.length(); i++) {
            freq[p.charAt(i)] += 1;
            // 初始化窗口内的字符频率
            freqTmp[s.charAt(i)] += 1;
        }
        int l = 0;
        int r = p.length();
        int lMin = 0;
        int rMin = p.length();
        // 找最小时，初始应为最大值
        int min = Integer.MAX_VALUE;
        // 右边界还没滑动到末尾时就一直往后滑动
        while (r <= s.length()) {
            boolean find = true;
            for (int i = 0; i < p.length(); i++) {
                if (freqTmp[p.charAt(i)] == 0) {
                    // 有一个p中的字母在s中找不到，直接跳出
                    find = false;
                    break;
                }else {
                    // 能找到但是频率不同
                    if (freqTmp[p.charAt(i)] != freq[p.charAt(i)]){
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
                // 最左边的字母不在p中，左边界l一定是可以向右移动地，需要更新freqTmp
                freqTmp[s.charAt(l)] -= 1;
                l++;
            } else {
                // 最左边的元素在p中，同时它的频率大于在p中的频率了，左边界l一定是可以向右移动地，需要更新freqTmp
                if (freqTmp[s.charAt(l)] > freq[s.charAt(l)]) {
                    freqTmp[s.charAt(l)] -= 1;
                    l++;
                } else {
                    // 必须放在这里~~否则会提前退出地
                    if (r + 1 > s.length()) {
                        break;
                    }
                    // 其他情况下只能右侧元素往右移动，并更新频率
                    freqTmp[s.charAt(r)] += 1;
                    r++;
                }
            }
        }
        if (min==Integer.MAX_VALUE){
            return "";
        }else {
            return s.substring(lMin, rMin);
        }
    }

    /**
     * 想到的异常用例：
     * S = "aa", T = "a"  ==> "a"
     * S = "abc", T = "acd"  ==> ""
     * S = "abc", T = "cba"  ==> abc
     * S = "bbaa", T = "aba"  ==>baa
     */
    public static void main(String[] args) {
        String S = "bbaa";
        // cba、acd都测测
        String T = "aba";
        System.out.println(new Solution().minWindow(S, T));
    }
}
