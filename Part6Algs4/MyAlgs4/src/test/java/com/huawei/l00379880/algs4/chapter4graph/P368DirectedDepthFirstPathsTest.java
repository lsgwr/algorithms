package com.huawei.l00379880.algs4.chapter4graph;

import com.huawei.l00379880.mylib.file.In;
import org.junit.Test;

/**
 * P368DirectedDepthFirstPaths Tester.
 * <p>
 * 效果图见P369
 * 深度优先搜索就是找一条路径就行了
 *
 * @author liangshanguang
 * @date 02/19/2018
 * @description test
 */
public class P368DirectedDepthFirstPathsTest {

    @Test
    public void testMain() {
        // 文件里每一行存储了一条边的两个顶点
        String filePath = "/Users/liangshanguang/Program/Algorithm/算法第四版资料/algs4-data/tinyDG.txt";
        In in = new In(filePath);
        P366Digraph digraph = new P366Digraph(in);
        // source、v、w都是顶点的标号范围为[0,V)
        int source = 3;
        P368DirectedDepthFirstPaths dfs = new P368DirectedDepthFirstPaths(digraph, source);
        for (int v = 0; v < digraph.getV(); v++) {
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
