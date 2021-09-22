package com.huawei.l00379880.algs4.chapter4graph;

import com.huawei.l00379880.mylib.file.In;
import edu.princeton.cs.algs4.BreadthFirstPaths;
import org.junit.Test;

/**
 * P346 BreathFirstPaths Tester.
 * 图见P338最上面的俩图
 *
 * @author liangshanguang
 * @date 02/16/2018
 * @description test
 */
public class P346BreadthFirstPathsTest {

    @Test
    public void testMain() {
        // 文件里每一行存储了一条边的两个顶点
        String filePath = "/Users/liangshanguang/Program/Algorithm/算法第四版资料/algs4-data/tinyG.txt";
        In in = new In(filePath);
        P336Graph graph = new P336Graph(in);
        // source、v、w都是顶点的标号范围为[0,V)
        int source = 0;
        P346BreadthFirstPaths bfs = new P346BreadthFirstPaths(graph, source);
        for (int v = 0; v < graph.getV(); v++) {
            if (bfs.hasPathTo(v)) {
                System.out.println("顶点" + source + "到顶点" + v + "的距离为(边的条数)：" + bfs.distTo(v));
                System.out.print(source + " to " + v + ":");
                for (Integer x : bfs.pathTo(v)) {
                    if (x == source) {
                        // 第1个点是起点
                        System.out.print(x);
                    } else {
                        System.out.print("-->" + x);
                    }
                }
                System.out.println();
            } else {
                System.out.println(source + " to " + v + ": 没有路径可以到达!");
            }
        }
    }
} 
