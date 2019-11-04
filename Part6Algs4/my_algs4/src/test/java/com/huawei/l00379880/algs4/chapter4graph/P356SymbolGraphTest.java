package com.huawei.l00379880.algs4.chapter4graph;

import com.huawei.l00379880.algs4.chapter1javabasic.P95LinkedListQueue;
import org.junit.Test;

/**
 * P356SymbolGraph Tester.
 * 图见P353最上面
 *
 * @author liangshanguang
 * @date 02/17/2018
 * @description test
 */
public class P356SymbolGraphTest {

    @Test
    public void testMain() {
        // 文件里每一行存储了一条边的两个顶点
        String filePath = "/Users/liangshanguang/Program/Algorithm/算法第四版资料/algs4-data/routes.txt";
        String delimeter = " ";
        P356SymbolGraph symbolGraph = new P356SymbolGraph(filePath, delimeter);
        P336Graph graph = symbolGraph.getGraph();
        String[] keys = symbolGraph.getKeys();
        // 1.打印出所有的邻接点
        for (String nodeName : keys) {
            if (symbolGraph.contains(nodeName)) {
                System.out.print(nodeName + "的邻接点为：");
                int nodeIndex = symbolGraph.indexOf(nodeName);
                // 通过indexOf和nameOf函数就可以完美地完成图的索引改成字符串的需求
                for (int v : graph.adj(nodeIndex)) {
                    System.out.print(symbolGraph.nameOf(v) + " ");
                }
                System.out.println();
            } else {
                System.out.println("图中不含有节点：" + nodeName);
            }
        }
        // 2.判断图中是否有环
        P352Cycle cycleFinder = new P352Cycle(graph);
        if (cycleFinder.hasCycle()) {
            System.out.println("发现环啦！！！");
            for (int v : cycleFinder.getCycle()) {
                System.out.print(symbolGraph.nameOf(v) + " ");
            }
            System.out.println();
        } else {
            System.out.println("图中无环！");
        }
        // 3.找到所有的连通分量
        P349ConnectedComponent cc = new P349ConnectedComponent(graph);

        //  3.1获取连通分量个数
        int ccCount = cc.getCount();
        System.out.println("连通分量个数为：" + ccCount);

        //  3.2.获取每个连通分量重的顶点列表
        P95LinkedListQueue<Integer>[] ccQueueArray = new P95LinkedListQueue[ccCount];
        for (int i = 0; i < ccCount; i++) {
            ccQueueArray[i] = new P95LinkedListQueue<>();
        }
        for (int v = 0; v < graph.getV(); v++) {
            // 每个连通分量都有一个id，实际就是从0开始递增的整数
            ccQueueArray[cc.getId(v)].enqueue(v);
        }
        //  3.3打印结果
        System.out.println("每个分量中含有的顶点为：");
        for (int i = 0; i < ccCount; i++) {
            System.out.print("分量" + (i + 1) + ": ");
            // 遍历队列中的每一个元素(相当于遍历连通分量了)
            for (int v : ccQueueArray[i]) {
                System.out.print(symbolGraph.nameOf(v) + " ");
            }
            System.out.println();
        }

        // 4.广度优先遍历寻找最短路径
        int source = 0;
        P346BreadthFirstPaths bfs = new P346BreadthFirstPaths(graph, source);
        for (int v = 0; v < graph.getV(); v++) {
            if (bfs.hasPathTo(v)) {
                System.out.println("顶点" + symbolGraph.nameOf(source) + "到顶点" + symbolGraph.nameOf(v) + "的距离为(边的条数)：" + bfs.distTo(v));
                System.out.print(symbolGraph.nameOf(source) + " to " + symbolGraph.nameOf(v) + ":");
                for (Integer x : bfs.pathTo(v)) {
                    if (x == source) {
                        // 第1个点是起点
                        System.out.print(symbolGraph.nameOf(x));
                    } else {
                        System.out.print("-->" + symbolGraph.nameOf(x));
                    }
                }
                System.out.println();
            } else {
                System.out.println(symbolGraph.nameOf(source) + " to " + symbolGraph.nameOf(v) + ": 没有路径可以到达!");
            }
        }
    }
} 
