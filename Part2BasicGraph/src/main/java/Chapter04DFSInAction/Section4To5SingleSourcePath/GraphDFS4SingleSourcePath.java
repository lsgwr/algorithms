/***********************************************************
 * @Description : 1.深度优先遍历DFS用于计算连通分量的个数：实际就是在DFS递归的地方加一个计数器
 *                2.获取每个连通分量的具体元素：实际通过改造visited数组，用不同的整型值来代替原来的boolean值，最后统计不同的整型值分类即可
 *                3.单源路径问题：要计算Path(v, w),选择(v, w)中的一个顶作为DFS深度优先遍历的起点，用Previous[]记录下起点到所有顶点的路径，肯定有到w的
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2019-08-07 22:05
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter04DFSInAction.Section4To5SingleSourcePath;

import Chapter02GraphExpress.Graph;

import java.util.*;

public class GraphDFS4SingleSourcePath {
    private Graph graph;


    /**
     * 存储顶点是否被访问的数组
     */
    private int[] visited;


    /**
     * 存储连通分量具体内容的Map
     */
    private Map<Integer, List<Integer>> connectedComponentsMap = new TreeMap<>();

    /**
     * 存放图的深度优先遍历的结果
     */
    private List<Integer> orderList = new ArrayList<>();


    /**
     * 连通分量计数器
     */
    private int connectedComponentCount;

    public GraphDFS4SingleSourcePath(Graph graph) {
        this.graph = graph;
        // 初始化访问数组，用图的顶点个数来访问
        visited = new int[graph.V()];
        // 数组初始化为-1
        Arrays.fill(visited, -1);
        // 从dfs(0)改成下面的代码，可以支持非连通的图
        for (int v = 0; v < graph.V(); v++) { // 等于-1表示还没被访问过
            if (visited[v] == -1) {
                // 第二个参数表示当前连通分量的标志(多个连通分量内的元素在visited内用connectedComponentCount这个值进行标记)
                dfs(v, connectedComponentCount);
                // 当退出递归时，相当于结束了一个连通图的遍历，所以连通分量数加1
                connectedComponentCount++;
            }
        }
    }

    /**
     * 判断v和w在图中是否是可以连接地
     */
    public boolean isConnected(int v, int w) {
        graph.validateVertex(v);
        graph.validateVertex(w);
        return visited[v] == visited[w];
    }

    public Iterable<Integer> getOrderList() {
        return orderList;
    }

    public int getConnectedComponentCount() {
        return connectedComponentCount;
    }

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

    /**
     * 深度优先遍历
     *
     * @param v    当前遍历到的顶点下标
     * @param ccid 当前连通分量的标记(同一个连通分量内的元素都在visited数组内用这个数值进行赋值标记)
     */
    private void dfs(int v, int ccid) {
        visited[v] = ccid;
        orderList.add(v);
        for (Integer w : graph.adj(v)) {
            if (visited[w] == -1) {
                // w点没被访问的话就递归接着访问
                dfs(w, ccid);
            }
        }
    }
}
