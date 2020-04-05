/***********************************************************
 * @Description : 被围绕的水域
 * https://leetcode-cn.com/problems/surrounded-regions/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/4/5 18:55
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C09_深度优先搜索.T130_被围绕的水域;

/**
 * 广搜。 从上下左右四个边界往里走， 凡是能碰到的 'O' ， 都是跟边界接壤的， 应该保留
 * <p>
 * BFS， 时间复杂度O(n)， 空间复杂度O(n)
 */
public class Solution {

    /**
     * 当前点求上右下左四个点时用到的矢量差,dirs 是directions的意思
     */
    private int[][] dirs = {
            {-1, 0},
            {0, 1},
            {1, 0},
            {0, -1}
    };

    private int R, C;

    /**
     * 判断第x行第y列的点(x, y)是否在grid所在的范围内
     */
    private boolean inArea(int x, int y) {
        return x >= 0 && x < R && y >= 0 && y < C;
    }

    public void solve(char[][] board) {
        if (board == null || board.length == 0) {
            return;
        }
        R = board.length;
        C = board[0].length;
        for (int i = 0; i < R; i++) {
            dfs(board, i, 0);
            dfs(board, i, C - 1);
        }
        for (int j = 0; j < C; j++) {
            dfs(board, 0, j);
            dfs(board, R - 1, j);
        }
        // 标记完毕，开始刷新O和X
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
                if (board[i][j] == '-') {
                    board[i][j] = 'O';
                }
            }
        }
    }

    public void dfs(char[][] board, int x, int y) {
        if (board[x][y] != 'O') {
            // 必须从值为O的点开始DFS
            return;
        }
        // 把没有被X包围的O先改成-，方便最后二次循环的时候改成O，被包围的O不会被改成-
        board[x][y] = '-';
        // 遍历节点(x, y)所有的邻接点,把和其连接的所有O点都标记成-，方便后面二次修改为X
        for (int[] dir : dirs) {
            int nextX = x + dir[0];
            int nextY = y + dir[1];
            // 点(nextX, nextY)必须在board区域内 + 必须是O，这样就把所有和边界O连在一起的O全变成
            if (inArea(nextX, nextY) && board[nextX][nextY] == 'O') {
                dfs(board, nextX, nextY);
            }
        }
    }
}
