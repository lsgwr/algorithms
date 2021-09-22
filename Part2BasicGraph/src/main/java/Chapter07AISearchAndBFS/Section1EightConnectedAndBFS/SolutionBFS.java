/***********************************************************
 * @Description : 1091. 二进制矩阵中的最短路径(基于非递归的bfs实现，最佳)
 * 在一个 N × N 的方形网格中，每个单元格有两种状态：空（0）或者阻塞（1）。
 * 一条从左上角到右下角、长度为 k 的畅通路径，由满足下述条件的单元格 C_1, C_2, ..., C_k 组成：
 *
 * 相邻单元格 C_i 和 C_{i+1} 在八个方向之一上连通（此时，C_i 和 C_{i+1} 不同且共享边或角）
 * C_1 位于 (0, 0)（即，值为 grid[0][0]）
 * C_k 位于 (N-1, N-1)（即，值为 grid[N-1][N-1]）
 * 如果 C_i 位于 (r, c)，则 grid[r][c] 为空（即，grid[r][c] == 0）
 * 返回这条从左上角到右下角的最短畅通路径的长度。如果不存在这样的路径，返回 -1 。
 * 链接：https://leetcode-cn.com/problems/shortest-path-in-binary-matrix
 *
 * 注意：
 * 1.栅格是屏幕坐标系，坐标点用(r, c)表示，第一个坐标是行(自上往下递增)，第二个坐标是列(自左往右递增)
 * 2.求最短路径，正好是BFS的特点，所以本题用基于非递归的BFS实现
 * 3.注意是八联通，和6.4里FloodFill的四联通区分下
 * 4.不单独建图，直接利用bfs的思想求解
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2019-12-17 11:44
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter07AISearchAndBFS.Section1EightConnectedAndBFS;

import java.util.ArrayDeque;
import java.util.Queue;

public class SolutionBFS {
    /**
     * 八联通偏移分量
     */
    private int[][] dirs = {{-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}};
    /**
     * 栅格的行数
     */
    private int R;
    /**
     * 栅格的列
     */
    private int C;

    /**
     * 栅格数据，这里可以直接看成一个图
     */
    private int[][] grid;

    /**
     * 记录节点是否被访问了的数组
     */
    private boolean[][] visited;

    private int[][][] pre;

    /**
     * 计算最大的岛面积
     *
     * @param grid 栅格网络，这里也可以看做图
     * @return 栅格grid左上角到右下角的最短距离
     */
    public int shortestPathBinaryMatrix(int[][] grid) {
        if (grid == null) {
            return 0;
        }
        this.R = grid.length;
        if (R == 0) {
            return 0;
        }
        this.C = grid[0].length;
        if (C == 0) {
            return 0;
        }
        if (grid[0][0] == 1) {
            // 起点就不是畅通的点，直接返回-1
            return -1;
        }
        this.grid = grid;
        this.visited = new boolean[R][C];
        // 记录每个被遍历到地点的上一个顶点
        this.pre = new int[R][C][2];
        bfs();
        // 栅格的点(0, 0)到(R-1, C-1)的最短距离
        return getPathLength(0, 0, R - 1, C - 1);
    }

    /**
     * 广度优先遍历所有为0的点
     */
    private void bfs() {
        Queue<Integer> rQueue = new ArrayDeque<>();
        Queue<Integer> cQueue = new ArrayDeque<>();
        // 当前行和列加入对应的栈
        rQueue.add(0);
        cQueue.add(0);
        // 加入到栈后就认为是被访问过了
        visited[0][0] = true;
        // 行栈和列栈都不为空就接着沿着点进行深度遍历
        while (!rQueue.isEmpty() && !cQueue.isEmpty()) {
            // 从栈里弹出要遍历其邻接点的点
            // 当前行
            int rCur = rQueue.remove();
            // 当前列
            int cCur = cQueue.remove();
            // 打印遍历经过的点
            // 遍历(r, c)的所有邻接点，八个邻接点都要进行遍历
            for (int[] dir : dirs) {
                // dir代表相当于当前点的位移
                int rNext = rCur + dir[0];
                int cNext = cCur + dir[1];
                // 1.判断是否继续往下递归遍历,因为加了dir偏移，所以必须判断是否还在栅格内
                // 2和3.此外还需要没被访问并且是空的状态
                if (inGrid(rNext, cNext) && !visited[rNext][cNext] && grid[rNext][cNext] == 0) {
                    // 遍历一层就加1
                    rQueue.add(rNext);
                    cQueue.add(cNext);
                    visited[rNext][cNext] = true;
                    // 记录节点访问过程
                    pre[rNext][cNext][0] = rCur;
                    pre[rNext][cNext][1] = cCur;
                }
            }
        }
    }

    private int getPathLength(int rTarget, int cTarget, int rDst, int cDst) {
        if (!visited[rDst][cDst]) {
            // 如果终点没被访问到说明不存在这样的路径，直接返回-1
            return -1;
        }
        int len = 1;
        int r = rDst;
        int c = cDst;
        // 只有当行列完全等于目标值是才算遍历路径完毕
        while ((r != rTarget) || (c != cTarget)) {
            // 只要还没遍历到目标点，就接着往前遍历.r和c必须暂存，否则r改了，重新给c赋值时就赋错了
            int rTmp = r;
            int cTmp = c;
            r = pre[rTmp][cTmp][0];
            c = pre[rTmp][cTmp][1];
            len++;
        }
        return len;
    }

    /**
     * 判断点(r, c)是否在栅格内
     *
     * @param r 行号
     * @param c 列号
     * @return
     */
    private boolean inGrid(int r, int c) {
        return r >= 0 && r < R && c >= 0 && c < C;
    }

    public static void main(String[] args) {
        int[][] grid = {{0, 0, 0}, {1, 1, 0}, {1, 1, 0}};
        SolutionBFS solution = new SolutionBFS();
        System.out.println(solution.shortestPathBinaryMatrix(grid));
    }
}
