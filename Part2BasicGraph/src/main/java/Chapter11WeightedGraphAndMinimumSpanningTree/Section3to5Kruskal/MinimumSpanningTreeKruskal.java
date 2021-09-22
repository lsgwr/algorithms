/***********************************************************
 * @Description : 最小生成树MST(Minimum Spanning Tree)的Kruskal算法实现
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2019/12/22 20:10
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter11WeightedGraphAndMinimumSpanningTree.Section3to5Kruskal;

import Chapter11WeightedGraphAndMinimumSpanningTree.Section1To2WeightedGraph.WeightedGraph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MinimumSpanningTreeKruskal {
    private WeightedGraph graph;

    /**
     * 最小生成树的顶点集合，按加入顺序来的，一定不要自己排序
     */
    private List<WeightedEdge> mst = new ArrayList<>();

    public MinimumSpanningTreeKruskal(WeightedGraph graph) {
        this.graph = graph;
        // 判断联通分量个数的
        GraphDFS4ConnectedComponents cc = new GraphDFS4ConnectedComponents(graph);
        if (cc.getConnectedComponentCount() > 1) {
            // 联通分量只能有1个，多余1个则没有最小生成树
            return;
        }
        kruskal();
    }

    public void kruskal(){
        // Kruskal算法核心
        // 1.获取所有的边并进行排序
        List<WeightedEdge> edges = new ArrayList<>();
        for (int v = 0; v < graph.V(); v++) {
            for (int w : graph.adj(v)) {
                if (v<w){
                    // v < w是为了防止无向图中一条边被遍历两次
                    edges.add(new WeightedEdge(v, w, graph.getWeight(v, w)));
                }
            }
        }
        // edges排序，为了能排序必须自己实现Comparable接口
        Collections.sort(edges);
        // 2.取排序后权值最小的边，并检查和已有的边是否生成环，如果没有生成环，则把边加入到mst中
        // 判断是否有环，用到了并查集，参考Part2Basic/Chapter11UnionFind
        UnionFind uf = new UnionFind(graph.V());
        // 因为edges已经从小到大排好序了，所以挨个取就好。
        for (WeightedEdge edge : edges) {
            int v = edge.getV();
            int w = edge.getW();
            // 新加入的两个顶点之前不联通，则可以加入到mst中
            if (!uf.isConnected(v, w)){
                mst.add(edge);
                // v和w不连通就设置为联通
                uf.unionElements(v, w);
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
