/***********************************************************
 * @Description : 240.搜索二维矩阵II
 * https://leetcode-cn.com/problems/search-a-2d-matrix-ii/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/31 11:21
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C06_查找.T240_搜索二维矩阵II;

/**
 * Search a 2D Matrix II
 * 时间复杂度O(m + n)， 空间复杂度O(1)
 * <p>
 * 从右上角开始, 比较 target 和 matrix[i][j] 的值。 如果小于 target , 则该行不可能有此数, 所
 * 以 i++ ; 如果大于 target , 则该列不可能有此数, 所以 j-- 。 遇到边界则表明该矩阵不含 target
 */
public class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        int i = 0;
        int j = matrix[0].length - 1;
        while (i < matrix.length && j >= 0) {
            final int x = matrix[i][j];
            if (target == x) {
                return true;
            } else if (x < target) {
                ++i;
            } else {
                --j;
            }
        }
        return false;
    }
}
