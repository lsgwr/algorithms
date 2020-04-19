/***********************************************************
 * @Description : 317. 离建筑物最近的距离
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/4/19 22:04
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C08_广度优先搜索.T317_离建筑物最短的距离;

import java.util.ArrayDeque;

public class Solution {
    private final int[][] dirs = {{1, 0}, {0, 1}, {0, -1}, {-1, 0}};
    private int[][] grid;
    private int R;
    private int C;

    public int shortestDistance(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return -1;
        }
        R = grid.length;
        C = grid[0].length;
        this.grid = grid;
        int minDistanceSum = Integer.MAX_VALUE;
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (grid[r][c] == 0) {
                    minDistanceSum = Math.min(minDistanceSum, bfs(r, c));
                }
            }
        }
        return minDistanceSum == Integer.MAX_VALUE ? -1 : minDistanceSum;
    }

    private boolean inGrid(int r, int c) {
        return r >= 0 && r < R && c >= 0 && c < C;
    }

    private int bfs(int rStart, int cStart) {
        boolean[][] visited = new boolean[R][C];
        ArrayDeque<Integer> rQueue = new ArrayDeque<>();
        ArrayDeque<Integer> cQueue = new ArrayDeque<>();
        rQueue.add(rStart);
        cQueue.add(cStart);
        visited[rStart][cStart] = true;
        int[][] distance = new int[R][C];
        distance[rStart][cStart] = 0;
        while (!rQueue.isEmpty() && !cQueue.isEmpty()) {
            int rCur = rQueue.remove();
            int cCur = cQueue.remove();
            for (int[] dir : dirs) {
                int rNext = rCur + dir[0];
                int cNext = cCur + dir[1];
                if (inGrid(rNext, cNext) && !visited[rNext][cNext] && grid[rNext][cNext] != 2) {
                    visited[rNext][cNext] = true;
                    distance[rNext][cNext] = distance[rCur][cCur] + 1;
                    if (grid[rNext][cNext] == 0) {
                        rQueue.add(rNext);
                        cQueue.add(cNext);
                    }
                }
            }
        }
        int result = 0;
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (grid[r][c] == 1) {
                    if (visited[r][c]) {
                        result += distance[r][c];
                    } else {
                        return Integer.MAX_VALUE;
                    }
                }
            }
        }
        return result;
    }
}
