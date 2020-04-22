/***********************************************************
 * @Description : 测试拿到所有的环
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/4/22
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package com.huawei.l00379880.Chapter04DFSInAction.S09环检测;

import com.huawei.l00379880.Chapter02GraphExpress.Graph;
import com.huawei.l00379880.Chapter02GraphExpress.ReadGraph;

public class Main {
    public static void main(String[] args) {
        String filepath = "graph.txt";
        Graph graph = new Graph(false);
        ReadGraph.init(graph, filepath);
        GraphDFS无向图环检测拿到所有简单环 graphDFS无向图环检测 = new GraphDFS无向图环检测拿到所有简单环(graph);
        System.out.println(graphDFS无向图环检测.getCycles());
        System.out.println();

        filepath = "graph2.txt";
        graph = new Graph(false);
        ReadGraph.init(graph, filepath);
        graphDFS无向图环检测 = new GraphDFS无向图环检测拿到所有简单环(graph);
        System.out.println(graphDFS无向图环检测.getCycles());
    }
}
/**
 * 顶点数V = 7, 边数E = 6
 * [[1, 2, 3, 1], [4, 5, 6, 4]]
 *
 * 顶点数V = 7, 边数E = 4
 * []
 */
