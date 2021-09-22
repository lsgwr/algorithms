/***********************************************************
 * @Description : 统一接口
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/5/9 07:49
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter8MinimumSpanningTree.Section6Kruskal;

public interface WeightedGraph<Weight extends Number & Comparable> {
    int V();

    int E();

    void addEdge(Edge<Weight> edge);

    boolean hasEdge(int v, int w);

    void show();

    Iterable<Edge<Weight>> adj(int v);
}
