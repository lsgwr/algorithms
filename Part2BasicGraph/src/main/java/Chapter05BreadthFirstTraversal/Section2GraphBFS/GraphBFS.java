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
     * 广度优先遍历的顺序结果(只有一种，不想DFS有前序、后序两种)
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
     * 从source点开始进行广度优先遍历
     *
     * @param source 起点
     */
    private void bfs(int source) {
        // ArrayDeque既可以当队列又可以当栈来用，参考 https://github.com/19920625lsg/liuyubobobo-algorithms/tree/master/Part2Basic/src/main/java/Chapter03StackAndQueues/JavaBuiltIn
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(source);
        visited[source] = true;
        while (!queue.isEmpty()) {
            int v = queue.remove();
            orderList.add(v);
            for (int w: graph.adj(v)){
                // 遍历v的所有顶点
                if (!visited[w]){
                    queue.add(w);
                    visited[w] = true;
                }
            }
        }
    }

    public Iterable<Integer> getOrder(){
        return orderList;
    }
}
