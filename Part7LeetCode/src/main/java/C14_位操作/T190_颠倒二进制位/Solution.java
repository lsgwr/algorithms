/***********************************************************
 * @Description : 190.颠倒二进制位
 * https://leetcode-cn.com/problems/reverse-bits/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/31 21:54
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C14_位操作.T190_颠倒二进制位;

/**
 * Time Complexity: O(logn), Space Complexity: O(1)
 */
public class Solution {
    /**
     * you need treat n as an unsigned value
     */
    public int reverseBits(int n) {
        int result = 0;
        for (int i = 0; i < 32; ++i) {
            if ((n & 1) == 1) {
                result = (result << 1) + 1;
            } else {
                result = result << 1;
            }
            n = n >> 1;
        }
        return result;
    }
}
