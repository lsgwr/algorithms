/***********************************************************
 * @Description : 爬楼梯，动态规划法
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/29 13:13
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C01_线性表.数组.T70_爬楼梯;

class Solution {
    public int climbStairs(int n) {
        if (n == 1 || n == 2) {
            return n;
        }
        /**
         * 记忆数组memory，用于存储子问题是否已经被访问
         */
        int[] memo = new int[n + 1];
        memo[1] = 1;
        memo[2] = 2;
        for (int i = 3; i <= n; i++) {
            memo[i] = memo[i - 1] + memo[i - 2];
        }
        return memo[n];
    }
}
