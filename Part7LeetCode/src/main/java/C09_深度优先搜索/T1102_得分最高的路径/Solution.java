/***********************************************************
 * @Description : 1102.得分最高的路径
 * https://leetcode-cn.com/problems/path-with-maximum-minimum-value/
 * 无向有权图的DFS遍历问题
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/4/19 10:42
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C09_深度优先搜索.T1102_得分最高的路径;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    /**
     * 行数
     */
    private int R;
    /**
     * 列数
     */
    private int C;
    /**
     * 上下左右四个方向
     */
    private final int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    /**
     * 节点访问情况，记得要在DFS回溯过程中设置回去
     */
    private boolean[][] visited;

    /**
     * 判断点是否在栅格内
     *
     * @param r 行
     * @param c 列
     */
    private boolean inGrid(int r, int c) {
        return r >= 0 && r < R && c >= 0 && c < C;
    }

    public int maximumMinimumPath(int[][] A) {
        R = A.length;
        C = A[0].length;
        visited = new boolean[R][C];
        // 存储所有起点到终点的路径
        List<List<int[]>> pathList = new ArrayList<>();
        // 记录实时路径的List
        List<int[]> path = new ArrayList<>();
        // DFS遍历找到所有(0, 0)到(R-1, C-1)的路径
        dfs(0, 0, path, pathList);
        // 从path中选取路径加权和最大的路径
        int maxScore = 0;
        for (List<int[]> pathCur : pathList) {
            // 路径的得分为该路径上的最小值
            int score = Integer.MAX_VALUE;
            for (int[] edge : pathCur) {
                // 不断取最小值
                score = Math.min(score, A[edge[0]][edge[1]]);
            }
            // 最终该路径的最小分数和总路径上的最大分数取较大值
            maxScore = Math.max(score, maxScore);
        }
        return maxScore;
    }

    /**
     * DFS遍历找到所有(0, 0)到(R-1, C-1)的路径
     *
     * @param r        当前的行
     * @param c        当前的列
     * @param path     当前的DFS路径
     * @param pathList 所有(0, 0)到(R-1, C-1)的路径列表
     */
    private void dfs(int r, int c, List<int[]> path, List<List<int[]>> pathList) {
        visited[r][c] = true;
        path.add(new int[]{r, c});
        if (r == R - 1 && c == C - 1) {
            // 复制一份当前路径到路径列表中
            pathList.add(new ArrayList<>(path));
            // 到了目的地就开始回溯
            return;
        }
        for (int[] dir : dirs) {
            int rNext = r + dir[0];
            int cNext = c + dir[1];
            if (inGrid(rNext, cNext) && !visited[rNext][cNext]) {
                dfs(rNext, cNext, path, pathList);
                // 回溯过程中要把点的情况设置回去
                visited[rNext][cNext] = false;
                // 之前加地点也要在回溯过程中退出去
                path.remove(path.size() - 1);
            }
        }
    }

    /**
     * {{5, 4, 5}, {1, 2, 6}, {7, 4, 6}};   ====> 4
     * {{2, 2, 1, 2, 2, 2}, {1, 2, 2, 2, 1, 2}}; ====> 2
     * {{3, 4, 6, 3, 4}, {0, 2, 1, 1, 7}, {8, 8, 3, 2, 7}, {3, 2, 4, 9, 8}, {4, 1, 2, 0, 0}, {4, 6, 5, 4, 3}}  ===> 3
     * {{1, 0, 1, 1, 1, 0, 0}, {0, 1, 1, 1, 1, 1, 0}, {1, 0, 1, 1, 1, 1, 0}, {1, 1, 1, 0, 1, 1, 0}, {1, 0, 1, 1, 0, 1, 0}};
     */
    public static void main(String[] args) {
        int[][] A = {{3, 4, 6, 3, 4}, {0, 2, 1, 1, 7}, {8, 8, 3, 2, 7}, {3, 2, 4, 9, 8}, {4, 1, 2, 0, 0}, {4, 6, 5, 4, 3}};
        System.out.println(new Solution().maximumMinimumPath(A));
    }
}
