/***********************************************************
 * @Description : 166.分数到小数
 * https://leetcode-cn.com/problems/fraction-to-recurring-decimal/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/31 22:42
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C15_数论.T166_分数到小数;

import java.util.HashMap;
import java.util.Map;

/**
 * Fraction to Recurring Decimal
 * Time Complexity: ?, Space Complexity: ?
 */
public class Solution {
    public String fractionToDecimal(int numerator, int denominator) {
        if (numerator == 0) {
            return "0";
        }
        final StringBuilder result = new StringBuilder();
        // determine the sign
        if ((numerator < 0) ^ (denominator < 0)) {
            result.append('-');
        }
        // Integer.MIN_VALUE will overflow, so use long
        // Math.abs(MIN_VALUE) will overflow
        long n = numerator;
        n = Math.abs(n);
        long d = denominator;
        d = Math.abs(d);
        // append integral part
        result.append(String.valueOf(n / d));
        if (n % d == 0) {
            return result.toString();
        }
        result.append('.');
        final Map<Long, Integer> map = new HashMap<>();
        // simulate the division process
        for (long r = n % d; r != 0; r %= d) {
            // find a existed remainder, so we reach
            // the end of the repeating part
            if (map.containsKey(r)) {
                result.insert(map.get(r), "(");
                result.append(')');
                break;
            }
            map.put(r, result.length());
            r *= 10;
            result.append(Character.forDigit((int) (r / d), 10));
        }
        return result.toString();
    }
}
