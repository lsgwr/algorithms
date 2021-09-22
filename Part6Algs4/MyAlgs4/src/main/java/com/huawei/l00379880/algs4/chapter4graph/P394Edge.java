/***********************************************************
 * @Description : 加权边的API类
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/2/21 下午10:09
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package com.huawei.l00379880.algs4.chapter4graph;

public class P394Edge implements Comparable<P394Edge> {
    private final int v;
    private final int w;
    private final double weight;

    public P394Edge(int v, int w, double weight) {
        if (v < 0) {
            throw new IllegalArgumentException("顶点v的坐标值必须大于0");
        }
        if (w < 0) {
            throw new IllegalArgumentException("顶点w的坐标值必须大于0");
        }
        if (Double.isNaN(weight)) {
            throw new IllegalArgumentException("weight 的值是NaN!不合法！");
        }
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }

    /**
     * Returns either endpoint of this edge.
     */
    public int either() {
        return v;
    }

    /**
     * 获取输入顶点的所在边的另一个顶点
     *
     * @param vertex 指定的顶点
     * @return 输入顶点的所在边的另一个顶点
     */
    public int other(int vertex) {
        if (vertex == v) {
            return w;
        } else if (vertex == w) {
            return v;
        } else {
            throw new IllegalArgumentException("非法的节点");
        }
    }

    @Override
    public int compareTo(P394Edge that) {

        return Double.compare(this.weight, that.weight);
    }

    @Override
    public String toString() {
        return String.format("%d-%d %.5f", v, w, weight);
    }
}
