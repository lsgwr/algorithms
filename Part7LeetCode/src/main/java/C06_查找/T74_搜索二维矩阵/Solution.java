/***********************************************************
 * @Description : 74.搜索二维矩阵
 * https://leetcode-cn.com/problems/search-a-2d-matrix/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/31 11:20
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C06_查找.T74_搜索二维矩阵;

/**
 * Search a 2D Matrix
 * 时间复杂度O(logn)， 空间复杂度O(1)
 */
public class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length == 0) {
            return false;
        }
        final int m = matrix.length;
        final int n = matrix[0].length;
        int first = 0;
        int last = m * n;
        while (first < last) {
            int mid = first + (last - first) / 2;
            int value = matrix[mid / n][mid % n];
            if (value == target) {
                return true;
            } else if (value < target) {
                first = mid + 1;
            } else {
                last = mid;
            }
        }
        return false;
    }
}
