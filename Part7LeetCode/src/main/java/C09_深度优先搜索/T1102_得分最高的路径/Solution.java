/***********************************************************
 * @Description : 1102.得分最高的路径
 * https://leetcode-cn.com/problems/path-with-maximum-minimum-value/
 * 无向有权图的DFS遍历问题
 * 参考教程：
 *      * https://leetcode-cn.com/problems/path-with-maximum-minimum-value/solution/java-dfs-er-fen-by-gaaakki/
 *      * https://leetcode-cn.com/problems/path-with-maximum-minimum-value/solution/python3-dfs-by-luoyuchieh/
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
     * 记录边权重的数组
     */
    private int[][] edgeWeightGrid;

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
        edgeWeightGrid = A;
        int result = 0;
        int lo = 1;
        // 得分最高的路径中的最小值一定小于等于A[0][0], A[-1][-1]中的较小值
        int hi = Math.min(A[0][0], A[R - 1][C - 1]);
        // 在[1, min(A[0][0], A[-1][-1])]这个区间中进行二分查找：
        while (lo <= hi) {
            // 取中间点
            int mid = lo + (hi - lo) / 2;
            // 每次DFS都要重新初始化这个数组
            visited = new boolean[R][C];
            // 如果存在一条路径中的所有值比查找的值大，就更新搜索范围，不断缩小，当while退出时，result就是要求的路径最小值中的最大值
            if (dfs(0, 0, mid)) {
                // 存在一条路径中的所有值比查找的值大
                result = mid;
                // 要找的最大值在右侧
                lo = mid + 1;
            } else {
                // 不存在一条路径中的所有值比查找的值大，说明在左侧
                hi = mid - 1;
            }
        }
        // 并把区间更新到右边的区间进行查找，否则在左边的区间查找．用这个方法进行剪枝，可以提高效率，防止超时
        return result;
    }

    /**
     * 是否存在一条路径中的所有值比查找的值大
     *
     * @param r      当前的行
     * @param c      当前的列
     * @param maxCur 当前的所有路径上最小分数中的最大值
     * @return 存在一条路径中的所有值比查找的值大则返回true，否则返回false
     */
    private boolean dfs(int r, int c, int maxCur) {
        visited[r][c] = true;
        if (r == R - 1 && c == C - 1) {
            return true;
        }
        for (int[] dir : dirs) {
            int rNext = r + dir[0];
            int cNext = c + dir[1];
            if (inGrid(rNext, cNext) && !visited[rNext][cNext] && edgeWeightGrid[rNext][cNext] >= maxCur) {
                if (dfs(rNext, cNext, maxCur)) {
                    return true;
                }
            }
        }
        return false;
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
