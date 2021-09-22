package com.huawei.l00379880.algs4.chapter4graph;

import com.huawei.l00379880.algs4.chapter1javabasic.P95LinkedListQueue;
import org.junit.Test;

/**
 * P376SymbolDigraph Tester.
 * 基于符号表的有向图(字符串代替节点的数字下标)
 *
 * @author liangshanguang
 * @date 02/20/2018
 * @description test
 */
public class P376SymbolDigraphTest {

    @Test
    public void testMain() {
        // 文件里每一行存储了一条边的两个顶点
        String filePath = "/Users/liangshanguang/Program/Algorithm/算法第四版资料/algs4-data/routes.txt";
        String delimeter = " ";
        P376SymbolDigraph symbolDigraph = new P376SymbolDigraph(filePath, delimeter);
        P366Digraph digraph = symbolDigraph.getGraph();
        String[] keys = symbolDigraph.getKeys();
        // 1.打印出所有的邻接点
        for (String nodeName : keys) {
            if (symbolDigraph.contains(nodeName)) {
                System.out.print(nodeName + "的邻接点为：");
                int nodeIndex = symbolDigraph.indexOf(nodeName);
                // 通过indexOf和nameOf函数就可以完美地完成图的索引改成字符串的需求
                for (int v : digraph.adj(nodeIndex)) {
                    System.out.print(symbolDigraph.nameOf(v) + " ");
                }
                System.out.println();
            } else {
                System.out.println("图中不含有节点：" + nodeName);
            }
        }
        // 2.判断图中是否有环
        P372DirectedCycle cycleFinder = new P372DirectedCycle(digraph);
        if (cycleFinder.hasCycle()) {
            System.out.println("发现环啦！！！");
            for (int v : cycleFinder.getCycleStack()) {
                System.out.print(symbolDigraph.nameOf(v) + " ");
            }
            System.out.println();
        } else {
            System.out.println("图中无环！");
        }


        // 3.广度优先遍历寻找最短路径
        int source = 0;
        P369DirectedBreadthFirstPaths bfs = new P369DirectedBreadthFirstPaths(digraph, source);
        for (int v = 0; v < digraph.getV(); v++) {
            if (bfs.hasPathTo(v)) {
                System.out.println("顶点" + symbolDigraph.nameOf(source) + "到顶点" + symbolDigraph.nameOf(v) + "的距离为(边的条数)：" + bfs.distTo(v));
                System.out.print(symbolDigraph.nameOf(source) + " to " + symbolDigraph.nameOf(v) + ":");
                for (Integer x : bfs.pathTo(v)) {
                    if (x == source) {
                        // 第1个点是起点
                        System.out.print(symbolDigraph.nameOf(x));
                    } else {
                        System.out.print("-->" + symbolDigraph.nameOf(x));
                    }
                }
                System.out.println();
            } else {
                System.out.println(symbolDigraph.nameOf(source) + " to " + symbolDigraph.nameOf(v) + ": 没有路径可以到达!");
            }
        }
    }
} 
