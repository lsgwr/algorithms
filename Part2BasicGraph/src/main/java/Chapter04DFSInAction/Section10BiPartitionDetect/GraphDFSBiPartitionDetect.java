/***********************************************************
 * @Description : 二分图检测
 * 原理：用两种颜色对图进行染色，如果最后每个顶点的所有邻接点和这个顶点的颜色都不同，说明当前图是个二分图
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2019-08-06 23:20
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter04DFSInAction.Section10BiPartitionDetect;

import Chapter02GraphExpress.Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GraphDFSBiPartitionDetect {
    private Graph graph;

    /**
     * 存储顶点是否被访问的数组
     */
    private boolean[] visited;

    /**
     * 存放图的深度优先遍历的结果
     */
    private List<Integer> orderList = new ArrayList<>();

    /**
     * 颜色数组，存储每个节点的颜色
     */
    private int[] colors;

    /**
     * 是否是二分图,默认成是二分图
     */
    private boolean biPartition = true;

    public GraphDFSBiPartitionDetect(Graph graph) {
        this.graph = graph;
        // 初始化访问数组，用图的顶点个数来访问
        visited = new boolean[graph.V()];
        // 初始化颜色数组
        this.colors = new int[graph.V()];
        // 初始化colors为-1，后面检测到二分图会更新这个数组，会有0和1两种
        Arrays.fill(this.colors, -1);
        // 从dfs(0)改成下面的代码，可以支持非连通的图,不用考虑连通分量的时候直接用dfs(v)即可
        for (int v = 0; v < graph.V(); v++) {
            if (!visited[v]) {
                // 第一个节点染成蓝色(0)
                if (!dfs(v, 0)) {
                    // 某一个联通分量不是二分图，整个图就不是二分图了，直接返回，不再检测剩下的二分图了
                    biPartition = false;
                    // 一旦检测到二分图立马跳出，一定别忘
                    break;
                }
            }
        }
    }

    public Iterable<Integer> getOrderList() {
        return orderList;
    }

    /**
     * 是否是二分图
     */
    public boolean isBiPartition() {
        return biPartition;
    }

    /**
     * 获取图划分后的二分图数组
     */
    public int[] getColors() {
        return colors;
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
        orderList.add(v);
        colors[v] = color;
        for (Integer w : graph.adj(v)) {
            if (!visited[w]) {
                // 颜色只有蓝(0)、绿(1)两种，w是v的邻接点，根据二分图的检测原理，w、v的颜色必须相反，只能一蓝一绿，蓝+绿 = 0 + 1 = 1,所以1-v的颜色 = 1-color = w的颜色
                if (!dfs(w, 1 - color)) {
                    // 返回false表示不是二分图
                    return false;
                }
            } else if (colors[w] == colors[v]) {
                // 如果w已经访问过，但是w作为v的邻接点和v的颜色相同，说明不是二分图
                return false;
            }
        }
        return true;
    }
}
