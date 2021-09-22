/***********************************************************
 * @Description : 64.最小路径和
 * https://leetcode-cn.com/problems/minimum-path-sum/
 * 纯递归实现，容易超时
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/26 12:13
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter09DynamicAllocate.Section2ClimbingStairs.LeetCode64最小路径和;

public class 解法1_纯递归实现 {
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] memo = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                memo[i][j] = recursive(i, j, grid);
            }
        }
        return memo[m - 1][n - 1];
    }

    private int recursive(int i, int j, int[][] grid) {
        if (i == 0 && j == 0) {
            return grid[0][0];
        }
        if (i == 0) {
            return recursive(i, j - 1, grid) + grid[i][j];
        }
        if (j == 0) {
            return recursive(i - 1, j, grid) + grid[i][j];
        }
        return Math.min(recursive(i, j - 1, grid), recursive(i - 1, j, grid)) + grid[i][j];
    }
}
