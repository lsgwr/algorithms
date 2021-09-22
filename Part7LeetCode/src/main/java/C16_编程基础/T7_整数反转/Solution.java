/***********************************************************
 * @Description : 7.整数反转
 * https://leetcode-cn.com/problems/reverse-integer/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/31 22:58
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C16_编程基础.T7_整数反转;

/**
 * Reverse Integer
 * 时间复杂度O(logn)， 空间复杂度O(1)
 * 考虑 1.负数的情况 2. 溢出的情况(正溢出&&负溢出， 比如 x = -2147483648(即-2^31) )
 */
public class Solution {
    public int reverse(int x) {
        long r = 0;
        long t = x;
        t = t > 0 ? t : -t;
        for (; t > 0; t /= 10) {
            r = r * 10 + t % 10;
        }
        boolean sign = x <= 0;
        if (r > 2147483647) {
            return 0;
        } else {
            if (sign) {
                return (int) -r;
            } else {
                return (int) r;
            }
        }
    }
}
