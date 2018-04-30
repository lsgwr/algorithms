/***********************************************************
 * @Description : 稀疏图---邻接表,
 *                新增：遍历表的实现
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/4/30 13:03
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter7GraphBasics.Section4ReadGraph;

import java.util.Vector;

public class SparseGraph implements Graph {
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
     * 邻接表，采用vector套vector的形式
     */
    private Vector<Integer>[] adj;

    SparseGraph(int vertices, boolean directed) {
        this.vertices = vertices;
        this.edges = 0;
        this.directed = directed;
        adj = (Vector<Integer>[]) new Vector[vertices];
        for (int i = 0; i < vertices; i++) {
            adj[i] = new Vector<>();
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
    public void addEdge(int v, int w) {
        // 先确保元素不越界
        assert (v >= 0 && v < vertices);
        assert (w >= 0 && w < vertices);
        // v和w之间是连接地,不需要再加一次，就直接退出。这样是为了防止平行边，但是从hasEdge实现可知成本较高，所以就by了
        // 平行边可以在所有边加完之后统一去掉，自己实现
        // if (hasEdge(v, w)) {
        //    return;
        // }
        adj[v].add(w);
        // v=w会生成自环边
        if (v != w && !directed) {
            // 无向图实际上是双向图，所以w到v也应该为true.如果是有向图这步就不用处理了
            adj[w].add(v);
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
        for (int i = 0; i < adj[v].size(); ++i) {
            // 判断是否v到w之间存在边
            if (adj[v].elementAt(i) == w) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void show() {
        for (int i = 0; i < vertices; i++) {
            System.out.print("vertex " + i + ":\t");
            for (int j = 0; j < adj[i].size(); j++) {
                System.out.print(adj[i].elementAt(j) + "\t");
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
        // 邻接表本身v处就是表达地v的所有邻接点
        return adj[v];
    }
}
