/***********************************************************
 * @Description : 图的最优表示(基于TreeSet的邻接表)，后面所有的图的操作都会基于这个去讲
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/08/02 07:53
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter7GraphBasics.Graph;
import java.util.TreeSet;

public class Graph implements IGraph {
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
    private TreeSet<Integer>[] adj;

    public Graph(int vertices, boolean directed) {
        this.vertices = vertices;
        this.edges = 0;
        this.directed = directed;
        // 泛型数组需要强制转换，可以认为是Java语言的缺陷
        adj = (TreeSet<Integer>[]) new TreeSet[vertices];
        for (int i = 0; i < vertices; i++) {
            adj[i] = new TreeSet<>();
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

    @Override
    public void validateVertex(int v) {
        assert (v >= 0 && v < vertices);
    }

    /**
     * 添加边,在v和w之间建立一条边
     */
    @Override
    public void addEdge(int v, int w) {
        // 先确保元素不越界
        validateVertex(v);
        validateVertex(w);
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
        validateVertex(v);
        validateVertex(w);
        // v的邻接表中是否有w
        return adj[v].contains(w);
    }

    @Override
    public int degree(int v) {
        return adj[v].size();
    }

    @Override
    public void show() {

        // 遍历所有顶点vertex(顶点都是按照编号顺序来地)
        for (int v = 0; v < vertices; v++) {
            System.out.print("vertex " + v + ":\t");
            // 遍历顶点vertex的所有邻接点
            for (Integer w : adj[v]) {
                System.out.print(w + "\t");
            }
            System.out.println();
        }
    }


    /**
     * 返回顶点v的所有临边
     * 由于java使用引用机制，返回一个Iterable对象不会带来额外开销,TreeSet、Vector、HashSet等都实现了Iterable接口
     */
    @Override
    public Iterable<Integer> adj(int v) {
        validateVertex(v);
        // 邻接表本身v处就是表达地v的所有邻接点
        return adj[v];
    }
}
