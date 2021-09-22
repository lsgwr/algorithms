/***********************************************************
 * @Description : 264.丑数II
 * https://leetcode-cn.com/problems/ugly-number-ii/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/31 22:36
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C15_数论.T264_丑数II;

/**
 * Ugly Number II
 * Time complexity: O(n), Space complexity: O(n)
 */
public class Solution {
    public int nthUglyNumber(int n) {
        final int[] nums = new int[n];
        // 1 is the first ugly number
        nums[0] = 1;
        int index = 0, index2 = 0, index3 = 0, index5 = 0;
        while (index + 1 < n) {
            int x2 = nums[index2] * 2;
            int x3 = nums[index3] * 3;
            int x5 = nums[index5] * 5;
            int min = Math.min(x2, Math.min(x3, x5));
            if (min == x2) {
                ++index2;
            } else if (min == x3) {
                ++index3;
            } else {
                ++index5;
            }
            // skip duplicate
            if (min != nums[index]) {
                nums[++index] = min;
            }
        }
        return nums[n - 1];
    }
}
