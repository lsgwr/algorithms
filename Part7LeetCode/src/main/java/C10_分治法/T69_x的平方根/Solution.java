/***********************************************************
 * @Description : 69.x的平方根
 * https://leetcode-cn.com/problems/sqrtx/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/31 17:57
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C10_分治法.T69_x的平方根;

/**
 * Plus One
 * 时间复杂度O(n)， 空间复杂度O(1)
 */
public class Solution {
    public int[] plusOne(int[] digits) {
        return add(digits, 1);
    }

    private static int[] add(int[] digits, int digit) {
        // carry, 进位
        int c = digit;
        for (int i = digits.length - 1; i >= 0; --i) {
            digits[i] += c;
            c = digits[i] / 10;
            digits[i] %= 10;
        }
        // assert (c == 1)
        if (c > 0) {
            int[] tmp = new int[digits.length + 1];
            System.arraycopy(digits, 0, tmp, 1, digits.length);
            tmp[0] = c;
            return tmp;
        } else {
            return digits;
        }
    }
}
