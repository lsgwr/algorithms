/***********************************************************
 * @Description : 263.丑数
 * https://leetcode-cn.com/problems/ugly-number/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/31 22:34
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C15_数论.T263_丑数;

/**
 * Ugly Number
 * Time complexity: O(logn), Space complexity: O(1)
 */
public class Solution {
    public boolean isUgly(int num) {
        if (num == 0) {
            return false;
        }
        while (num % 2 == 0) {
            num /= 2;
        }
        while (num % 3 == 0) {
            num /= 3;
        }
        while (num % 5 == 0) {
            num /= 5;
        }
        return num == 1;
    }
}
