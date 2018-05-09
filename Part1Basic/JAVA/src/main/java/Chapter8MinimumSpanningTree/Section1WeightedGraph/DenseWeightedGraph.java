/***********************************************************
 * @Description : 加权稠密图--邻接矩阵
 *                新增：遍历表的实现
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/5/9 07:52
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter8MinimumSpanningTree.Section1WeightedGraph;

import java.util.Vector;

public class DenseWeightedGraph<Weight extends Number & Comparable> implements WeightedGraph {
    /**
     * 图的顶点数
     */
    private int vertices;
    /**
     * 图的边数
     */
    private int edges;
    /**
     * 当前图是有向图还是无向图
     */
    private boolean directed;
    /**
     * 邻接矩阵，采用vector套vector的形式
     */
    private Edge<Weight>[][] adj;


    DenseWeightedGraph(int vertices, boolean directed) {
        this.vertices = vertices;
        this.edges = 0;
        this.directed = directed;
        // adj初始化为n*n的布尔矩阵，每一个g[i][j]均为false,表示没有任何边
        // false为boolean类型变量默认值
        adj = new Edge[vertices][vertices];
        // g初始化为vertices * vertices的布尔矩阵, 每一个g[i][j]均为null, 表示没有任和边
        for (int i = 0; i < vertices; i++) {
            for (int j = 0; j < vertices; j++) {
                adj[i][j] = null;
            }
        }
    }


    /**
     * 返回顶点数
     */
    @Override
    public int V() {
        return vertices;
    }

    /**
     * 返回边的数目
     */
    @Override
    public int E() {
        return edges;
    }

    /**
     * 添加边,在v和w之间建立一条边
     */
    @Override
    public void addEdge(Edge edge) {
        // 先确保元素不越界
        assert (edge.v() >= 0 && edge.v() < vertices);
        assert (edge.w() >= 0 && edge.w() < vertices);
        // v和w之间是连接地,不需要再加一次，就直接退出
        if (hasEdge(edge.v(), edge.w())) {
            return;
        }
        adj[edge.v()][edge.w()] = new Edge(edge);
        if (edge.v() != edge.w() && !directed) {
            // 无向图实际上是双向图，所以w到v也应该为true.如果是有向图这步就不用处理了
            // 注意这里new了一个Edge而不是直接传入edge，是因为要加入地是w-->v的边。上面是v->w的边
            adj[edge.w()][edge.v()] = new Edge(edge.w(), edge.v(), edge.wt());
        }
        // 边加1
        edges++;
    }

    /**
     * v和w之间是否存在边
     */
    @Override
    public boolean hasEdge(int v, int w) {
        // 先确保元素不越界
        assert (v >= 0 && v < vertices);
        assert (w >= 0 && w < vertices);
        return adj[v][w] != null;
    }

    @Override
    public void show() {
        for (int i = 0; i < vertices; i++) {
            for (int j = 0; j < vertices; j++) {
                if (adj[i][j] != null) {
                    System.out.print(adj[i][j].wt() + "\t");
                } else {
                    System.out.print("NULL\t");
                }
            }
            System.out.println();
        }
    }

    /**
     * 返回顶点v的所有临边
     * 由于java使用引用机制，返回一个vector不会带来额外开销
     */
    @Override
    public Iterable<Edge<Weight>> adj(int v) {
        assert (v >= 0 && v < vertices);
        Vector<Edge<Weight>> adjV = new Vector<>();
        for (int i = 0; i < vertices; i++) {
            // 邻接矩阵中为不为NULL表示v和i是相连地
            if (adj[v][i] != null) {
                adjV.add(adj[v][i]);
            }
        }
        return adjV;
    }
}
