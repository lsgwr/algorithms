/***********************************************************
 * @Description : 读取图的信息
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/4/30 14:07
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter8MinimumSpanningTree.Section3LazyPrim;

import java.util.Vector;

public class Main {
    public static void main(String[] args) {
        // 使用两种图的存储方式读取testG1.txt文件
        String filename = "/Users/liangshanguang/Program/Algorithm/liuyubobobo-algorithms/Part1Basic/JAVA/src/main/java/Chapter8MinimumSpanningTree/Section3LazyPrim/graph1.txt";
        SparseWeightedGraph g1 = new SparseWeightedGraph(8, false);
        ReadWeightedGraph readWeightedGraph1 = new ReadWeightedGraph(g1, filename);
        System.out.println("graph1 in Sparse WeightedGraph:");
        g1.show();
        System.out.println("Test Lazy Prim MST: ");
        LazyPrimMST<Double> lazyPrimMST = new LazyPrimMST<>(g1);
        // 获取最小生成树的所有边
        Vector<Edge<Double>> mst = lazyPrimMST.mstEdges();
        for (int i = 0; i < mst.size(); i++) {
            System.out.println(mst.elementAt(i));
        }
        System.out.println("The weight of MST is : "+lazyPrimMST.result());
        System.out.println();

        // 注意：稠密自己仿照着来就行，挺简答地
    }
}
