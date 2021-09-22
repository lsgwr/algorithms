/***********************************************************
 * @Description : 79.单词搜索
 * https://leetcode-cn.com/problems/word-search/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/31 17:48
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C09_深度优先搜索.T79_单词搜索;

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
    private char[][] grid;
    private boolean[][] visited;

    /**
     * 判断第x行第y列的点(x, y)是否在grid所在的范围内
     */
    private boolean inArea(int x, int y) {
        return x >= 0 && x < R && y >= 0 && y < C;
    }

    /**
     * 图的初始化
     *
     * @param board 二维的图
     * @param word  要找的单词
     * @return
     */
    public boolean exist(char[][] board, String word) {
        this.grid = board;
        if (grid == null) {
            return false;
        }
        R = grid.length;
        if (R == 0) {
            // 如果行数为0说明图为空，直接返回
            return false;
        }
        C = grid[0].length;
        if (C == 0) {
            // 如果列数为0说明图为空，直接返回
            return false;
        }
        visited = new boolean[R][C];
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                // 点没有被访问而且字符等于word的第一个字符，才以这个点作为起点进行DFS遍历
                if (grid[i][j] == word.charAt(0)) {
                    // 以(i,j)作为起点进行DFS，看看遍历结果能否组成提供的单词
                    List<Character> cList = new ArrayList<>();
                    cList.add(word.charAt(0));
                    dfs(i, j, 1, word, cList);
                    if (cList.size() == word.length()) {
                        return true;
                    }
                    // 每次递归DFS遍历完，数组要重新初始化
                    visited = new boolean[R][C];
                }
            }
        }
        return false;
    }

    private void dfs(int x, int y, int start, String word, List<Character> cList) {
        if (cList.size() == word.length()) {
            return;
        }
        visited[x][y] = true;
        // 遍历节点(x, y)所有的邻接点,判断是陆地的
        for (int d = 0; d < dirs.length; d++) {
            int nextX = x + dirs[d][0];
            int nextY = y + dirs[d][1];
            // 点(next_x, next_y)必须在grid区域内 + 没被访问过 + 是陆地(点(x, y)是陆地)
            if (inArea(nextX, nextY) && !visited[nextX][nextY] && grid[nextX][nextY] == word.charAt(start)) {
                cList.add(grid[nextX][nextY]);
                dfs(nextX, nextY, start + 1, word, cList);
                if (cList.size() == word.length()) {
                    break;
                }
                // 状态重置：列表和访问数组
                cList.remove(cList.size() - 1);
                visited[nextX][nextY] = false;
            }
        }
    }
}
