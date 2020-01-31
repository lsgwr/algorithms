/***********************************************************
 * @Description : 191.位1的个数
 * https://leetcode-cn.com/problems/number-of-1-bits/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/31 21:59
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C14_位操作.T191_位1的个数;

/**
 * Time Complexity: O(32), Space Complexity: O(1)
 */
public class Solution {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        int count = 0;
        for (int i = 0; i < 32; ++i) {
            count += n & 1;
            n >>>= 1;
        }
        return count;
    }
}
