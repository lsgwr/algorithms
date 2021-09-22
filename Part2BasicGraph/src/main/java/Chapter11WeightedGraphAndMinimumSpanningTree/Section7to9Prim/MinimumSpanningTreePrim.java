/***********************************************************
 * @Description : 最小生成树MST(Minimum Spanning Tree)的Prim算法实现
 *                基于有限队列(最小堆)来实现
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2019/12/22 22:22
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter11WeightedGraphAndMinimumSpanningTree.Section7to9Prim;

import Chapter11WeightedGraphAndMinimumSpanningTree.Section1To2WeightedGraph.WeightedGraph;
import Chapter11WeightedGraphAndMinimumSpanningTree.Section3to5Kruskal.GraphDFS4ConnectedComponents;
import Chapter11WeightedGraphAndMinimumSpanningTree.Section3to5Kruskal.WeightedEdge;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class MinimumSpanningTreePrim {
    private WeightedGraph graph;

    /**
     * 最小生成树的顶点集合，按加入顺序来的，一定不要自己排序
     */
    private List<WeightedEdge> mst = new ArrayList<>();
    /**
     * 遍历的起始点
     */
    private int start = 0;

    public MinimumSpanningTreePrim(WeightedGraph graph) {
        this.graph = graph;
        // 判断联通分量个数的
        GraphDFS4ConnectedComponents cc = new GraphDFS4ConnectedComponents(graph);
        if (cc.getConnectedComponentCount() > 1) {
            // 联通分量只能有1个，多余1个则没有最小生成树
            return;
        }
        prim();
    }

    public void prim() {
        boolean[] visited = new boolean[graph.V()];
        // 初始化时进行(1, V-1)的prim计算
        visited[start] = true;
        // 存储边的有限队列(基于最小堆实现)
        Queue<WeightedEdge> pq = new PriorityQueue<>();
        // 初始时所有0的邻接边都是切分边
        for (int w : graph.adj(start)) {
            pq.add(new WeightedEdge(start, w, graph.getWeight(start, w)));
        }
        // 不算找最小横切边加入到mst中
        while (!pq.isEmpty()){
            // pq为表示所有的点都被考虑过了
            WeightedEdge minEdge = pq.remove();
            // 判断是否是合法的横切边
            if (visited[minEdge.getV()] && visited[minEdge.getW()]){
                continue;
            }
            mst.add(minEdge);
            // 最小生成树多了一条边，需要扩展切分
            int vNew = visited[minEdge.getV()]?minEdge.getW():minEdge.getV();
            visited[vNew] = true;
            for (int w : graph.adj(vNew)) {
                if (!visited[w]){
                    pq.add(new WeightedEdge(vNew, w, graph.getWeight(vNew, w)));
                }
            }
        }
    }

    /**
     * 获取最小生成树
     *
     * @return 最小生成树列表
     */
    public List<WeightedEdge> getMst() {
        return mst;
    }
}
