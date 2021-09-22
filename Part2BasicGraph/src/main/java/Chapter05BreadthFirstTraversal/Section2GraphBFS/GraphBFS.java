/***********************************************************
 * @Description : 图的广度优先遍历的实现
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2019-08-09 08:21
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter05BreadthFirstTraversal.Section2GraphBFS;

import Chapter02GraphExpress.Graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class GraphBFS {

    private Graph graph;
    /**
     * 顶点的访问情况的数组
     */
    private boolean[] visited;

    /**
     * 广度优先遍历的顺序结果(只有一种，不像DFS有前序、后序两种)
     */
    private List<Integer> orderList = new ArrayList<>();

    public GraphBFS(Graph graph) {
        this.graph = graph;
        this.visited = new boolean[graph.V()];
        // 从bfs(0)改成下面的代码，可以支持非连通的图,不用考虑连通分量的时候直接用bfs(v)即可
        for (int v = 0; v < graph.V(); v++) {
            if (!visited[v]) {
                bfs(v);
            }
        }
    }

    /**
     * 从source点开始进行广度优先遍历，非递归实现
     /**
     *          1
     *        / | \
     *       /  |  \
     *      3   2  4
     *     / \
     *    5  6
     * bfs遍历结果：[1,3,2,4,5,6]
     *
     * @param source 图遍历的起点
     */
    private void bfs(int source) {
        // ArrayDeque既可以当队列又可以当栈来用，参考 https://gitee.com/lsgwr/algorithms/tree/master/Part2Basic/src/main/java/Chapter03StackAndQueues/JavaBuiltIn
        Queue<Integer> queue = new ArrayDeque<>();
        // 添加起始点source到队列
        queue.add(source);
        // 添加进队列就被认为是访问过了
        visited[source] = true;
        // 访问到就要加入到order中
        orderList.add(v);
        // 队列不为空，说明图还没有遍历完
        while (!queue.isEmpty()) {
            // 出队列
            int v = queue.remove();
            for (int w: graph.adj(v)){
                // 遍历v的所有邻接点
                if (!visited[w]){
                    // 把没被访问过的邻接点加入到队列中
                    queue.add(w);
                    // 加入队列就认为被visit了
                    visited[w] = true;
                    // 访问到就要加入到order中
                    orderList.add(v);
                }
            }
        }
    }

    public Iterable<Integer> getOrderList(){
        return orderList;
    }
}
