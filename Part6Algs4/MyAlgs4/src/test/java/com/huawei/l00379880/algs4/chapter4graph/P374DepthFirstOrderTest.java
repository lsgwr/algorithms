package com.huawei.l00379880.algs4.chapter4graph;

import com.huawei.l00379880.mylib.file.In;
import org.junit.Test;

/**
 * P374DepthFirstOrder Tester.
 * 图的先序、后序、反后序遍历
 *
 * @author liangshanguang
 * @date 02/20/2018
 * @description test
 */
public class P374DepthFirstOrderTest {

    @Test
    public void testMain() {
        String filePath = "/Users/liangshanguang/Program/Algorithm/算法第四版资料/algs4-data/tinyDAG.txt";
        In in = new In(filePath);
        P366Digraph digraph = new P366Digraph(in);

        P374DepthFirstOrder dfs = new P374DepthFirstOrder(digraph);
        System.out.println("   v  pre post");
        System.out.println("--------------");
        for (int v = 0; v < digraph.getV(); v++) {
            System.out.printf("%4d %4d %4d\n", v, dfs.getPre(v), dfs.getPost(v));
        }

        System.out.print("Preorder:  ");
        for (int v : dfs.getPreOrderQueue()) {
            System.out.print(v + " ");
        }
        System.out.println();

        System.out.print("Postorder: ");
        for (int v : dfs.getPostOrderQueue()) {
            System.out.print(v + " ");
        }
        System.out.println();

        System.out.print("Reverse postorder: ");
        for (int v : dfs.reversePostOrderQueue()) {
            System.out.print(v + " ");
        }
        System.out.println();

    }
} 
