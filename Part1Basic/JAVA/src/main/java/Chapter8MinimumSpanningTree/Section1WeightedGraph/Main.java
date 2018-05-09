/***********************************************************
 * @Description : 读取图的信息
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/4/30 14:07
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter8MinimumSpanningTree.Section1WeightedGraph;

public class Main {
    public static void main(String[] args) {
        // 使用两种图的存储方式读取testG1.txt文件
        String filename = "/Users/liangshanguang/Program/Algorithm/liuyubobobo-algorithms/Part1Basic/JAVA/src/main/java/Chapter8MinimumSpanningTree/Section1WeightedGraph/graph1.txt";
        SparseWeightedGraph g1 = new SparseWeightedGraph(13, false);
        ReadWeightedGraph readWeightedGraph1 = new ReadWeightedGraph(g1, filename);
        System.out.println("graph1 in Sparse WeightedGraph:");
        g1.show();

        System.out.println();

        DenseWeightedGraph g2 = new DenseWeightedGraph(13, false);
        ReadWeightedGraph readWeightedGraph2 = new ReadWeightedGraph(g2, filename);
        System.out.println("graph1 in Dense WeightedGraph:");
        g2.show();

        System.out.println();

        // 使用两种图的存储方式读取testG2.txt文件
        SparseWeightedGraph g3 = new SparseWeightedGraph(6, false);
        ReadWeightedGraph readWeightedGraph3 = new ReadWeightedGraph(g3, filename);
        System.out.println("graph2 in Sparse WeightedGraph:");
        g3.show();

        System.out.println();

        DenseWeightedGraph g4 = new DenseWeightedGraph(6, false);
        ReadWeightedGraph readWeightedGraph4 = new ReadWeightedGraph(g4, filename);
        System.out.println("graph2 in Dense WeightedGraph:");
        g4.show();
    }
}
