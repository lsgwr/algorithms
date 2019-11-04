package com.huawei.l00379880.algs4.chapter4graph;

import com.huawei.l00379880.mylib.file.In;
import org.junit.Test;

/**
 * P352Cycle Tester.
 * 图见P351最上面
 *
 * @author liangshanguang
 * @date 02/17/2018
 * @description test
 */
public class P352CycleTest {

    @Test
    public void testMain() {
        // 文件里每一行存储了一条边的两个顶点
        String filePath = "/Users/liangshanguang/Program/Algorithm/算法第四版资料/algs4-data/tinyG.txt";
        In in = new In(filePath);
        P336Graph graph = new P336Graph(in);
        P352Cycle cycleFinder = new P352Cycle(graph);
        if (cycleFinder.hasCycle()) {
            System.out.println("发现环啦！！！");
            for (int v : cycleFinder.getCycle()) {
                System.out.print(v + " ");
            }
            System.out.println();
        } else {
            System.out.println("图中无环！");
        }
    }
} 
