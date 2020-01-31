/***********************************************************
 * @Description : 268.缺失数字
 * https://leetcode-cn.com/problems/missing-number/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/31 22:16
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C14_位操作.T268_缺失数字;

/**
 * Missing Number
 * Time Complexity: O(n), Space Complexity: O(1)
 */
public class Solution {
    public int missingNumber(int[] nums) {
        int sum = 0;
        for (int x : nums) {
            sum += x;
        }
        final int n = nums.length;
        final int sumExpected = (n * (n + 1)) / 2;
        return sumExpected - sum;
    }
}
