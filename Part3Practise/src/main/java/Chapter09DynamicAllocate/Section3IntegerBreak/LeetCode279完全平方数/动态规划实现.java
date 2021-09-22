/***********************************************************
 * @Description : 279.完全平方数：给定正整数 n，找到若干个完全平方数（比如 1, 4, 9, 16, ...）
 * 使得它们的和等于 n。你需要让组成和的完全平方数的个数最少
 *
 * 基于动态规划实现
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/26 17:06
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter09DynamicAllocate.Section3IntegerBreak.LeetCode279完全平方数;

import java.util.Arrays;

public class 动态规划实现 {
    public int numSquares(int n) {
        // meme[i]代表找到若干个完全平方数（比如 1, 4, 9, 16, ...）使得它们的和等于 n
        int[] memo = new int[n + 1];
        Arrays.fill(memo, Integer.MAX_VALUE);
        memo[0] = 0;
        memo[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 1; i - j * j >= 0; j++) {
                // 之所以还要和memo[i]比较是因为在内层循环中会不算更新meme[i]
                memo[i] = Math.min(memo[i], memo[i - j * j] + 1);
            }
        }
        return memo[n];
    }
}
