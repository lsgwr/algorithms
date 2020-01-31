/***********************************************************
 * @Description : 9.回文数
 * https://leetcode-cn.com/problems/palindrome-number/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/31 23:00
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C16_编程基础.T9_回文数;

/**
 * Palindrome Number
 * 时间复杂度O(1)， 空间复杂度O(1)
 */
public class Solution {
    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        // divisor
        int d = 1;
        while (x / d >= 10) {
            d *= 10;
        }
        while (x > 0) {
            // quotient
            int q = x / d;
            // remainder
            int r = x % 10;
            if (q != r) {
                return false;
            }
            x = x % d / 10;
            d /= 100;
        }
        return true;
    }
}
