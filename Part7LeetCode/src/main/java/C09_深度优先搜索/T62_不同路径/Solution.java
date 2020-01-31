/***********************************************************
 * @Description : 62.不同路径
 * https://leetcode-cn.com/problems/unique-paths/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/31 17:07
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C09_深度优先搜索.T62_不同路径;

class Solution {
    public int uniquePaths(int m, int n) {
        int[][] memo = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || j == 0) {
                    memo[i][j] = 1;
                    continue;
                }
                memo[i][j] = memo[i - 1][j] + memo[i][j - 1];
            }
        }
        return memo[m - 1][n - 1];
    }
}
