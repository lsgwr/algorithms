/***********************************************************
 * @Description : Kruskal算法的实现
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/5/12 17:01
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter8MinimumSpanningTree.Section6Kruskal;

import java.util.Vector;

public class KruskalMST<Weight extends Number & Comparable> {
    private Vector<Edge<Weight>> mst;
    private Number mstWeight;

    KruskalMST(WeightedGraph graph) {
        mst = new Vector<>();
        // 先对所有的节点采用堆排序
        MinHeap<Edge<Weight>> pq = new MinHeap<>(graph.E());
        for (int i = 0; i < graph.V(); i++) {
            // 迭代器，迭代i所对应的临边
            for (Object item : graph.adj(i)) {
                Edge<Weight> edge = (Edge<Weight>) item;
                // 注意无向图一条边会被循环两次，为了避免重复插入,所以额外加一个判断。把edge的两个顶点v和w满足v<w的加入到最小堆中
                if (edge.v() < edge.w()) {
                    pq.insert(edge);
                }
            }
        }
        // 构造并查集
        UnionFind uf = new UnionFind(graph.V());
        // 下面是Kruskal算的核心：不断遍历当前所有边中的最小边(minHeap来不断pop),如果这条边加入后会形成环
        // (利用并查集的原理：边的两个短点在并查集中有一个root,则加入这条边一定会形成环)，就跳过，否则加加入mst
        while (!pq.isEmpty() && mst.size() < graph.V() - 1) {
            // pq不为空而且mst中的边的条数还没收集到v-1条(最小生成树在v个顶点的图中一定要有v-1个点)，就继续找
            Edge<Weight> edge = pq.popMin();
            if (uf.isConnected(edge.v(), edge.w())) {
                continue;
            }
            mst.add(edge);
            // 没连接过就Union一下
            uf.unionElements(edge.v(), edge.w());
        }
        // 计算最小生成树的权值
        mstWeight = mst.elementAt(0).wt();
        for (int i = 1; i < mst.size(); i++) {
            mstWeight = mstWeight.doubleValue() + mst.elementAt(i).wt().doubleValue();
        }
    }


    /**
     * 获取最小生成树的所有的边
     */
    Vector<Edge<Weight>> mstEdges() {
        return mst;
    }

    /**
     * 获取最小生成树的总权重
     */
    Number result() {
        return mstWeight;
    }

    public static void main(String[] args) {
        String filename = "/Users/liangshanguang/Program/Algorithm/liuyubobobo-algorithms/Part1Basic/JAVA/src/main/java/Chapter8MinimumSpanningTree/Section6Kruskal/graph1.txt";
        int V = 8;

        SparseWeightedGraph<Double> g = new SparseWeightedGraph<Double>(V, false);
        ReadWeightedGraph readGraph = new ReadWeightedGraph(g, filename);
        g.show();
        // Test Prim MST
        System.out.println("Test Kruskal MST:");
        KruskalMST<Double> kruskalMST = new KruskalMST<>(g);
        Vector<Edge<Double>> mst = kruskalMST.mstEdges();
        for( int i = 0 ; i < mst.size() ; i ++ ) {
            System.out.println(mst.elementAt(i));
        }
        System.out.println("The MST weight is: " + kruskalMST.result());

        System.out.println();
    }
}
