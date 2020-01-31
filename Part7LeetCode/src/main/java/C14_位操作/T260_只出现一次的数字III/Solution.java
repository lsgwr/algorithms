/***********************************************************
 * @Description : 260.只出现一次的数字III
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/31 22:09
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C14_位操作.T260_只出现一次的数字III;

/**
 * Single Number III
 * Time Complexity: O(log n), Space Complexity: O(1)
 */
public class Solution {
    public int[] singleNumber(int[] nums) {
        int xorResult = 0;
        for (int x : nums) {
            xorResult ^= x;
        }
        // get the index of first 1
        int k = 0;
        for (k = 0; k < Integer.SIZE; ++k) {
            if (((xorResult >>> k) & 1) == 1) {
                break;
            }
        }
        // use k to split the array into two subarrays
        // XOR result of the first subarray
        int xorResult2 = 0;
        for (int x : nums) {
            if (((x >>> k) & 1) == 1) {
                xorResult2 ^= x;
            }
        }
        return new int[]{xorResult2, xorResult ^ xorResult2};
    }
}
