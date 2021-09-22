/***********************************************************
 * @Description : 200.岛屿的数量(DFS求连通分量的个数即可)
 * https://leetcode-cn.com/problems/number-of-islands/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/25 11:50
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package CHapter08RecursiveAndBackTrack.LeetCode200FloodFill之岛屿的数量;

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

    /**
     * 传进来的grid有多少行(Row)多少列(Col)
     */
    private int R, C;

    /**
     * 岛屿网格的情况
     */
    private char[][] grid;
    private boolean[][] visited;
    /**
     * 连通分量的个数
     */
    private int cCount = 0;

    /**
     * 判断第x行第y列的点(x, y)是否在grid所在的范围内
     */
    private boolean inArea(int x, int y) {
        return x >= 0 && x < R && y >= 0 && y < C;
    }

    public int numIslands(char[][] grid) {
        this.grid = grid;
        if (grid == null) {
            return 0;
        }
        R = grid.length;
        if (R == 0) {
            // 如果行数为0说明图为空，直接返回
            return 0;
        }
        C = grid[0].length;
        if (C == 0) {
            // 如果列数为0说明图为空，直接返回
            return 0;
        }
        visited = new boolean[R][C];
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                // 点没有被访问而且字符等于word的第一个字符，才以这个点作为起点进行DFS遍历
                if (grid[i][j] == '1' && !visited[i][j]) {
                    dfs(i, j);
                    cCount++;
                }
            }
        }
        return cCount;
    }

    private void dfs(int x, int y) {
        visited[x][y] = true;
        // 遍历节点(x, y)所有的邻接点,判断是陆地的
        for (int d = 0; d < dirs.length; d++) {
            int nextX = x + dirs[d][0];
            int nextY = y + dirs[d][1];
            // 点(next_x, next_y)必须在grid区域内 + 没被访问过 + 是陆地(点(x, y)是陆地)
            if (inArea(nextX, nextY) && !visited[nextX][nextY] && grid[nextX][nextY] == '1') {
                dfs(nextX, nextY);
            }
        }
    }
}
