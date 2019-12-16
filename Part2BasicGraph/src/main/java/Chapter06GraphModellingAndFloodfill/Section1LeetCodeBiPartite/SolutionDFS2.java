/***********************************************************
 * @Description : 二分图检测的递归DFS实现
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2019-12-16 16:21
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter06GraphModellingAndFloodfill.Section1LeetCodeBiPartite;

import java.util.Arrays;

/**
 * LeetCode 785 is-graph-bipartite https://leetcode-cn.com/problems/is-graph-bipartite/ 判断二分图，
 * 参考 Chapter04DFSInAction.Section10BiPartitionDetect.GraphDFSBiPartitionDetect
 */

public class SolutionDFS2 {
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
        for (int v = 0; v < graph.length; v++) { // v作为graph的下标正好能作为图的定点(范围为0~n)，这点务必好好理解，遇到不是0~n的定点，可以用map映射，0~n内的元素作为键，自定义的定点(如字符串作为键对应的值)
            if (!visited[v]) { // 一定注意v顶没被访问才进行新一轮的dfs，因为只要联通分量的一个元素被遍历了，那么这个联通分量内的一定都被遍历了
                if (!dfs(v, 1)) {
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
     * @param v     深度优先遍历的起点
     * @param color source要被染地色
     * @return 是否是二分图
     */
    private boolean dfs(int v, int color) {
        // 起点染色为color
        colors[v] = color;
        // 进入本层递归就认为是被访问过了
        visited[v] = true;
        for (int w : graph[v]) { // 遍历v的邻接点
            if (!visited[w]) { // 如果w还没被访问，就把元素加入到队列里
                if (!dfs(w, 1 - color)) {// 1-color表示邻接点颜色和父节点颜色要相反
                    // 判断子递归的返回值，如果是false直接返回
                    return false;
                }
            } else {
                // 如果v的一个邻接点w之前已经被访问了，那么看下是不是和v的颜色相同，如果相同，说明有环
                if (colors[w] == colors[v]) {
                    // 如果w已经访问过，但是w作为v的邻接点和v的颜色相同，说明不是二分图
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[][] graph = {{1, 3}, {0, 2}, {1, 3}, {0, 2}};
        SolutionDFS2 dfs = new SolutionDFS2();
        System.out.println(dfs.isBipartite(graph));
    }
}
