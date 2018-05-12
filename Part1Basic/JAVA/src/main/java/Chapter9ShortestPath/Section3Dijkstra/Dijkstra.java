/***********************************************************
 * @Description : 最短路径的Dijkstra算法，注意只能求没有负权边的图
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/5/12 22:00
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter9ShortestPath.Section3Dijkstra;

import java.util.Stack;
import java.util.Vector;

public class Dijkstra<Weight extends Number & Comparable> {
    private WeightedGraph graph;
    /**
     * 起点
     */
    private int s;
    private Number[] distTo;
    /**
     * 找到最短距离的点
     */
    private boolean[] marked;
    /**
     * 记录到大当前顶点的前一个顶点
     */
    private Edge<Weight>[] from;

    public Dijkstra(WeightedGraph graph, int s) {
        this.graph = graph;
        this.s = s;
        distTo = new Number[graph.V()];
        marked = new boolean[graph.V()];
        from = new Edge[graph.V()];
        for (int i = 0; i < graph.V(); i++) {
            distTo[i] = 0.0; // 默认构造函数
            marked[i] = false;  // 上来默认都没被访问过
            from[i] = null; // 起点s没有前一个起点，所以为NULL
        }
        // 初始化最小索引堆
        IndexMinHeap<Weight> pq = new IndexMinHeap<>(graph.V());

        /***** Dijkstra算法的核心实现 *******/
        // s到s的距离为Weight()的默认初始值
        distTo[s] = 0.0;
        // 初始化s->s的边，比较特殊
        from[s] = new Edge<>(s, s, (Weight) (Number) (0.0));
        // 第一个顶点标记下
        marked[s] = true;
        pq.insert((Weight) distTo[s], s);
        while (!pq.isEmpty()) {
            // 弹出s到所有邻接边中最小的一条所对应的顶点v.v为每一轮的开始点
            int v = pq.popMinIndex();
            // distTo[v]就是s到v的最短距离
            marked[v] = true;
            // 迭代器，迭代i所对应的临边
            for (Object item : graph.adj(v)) {
                Edge<Weight> edge = (Edge<Weight>) item;
                // 获取临边上和v连接的顶点
                int w = edge.other(v);
                // 如果没被标记为已经找到最短距离
                if (!marked[w]) {
                    // 如果s->w的距离没有赋过值或者赋过值但是值大于松弛后的值(加一个中转点后的值),那么distTo(w)就需要更新下
                    if (from[w] == null || distTo[v].doubleValue() + edge.wt().doubleValue() < distTo[w].doubleValue()) {
                        distTo[w] = distTo[v].doubleValue() + edge.wt().doubleValue();
                        // w往前数的第一条边
                        from[w] = edge;
                        if (pq.contain(w)) {
                            // 索引有点队列，更新每个点的对应的最短距离
                            pq.update(w, (Weight) distTo[w]);
                        } else {
                            // 没有计算过最短距离地，第一次要插入下
                            pq.insert((Weight) distTo[w], w);
                        }
                    }
                }
            }

        }
    }

    /**
     * 从起点s到w的最短路径大小
     */
    Number shortestPathTo(int w) {
        // w的必须小于总得节点数(w相当于节点在数组中的下标)
        assert (w >= 0 && w < graph.V());
        // s到w之间有路径
        assert (hasPathTo(w));
        return distTo[w];
    }

    /**
     * s到w之间是否有路径
     */
    boolean hasPathTo(int w) {
        // w的必须小于总得节点数(w相当于节点在数组中的下标)
        assert (w >= 0 && w < graph.V());
        // s到w之间有路径
        return marked[w];
    }

    /**
     * s->w的最短路径上的所有边，从from一各个往回找
     */
    Vector<Edge<Weight>> shortestPath(int w) {

        // w的必须小于总得节点数(w相当于节点在数组中的下标)
        assert (w >= 0 && w < graph.V());
        // s到w之间有路径
        assert (hasPathTo(w));
        Stack<Edge<Weight>> edgeStack = new Stack<>();
        // 找到w的上一个顶点
        Edge<Weight> edge = from[w];
        // 还没找到头(起点s)
        while (edge.v() != this.s) {
            // 把当前边加入栈
            edgeStack.push(edge);
            // 接着往前找一条边
            edge = from[edge.v()];
        }
        // 最后反推到第一个点(一般都是起点s，s也要加入到最短路径中)
        edgeStack.push(edge);
        Vector<Edge<Weight>> vec = new Vector<>();
        // 逆向从栈中输出路径
        while (!edgeStack.empty()) {
            edge = edgeStack.pop();
            vec.add(edge);
        }
        return vec;
    }

    /**
     * 完整显示s->w的完整路径
     */
    void showPath(int w) {
        // w的必须小于总得节点数(w相当于节点在数组中的下标)
        assert (w >= 0 && w < graph.V());
        // s到w之间有路径
        assert (hasPathTo(w));
        // 计算s->w之间的最短路径
        Vector<Edge<Weight>> path = shortestPath(w);
        for (int i = 0; i < path.size(); i++) {
            System.out.print(path.elementAt(i).v() + "->");
            // 最后一个顶点输出格式要变一下，前面输最后一个边的v点了，这里再输出下最后一条边的另一个点w
            if (i == path.size() - 1) {
                System.out.println(path.elementAt(i).w());
            }
        }
    }


}
