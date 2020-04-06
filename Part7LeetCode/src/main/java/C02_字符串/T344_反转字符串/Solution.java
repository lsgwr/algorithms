/***********************************************************
 * @Description : 344.反转字符串
 * https://leetcode-cn.com/problems/reverse-string/
 * 编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 char[] 的形式给出。
 *
 * 不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。
 *
 * 你可以假设数组中的所有字符都是 ASCII 码表中的可打印字符。
 *
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/4/6 11:26
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C02_字符串.T344_反转字符串;

class Solution {
    public void reverseString(char[] s) {
        int l = 0;
        int r = s.length - 1;
        while(l < r){
            char tmp = s[l];
            s[l] = s[r];
            s[r] = tmp;
            l++;
            r--;
        }
    }
}