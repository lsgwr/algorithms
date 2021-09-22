/***********************************************************
 * @Description : 深度优先遍历(支持连通图, 不支持非连通图)
 *                CC: connected components
 *                不考虑连通分量的图的遍历
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2019-08-06 23:20
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter03DepthFirstTraversal;

import Chapter02GraphExpress.Graph;

import java.util.ArrayList;
import java.util.List;

public class GraphDFSNoCC {
    private Graph graph;

    /**
     * 存储顶点是否被访问的数组
     */
    private boolean[] visited;

    /**
     * 存放图的深度优先遍历的结果
     */
    private List<Integer> orderList = new ArrayList<>();

    public GraphDFSNoCC(Graph graph) {
        this.graph = graph;
        // 初始化访问数组，用图的顶点个数来访问
        visited = new boolean[graph.V()];
        // dfs(0)只能遍历一个联通分量
        dfs(0);
    }

    public Iterable<Integer> getOrderList() {
        return orderList;
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
