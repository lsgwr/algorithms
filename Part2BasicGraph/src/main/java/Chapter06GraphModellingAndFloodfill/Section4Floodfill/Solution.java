/***********************************************************
 * @Description : 岛屿的最大面积问题，等于1的点表示陆地，找到连接到一起的最多的点即表示最大的面积
 *                优化：实际解决洪泛问题并不需要显示地构造图，可以省去一半代码，关键在于理解原理。visited和grid完全对应
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2019-08-10 20:28
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter06GraphModellingAndFloodfill.Section4Floodfill;

public class Solution {

    /**
     * 当前点求上右下左四个点时用到的矢量差,dirs 是directions的意思
     */
    private int[][] dirs = {
            {-1, 0},
            {0, 1},
            {1, 0},
            {0, -1}
    };

    /**
     * 传进来的grid有多少行(Row)多少列(Col)
     */
    private int R, C;

    /**
     * 岛屿网格的情况
     */
    private int[][] grid;

    /**
     * 判断grid中所有的点是否被访问，因为此时(x, y)共同缺点一个点，所以visited也是二维数组
     */
    private boolean[][] visited;


    public int maxAreaOfIsland(int[][] grid) {
        if (grid == null) {
            return 0;
        }
        R = grid.length;
        if (R == 0) {
            // 如果行数为0说明图为空，直接返回
            return 0;
        }
        C = grid[0].length;
        if (C == 0) {
            // 如果列数为0说明图为空，直接返回
            return 0;
        }
        this.grid = grid;
        // 构造出想要的图，图实际就是简化后的邻接表形式的图
        // 最大的连通分量内有多少节点
        int result = 0;
        // visited的长度就是graph的长度，即grid内所有的节点数
        visited = new boolean[R][C];

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (!visited[i][j] && grid[i][j] == 1) {
                    // grid[i][j] == 1表示(i, j)是陆地,而且还没被访问，然后才有必要继续往下遍历
                    result = Math.max(result, dfs(i, j));
                }
            }
        }
        return result;
    }

    private int dfs(int x, int y) {
        visited[x][y] = true;
        // 进来后至少有一个点v被访问了，所以连通分量的节点数初始化为1
        int result = 1;
        // 遍历节点(x, y)所有的邻接点,判断是陆地的
        for (int d = 0; d < dirs.length; d++) {
            int next_x = x + dirs[d][0];
            int next_y = y + dirs[d][1];
            // 点(next_x, next_y)必须在grid区域内 + 没被访问过 + 是陆地(点(x, y)已经保障是陆地的前提下)
            if (inArea(next_x, next_y) && !visited[next_x][next_y] && grid[next_x][next_y] == 1) {
                result += dfs(next_x, next_y);
            }
        }
        return result;
    }

    /**
     * 判断第x行第y列的点(x, y)是否在grid所在的范围内
     */
    private boolean inArea(int x, int y) {
        return x >= 0 && x < R && y >= 0 && y < C;
    }
}
