/********************************************************************************
 * @Description : 符号表图(前面的图的索引是整数0~V,这里通过键值对代替前面图中的数字图索引)
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/2/17 上午12:46
 * @email       : liangshanguang2@gmail.com
 *******************************************************************************/
package com.huawei.l00379880.algs4.chapter4graph;

import com.huawei.l00379880.algs4.chapter3search.P230ST;
import com.huawei.l00379880.mylib.file.In;

public class P356SymbolGraph {
    /**
     * 键：符号字符串, 值：原来图中的整数索引
     * string -> index
     */
    private P230ST<String, Integer> st;
    /**
     * 所有的键,数组下表其实就是原来的图中的整数索引。
     * index  -> string
     * 所以对SymbolGraph中的Graph处理后，通过
     * keys[i]即可获得Graph中节点i对应的字符串代表
     */
    private String[] keys;
    /**
     * 套一个原来的图
     */
    private P336Graph graph;

    /**
     * 初始化符号图
     *
     * @param filePath  用于描绘图的输入文件
     * @param delimiter 文件中用于分割单个节点的分隔符
     */
    public P356SymbolGraph(String filePath, String delimiter) {
        st = new P230ST<>();
        // First pass builds the index by reading strings to associate
        // distinct strings with an index
        In in = new In(filePath);
        // 只要还有输入
        while (!in.isEmpty()) {
            // 读入一个节点及其临界点的值
            String[] a = in.readLine().split(delimiter);
            for (int i = 0; i < a.length; i++) {
                if (!st.contains(a[i])) {
                    // 新增键值对，size实际就是从0~V递增地
                    st.put(a[i], st.size());
                }
            }
        }
        System.out.println("完成了图的读入！来源：" + filePath);
        // inverted index to get string keys in an array
        keys = new String[st.size()];
        for (String key : st.keys()) {
            keys[st.get(key)] = key;
        }

        // second pass builds the graph by connecting first vertex on each
        // line to all others
        // 初始化指定节点数的图
        graph = new P336Graph(st.size());
        // 前面读完了，这里得再读一次
        in = new In(filePath);
        while (in.hasNextLine()) {
            // 每一行代表点极其邻接点
            String[] a = in.readLine().split(delimiter);
            int v = st.get(a[0]);
            for (int i = 1; i < a.length; i++) {
                int w = st.get(a[i]);
                graph.addEdge(v, w);
            }
        }
    }

    public boolean contains(String s) {
        return st.contains(s);
    }

    /**
     * 根据键(字符串)获取值(整数),这里的值即为图中的节点索引，所以为index
     */
    public int indexOf(String s) {
        return st.get(s);
    }

    /**
     * 根据值(整型)获取键(字符串)，这里的含义是根据图中的节点索引(st的值)获取节点名(st的键)
     */
    public String nameOf(int v) {
        validateVertex(v);
        return keys[v];
    }

    public P336Graph getGraph() {
        return graph;
    }

    public String[] getKeys() {
        return keys;
    }

    /**
     * 校验顶点是否合法
     *
     * @param v 指定顶点
     */
    private void validateVertex(int v) {
        int V = graph.getV();
        if (v < 0 || v >= V) {
            throw new IllegalArgumentException("顶点编号:" + v + "应该在0~" + V + "之间");
        }
    }
}
