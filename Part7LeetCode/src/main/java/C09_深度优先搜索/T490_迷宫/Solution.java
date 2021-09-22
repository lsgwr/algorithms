/***********************************************************
 * @Description : 490.迷宫 通过了64/78个用例
 * https://leetcode-cn.com/problems/the-maze/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/4/16 23:11
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C09_深度优先搜索.T490_迷宫;

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
     * 节点的访问数组
     */
    private boolean[][] visited;

    /**
     * 栅格
     */
    private int[][] grid;

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

    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        R = maze.length;
        C = maze[0].length;
        this.visited = new boolean[R][C];
        this.grid = maze;
        return dfs(start, destination);
    }

    /**
     * 计算小球能否到达目的地
     *
     * @param start       起点坐标
     * @param destination 目标点坐标
     */
    private boolean dfs(int[] start, int[] destination) {
        if (visited[start[0]][start[1]]) {
            return false;
        }
        if (start[0] == destination[0] && start[1] == destination[1]) {
            return true;
        }
        // 标记当前访问节点
        visited[start[0]][start[1]] = true;
        for (int[] dir : dirs) {
            // 计算下一个点
            int nextR = start[0] + dir[0];
            int nextC = start[1] + dir[1];
            // 该方向畅通地话就一直往下走
            while (inGrid(nextR, nextC) && grid[nextR][nextC] == 0) {
                nextR = nextR + dir[0];
                nextC = nextC + dir[1];
            }
            // 一直走到无法畅通了，就dfs尝试换方向走。因为while最后多算了一次，所以dfs需要减去一次dir
            if (dfs(new int[]{nextR - dir[0], nextC - dir[1]}, destination)) {
                return true;
            }
        }
        return false;
    }

    /**
     * [0,4] [4,4]  true
     * [4,3] [0,1]  false
     * [0,4] [1,2]  true
     */
    public static void main(String[] args) {
        int[][] maze = {{0, 0, 1, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 1, 0}, {1, 1, 0, 1, 1}, {0, 0, 0, 0, 0}};
        int[] start = {0, 4};
        int[] destination = {1, 2};
        System.out.println(new Solution().hasPath(maze, start, destination));
    }
}
