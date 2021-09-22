/***********************************************************
 * @Description : 85.最大矩形
 * https://leetcode-cn.com/problems/maximal-rectangle/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/31 19:00
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C12_动态规划.T85_最大矩形;

import java.util.Arrays;

/**
 * Maximal Rectangle
 * 时间复杂度O(n^2)， 空间复杂度O(n)
 */
public class Solution {
    public int maximalRectangle(char[][] matrix) {
        if (matrix.length == 0) return 0;
        final int m = matrix.length;
        final int n = matrix[0].length;
        int[] H = new int[n];
        int[] L = new int[n];
        int[] R = new int[n];
        Arrays.fill(R, n);
        int ret = 0;
        for (char[] chars : matrix) {
            int left = 0, right = n;
            // calculate L(i, j) from left to right
            for (int j = 0; j < n; ++j) {
                if (chars[j] == '1') {
                    ++H[j];
                    L[j] = Math.max(L[j], left);
                } else {
                    left = j + 1;
                    H[j] = 0;
                    L[j] = 0;
                    R[j] = n;
                }
            }
            // calculate R(i, j) from right to left
            for (int j = n - 1; j >= 0; --j) {
                if (chars[j] == '1') {
                    R[j] = Math.min(R[j], right);
                    ret = Math.max(ret, H[j] * (R[j] - L[j]));
                } else {
                    right = j;
                }
            }
        }
        return ret;
    }
}
