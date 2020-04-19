/***********************************************************
 * @Description : 286. 墙与门
 * https://leetcode-cn.com/problems/walls-and-gates/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/4/19 18:26
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C08_广度优先搜索.T286_墙与门;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

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
        // 把所有的门的位置找出来
        List<int[]> doors = new ArrayList<>();
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (rooms[r][c] == 0) {
                    doors.add(new int[]{r, c});
                }
            }
        }
        int[][] result = new int[R][C];
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                result[r][c] = rooms[r][c];
            }
        }
        // 针对每个房间，利用BFS寻找到门的最小距离(多个门需要取最小值)
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (rooms[r][c] == INF) {
                    int minDistance = INF;
                    // bfs求到每个门的距离，如果到达不了某个门则返回无穷大
                    for (int[] door : doors) {
                        boolean[][] visited = new boolean[R][C];
                        // 记录每个节点的上一个访问节点
                        int[][][] prev = new int[R][C][2];
                        if (bfs(r, c, door[0], door[1], visited, prev)){
                            // 如果点(r, c)到door有路径
                            int distance = getDistance(prev, r, c, door[0], door[1]);
                            minDistance = Math.min(distance, minDistance);
                        }
                    }
                    // 把最终的最小距离设置进来
                    result[r][c] = minDistance;
                }
            }
        }
        // 注意上面过程中不要修改rooms，最后改一下rooms的指针即可
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                rooms[r][c] = result[r][c];
            }
        }
    }

    private int getDistance(int[][][] prev, int rStart, int cStart, int rEnd, int cEnd) {
        int distance = 1;
        int rCur = rEnd;
        int cCur = cEnd;
        while (prev[rCur][cCur][0] != rStart || prev[rCur][cCur][1]!=cStart){
            distance++;
            int tmp = rCur;
            rCur = prev[rCur][cCur][0];
            cCur = prev[tmp][cCur][1];
        }
        return distance;
    }

    /**
     * 从(rStart, cStart)是否能到达(rEnd, cEnd)
     */
    private boolean bfs(int rStart, int cStart, int rEnd, int cEnd, boolean[][] visited, int[][][] prev) {
        ArrayDeque<Integer> rQueue = new ArrayDeque<>();
        ArrayDeque<Integer> cQueue = new ArrayDeque<>();
        visited[rStart][cStart] = true;
        rQueue.add(rStart);
        cQueue.add(cStart);
        while (!rQueue.isEmpty() && !cQueue.isEmpty()) {
            int rCur = rQueue.remove();
            int cCur = cQueue.remove();
            for (int[] dir : dirs) {
                int rNext = rCur + dir[0];
                int cNext = cCur + dir[1];
                if (rNext == rEnd && cNext == cEnd) {
                    prev[rNext][cNext] = new int[]{rCur, cCur};
                    return true;
                }
                // 在栅格内 + 没被访问 +
                if (inGrid(rNext, cNext) && !visited[rNext][cNext] && rooms[rNext][cNext] == INF) {
                    visited[rNext][cNext] = true;
                    prev[rNext][cNext] = new int[]{rCur, cCur};
                    rQueue.add(rNext);
                    cQueue.add(cNext);
                }
            }
        }
        return false;
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