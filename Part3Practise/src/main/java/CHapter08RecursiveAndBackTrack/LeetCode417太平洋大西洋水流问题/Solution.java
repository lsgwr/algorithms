/***********************************************************
 * @Description : 417.太平洋大西洋水流问题
 * 实际就是DFS从某个点开始，只能找不必自己高的邻接点，在图中进行DFS看能否既能到达左上角又能到达右下角
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/25 16:31
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package CHapter08RecursiveAndBackTrack.LeetCode417太平洋大西洋水流问题;

import java.util.ArrayList;
import java.util.List;

class Solution {
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
    private int[][] grid;
    private boolean[][] visited;

    /**
     * 判断第x行第y列的点(x, y)是否在grid所在的范围内
     */
    private boolean inArea(int x, int y) {
        return x >= 0 && x < R && y >= 0 && y < C;
    }

    public List<List<Integer>> pacificAtlantic(int[][] matrix) {
        List<List<Integer>> result = new ArrayList<>();
        this.grid = matrix;
        if (grid == null) {
            return result;
        }
        R = grid.length;
        if (R == 0) {
            // 如果行数为0说明图为空，直接返回
            return result;
        }
        C = grid[0].length;
        if (C == 0) {
            // 如果列数为0说明图为空，直接返回
            return result;
        }
        visited = new boolean[R][C];
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                // 点没有被访问而且字符等于word的第一个字符，才以这个点作为起点进行DFS遍历
                boolean pacific = dfs(i, j, true);
                visited = new boolean[R][C];
                boolean atlantic = dfs(i, j, false);
                visited = new boolean[R][C];
                if (pacific && atlantic) {
                    List<Integer> list = new ArrayList<>();
                    list.add(i);
                    list.add(j);
                    result.add(list);
                }
            }
        }
        return result;
    }

    /**
     * dfs遍历看起点是否能到到
     *
     * @param x       行
     * @param y       列
     * @param pacific true代表检查能否到达太平洋()，false代表检查能否到到大西洋
     * @return 是否能到达太平洋或大西洋
     */
    private boolean dfs(int x, int y, boolean pacific) {
        if (pacific) {
            // 检查能否到达太平洋
            if (x == 0 || y == 0) {
                return true;
            }
        } else {
            // 检查大西洋
            if (x == R - 1 || y == C - 1) {
                return true;
            }
        }
        visited[x][y] = true;
        for (int d = 0; d < dirs.length; d++) {
            int nextX = x + dirs[d][0];
            int nextY = y + dirs[d][1];
            // 点(next_x, next_y)必须在grid区域内 + 没被访问过 + 往地处流 + 能到达边界
            if (inArea(nextX, nextY) && !visited[nextX][nextY] && grid[nextX][nextY] <= grid[x][y] && dfs(nextX, nextY, pacific)) {
                return true;
            }
        }
        // 到最后都没能到达边界，则说明无法到到，退出即可
        return false;
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 2, 2, 3, 5},
                {3, 2, 3, 4, 4},
                {2, 4, 5, 3, 1},
                {6, 7, 1, 4, 5},
                {5, 1, 1, 2, 4}
        };
        System.out.println(new Solution().pacificAtlantic(matrix));
    }
}
