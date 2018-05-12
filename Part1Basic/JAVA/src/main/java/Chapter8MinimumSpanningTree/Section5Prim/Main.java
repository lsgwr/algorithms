/***********************************************************
 * @Description : 读取图的信息
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/4/30 14:07
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter8MinimumSpanningTree.Section5Prim;

import java.util.Vector;

public class Main {
    public static void main(String[] args) {
        // 使用两种图的存储方式读取testG1.txt文件
        String filename = "/Users/liangshanguang/Program/Algorithm/liuyubobobo-algorithms/Part1Basic/JAVA/src/main/java/Chapter8MinimumSpanningTree/Section5Prim/testG4.txt";
        SparseWeightedGraph g1 = new SparseWeightedGraph(10000, false);
        ReadWeightedGraph readWeightedGraph1 = new ReadWeightedGraph(g1, filename);
        System.out.println("graph1 in Sparse WeightedGraph:");
        long startTime, endTime;
        System.out.println("Test Lazy Prim MST: ");
        startTime = System.currentTimeMillis();
        LazyPrimMST<Double> lazyPrimMST = new LazyPrimMST<>(g1);
        System.out.println("The weight of Lazy Prim MST is : "+lazyPrimMST.result());
        endTime = System.currentTimeMillis();
        System.out.println("Time for test: " + (endTime-startTime) + "ms.");
        System.out.println();

        System.out.println("Test Prim MST: ");
        startTime = System.currentTimeMillis();
        PrimMST<Double> primMST = new PrimMST<>(g1);
        System.out.println("The weight of Prim MST is : "+primMST.result());
        endTime = System.currentTimeMillis();
        System.out.println("Test to test: " + (endTime-startTime) + "ms.");
        System.out.println();

        // 注意：稠密自己仿照着来就行，挺简答地
    }
}
