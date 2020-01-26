/***********************************************************
 * @Description : 64.最小路径和
 * https://leetcode-cn.com/problems/minimum-path-sum/
 * 执行用时 : 2 ms , 在所有 Java 提交中击败了 98.74% 的用户 内存消耗 : 36 MB , 在所有 Java 提交中击败了 99.71% 的用户
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/26 12:13
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter09DynamicAllocate.Section2ClimbingStairs.LeetCode64最小路径和;

public class 解法2_递归加记忆数组去除重复子问题 {
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
