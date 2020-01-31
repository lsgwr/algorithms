/***********************************************************
 * @Description : 63.不同路径II
 * https://leetcode-cn.com/problems/unique-paths-ii/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/31 17:09
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C09_深度优先搜索.T63_不同路径II;

class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] memo = new int[m][n];
        if (obstacleGrid[0][0] == 1 || obstacleGrid[m - 1][n - 1] == 1) {
            return 0;
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                memo[i][j] = -1;
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (memo[i][j] != -1) {
                    // 已经处理过了就不要再处理了
                    continue;
                }
                if (i == 0) {
                    if (obstacleGrid[i][j] == 1) {
                        for (int k = j; k < n; k++) {
                            // 往后列的值都为0了
                            memo[i][k] = 0;
                        }
                    } else {
                        memo[i][j] = 1;
                    }
                    continue;
                }
                if (j == 0) {
                    if (obstacleGrid[i][j] == 1) {
                        for (int k = i; k < m; k++) {
                            // 往后列的值都为0了
                            memo[k][j] = 0;
                        }
                    } else {
                        memo[i][j] = 1;
                    }
                    continue;
                }
                if (obstacleGrid[i][j] == 1) {
                    memo[i][j] = 0;
                    continue;
                }
                memo[i][j] = memo[i - 1][j] + memo[i][j - 1];
            }
        }
        return memo[m - 1][n - 1];
    }
}
