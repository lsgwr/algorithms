package com.huawei.l00379880.algs4.chapter4graph;

import com.huawei.l00379880.algs4.chapter1javabasic.P95LinkedListQueue;
import com.huawei.l00379880.mylib.file.In;
import org.junit.Test;

/**
 * P349ConnectedComponent Tester.
 * 图见P338最上面的俩图
 *
 * @author liangshanguang
 * @date 02/16/2018
 * @description test
 */
public class P349ConnectedComponentTest {

    @Test
    public void testMain() {
        // 文件里每一行存储了一条边的两个顶点
        String filePath = "/Users/liangshanguang/Program/Algorithm/算法第四版资料/algs4-data/tinyG.txt";
        In in = new In(filePath);
        P336Graph graph = new P336Graph(in);
        P349ConnectedComponent cc = new P349ConnectedComponent(graph);

        // 1.获取连通分量个数
        int ccCount = cc.getCount();
        System.out.println("连通分量个数为：" + ccCount);

        // 2.获取每个连通分量重的顶点列表
        P95LinkedListQueue<Integer>[] ccQueueArray = new P95LinkedListQueue[ccCount];
        for (int i = 0; i < ccCount; i++) {
            ccQueueArray[i] = new P95LinkedListQueue<>();
        }
        for (int v = 0; v < graph.getV(); v++) {
            // 每个连通分量都有一个id，实际就是从0开始递增的整数
            ccQueueArray[cc.getId(v)].enqueue(v);
        }
        // 打印结果
        System.out.println("每个分量重含有的顶点为：");
        for (int i = 0; i < ccCount; i++) {
            System.out.print("分量" + (i + 1) + ": ");
            // 遍历队列中的每一个元素(相当于遍历连通分量了)
            for (int v : ccQueueArray[i]) {
                System.out.print(v + " ");
            }
            System.out.println();
        }

    }
} 
