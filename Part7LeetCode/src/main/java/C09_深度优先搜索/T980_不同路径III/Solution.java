/***********************************************************
 * @Description : 哈密尔顿问题的具体问题
 * leetcode 980号问题 https://leetcode-cn.com/problems/unique-paths-iii/
 * 在二维网格 grid 上，有 4 种类型的方格：
 *
 * 1 表示起始方格。且只有一个起始方格。
 * 2 表示结束方格，且只有一个结束方格。
 * 0 表示我们可以走过的空方格。
 * -1 表示我们无法跨越的障碍。
 * 返回在四个方向（上、下、左、右）上行走时，从起始方格到结束方格的不同路径的数目，每一个无障碍方格都要通过一次。
 *
 * 类似前面的四联通问题， 引入前面的四联通问题+求汉密尔顿路径的思想=我们的题解
 * 注意栅格是屏幕坐标系，坐标点用(r, c)表示，第一个坐标是行(自上往下递增)，第二个坐标是列(自左往右递增)
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2019/12/20 23:51
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C09_深度优先搜索.T980_不同路径III;

class Solution {

    /**
     * 根据行和列获取状态压缩后的值
     *
     * @param r 行号
     * @param c 列号
     * @return r*10+c
     */
    private int zip(int r, int c) {
        return r * C + c;
    }

    /**
     * 根据状态压缩后的值获取原始的行和列
     *
     * @param state 压缩后的状态值
     * @return 状态解压后的行和列
     */
    private int[] unzip(int state) {
        return new int[]{state / C, state % C};
    }

    /**
     * 判断点(r, c)是否在栅格内
     *
     * @param r 行号
     * @param c 列号
     */
    private boolean inGrid(int r, int c) {
        return r >= 0 && r < R && c >= 0 && c < C;
    }

    /**
     * 四联通偏移分量
     */
    private int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
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

    /**
     * 1 表示起始方格。且只有一个起始方格。坐标压缩后的点
     */
    private int START;

    /**
     * 2 表示结束方格，且只有一个结束方格。坐标压缩后的点
     */
    private int FINAL;

    /**
     * 初始化的时候需要访问的总节点数，本题目中不等于-1的点都是要访问的
     */
    private int LEFT;

    public int uniquePathsIII(int[][] grid) {
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
        this.grid = grid;
        this.visited = new boolean[R][C];
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (grid[r][c] == 1) {
                    START = zip(r, c);
                }
                if (grid[r][c] == 2) {
                    FINAL = zip(r, c);
                }
                if (grid[r][c] != -1) {
                    LEFT++;
                }
            }
        }
        // 起点和起点的父节点都认为是START，要访问的节点数为节点值不等于-1的点
        return dfs(START, LEFT);
    }

    /**
     * DFS遍历，过程中使用回溯法找到哈密尔顿路径
     *
     * @param v    当前递归遍历到的节点,zip状态压缩行和列后的值
     * @param left 本次递归还剩下多少元素没被访问，一定注意left是本层递归中的局部变量，
     *             不是在所有递归中都有效地！！回退到上一层递归left的意义就变了！！每层递归都有自己的left!!!
     * @return 找到地哈密尔顿路径的数量
     */
    private int dfs(int v, int left) {
        // 计算v点的行和列
        int[] point = unzip(v);
        int r = point[0], c = point[1];
        visited[r][c] = true;
        left--;

        if (left == 0 && v == FINAL) {
            // 本次找到了一个符合条件的路径了，但是本题是要找出所有的，所以终点的访问状态要再设置为false.
            visited[r][c] = false;
            // 本次的v即是终点，并且所有非障碍点都已经被访问了，则此次递归找到了一个哈密尔顿路径
            return 1;
        }

        // 此次递归中的哈密尔顿路径的数量
        int cnt = 0;

        // 还没递归完就接着递归
        for (int[] dir : dirs) {
            // dir代表相当于当前点的位移
            int rNext = r + dir[0], cNext = c + dir[1];
            // 在栅格内+节点值等于0+没被访问 表示是我们可以走过的空方格
            if (inGrid(rNext, cNext) && grid[rNext][cNext] != -1 && !visited[rNext][cNext]) {
                // w点没被访问的话就递归接着访问
                cnt += dfs(zip(rNext, cNext), left);
            }
        }
        // 没找到要回退，所以要把v点设置为未被访问过，即设置为False
        visited[r][c] = false;
        return cnt;
    }

    public static void main(String[] args) {
        int[][] grid = {{1, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 2}};
        // 4
        System.out.println(new Solution().uniquePathsIII(grid));
    }
}
