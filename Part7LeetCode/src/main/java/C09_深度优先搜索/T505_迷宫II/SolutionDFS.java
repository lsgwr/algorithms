import java.util.Arrays;

/**
 * 参考：https://leetcode-cn.com/problems/the-maze-ii/solution/mi-gong-ii-by-leetcode/
 */
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
        // 起点的初始化务必要做
        dist[start[0]][start[1]] = 0;
        dfs(start);
        return dist[destination[0]][destination[1]] == Integer.MAX_VALUE ? -1 : dist[destination[0]][destination[1]];
    }

    /**
     * 让球停在目的地的最短距离
     * <p>
     * 我们可以使用深度优先搜索对整颗搜索树进行遍历。我们从起始位置开始，
     * 每次选择一条路线进行搜索，并用计数器 count 记录当前的步数。
     * 为了防止重复搜索，我们需要使用一个二维数组 distance 记录
     * 从起始位置到 (i, j) 的最小步数 distance[i, j]，若在某
     * 一次搜索到位置 (i, j) 时，distance[i, j] 的值小于等于
     * count，那么我们可以进行回溯，不必继续搜索下去
     *
     * @param start 起点坐标
     */
    private void dfs(int[] start) {
        for (int[] dir : dirs) {
            // dir代表相当于当前点的位移
            int rNext = start[0] + dir[0];
            int cNext = start[1] + dir[1];
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
            if (dist[start[0]][start[1]] + cnt < dist[rNext][cNext]) {
                dist[rNext][cNext] = dist[start[0]][start[1]] + cnt;
                dfs(new int[]{rNext, cNext});
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