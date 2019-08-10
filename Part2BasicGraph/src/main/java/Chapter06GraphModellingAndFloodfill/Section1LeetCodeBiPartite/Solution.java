package Chapter06GraphModellingAndFloodfill.Section1LeetCodeBiPartite;

import java.util.Arrays;

/**
 * LeetCode 785 is-graph-bipartite https://leetcode-cn.com/problems/is-graph-bipartite/ 判断二分图，
 * 参考 Chapter04DFSInAction.Section10BiPartitionDetect.GraphDFSBiPartitionDetect
 */

public class Solution {

    /**
     * 图的二维数组表示
     */
    private int[][] graph;

    /**
     * 存储顶点是否被访问的数组
     */
    private boolean[] visited;

    /**
     * 颜色数组，存储每个节点的颜色,0是蓝色，1是绿色
     */
    private int[] colors;

    /**
     * 是否是二分图,默认成是二分图
     */
    private boolean biPartition = true;


    /**
     * @param graph 直接给出了图的临界表，实际就相当于把图给出来了，判断二分图需要的参数都可以拿到了
     * @return 当前的图是否是二分图
     */
    public boolean isBipartite(int[][] graph) {
        this.graph = graph;
        // 初始化访问数组，用图的顶点个数来访问
        visited = new boolean[graph.length];
        // 初始化颜色数组
        this.colors = new int[graph.length];
        Arrays.fill(this.colors, -1);
        // 从dfs(0)改成下面的代码，可以支持非连通的图,不用考虑连通分量的时候直接用dfs(v)即可
        for (int v = 0; v < graph.length; v++) {
            if (!visited[v]) {
                // 第一个节点染成蓝色(0)
                if (!dfs(v, 0)) {
                    biPartition = false;
                    // 一旦检测到二分图立马跳出，一定别忘
                    break;
                }
            }
        }
        return biPartition;
    }

    /**
     * dfs过程中检测当前图是否是二分图
     *
     * @param v     当前的顶点
     * @param color v点的染色
     * @return 是否是二分图
     */
    private boolean dfs(int v, int color) {
        visited[v] = true;
        colors[v] = color;
        for (Integer w : graph[v]) {
            if (!visited[w]) {
                // 颜色只有蓝(0)、绿(1)两种，w是v的邻接点，根据二分图的检测原理，w、v的颜色必须相反，只能一蓝一绿，蓝+绿 = 0 + 1 = 1,所以1-v的颜色 = 1-color = w的颜色
                if (!dfs(w, 1 - color)) {
                    // 返回false表示不是二分图
                    return false;
                }
            } else if (colors[w] == colors[v]) {
                // 如果w已经访问过，但是w作为v的邻接点和v的颜色相同，说明有二分图
                return false;
            }
        }
        return true;
    }
}
