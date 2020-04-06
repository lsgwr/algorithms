/***********************************************************
 * @Description : 345.反转字符串中的元音字母
 * https://leetcode-cn.com/problems/reverse-vowels-of-a-string/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/4/6 11:29
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C02_字符串.T345_反转字符串中的元音字母;

class Solution {
    public String reverseVowels(String s) {
        String vowels = "aeiouAEIOU"; // 注意大小写
        char[] chs = s.toCharArray();
        int l = 0;
        int r = s.length() - 1;
        while(l < r){
            // 左边不是元音字符
            if(!vowels.contains(String.valueOf(chs[l]))){
                l++;
                continue;
            }
            // 右边不是元音字符
            if(!vowels.contains(String.valueOf(chs[r]))){
                r--;
                continue;
            }
            // 左右都为元音字符
            char tmp = chs[l];
            chs[l] = chs[r];
            chs[r] = tmp;
            l++;
            r--;
        }
        return String.valueOf(chs);
    }
}
