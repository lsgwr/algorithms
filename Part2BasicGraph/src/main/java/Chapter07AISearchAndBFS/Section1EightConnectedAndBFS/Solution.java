/***********************************************************
 * @Description : 八连通分量问题，用BFS求最短路径问题
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2019/8/11 10:01
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter07AISearchAndBFS.Section1EightConnectedAndBFS;

import java.util.ArrayDeque;
import java.util.Queue;

public class Solution {
    /**
     * 当前点求上右下左对角线八个点时用到的矢量差,dirs 是directions的意思
     */
    private int[][] dirs = {{-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}};

    /**
     * 传进来的grid有多少行(Row)多少列(Col)
     */
    private int R, C;

    /**
     * 单源路径的起点、目标点
     */
    private int sourceX, sourceY, targetX, targetY;

    /**
     * 岛屿网格的情况
     */
    private int[][] grid;

    /**
     * 判断grid中所有的点是否被访问，因为此时(x, y)共同缺点一个点，所以visited也是二维数组
     */
    private boolean[][] visited;

    /**
     * distance[x][y]表示点(x, y)到起点的距离
     */
    private int[][] distance;

    public int shortestPathBinaryMatrix(int[][] grid) {
        if (grid == null) {
            return 0;
        }
        R = grid.length;
        C = grid[0].length;
        this.grid = grid;
        // 构造出想要的图，图实际就是简化后的邻接表形式的图
        // visited的长度就是graph的长度，即grid内所有的节点数
        visited = new boolean[R][C];
        // 记录每个点到起点的距离
        distance = new int[R][C];
        // 距离都初始化为-1，因为题目要求找不到路径时返回-1
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                distance[i][j] = -1;
            }
        }
        // 题目中的条件，只能从值为0的单元格走，为1表示单元格被阻塞了，不能走
        if (grid[0][0] == 1) {
            return -1;
        }
        // 只有一个单元格而且不是阻塞地，直接返回1即可，表示至少距离是1
        if (R == 1 && C == 1) {
            return 1;
        }

        // 设置广度优先遍历的起点和终点
        // 起点
        this.sourceX = 0;
        this.sourceY = 0;
        // 终点
        this.targetX = R - 1;
        this.targetY = C - 1;
        // 进行广度优先遍历
        bfs();
        // 返回计算的最短距离
        return shortestDistance();
    }

    /**
     * 从source点开始进行广度优先遍历
     */
    private void bfs() {
        // ArrayDeque既可以当队列又可以当栈来用，参考 https://github.com/19920625lsg/liuyubobobo-algorithms/tree/master/Part2Basic/src/main/java/Chapter03StackAndQueues/JavaBuiltIn
        Queue<Integer> queue = new ArrayDeque<>();
        // 二维转一维
        int source = sourceX * C + sourceY;
        // 把初始点加入到队列中
        queue.add(source);
        // 起始点至少算一个，距离为1
        distance[sourceX][sourceY] = 1;
        // 至少有一个点被访问(即是起始点)
        visited[sourceX][sourceY] = true;
        // 目标点二维转一维
        int target = targetX * C + targetY;

        if (source == target) {
            return;
        }
        while (!queue.isEmpty()) {
            // 中间的点
            int v = queue.remove();
            // 一位转二维
            int x = v / C;
            int y = v % C;
            // 遍历(x, y)的八个连通点,相当于遍历(x, y)的所有邻接点了
            for (int d = 0; d < dirs.length; d++) {
                int nextX = x + dirs[d][0];
                int nextY = y + dirs[d][1];
                // 点(nextX, nextY)必须在grid区域内 + 没被访问过 + 不阻塞(点(x, y)不是1，题干里有)
                if (inArea(nextX, nextY) && !visited[nextX][nextY] && grid[nextX][nextY] != 1) {
                    // 二维转一维
                    int next = nextX * C + nextY;
                    queue.add(next);
                    // next点被访问了
                    visited[nextX][nextY] = true;
                    // 记录下next点到起点的距离
                    distance[nextX][nextY] = distance[x][y] + 1;
                    if (nextX == targetX && nextY == targetY) {
                        return;
                    }
                }
            }
        }
    }

    /**
     * 判断第x行第y列的点(x, y)是否在grid所在的范围内
     */
    private boolean inArea(int x, int y) {
        return x >= 0 && x < R && y >= 0 && y < C;
    }

    /**
     * 从source到target的最短路径值
     */
    public int shortestDistance() {
        return distance[targetX][targetY];
    }
}
