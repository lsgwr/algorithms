/***********************************************************
 * @Description : 深度优先遍历(支持连通图和非连通图)，基于栈的非递归实现
 *                其实递归的作用就类似于栈，后面讲BFS实际就是把本节的栈改为队列即可
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2019-12-18 23:44
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter03DepthFirstTraversal;

import Chapter02GraphExpress.Graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class GraphDFSNoRecursion {
    private Graph graph;

    /**
     * 存储顶点是否被访问的数组
     */
    private boolean[] visited;

    /**
     * 存放图的深度优先遍历的结果
     */
    private List<Integer> orderList = new ArrayList<>();

    public GraphDFSNoRecursion(Graph graph) {
        this.graph = graph;
        // 初始化访问数组，用图的顶点个数来访问
        visited = new boolean[graph.V()];
        // 从dfs(0)改成下面的代码，可以支持非连通的图,不用考虑连通分量的时候直接用dfs(v)即可
        for (int v = 0; v < graph.V(); v++) {
            // 访问过表示在之前的联通分量已经走到过了
            if (!visited[v]) {
                dfs(v);
            }
        }
    }

    public Iterable<Integer> getOrderList() {
        return orderList;
    }

    private void dfs(int v) {
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        stack.push(v);
        visited[v] = true;
        orderList.add(v);
        while (!stack.isEmpty()) {
            int cur = stack.pop();
            for (Integer w : graph.adj(cur)) {
                if (!visited[w]) {
                    // w点没被访问的话就递归接着访问
                    stack.push(w);
                    visited[w] = true;
                    orderList.add(w);
                }
            }
        }
    }
}
