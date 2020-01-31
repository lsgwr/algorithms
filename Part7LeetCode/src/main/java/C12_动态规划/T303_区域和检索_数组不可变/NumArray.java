/***********************************************************
 * @Description : 303.区域和检索_数组不可变
 * https://leetcode-cn.com/problems/range-sum-query-immutable/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/31 21:33
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C12_动态规划.T303_区域和检索_数组不可变;

public class NumArray {
    private final int[] memo;

    public NumArray(int[] nums) {
        this.memo = new int[nums.length];
        int sum = 0;
        for (int i = 0; i < nums.length; ++i) {
            sum += nums[i];
            memo[i] = sum;
        }
    }

    public int sumRange(int i, int j) {
        return memo[j] - (i == 0 ? 0 : memo[i - 1]);
    }
}
