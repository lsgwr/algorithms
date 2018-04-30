/***********************************************************
 * @Description : 统一接口
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/4/30 13:58
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter7GraphBasics.Section5ConnectedConponents;

public interface Graph {
    int V();

    int E();

    void addEdge(int v, int w);

    boolean hasEdge(int v, int w);

    void show();

    Iterable<Integer> adj(int v);
}
