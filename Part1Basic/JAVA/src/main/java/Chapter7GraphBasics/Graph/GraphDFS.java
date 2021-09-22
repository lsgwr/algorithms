/***********************************************************
 * @Description : 深度优先遍历(支持连通图和非连通图)
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2019-08-06 23:20
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter7GraphBasics.Graph;


import java.util.ArrayList;
import java.util.List;

public class GraphDFS {
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
     * 连通分量计数器
     */
    private int connectedComponentCount;

    public GraphDFS(Graph graph) {
        this.graph = graph;
        // 初始化访问数组，用图的顶点个数来访问
        visited = new boolean[graph.V()];
        // 从dfs(0)改成下面的代码，可以支持非连通的图
        for (int v = 0; v < graph.V(); v++) {
            if (!visited[v]) {
                dfs(v);
                // 当退出递归时，相当于结束了一个连通图的遍历，所以连通分量数加1
                connectedComponentCount++;
            }
        }
    }

    public Iterable<Integer> getOrderList() {
        return orderList;
    }

    public int getConnectedComponentCount() {
        return connectedComponentCount;
    }

    private void dfs(int v) {
        visited[v] = true;
        orderList.add(v);
        for (Integer w : graph.adj(v)) {
            if (!visited[w]) {
                // w点没被访问的话就递归接着访问
                dfs(w);
            }
        }
    }
}
