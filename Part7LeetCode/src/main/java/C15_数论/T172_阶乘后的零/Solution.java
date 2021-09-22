/***********************************************************
 * @Description : 172.阶乘后的零
 * https://leetcode-cn.com/problems/factorial-trailing-zeroes/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/31 22:48
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C15_数论.T172_阶乘后的零;

/**
 * Factorial Trailing Zeroes
 * Time Complexity: O(logn), Space Complexity: O(1)
 */
public class Solution {
    public int trailingZeroes(int n) {
        int result = 0;
        while (n > 0) {
            result += n / 5;
            n /= 5;
        }
        return result;
    }
}
