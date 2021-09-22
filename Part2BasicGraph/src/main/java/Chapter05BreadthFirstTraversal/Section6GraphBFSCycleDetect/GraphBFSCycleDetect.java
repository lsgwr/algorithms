package Chapter05BreadthFirstTraversal.Section6GraphBFSCycleDetect;

import Chapter02GraphExpress.Graph;

import java.util.*;

/**
 * 广度优先遍历实现环检测
 */
public class GraphBFSCycleDetect {
    private Graph graph;
    /**
     * 顶点的访问情况的数组
     */
    private boolean[] visited;

    /**
     * 广度优先遍历的顺序结果(只有一种，不想DFS有前序、后序两种)
     */
    private List<Integer> orderList = new ArrayList<>();

    private int[] pre;
    private boolean hasCycle = false;

    public GraphBFSCycleDetect(Graph graph) {
        this.graph = graph;
        this.visited = new boolean[graph.V()];
        pre = new int[graph.V()];
        Arrays.fill(pre, -1);
        // 从bfs(0)改成下面的代码，可以支持非连通的图,不用考虑连通分量的时候直接用bfs(v)即可
        for (int v = 0; v < graph.V(); v++) {
            if (!visited[v]) {
                if (bfs(v)) {
                    hasCycle = true;
                    break;
                }
            }
        }
    }

    /**
     * 从source点开始进行广度优先遍历
     */
    private boolean bfs(int source) {
        // ArrayDeque既可以当队列又可以当栈来用，参考 https://github.com/19920625lsg/algorithms/tree/master/Part2Basic/src/main/java/Chapter03StackAndQueues/JavaBuiltIn
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(source);
        visited[source] = true;
        pre[source] = source;
        while (!queue.isEmpty()) {
            int v = queue.remove();
            orderList.add(v);
            for (int w : graph.adj(v)) {
                // 遍历v的所有邻接点
                if (!visited[w]) {
                    queue.add(w);
                    visited[w] = true;
                    pre[w] = v;
                } else if (pre[v] != w) {
                    // 当检测到一个节点(当前节点current)的相邻节点已经被visited但是这个相邻节点不是current的上一个visited节点，就说明图中有环了
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 图中是否有环
     */
    public boolean hasCycle() {
        return hasCycle;
    }

    public Iterable<Integer> getOrderList() {
        return orderList;
    }
}
