/***********************************************************
 * @Description : 无权有向图的环检测:无向图中的环的判定方法在有向图中不适用，有向图通过在遍历过程中添加标记即可，递归回退时取消对应顶点的标记
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2019-12-25 7:36
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter13DirectedGraph.Section3CycleDetectAndDAG;

import Chapter02GraphExpress.Graph;

import java.util.ArrayList;
import java.util.List;

public class GraphDFSCycleDetectDirected {
    private Graph graph;

    /**
     * 存储顶点是否被访问的数组
     */
    private boolean[] visited;
    /**
     * 记录是否在向前路径的数组，递归回退时置为false
     */
    private boolean[] onPath;

    private boolean hasCycle = false;

    /**
     * 存放图的深度优先遍历的结果
     */
    private List<Integer> orderList = new ArrayList<>();

    public GraphDFSCycleDetectDirected(Graph graph) {
        if (!graph.isDirected()) {
            throw new IllegalArgumentException("只能传入有向图！");
        }
        this.graph = graph;
        // 初始化访问数组，用图的顶点个数来访问
        visited = new boolean[graph.V()];
        onPath = new boolean[graph.V()];
        // 从dfs(0)改成下面的代码，可以支持非连通的图,不用考虑连通分量的时候直接用dfs(v)即可。
        // for循环检测所有的联通分量，一个联通分量有环，当前图就是有环图
        for (int v = 0; v < graph.V(); v++) {
            if (!visited[v]) {
                if (dfs(v)) {
                    hasCycle = true;
                    break;
                }
            }
        }
    }

    public Iterable<Integer> getOrderList() {
        return orderList;
    }

    /**
     * 返回当前图是否有环
     */
    public boolean hasCycle() {
        return hasCycle;
    }

    /**
     * 从顶点v出发，进行DFS，顺便检测当前图是否有环
     *
     * @param v 当前遍历到的点
     *          return        当前的图是否有环
     */
    private boolean dfs(int v) {
        visited[v] = true;
        orderList.add(v);
        onPath[v] = true;
        for (Integer w : graph.adj(v)) {
            if (!visited[w]) {
                // w点没被访问的话就递归接着访问
                if (dfs(w)) {
                    // dfs递归往下访问，遇到有环就可以直接返回true了
                    return true;
                }
            } else if (onPath[w]) {
                //这个else分支就是有向图环检测的核心，即在我们的向前路径onPath上。不用无向图中的w!=parent是因为有向图中一条边如果是双向地也可以认为有环
                return true;
            }
        }
        // 递归回退时把标记取消
        onPath[v] = false;
        return false;
    }
}
