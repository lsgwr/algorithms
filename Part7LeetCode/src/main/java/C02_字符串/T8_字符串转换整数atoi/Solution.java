/***********************************************************
 * @Description : 8.字符串转换整数atoi
 * https://leetcode-cn.com/problems/string-to-integer-atoi/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/30 11:06
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C02_字符串.T8_字符串转换整数atoi;

/**
 * String to Integer (atoi)
 * 时间复杂度O(n)， 空间复杂度O(1)
 */
class Solution {
    public int myAtoi(final String str) {
        // 闯入地为空字符串时返回地是0， 不管有多少个空格
        if ("".equals(str.trim())){
            return 0;
        }
        int num = 0;
        int sign = 1;
        final int n = str.length();
        if (n == 0) {
            return 0;
        }
        int i = 0;
        while (i < n && str.charAt(i) == ' ') {
            i++;
        }
        if (str.charAt(i) == '+') {
            i++;
        } else if (str.charAt(i) == '-') {
            sign = -1;
            i++;
        }
        for (; i < n; i++) {
            if (str.charAt(i) < '0' || str.charAt(i) > '9') {
                break;
            }
            if (num > Integer.MAX_VALUE / 10 || (num == Integer.MAX_VALUE / 10 && (str.charAt(i) - '0') > Integer.MAX_VALUE % 10)) {
                return sign == -1 ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            }
            num = num * 10 + str.charAt(i) - '0';
        }
        return num * sign;
    }
}
