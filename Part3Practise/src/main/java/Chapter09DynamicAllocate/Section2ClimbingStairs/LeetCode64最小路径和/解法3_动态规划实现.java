/***********************************************************
 * @Description : 64.最小路径和
 * https://leetcode-cn.com/problems/minimum-path-sum/
 *
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/26 14:55
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter09DynamicAllocate.Section2ClimbingStairs.LeetCode64最小路径和;

public class 解法3_动态规划实现 {
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] memo = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    memo[i][j] = grid[0][0];
                    continue;
                }
                if (i == 0) {
                    memo[i][j] = memo[i][j - 1] + grid[i][j];
                    continue;
                }
                if (j == 0) {
                    memo[i][j] = memo[i - 1][j] + grid[i][j];
                    continue;
                }
                memo[i][j] = Math.min(memo[i][j - 1], memo[i - 1][j]) + grid[i][j];
            }
        }
        return memo[m - 1][n - 1];
    }
}
