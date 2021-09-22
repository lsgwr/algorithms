package com.huawei.l00379880.algs4.chapter4graph;

import com.huawei.l00379880.mylib.file.In;
import org.junit.Test;

/**
 * P372DirectedCycle Tester.
 * 检测有向图中的环，图见P372上面
 *
 * @author liangshanguang
 * @date 02/20/2018
 * @description test
 */
public class P372DirectedCycleTest {

    @Test
    public void testMain() {
        // 文件里每一行存储了一条边的两个顶点
        String filePath = "/Users/liangshanguang/Program/Algorithm/算法第四版资料/algs4-data/tinyDG.txt";
        In in = new In(filePath);
        P366Digraph digraph = new P366Digraph(in);
        P372DirectedCycle cycleFinder = new P372DirectedCycle(digraph);
        if (cycleFinder.hasCycle()) {
            System.out.println("发现环啦！！！");
            for (int v : cycleFinder.getCycleStack()) {
                System.out.print(v + " ");
            }
            System.out.println();
        } else {
            System.out.println("图中无环！");
        }
    }
} 
