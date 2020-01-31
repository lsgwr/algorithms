/***********************************************************
 * @Description : 59.螺旋矩阵II
 * https://leetcode-cn.com/problems/spiral-matrix-ii/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/31 23:34
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C16_编程基础.T59_螺旋矩阵II;

/**
 * Spiral Matrix II
 * 时间复杂度O(n^2)， 空间复杂度O(n^2)
 */
public class Solution {
    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        int begin = 0, end = n - 1;
        int num = 1;
        while (begin < end) {
            for (int j = begin; j < end; ++j) {
                matrix[begin][j] = num++;
            }
            for (int i = begin; i < end; ++i) {
                matrix[i][end] = num++;
            }
            for (int j = end; j > begin; --j) {
                matrix[end][j] = num++;
            }
            for (int i = end; i > begin; --i) {
                matrix[i][begin] = num++;
            }
            ++begin;
            --end;
        }
        if (begin == end) {
            matrix[begin][begin] = num;
        }
        return matrix;
    }
}
