/***********************************************************
 * @Description : 50.Pow(x,n)
 * https://leetcode-cn.com/problems/powx-n/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/31 17:54
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C10_分治法.T50_Pow_x_n;

/**
 * Pow(x, n)
 * 二分法， $x^n = x^{n/2} * x^{n/2} * x^{n\%2}$
 * 时间复杂度O(logn)， 空间复杂度O(1)
 */
public class Solution {
    public double myPow(double x, int n) {
        if (n < 0) {
            return 1.0 / power(x, -n);
        } else {
            return power(x, n);
        }
    }

    private static double power(double x, int n) {
        if (n == 0) {
            return 1;
        }
        double v = power(x, n / 2);
        if (n % 2 == 0) {
            return v * v;
        } else {
            return v * v * x;
        }
    }
}
