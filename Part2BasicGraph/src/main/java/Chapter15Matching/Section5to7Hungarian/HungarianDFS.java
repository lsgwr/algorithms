/***********************************************************
 * @Description : 基于图的BFS遍历实现的匈牙利算法(Hungarian算法)
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2019/12/28 0:29
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter15Matching.Section5to7Hungarian;

import Chapter02GraphExpress.Graph;
import Chapter04DFSInAction.Section10BiPartitionDetect.GraphDFSBiPartitionDetect;

import java.util.*;

public class HungarianDFS {
    /**
     * 要找最大匹配的二分图
     */
    private Graph graph;
    /**
     * 最大匹配的值
     */
    private int maxMatch = 0;

    /**
     * match[v]=w表示顶点v在图中匹配的顶点是w
     */
    private int[] matching;

    /**
     * 顶点访问数组
     */
    private boolean[] visited;

    public HungarianDFS(Graph graph) {
        GraphDFSBiPartitionDetect biPartitionDetect = new GraphDFSBiPartitionDetect(graph);
        if (!biPartitionDetect.isBiPartition()) {
            throw new IllegalArgumentException("匹配问题必须针对地是二分图!");
        }
        this.graph = graph;
        // 二分图中的顶点颜色区分，一半为0(左侧)，一半为1(右侧)
        int[] colors = biPartitionDetect.getColors();
        int V = graph.V();
        matching = new int[V];
        Arrays.fill(matching, -1);
        // boolean类型默认为false
        visited = new boolean[V];
        Arrays.fill(visited, false);

        for (int v = 0; v < V; v++) {
            // 只遍历二分图中左侧还未被遍历的点(颜色为0)
            if (colors[v] == 0 && matching[v] == -1) {
                // 每个左侧的点都进行一次bfs来找增广路径
                if (dfs(v)) {
                    // 本地bfs找到了一条增广路径
                    maxMatch++;
                }
            }
        }
    }

    /**
     * dfs找增广路径
     *
     * @param source dfs遍历的起点，一定是二分图中左半部分的点
     * @return 本次dfs是否找到了增广路径
     */
    private boolean dfs(int source) {
        visited[source] = true;
        for (int u : graph.adj(source)) {
            if (!visited[u]) {
                visited[u] = true;
                if (matching[u] == -1 || dfs(matching[u])) {
                    // 等于-1表示未找到增广路径
                    matching[source] = u;
                    matching[u] = source;
                    // u未被访问，即找到了1条增广路径,回到上一层递归
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 获取最大匹配数
     */
    public int getMaxMatch() {
        return maxMatch;
    }

    /**
     * 当前的最大匹配是否是完全匹配(也可以叫完全匹配，即所有的点都有二分图另一侧的点和自己匹配而且匹配对互不干扰O)
     */
    public boolean isPrefectMatch() {
        return maxMatch * 2 == graph.V();
    }
}
