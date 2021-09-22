/***********************************************************
 * @Description : 326.3的幂
 * https://leetcode-cn.com/problems/power-of-three/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/31 22:23
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C14_位操作.T326_3的幂;

/**
 * Power of Three
 * Time Complexity: O(1), Space Complexity: O(1)
 */
public class Solution {
    public boolean isPowerOfThree(int n) {
        return (Math.log10(n) / Math.log10(3)) % 1 == 0;
    }
}
