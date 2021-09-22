/***********************************************************
 * @Description : 64.最小路径和
 * https://leetcode-cn.com/problems/minimum-path-sum/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/31 19:17
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C12_动态规划.T64_最小路径和;

class Solution {
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] memo = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                memo[i][j] = Integer.MAX_VALUE;
            }
        }
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
                // i不为0，j也不为0，需要比较左上角和右下角的点了
                memo[i][j] = Math.min(memo[i][j - 1], memo[i - 1][j]) + grid[i][j];
            }
        }
        return memo[m - 1][n - 1];
    }
}
