/***********************************************************
 * @Description : 最小生成树的Lazy Prim算法
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/5/11 00:22
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter8MinimumSpanningTree.Section6Kruskal;

import java.util.Vector;

public class LazyPrimMST<Weight extends Number & Comparable> {
    private WeightedGraph<Weight> graph;
    /**
     * pq:priority queue 优先队列，其实就是最x堆
     */
    private MinHeap<Edge<Weight>> pq;
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

    LazyPrimMST(WeightedGraph<Weight> graph) {
        this.graph = graph;
        pq = new MinHeap<>(graph.E());
        // 根据顶点数确定marked的长度
        marked = new boolean[graph.V()];
        mst = new Vector<>();
        //*************** lazy的核心算法 ******************
        // 初始化时从第一个节点开始访问
        visit(0);
        // 优先队列(最小堆)不为空，取出最小值
        while (!pq.isEmpty()) {
            Edge<Weight> edge = pq.popMin();
            if (marked[edge.v()] == marked[edge.w()]) {
                // 弹出来的最小边的两个顶点都已经被访问过了(现在肯定都在横切边的左侧或右侧了)，不能用切分定理了，直接跳过这个点
                continue;
            }
            // 当是横切边的情况下，弹出的最小值一定是最小生成树v-1条边中的一条了，加入到mst中
            mst.add(edge);
            // 接着把沿着edge把没访问的点访问起来，邻切边的两个顶点肯定有一个没被访问过
            if (!marked[edge.v()]) {
                visit(edge.v());
            } else {
                visit(edge.w());
            }
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
        for (Edge<Weight> edge : graph.adj(v)) {
            if (!marked[edge.other(v)]) {
                // v的没被访问过的邻边(即切分定理中的横切边)加入到最小堆中
                pq.insert(edge);
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
}
