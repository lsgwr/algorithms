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
        return dfs(start, destination, null);
    }

    /**
     * 计算小球能否到达目的地
     *
     * @param start       起点坐标
     * @param destination 目标点坐标
     * @param preDir      上一次的移动方向
     * @return
     */
    private boolean dfs(int[] start, int[] destination, int[] preDir) {
        // 标记当前访问节点
        visited[start[0]][start[1]] = true;
        if (start[0] == destination[0] && start[1] == destination[1]) {
            if (preDir != null) {
                int nextR = start[0] + preDir[0];
                int nextC = start[1] + preDir[1];
                // 上一次的方向不为空，判断小球下一步的位置
                if (!inGrid(nextR, nextC) || grid[nextR][nextC] == 1) {
                    // 如果当前到达边界或者碰到墙了，下一步出不去了，此时达到我们的目的地而且停下来了
                    return true;
                }
            } else {
                // 说明一开始的起点就是终点，不需要动，直接返回true即可
                return true;
            }
        } else {
            // 没到达目的地，在允许的方向上继续滑行
            if (preDir != null) {
                int nextR = start[0] + preDir[0];
                int nextC = start[1] + preDir[1];
                // 上一次的方向上的下一个点不为空，判断小球下一步的位置
                while (inGrid(nextR, nextC) && grid[nextR][nextC] == 0) {
                    // 在栅格内，而且下一个点仍然是空地，则继续向下滑行
                    start[0] = nextR;
                    start[1] = nextC;
                    // 标记访问节点
                    visited[start[0]][start[1]] = true;
                    if (start[0] == destination[0] && start[1] == destination[1]) {
                        return true;
                    }
                    // 不断更新下一个继续滑行的坐标
                    nextR = start[0] + preDir[0];
                    nextC = start[1] + preDir[1];
                }
            }
        }
        // 经过了上面的滑行，小球停下来了，继续朝可能的方向滑行
        for (int[] dir : dirs) {
            int nextR = start[0] + dir[0];
            int nextC = start[1] + dir[1];
            if (inGrid(nextR, nextC) && grid[nextR][nextC] == 0 && !visited[nextR][nextC]) {
                // 改方向的下一个点在栅格内，而且是空地
                if (dfs(new int[]{nextR, nextC}, destination, dir)) {
                    return true;
                }
            }
        }
        // 走到这里还没找到就是真没找到了
        return false;
    }

    public static void main(String[] args) {
        int[][] maze = {{0, 0, 1, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 1, 0}, {1, 1, 0, 1, 1}, {0, 0, 0, 0, 0}};
        int[] start = {0, 4};
        int[] destination = {4, 4};
        System.out.println(new Solution().hasPath(maze, start, destination));
    }
}
