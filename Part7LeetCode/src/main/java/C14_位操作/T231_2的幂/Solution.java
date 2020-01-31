/***********************************************************
 * @Description : 231.2的幂
 * https://leetcode-cn.com/problems/power-of-two/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/31 22:14
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C14_位操作.T231_2的幂;

/**
 * Power of Two
 * Time Complexity: O(1), Space Complexity: O(1)
 */
public class Solution {
    public boolean isPowerOfTwo(int n) {
        return n > 0 && (n & (n - 1)) == 0;
    }
}
