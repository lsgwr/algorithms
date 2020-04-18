import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

class Solution {
    /**
     * 四联通问题，可以移动的四个方向
     */
    private int[][] dirs = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
    /**
     * 行数
     */
    private int R;
    /**
     * 列数
     */
    private int C;

    /**
     * 栅格
     */
    private int[][] grid;

    /**
     * 起点到路径上的每个点的最短路径
     */
    private int[][] dist;

    /**
     * 判断点(r, c)是否在栅格内
     *
     * @param r 点的行号
     * @param c 点的列号
     * @return 点是否在栅格内
     */
    private boolean inGrid(int r, int c) {
        return r >= 0 && r < R && c >= 0 && c < C;
    }

    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        R = maze.length;
        C = maze[0].length;
        this.dist = new int[R][C];
        for (int[] row : this.dist) {
            // 求最小值则初始化为最大值
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        this.grid = maze;
        bfs(start);
        return dist[destination[0]][destination[1]] == Integer.MAX_VALUE ? -1 : dist[destination[0]][destination[1]];
    }

    /**
     * 计算小球能否到达目的地
     *
     * 注意在一般的广度优先搜索中，我们不会经过同一个节点超过一次，但在这道题目中，
     * 只要从起始位置到当前节点的步数 count 小于之前记录的最小步数 distance[i, j]，
     * 我们就会把 (i, j) 再次加入队列中。并不断更新distance
     *
     * @param start       起点坐标
     */
    private void bfs(int[] start) {
        Queue<Integer> rQueue = new ArrayDeque<>();
        Queue<Integer> cQueue = new ArrayDeque<>();
        // 当前行和列加入对应的队列
        rQueue.add(start[0]);
        cQueue.add(start[1]);
        dist[start[0]][start[1]] = 0;
        // 行队列和列队列都不为空就接着沿着点进行深度遍历
        while (!rQueue.isEmpty() && !cQueue.isEmpty()) {
            // 从队列里弹出要遍历其邻接点的点
            // 当前行
            int rCur = rQueue.remove();
            // 当前列
            int cCur = cQueue.remove();
            // 遍历(r, c)的所有邻接点，顺时针上右下左四个点进行遍历
            for (int[] dir : dirs) {
                // dir代表相当于当前点的位移
                int rNext = rCur + dir[0];
                int cNext = cCur + dir[1];
                int cnt = 0;
                // 该方向畅通地话就一直往下走
                while (inGrid(rNext, cNext) && grid[rNext][cNext] == 0) {
                    rNext = rNext + dir[0];
                    cNext = cNext + dir[1];
                    cnt++;
                }
                // 一直走到无法畅通了，就dfs尝试换方向走。因为while最后多算了一次，所以dfs需要减去一次dir
                rNext = rNext - dir[0];
                cNext = cNext - dir[1];
                // 注意在一般的广度优先搜索中，我们不会经过同一个节点超过一次，但在这道题目中，
                // 只要从起始位置到当前节点的步数 count 小于之前记录的最小步数 distance[i, j]，
                // 我们就会把 (i, j) 再次加入队列中。并不断更新distance
                if (dist[rCur][cCur] + cnt < dist[rNext][cNext]){
                    dist[rNext][cNext] = dist[rCur][cCur] + cnt;
                    rQueue.add(rNext);
                    cQueue.add(cNext);
                }
            }
        }
    }

    /**
     * [0,4] [4,4]  true
     * [4,3] [0,1]  false
     * [0,4] [1,2]  true
     */
    public static void main(String[] args) {
        int[][] maze = {{0, 0, 1, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 1, 0}, {1, 1, 0, 1, 1}, {0, 0, 0, 0, 0}};
        int[] start = {0, 4};
        int[] destination = {4, 4};
        System.out.println(new Solution().shortestDistance(maze, start, destination));
    }
}