/***********************************************************
 * @Description : 304.二维区域和检索_矩阵不可变
 * https://leetcode-cn.com/problems/range-sum-query-2d-immutable/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/31 21:36
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C12_动态规划.T304_二维区域和检索_矩阵不可变;

public class NumMatrix {
    private final int[][] memo;

    public NumMatrix(int[][] matrix) {
        final int m = matrix.length;
        final int n = matrix.length > 0 ? matrix[0].length : 0;
        this.memo = new int[m + 1][n + 1];
        for (int i = 1; i < m + 1; ++i) {
            int rowSum = 0;
            for (int j = 1; j < n + 1; ++j) {
                memo[i][j] += rowSum + matrix[i - 1][j - 1];
                if (i > 1) {
                    memo[i][j] += memo[i - 1][j];
                }
                rowSum += matrix[i - 1][j - 1];
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        return memo[row2 + 1][col2 + 1] + memo[row1][col1] - memo[row1][col2 + 1] - memo[row2 + 1][col1];
    }
}
