/***********************************************************
 * @Description : T675_为高尔夫比赛砍树
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/4/19 22:39
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C08_广度优先搜索.T675_为高尔夫比赛砍树;

import java.util.ArrayDeque;
import java.util.List;
import java.util.PriorityQueue;

public class Solution {
    private int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    private int R;
    private int C;
    private List<List<Integer>> grid;
    /**
     * 上一次BFS起始的行he列
     */
    private int rStartLast;
    private int cStartLast;

    public int cutOffTree(List<List<Integer>> forest) {
        if (forest == null || forest.size() == 0) {
            return -1;
        }
        R = forest.size();
        C = forest.get(0).size();
        grid = forest;
        // 存储要砍地树
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (grid.get(r).get(c) > 1) {
                    pq.add(grid.get(r).get(c));
                }
            }
        }
        // 从矮到高依次砍树
        int stepsSum = 0;
        rStartLast = 0;
        cStartLast = 0;
        while (!pq.isEmpty()) {
            // 砍倒高度为pq.remove()的树需要的最小步数
            int steps = bfs(rStartLast, cStartLast, pq.remove());
            if (steps == -1) {
                // 一棵树砍不到就砍不到了
                return -1;
            } else {
                stepsSum += steps;
            }
        }
        return stepsSum;
    }

    private boolean inGrid(int r, int c) {
        return r >= 0 && r < R && c >= 0 && c < C;
    }

    /**
     * 砍倒高度为treeHeight的树
     *
     * @param treeHeight 要砍倒的树的高度
     * @return 能砍倒返回步数，砍不到返回-1
     */
    private int bfs(int rStart, int cStart, int treeHeight) {
        ArrayDeque<Integer> rQueue = new ArrayDeque<>();
        ArrayDeque<Integer> cQueue = new ArrayDeque<>();
        boolean[][] visited = new boolean[R][C];
        // distance[i][j]表示点(i, j)到起点(0, 0)的步数
        int[][] distance = new int[R][C];
        rQueue.add(rStart);
        cQueue.add(cStart);
        visited[rStart][cStart] = true;
        distance[rStart][cStart] = 0;
        if (grid.get(rStart).get(cStart) == treeHeight) {
            // 起点就是要砍地树
            return 0;
        }
        while (!rQueue.isEmpty() && !cQueue.isEmpty()) {
            int rCur = rQueue.remove();
            int cCur = cQueue.remove();
            for (int[] dir : dirs) {
                int rNext = rCur + dir[0];
                int cNext = cCur + dir[1];
                if (inGrid(rNext, cNext) && !visited[rNext][cNext] && grid.get(rNext).get(cNext) != 0) {
                    visited[rNext][cNext] = true;
                    distance[rNext][cNext] = distance[rCur][cCur] + 1;
                    if (grid.get(rNext).get(cNext) == 1){
                        // 如果是可以行走的地面才继续往后走
                        rQueue.add(rNext);
                        cQueue.add(cNext);
                    }
                    // 找到了要砍地树
                    if (grid.get(rNext).get(cNext) == treeHeight) {
                        // 砍完树，树的高度变为1
                        grid.get(rNext).set(cNext, 1);
                        // 下一次从这一次砍树的位置出发
                        rStartLast = rNext;
                        cStartLast = cNext;
                        // 找到要砍的树了，直接返回距离
                        return distance[rNext][cNext];
                    }
                }
            }
        }
        // 最后也没找到，返回-1
        return -1;
    }
}
