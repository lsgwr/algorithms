/***********************************************************
 * @Description : 最小生成树MST(Minimum Spanning Tree)的Kruskal算法实现
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2019/12/22 20:10
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter11WeightedGraphAndMinimumSpanningTree.Section7to9Prim;

import Chapter11WeightedGraphAndMinimumSpanningTree.Section1To2WeightedGraph.WeightedGraph;
import Chapter11WeightedGraphAndMinimumSpanningTree.Section3to5Kruskal.GraphDFS4ConnectedComponents;
import Chapter11WeightedGraphAndMinimumSpanningTree.Section3to5Kruskal.UnionFind;
import Chapter11WeightedGraphAndMinimumSpanningTree.Section3to5Kruskal.WeightedEdge;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MinimumSpanningTreePrim {
    private WeightedGraph graph;

    /**
     * 最小生成树的顶点集合，按加入顺序来的，一定不要自己排序
     */
    private List<WeightedEdge> mst = new ArrayList<>();

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
        int vertices = graph.V();
        visited[0] = true;
        // 最终的最小生成树一共v-1条边，所以下标从1开始
        for (int i = 1; i < vertices; i++) {
            WeightedEdge minEdge = new WeightedEdge(-1, -1, Integer.MAX_VALUE);
            for (int v = 0; v < vertices; v++) {
                // 不断扩充切分
                if (visited[v]) {
                    for (int w : graph.adj(v)) {
                        // !visited[w]表示边v-w是横切边
                        if (!visited[w] && graph.getWeight(v, w) < minEdge.getWeight()) {
                            // 不断更新到最小的横切边
                            minEdge = new WeightedEdge(v, w, graph.getWeight(v, w));
                        }
                    }
                }
            }
            mst.add(minEdge);
            // 更新顶点访问状态，true表示已经加入到最小生成树了，后面找横切边就不会遍历到对应的边了
            visited[minEdge.getV()] = true;
            visited[minEdge.getW()] = true;
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
