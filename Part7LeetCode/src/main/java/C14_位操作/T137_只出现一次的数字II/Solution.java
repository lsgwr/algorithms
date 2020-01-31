/***********************************************************
 * @Description : 137.只出现一次的数字II
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/31 22:07
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C14_位操作.T137_只出现一次的数字II;

/**
 * Single Number II
 * 方法1， 时间复杂度O(n)， 空间复杂度O(1)
 */
public class Solution {
    public int singleNumber(int[] nums) {
        // 一个整数的bit数， 即整数字长
        final int W = Integer.SIZE;
        // count[i]表示在在i位出现的1的次数
        int[] count = new int[W];
        for (int num : nums) {
            for (int j = 0; j < W; j++) {
                count[j] += (num >> j) & 1;
                count[j] %= 3;
            }
        }
        int result = 0;
        for (int i = 0; i < W; i++) {
            result += (count[i] << i);
        }
        return result;
    }
}
