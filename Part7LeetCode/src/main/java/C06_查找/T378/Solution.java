/***********************************************************
 * @Description : 378. 有序矩阵中第K小的元素
 * https://leetcode-cn.com/problems/kth-smallest-element-in-a-sorted-matrix/
 *
 * 参考题解：https://leetcode-cn.com/problems/kth-smallest-element-in-a-sorted-matrix/solution/er-fen-chao-ji-jian-dan-by-jacksu1024/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/4/15 23:18
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C06_查找.T378;

class Solution {
    private int m;
    private int n;

    public int kthSmallest(int[][] matrix, int k) {
        m = matrix.length;
        n = matrix[0].length;

        int l = matrix[0][0], h = matrix[m - 1][n - 1];
        while (l < h) {
            // 二分法
            int mid = (l + h) / 2;
            // 获取值mid在数组matrix中的索引(这个索引实际就是第几小)
            int rank = get_rank(matrix, mid);
            if (rank >= k) {
                h = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    /**
     * 获取目标值target在matrix中的索引
     */
    private int get_rank(int[][] matrix, int target) {
        int res = 0;
        for (int i = 0; i < m && matrix[i][0] <= target; i++) {
            for (int j = 0; j < n && matrix[i][j] <= target; j++) {
                res++;
            }
        }
        return res;
    }
}
