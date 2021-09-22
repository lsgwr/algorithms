/***********************************************************
 * @Description : 图中的边的表示
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2019/12/19 19:11
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter08BridgesAndCutPoints.Section1To4Bridges;

public class Edge {
    private int v;
    private int w;

    public Edge(int v, int w) {
        this.v = v;
        this.w = w;
    }

    @Override
    public String toString() {
        return +v + "-" + w;
    }
}
