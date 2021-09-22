/***********************************************************
 * @Description : 201.数字范围按位与
 * https://leetcode-cn.com/problems/bitwise-and-of-numbers-range/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/31 22:21
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C14_位操作.T201_数字范围按位与;

/**
 * Bitwise AND of Numbers Range
 * Time Complexity: O(log n), Space Complexity: O(1)
 */
public class Solution {
    public int rangeBitwiseAnd(int m, int n) {
        while (n > m) {
            n &= n - 1;
        }
        return m & n;
    }
}
