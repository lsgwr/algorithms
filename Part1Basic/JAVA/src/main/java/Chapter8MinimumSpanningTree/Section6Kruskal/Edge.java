/***********************************************************
 * @Description : 加权图的边
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/5/9 00:41
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter8MinimumSpanningTree.Section6Kruskal;

public class Edge<Weight extends Number & Comparable> implements Comparable<Edge<Weight>> {
    /**
     * 边的两个顶点
     */
    private int a, b;
    /**
     * 边的权值
     */
    private Weight weight;

    public Edge(Edge<Weight> edge) {
        this.a = edge.a;
        this.b = edge.b;
        this.weight = edge.weight;
    }

    public Edge(int a, int b, Weight weight) {
        this.a = a;
        this.b = b;
        this.weight = weight;
    }

    /**
     * 返回第一个顶点
     */
    public int v() {
        return a;
    }

    /**
     * 返回第二个顶点
     */
    public int w() {
        return b;
    }

    /**
     * 返回边的权重
     *
     * @return 权重对象
     */
    public Weight wt() {
        return weight;
    }

    /**
     * 给定一个顶点，返回另一个顶点
     *
     * @param x 要求相邻的点的点
     * @return 相邻的点
     */
    public int other(int x) {
        assert x == a || x == b;
        return x == a ? b : a;
    }

    @Override
    public String toString() {
        return a + "--" + b + ":" + weight;
    }

    @Override
    public int compareTo(Edge that) {
        return weight.compareTo(that.wt());
    }
}
