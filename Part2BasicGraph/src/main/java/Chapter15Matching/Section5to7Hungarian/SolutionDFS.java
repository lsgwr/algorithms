/***********************************************************
 * @description : :LeetCode LCP4.覆盖 多米诺骨牌问题
 * https://leetcode-cn.com/problems/broken-board-dominoes/
 * 基于BFS的匈牙利算法重做多米诺骨牌问题
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2019/12/27 18:58
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter15Matching.Section5to7Hungarian;

import Chapter02GraphExpress.Graph;

public class SolutionDFS {
    /**
     * 输入：n, m代表棋盘的大小；
     *
     * @param n      棋盘的宽度
     * @param m      棋盘的高度
     * @param broken broken是一个b * 2的二维数组，其中每个元素代表棋盘上每一个坏掉的格子的位置
     * @return 最多能放多少个多米诺骨牌
     */
    public int domino(int n, int m, int[][] broken) {
        int[][] board = new int[n][m];
        for (int[] p : broken) {
            // 把坏掉的格子置为1
            board[p[0]][p[1]] = 1;
        }
        // 初始化一个无向图，这次不是从文件里服读取了，自己从board中进行取值构造Graph
        Graph graph = new Graph(n * m, false);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (j + 1 < m && board[i][j] == 0 && board[i][j + 1] == 0) {
                    // 下面是二维坐标转一维坐标
                    int v = i * m + j;
                    int w = i * m + (j + 1);
                    graph.addEdge(v, w);
                }
                if (i + 1 < n && board[i][j] == 0 && board[i + 1][j] == 0) {
                    // 下面是二维坐标转一维坐标
                    int v = i * m + j;
                    int w = (i + 1) * m + j;
                    graph.addEdge(v, w);
                }
            }
        }

        // 利用最大匹配的类求上面二分图graph
        HungarianDFS maxMatching = new HungarianDFS(graph);
        return maxMatching.getMaxMatch();
    }

    public static void main(String[] args) {
        int n = 2, m = 3;
        int[][] broken = {{1, 0}, {1, 1}};
        SolutionDFS solution = new SolutionDFS();
        System.out.println("最多能放" + solution.domino(n, m, broken) + "块多米诺骨牌");
    }
}
/**
 * 最多能放2块多米诺骨牌
 */
