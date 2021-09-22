/***********************************************************
 * @Description : 加权有向边.有向边无序比较边的大小，
 *              所以此处无序继承Comparable并实现CompareTo方法了
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/2/23 上午12:02
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package com.huawei.l00379880.algs4.chapter4graph;

public class P415DirectedEdge {
    private final int v;
    private final int w;
    private final double weight;

    public P415DirectedEdge(int v, int w, double weight) {
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

    public int from() {
        return v;
    }

    public int to() {
        return w;
    }

    public double getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return v + "-->" + w + " " + String.format("%5.2f", weight);
    }
}
