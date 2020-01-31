/***********************************************************
 * @Description : 64.最小路径和
 * https://leetcode-cn.com/problems/minimum-path-sum/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/31 19:17
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C12_动态规划.T64_最小路径和;

class Solution {
    private int[][] memo;

    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        memo = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                memo[i][j] = recursive(i, j, grid);
            }
        }
        return memo[m - 1][n - 1];
    }

    private int recursive(int i, int j, int[][] grid) {
        if (memo[i][j] != 0) {
            return memo[i][j];
        }
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
