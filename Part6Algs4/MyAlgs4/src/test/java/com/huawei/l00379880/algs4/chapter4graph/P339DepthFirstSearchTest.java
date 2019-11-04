package com.huawei.l00379880.algs4.chapter4graph;

import com.huawei.l00379880.mylib.file.In;
import org.junit.Test;

/**
 * P339DepthFirstSearch Tester.
 *
 * @author liang shan guang
 * @datetime 02/14/2018
 * @email liangshanguang2@gmail.com
 * @description
 */
public class P339DepthFirstSearchTest {
    /**
     * Description: 见P338上面的两个图
     */
    @Test
    public void testMain() {
        // 文件里每一行存储了一条边的两个顶点
        String filePath = "D:\\l00379880\\GithubProjects\\algs4-data\\tinyG.txt";
        In in = new In(filePath);
        P336Graph graph = new P336Graph(in);
        // source、v、w都是顶点的标号范围为[0,V)
        int source = 9;
        P339DepthFirstSearch dfs = new P339DepthFirstSearch(graph, source);
        System.out.print("source:" + source + ", vertexes connected to source:");
        int V = graph.getV();
        for (int v = 0; v < V; v++) {
            if (dfs.hasPathTo(v)) {
                System.out.print(v + "  ");
            }
        }
        System.out.println();
        System.out.print("判断是否是连通图:");
        // 连通的点数count已经算上source点了
        if (dfs.getCount() != graph.getV()) {
            System.out.println("false");
        } else {
            System.out.println("true");
        }
    }
} 
