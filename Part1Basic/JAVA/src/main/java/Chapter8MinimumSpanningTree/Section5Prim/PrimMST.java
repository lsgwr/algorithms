/***********************************************************
 * @Description : 最小生成树的Prim算法
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/5/12 14:30
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter8MinimumSpanningTree.Section5Prim;

import java.util.Vector;

public class PrimMST<Weight extends Number & Comparable> {
    private WeightedGraph graph;
    /**
     * pq:priority queue 优先队列，其实就是最x堆
     */
    private IndexMinHeap<Weight> pq;
    /**
     * 访问的点所对应的当前最小横切边
     */
    private Edge<Weight>[] edgeTo;
    /**
     * 用于标记各个元素是否已经被标记，被标记了说明已经被划分到了另外一个分割区
     */
    private boolean[] marked;
    /**
     * 存储组成最小生成树的v-1条边
     */
    private Vector<Edge<Weight>> mst;
    /**
     * 最小生成树的权值.一般是double类型(这里写成NUmber是因为double、int 、flot都继承自Number类型)
     */
    private Number mstWeight;

    PrimMST(WeightedGraph graph) {
        this.graph = graph;
        // Prim算法的核心，就是这里把时间复杂度从O(ElogE)将为了(ElogV)
        pq = new IndexMinHeap<>(graph.V());
        // 根据顶点数确定marked的长度
        marked = new boolean[graph.V()];
        edgeTo = new Edge[graph.V()];
        // 初始化数组
        for (int i = 0; i < graph.V(); i++) {
            marked[i] = false;
            edgeTo[i] = null;
        }
        mst = new Vector<>();
        //*************** 不 lazy的核心算法 ******************
        // 初始化时从第一个节点开始访问
        visit(0);
        // 优先队列(最小堆)不为空，取出最小值
        while (!pq.isEmpty()) {
            int v = pq.popMinIndex();
            // 验证下v有横切边
            assert (edgeTo[v] != null);
            // 当是横切边的情况下，弹出的最小值一定是最小生成树v-1条边中的一条了，加入到mst中
            mst.add(edgeTo[v]);
            visit(v);
        }

        // 计算最小生成树的权值
        mstWeight = mst.elementAt(0).wt();
        for (int i = 1; i < mst.size(); i++) {
            mstWeight = mstWeight.doubleValue() + mst.elementAt(i).wt().doubleValue();
        }
    }

    /**
     * 遍历横切边
     *
     * @param v 加入地药访问的点
     */
    void visit(int v) {
        // 开始先保证v没有被访问过
        assert (!marked[v]);
        marked[v] = true;
        // 将和节点v相连接的未访问的另一端点，和与之相连接的边，放入最小堆中.注意泛型数组需要强制转换
        for (Object item : graph.adj(v)) {
            Edge<Weight> edge = (Edge<Weight>) item;
            // v在edge边上的另一端的顶点
            int w = edge.other(v);
            // 访问过的都会标成红色，代表已经访问.我们需要未被访问过地
            if (!marked[w]) {
                // 如果顶点在求最小横切边的时候被访问过，说明pq中w位置的元素已经存在了，需要更新(update)，否则需要insert
                if (edgeTo[w] == null) {
                    // 如果这个边从来没被访问过的话
                    pq.insert(edge.wt(), w);
                    // 标记下，和w相连地边有了，就是edge
                    edgeTo[w] = edge;

                } else if (edge.wt().compareTo(edgeTo[w].wt()) < 0) {
                    // 之前标记过而且新的全小于原来的权重就更新
                    pq.update(w, edge.wt());
                    edgeTo[w] = edge;
                }
            }
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
        String filename = "/Users/liangshanguang/Program/Algorithm/liuyubobobo-algorithms/Part1Basic/JAVA/src/main/java/Chapter8MinimumSpanningTree/Section5Prim/graph1.txt";
        int V = 8;

        SparseWeightedGraph<Double> g = new SparseWeightedGraph<Double>(V, false);
        ReadWeightedGraph readGraph = new ReadWeightedGraph(g, filename);
        g.show();
        // Test Prim MST
        System.out.println("Test Prim MST:");
        PrimMST<Double> primMST = new PrimMST<Double>(g);
        Vector<Edge<Double>> mst = primMST.mstEdges();
        for( int i = 0 ; i < mst.size() ; i ++ ) {
            System.out.println(mst.elementAt(i));
        }
        System.out.println("The MST weight is: " + primMST.result());

        System.out.println();
    }
}
