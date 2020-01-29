/***********************************************************
 * @Description : 73.矩阵置零
 * https://leetcode-cn.com/problems/set-matrix-zeroes/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/29 13:58
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package 线性表.数组.T73_矩阵置零;

class Solution {
    public void setZeroes(int[][] matrix) {
        // 设置行
        int m = matrix.length;
        int n = matrix[0].length;
        boolean[] row = new boolean[m];
        boolean[] col = new boolean[n];

        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(matrix[i][j] == 0){
                    row[i] = true;
                    col[j] = true;
                }
            }
        }
        for(int i = 0; i < m; i++){
            if(row[i]){
                matrix[i] = new int[n];
            }
        }
        for(int j = 0; j < n; j++){
            if(col[j]){
                for(int i = 0; i < m; i++){
                    matrix[i][j] = 0;
                }
            }
        }
    }
}
