/**
 * 广度优先遍历计算连通分量
 */
package Chapter05BreadthFirstTraversal.Section5GraphBFSConnectedComponents;

import Chapter02GraphExpress.Graph;

import java.util.*;

public class GraphBFSConnectedComponents {

    private Graph graph;
    /**
     * 顶点的访问情况的数组
     */
    private int[] visited;

    /**
     * 广度优先遍历的顺序结果(只有一种，不想DFS有前序、后序两种)
     */
    private List<Integer> orderList = new ArrayList<>();

    /**
     * 存储连通分量具体内容的Map
     */
    private Map<Integer, List<Integer>> connectedComponentsMap = new TreeMap<>();

    /**
     * 连通分量计数器
     */
    private int connectedComponentCount;

    public GraphBFSConnectedComponents(Graph graph) {
        this.graph = graph;
        this.visited = new int[graph.V()];
        Arrays.fill(visited, -1);
        // 从bfs(0)改成下面的代码，可以支持非连通的图,不用考虑连通分量的时候直接用bfs(v)即可
        for (int v = 0; v < graph.V(); v++) {
            if (visited[v] == -1) { // 被访问过地定点不会作为起始点进入bfs()
                // 第二个参数表示当前连通分量的标志(多个连通分量内的元素在visited内用connectedComponentCount这个值进行标记)
                bfs(v, connectedComponentCount);
                // 当退出bfs()时，相当于结束了一个连通图的遍历，所以连通分量数加1
                connectedComponentCount++;
            }
        }
    }

    /**
     * 广度优先遍历,非递归
     *
     * @param source 遍历的起点
     * @param ccid   当前连通分量的标记(同一个连通分量内的元素都在visited数组内用这个数值进行赋值标记)
     */
    private void bfs(int source, int ccid) {
        // ArrayDeque既可以当队列又可以当栈来用，参考 https://github.com/19920625lsg/algorithms/tree/master/Part2Basic/src/main/java/Chapter03StackAndQueues/JavaBuiltIn
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(source);
        visited[source] = ccid;
        while (!queue.isEmpty()) {
            int v = queue.remove();
            orderList.add(v);
            for (int w : graph.adj(v)) {
                // 遍历v的所有顶点
                if (visited[w] == -1) {
                    queue.add(w);
                    visited[w] = ccid;
                }
            }
        }
    }

    /**
     * 获取广度遍历的结果
     */
    public Iterable<Integer> getOrderList() {
        return orderList;
    }

    /**
     * 获取连通分量的个数
     */
    public int getConnectedComponentCount() {
        return connectedComponentCount;
    }

    /**
     * 获取访问的点的数组
     *
     * @return
     */
    public int[] getVisited() {
        return visited;
    }

    /**
     * 获取连通分量的详细信息，Map的key是ccid，value是ccid对应连通分量内的所有元素
     */
    public Map<Integer, List<Integer>> getConnectedComponentsMap() {
        for (int ccid = 0; ccid < connectedComponentCount; ccid++) {
            // vertexIndex表示顶点的索引，即从txt文本中读入地一个个边的顶点数值
            for (int vertexIndex = 0; vertexIndex < visited.length; vertexIndex++) {
                // 只有当已访问数组中vertexIndex下标对应的值等于ccid才加到ccid对应的连通分量List中
                if (ccid == visited[vertexIndex]) {
                    // 给Map赋值
                    if (connectedComponentsMap.get(ccid) == null) {
                        // 以前没加过这个连通分量地话
                        List<Integer> ccList = new ArrayList<>();
                        ccList.add(vertexIndex);
                        // 把连通分量加进去
                        connectedComponentsMap.put(ccid, ccList);
                    } else {
                        // 以前加入过ccList了，那么这次直接往list里加元素即可
                        connectedComponentsMap.get(ccid).add(vertexIndex);
                    }
                }
            }
        }
        return connectedComponentsMap;
    }

}
