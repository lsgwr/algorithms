/***********************************************************
 * @Description : 无向有权图的边的表示
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2019/12/22 20:13
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter11WeightedGraphAndMinimumSpanningTree.Section3to5Kruskal;

public class WeightedEdge implements Comparable<WeightedEdge> {
    /**
     * 顶点
     */
    private int v;
    /**
     * 顶点
     */
    private int w;
    /**
     * 边v-w的权重
     */
    private int weight;

    public WeightedEdge(int v, int w, int weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return String.format("(%d-%d: %d)", v, w, weight);
    }

    @Override
    public int compareTo(WeightedEdge that) {
        return this.weight - that.weight;
    }

    public int getV() {
        return v;
    }

    public int getW() {
        return w;
    }
}
