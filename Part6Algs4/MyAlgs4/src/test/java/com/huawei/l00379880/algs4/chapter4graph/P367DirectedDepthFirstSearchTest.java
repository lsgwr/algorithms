package com.huawei.l00379880.algs4.chapter4graph;

import com.huawei.l00379880.mylib.file.In;
import org.junit.Test;

/**
 * P367DirectedDepthFirstSearch Tester.
 *
 * @author liangshanguang
 * @date 02/19/2018
 * @description test
 */
public class P367DirectedDepthFirstSearchTest {

    @Test
    public void testMain() {
        // 文件里每一行存储了一条边的两个顶点
        String filePath = "/Users/liangshanguang/Program/Algorithm/算法第四版资料/algs4-data/tinyDG.txt";
        In in = new In(filePath);
        P366Digraph digraph = new P366Digraph(in);
        // source、v、w都是顶点的标号范围为[0,V)
        int source = 2;
        P367DirectedDepthFirstSearch dfs = new P367DirectedDepthFirstSearch(digraph, source);
        // 这里的连接上是指出度中的线，入度的连上不算
        System.out.print("source:" + source + ", vertexes connected to source:");
        int V = digraph.getV();
        for (int v = 0; v < V; v++) {
            if (dfs.hasPathTo(v)) {
                System.out.print(v + "  ");
            }
        }
        System.out.println();
        System.out.println("入度：" + digraph.getIndegree(source));
        System.out.println("出度：" + digraph.getOutdegree(source));
        System.out.print("判断是否是连通图:");
        // 连通的点数count已经算上source点了
        if (dfs.getCount() != digraph.getV()) {
            System.out.println("false");
        } else {
            System.out.println("true");
        }
    }
} 
