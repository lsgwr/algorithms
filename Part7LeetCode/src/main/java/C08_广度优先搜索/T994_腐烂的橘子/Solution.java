package C08_广度优先搜索.T994_腐烂的橘子;

import java.util.ArrayList;
import java.util.List;

/***********************************************************
 * @Description : 994.腐烂的橘子，类似公司的考题"杨树林抑制剂"和"102号问题二叉树的层序遍历"
 *              总结：这种需要记录广度优先遍历层次的问题几乎都是这么个做法
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/4/20 19:21
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/

class Solution {
    private int R;
    private int C;
    private final int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    private boolean inGrid(int r, int c) {
        return r >= 0 && r < R && c >= 0 && c < C;
    }

    /**
     * 这种需要一圈一圈往外传播的一般用BFS解，
     * 1.先找到起始所有腐烂的橘子，
     * 2.然后循环处理，把新腐烂的橘子加入下一次循环的队列中，
     * 3.当下一次循环的队列为空时，说明不能继续腐烂了，判断一下还有没有新鲜的橘子，如果有，就返回-1，否则返回分钟数
     */
    public int orangesRotting(int[][] grid) {
        R = grid.length;
        C = grid[0].length;

        // 1.先找到起始所有腐烂的橘子，
        List<int[]> rotList = new ArrayList<>();
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (grid[r][c] == 2) {
                    rotList.add(new int[]{r, c});
                }
            }
        }
        int minute = 0;
        // 2.然后循环处理，把新腐烂的橘子加入下一次循环的队列中
        while (rotList.size() != 0) {
            List<int[]> newRotList = new ArrayList<>();
            for (int[] rotNode : rotList) {
                int rCur = rotNode[0];
                int cCur = rotNode[1];
                for (int[] dir : dirs) {
                    int rNext = rCur + dir[0];
                    int cNext = cCur + dir[1];
                    if (inGrid(rNext, cNext) && grid[rNext][cNext] == 1) {
                        // 新鲜的橘子被感染成腐烂地了
                        grid[rNext][cNext] = 2;
                        newRotList.add(new int[]{rNext, cNext});
                    }
                }
            }
            // 当下一次循环的队列为空时，说明不能继续腐烂了
            if (newRotList.size() == 0) {
                break;
            }
            // 不为空表示还能继续腐烂
            rotList = new ArrayList<>(newRotList);
            minute++;
        }
        // 判断一下还有没有新鲜的橘子，如果有，就返回-1，否则返回分钟数
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (grid[r][c] == 1) {
                    return -1;
                }
            }
        }
        return minute;
    }

    public static void main(String[] args) {
        int[][] grid = {{2, 1, 1}, {1, 1, 0}, {0, 1, 1}};
        System.out.println(new Solution().orangesRotting(grid));
    }
}
