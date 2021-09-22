/***********************************************************
 * @Description : 48.翻转图像
 * https://leetcode-cn.com/problems/rotate-image/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/28 22:05
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C01_线性表.数组.T48_旋转图像;

/**
 * Rotate Image
 * 思路：沿着水平中线反转，再沿着主对角线反转
 */
public class Solution {
    public void rotate(final int[][] matrix) {
        final int n = matrix.length;
        // 沿着水平中线反转
        for (int i = 0; i < n / 2; ++i) {
            for (int j = 0; j < n; ++j) {
                swap(matrix, i, j, n - 1 - i, j);
            }
        }
        // 沿着主对角线反转
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                swap(matrix, i, j, j, i);
            }
        }
    }

    private static void swap(final int[][] matrix, int i, int j, int p, int q) {
        int tmp = matrix[i][j];
        matrix[i][j] = matrix[p][q];
        matrix[p][q] = tmp;
    }
}
