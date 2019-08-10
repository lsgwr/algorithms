/***********************************************************
 * @Description : 岛屿的最大面积问题，等于1的点表示陆地，找到连接到一起的最多的点即表示最大的面积
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2019-08-10 20:28
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter06GraphModellingAndFloodfill.Section2And3GraphModel;

import java.util.HashSet;

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
     * 判断grid中所有的点是否被访问
     */
    private boolean[] visited;

    /**
     * 图的邻接表(TreeSet实现的图性能最高,但是因为Tree是有序地，为了保持grid原本的顺序，这里用了HashSet)
     * 原来的是 private TreeSet<Integer>[] adj.但是在这里不需要其他的图操作，所以这里的邻接表就可以认为是图graph
     */
    private HashSet<Integer>[] graph;

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
        graph = constructGraph();
        // 最大的连通分量内有多少节点
        int result = 0;
        // visited的长度就是graph的长度，即grid内所有的节点数
        visited = new boolean[graph.length];
        for (int v = 0; v < graph.length; v++) {
            int x = v / C;
            int y = v % C;
            if (!visited[v] && grid[x][y] == 1) {
                // grid[x][y] == 1表示(x, y)是陆地,然后才有必要继续往下遍历
                result = Math.max(result, dfs(v));
            }
        }

        return result;
    }

    private int dfs(int v) {
        visited[v] = true;
        // 进来后至少有一个点v被访问了，所以连通分量的节点数初始化为1
        int result = 1;
        // 遍历节点v所有的邻接点
        for (int w : graph[v]) {
            if (!visited[w]) {
                result += dfs(w);
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


    /**
     * 根据grid构建图的邻接表(这里的邻接表等同于图graph了，因为我们不需要图的其他操作了)
     */
    private HashSet<Integer>[] constructGraph() {
        // grid中一共有rows*cols个点，每个点都有自己的邻接点(上右下左等)
        HashSet<Integer>[] graph = new HashSet[R * C];
        for (int i = 0; i < graph.length; i++) {
            // grid中每个点都有自己的邻接点集合
            graph[i] = new HashSet<>();
        }
        // 每个点的一维坐标转化成二维坐标，原理图见 https://img.mukewang.com/szimg/5d4e73b50001312b17281080.jpg
        // 二维转一维，每行有13个元素(多少列)，标记为C,则：x = v / C ; y = v % C, v是vertex的意思，代表grid中的每个点顶点
        for (int v = 0; v < graph.length; v++) {
            int x = v / C;
            int y = v % C;
            if (grid[x][y] == 1) {
                // grid[x][y] == 1表示点(x, y)是陆地，当前点是陆地才有必要向周围邻接点蔓延，看相邻的陆地有多少
                for (int d = 0; d < dirs.length; d++) {
                    // 开始定位上右下左四个点,dirs数组中每个元素可以定位到一个点
                    int next_x = x + dirs[d][0];
                    int next_y = y + dirs[d][1];
                    // 必须验证next_x和next_y在grid内，因为第一行没有上面的点，最后一行没有下面的点，最左右的列也是一样
                    // grid[next_x][next_y] == 1表明(next_x, next_y)是陆地，因为(x, y)也是陆地，所以(x, y)和(next_x, next_y)是连通地
                    if (inArea(next_x, next_y) && grid[next_x][next_y] == 1) {
                        // 等于1表明和当前点(x, y)是联通地
                        // 二维转一维
                        int next = next_x * C + next_y;
                        // 无向图需要把两个方向都加入到邻接表中
                        graph[v].add(next);
                        graph[next].add(v);
                    }
                }
            }
        }
        return graph;
    }
}
