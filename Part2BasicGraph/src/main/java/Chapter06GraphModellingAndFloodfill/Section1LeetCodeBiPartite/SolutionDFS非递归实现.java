/***********************************************************
 * @Description : 二分图检测的非递归DFS实现
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2019-08-09 08:21
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter06GraphModellingAndFloodfill.Section1LeetCodeBiPartite;

import java.util.ArrayDeque;
import java.util.Arrays;

/**
 * LeetCode 785 is-graph-bipartite https://leetcode-cn.com/problems/is-graph-bipartite/ 判断二分图，
 * 参考 Chapter04DFSInAction.Section10BiPartitionDetect.GraphDFSBiPartitionDetect
 */

public class SolutionDFS非递归实现 {
    /**
     * 使用邻接表形式表达的矩阵。第一个维度的下标就代表图的顶点(顶点用0~n的标号表示)
     */
    private int[][] graph;

    /**
     * 顶点是否被访问了
     */
    private boolean[] visited;

    /**
     * 顶点的染色
     */
    private int[] colors;

    /**
     * 当前图是否是二分图,默认是二分图即true
     */
    private boolean isBi = true;

    public boolean isBipartite(int[][] graph) {
        this.graph = graph;
        // 初始化visited数组长度为graph的第一维度长度
        this.visited = new boolean[graph.length];
        this.colors = new int[graph.length];
        // 初始化为false，表示未被访问
        Arrays.fill(visited, false);
        // 颜色必须显式初始化
        Arrays.fill(colors, -1);
        for (int v = 0; v < graph.length; v++) {
            // 一定注意v顶没被访问才进行新一轮的dfs，因为只要联通分量的一个元素被遍历了，那么这个联通分量内的一定都被遍历了
            if (!visited[v]) {
                if (!dfs(v)) {
                    isBi = false;
                    break;
                }
            }

        }
        return isBi;
    }

    /**
     * 深度优先遍历，判断是否是二分图
     *
     * @param source 深度优先遍历的起点
     * @return 是否是二分图
     */
    private boolean dfs(int source) {
        // ArrayDeque使用 https://gitee.com/lsgwr/algorithms/tree/master/Part2Basic/src/main/java/Chapter03StackAndQueues/JavaBuiltIn
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        // 起点染色为1
        colors[source] = 1;
        stack.push(source);
        // 加入到栈就认为被访问过了
        visited[source] = true;
        // 栈不为空就可以一直往下dfs
        while (!stack.isEmpty()) {
            // 获取栈顶元素v
            int v = stack.pop();
            // 遍历v的邻接点
            for (int w : graph[v]) {
                // 如果w还没被访问，就把元素加入到栈里
                if (!visited[w]) {
                    stack.push(w);
                    visited[w] = true;
                    // 颜色必须和父节点颜色相反
                    colors[w] = 1 - colors[v];
                } else {
                    // 如果v的一个邻接点w之前已经被访问了，那么看下是不是和v的颜色相同，如果相同，说明有环
                    if (colors[w] == colors[v]) {
                        // 不是二分图
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[][] graph = {{1, 3}, {0, 2}, {1, 3}, {0, 2}};
        SolutionDFS非递归实现 dfs = new SolutionDFS非递归实现();
        System.out.println(dfs.isBipartite(graph));
    }
}
