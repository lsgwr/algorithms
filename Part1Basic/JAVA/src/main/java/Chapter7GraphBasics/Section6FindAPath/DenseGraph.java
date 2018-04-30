/***********************************************************
 * @Description : 稠密图--邻接矩阵
 *                新增：遍历表的实现
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/4/30 13:04
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter7GraphBasics.Section6FindAPath;

import java.util.Vector;

public class DenseGraph implements Graph {
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
    private boolean[][] adj;


    DenseGraph(int vertices, boolean directed) {
        this.vertices = vertices;
        this.edges = 0;
        this.directed = directed;
        // adj初始化为n*n的布尔矩阵，每一个g[i][j]均为false,表示没有任何边
        // false为boolean类型变量默认值
        adj = new boolean[vertices][vertices];
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
    public void addEdge(int v, int w) {
        // 先确保元素不越界
        assert (v >= 0 && v < vertices);
        assert (w >= 0 && w < vertices);
        // v和w之间是连接地,不需要再加一次，就直接退出
        if (hasEdge(v, w)) {
            return;
        }
        adj[v][w] = true;
        if (!directed) {
            // 无向图实际上是双向图，所以w到v也应该为true.如果是有向图这步就不用处理了
            adj[w][v] = true;
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
        return adj[v][w];
    }

    @Override
    public void show() {
        for (int i = 0; i < vertices; i++) {
            for (int j = 0; j < vertices; j++) {
                System.out.print(adj[i][j] + "\t");
            }
            System.out.println();
        }
    }

    /**
     * 返回顶点v的所有临边
     * 由于java使用引用机制，返回一个vector不会带来额外开销
     */
    @Override
    public Iterable<Integer> adj(int v) {
        assert (v >= 0 && v < vertices);
        Vector<Integer> adjV = new Vector<>();
        for (int i = 0; i < vertices; i++) {
            // 邻接矩阵中为true表示v和i是相连地
            if (adj[v][i]) {
                adjV.add(i);
            }
        }
        return adjV;
    }
}
