/***********************************************************
 * @Description : 125.验证回文串
 * https://leetcode-cn.com/problems/valid-palindrome/
 * 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
 * 说明：本题中，我们将空字符串定义为有效的回文串
 *
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/4/6 11:21
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C02_字符串.T125_验证回文串;

class Solution {
    public boolean isPalindrome(String s) {
        s = s.toLowerCase();
        int l = 0;
        int r = s.length() - 1;
        while(l < r){
            // 只要还没相遇就接着往下走
            if(!Character.isLetterOrDigit(s.charAt(l))){
                // 左边的字符不是字母或数字
                l++;
                continue;
            }
            if(!Character.isLetterOrDigit(s.charAt(r))){
                // 右边的字符不是字母或数字
                r--;
                continue;
            }
            // 左右两边都是字母或数字，只要不相等就说明不是回文
            if(s.charAt(l) != s.charAt(r)){
                return false;
            }
            // 相等地话，继续向中间靠拢
            l++;
            r--;
        }
        return true;
    }
}
