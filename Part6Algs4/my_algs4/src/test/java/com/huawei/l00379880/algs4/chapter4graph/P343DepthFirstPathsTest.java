package com.huawei.l00379880.algs4.chapter4graph;

import com.huawei.l00379880.mylib.file.In;
import org.junit.Test;

/**
 * P343DepthFirstPaths Tester.
 *
 * @author liang shan guang
 * @datetime 02/14/2018
 * @email liangshanguang2@gmail.com
 * @description
 */
public class P343DepthFirstPathsTest {
    /**
     * 图见P338最上面的俩图
     */
    @Test
    public void testMain() {
        // 文件里每一行存储了一条边的两个顶点
        String filePath = "/Users/liangshanguang/Program/Algorithm/算法第四版资料/algs4-data/tinyG.txt";
        In in = new In(filePath);
        P336Graph graph = new P336Graph(in);
        // source、v、w都是顶点的标号范围为[0,V)
        int source = 0;
        P343DepthFirstPaths dfs = new P343DepthFirstPaths(graph, source);
        for (int v = 0; v < graph.getV(); v++) {
            if (dfs.hasPathTo(v)) {
                System.out.print(source + " to " + v + ":");
                for (Integer x : dfs.pathTo(v)) {
                    if (x == source) {
                        // 第一个点都是source
                        System.out.print(x);
                    } else {
                        System.out.print("->" + x);
                    }
                }
            } else {
                System.out.print(source + " to " + v + ": 没有路径可以到达!");
            }
            System.out.println();
        }
    }
} 
