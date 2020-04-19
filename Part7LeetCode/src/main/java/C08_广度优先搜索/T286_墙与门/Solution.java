/***********************************************************
 * @Description : 286. 墙与门
 * https://leetcode-cn.com/problems/walls-and-gates/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/4/19 18:26
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C08_广度优先搜索.T286_墙与门;

import java.util.ArrayDeque;

public class Solution {
    private final int[][] dirs = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    /**
     * 不能到达门或者空房间
     */
    private final int INF = 2147483647;
    /**
     * 行数
     */
    private int R;
    /**
     * 列数
     */
    private int C;
    /**
     * 房间情况
     */
    private int[][] rooms;

    private boolean inGrid(int r, int c) {
        return r >= 0 && r < R && c >= 0 && c < C;
    }

    public void wallsAndGates(int[][] rooms) {
        if (rooms == null || rooms.length == 0) {
            return;
        }
        R = rooms.length;
        C = rooms[0].length;
        this.rooms = rooms;
        // 针对每个房间，利用BFS寻找到门的最小距离(多个门需要取最小值)
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (rooms[r][c] == INF) {
                    // 从空房子开始找门，返回的到最近的门的距离
                    rooms[r][c] = bfs(r, c);
                }
            }
        }
    }

    /**
     * 从(rStart, cStart)是否能到达(rEnd, cEnd)
     */
    private int bfs(int rStart, int cStart) {
        boolean[][] visited = new boolean[R][C];
        ArrayDeque<Integer> rQueue = new ArrayDeque<>();
        ArrayDeque<Integer> cQueue = new ArrayDeque<>();
        rQueue.add(rStart);
        cQueue.add(cStart);
        visited[rStart][cStart] = true;
        // distance[i][j]表示点(i, j)到起点(rStart, cStart)的距离
        int[][] distance = new int[R][C];
        int minDistance = INF;
        while (!rQueue.isEmpty() && !cQueue.isEmpty()) {
            int rCur = rQueue.remove();
            int cCur = cQueue.remove();
            for (int[] dir : dirs) {
                int rNext = rCur + dir[0];
                int cNext = cCur + dir[1];
                // 在栅格内 + 没被访问 + 不是障碍
                if (inGrid(rNext, cNext) && !visited[rNext][cNext] && rooms[rNext][cNext] != -1) {
                    distance[rNext][cNext] = distance[rCur][cCur] + 1;
                    visited[rNext][cNext] = true;
                    if (rooms[rNext][cNext] == 0) {
                        // 到达目的地了就更新最小距离
                        minDistance = Math.min(distance[rNext][cNext], minDistance);
                    }else {
                        // 是空房才继续往下走
                        rQueue.add(rNext);
                        cQueue.add(cNext);
                    }

                }
            }
        }
        return minDistance;
    }

    public static void main(String[] args) {
        int[][] rooms = {
                {2147483647, -1, 0, 2147483647},
                {2147483647, 2147483647, 2147483647, -1},
                {2147483647, -1, 2147483647, -1},
                {0, -1, 2147483647, 2147483647}
        };
        new Solution().wallsAndGates(rooms);
    }
}