/***********************************************************
 * @Description : 
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/25 18:48
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package CHapter08RecursiveAndBackTrack.LeetCode51NQueens;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    boolean[] col = null;
    boolean[] left = null;
    boolean[] right = null;
    List<List<String>> ret = new ArrayList<>();

    public List<List<String>> solveNQueens(int n) {
        // col[i]表示第i列被占用
        col = new boolean[n];
        // 左倾对角线
        left = new boolean[2 * n - 1];
        // 右倾对角线
        right = new boolean[2 * n - 1];
        // 二维平面
        char[][] board = new char[n][n];
        // 回溯法求解问题
        backTrack(board, 0, n);
        return ret;
    }
    // i代表行，j代表列
    void backTrack(char[][] board, int i, int n) {
        if (i >= n) {
            // 找到能一个能摆放皇后的位置，加入到结果列表中
            List<String> list = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                list.add(new String(board[j]));
            }
            ret.add(list);
            return;
        }
        // 初始化棋盘啥都不放
        Arrays.fill(board[i], '.');
        for (int j = 0; j < n; j++) {
            if (!col[j] && !left[i + j] && !right[i - j + n - 1]) {
                // 一、尝试在当前位置摆放皇后
                // 摆放皇后
                board[i][j] = 'Q';
                // 列占用
                col[j] = true;
                // 左倾对角线的规律
                left[i + j] = true;
                // 右倾对角线的规律
                right[i - j + n - 1] = true;
                // 二、递归求解其合法性
                backTrack(board, i + 1, n);
                // 三、递归回退过程中需要恢复初始的状态
                // 拿走皇后
                board[i][j] = '.';
                // 列占用解除
                col[j] = false;
                // 左对角线占用解除
                left[i + j] = false;
                // 右对角线占用解除
                right[i - j + n - 1] = false;
            }
        }
    }
}
