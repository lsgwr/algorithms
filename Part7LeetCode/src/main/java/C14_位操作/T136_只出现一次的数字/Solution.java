/***********************************************************
 * @Description : 136.只出现一次的数字
 * https://leetcode-cn.com/problems/single-number/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/31 22:05
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C14_位操作.T136_只出现一次的数字;

/**
 * 时间复杂度O(n)， 空间复杂度O(1)
 */
public class Solution {
    public int singleNumber(int[] nums) {
        int x = 0;
        for (int i : nums) {
            x ^= i;
        }
        return x;
    }
}
