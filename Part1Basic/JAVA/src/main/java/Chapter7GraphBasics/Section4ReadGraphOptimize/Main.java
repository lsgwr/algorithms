/***********************************************************
 * @Description : 读取图的信息
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/4/30 14:07
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter7GraphBasics.Section4ReadGraphOptimize;

public class Main {
    public static void main(String[] args) {
        // 使用两种图的存储方式读取testG1.txt文件，txt文件第一行表示几条边几个顶点(13, 13)以及(6, 8)分别表示13个顶点13条边以及6个顶点8条边
        String filename = "src/main/java/Chapter7GraphBasics/Section4ReadGraphOptimize/graph1.txt";
        SparseGraph g1 = new SparseGraph(13, false);
        ReadGraph readGraph1 = new ReadGraph(g1, filename);
        System.out.println("graph1 in Sparse Graph:");
        g1.show();

        System.out.println();

        DenseGraph g2 = new DenseGraph(13, false);
        ReadGraph readGraph2 = new ReadGraph(g2, filename);
        System.out.println("graph1 in Dense Graph:");
        g2.show();

        System.out.println();

        // 使用两种图的存储方式读取testG2.txt文件
        filename = "src/main/java/Chapter7GraphBasics/Section4ReadGraphOptimize/graph2.txt";
        SparseGraph g3 = new SparseGraph(6, false);
        ReadGraph readGraph3 = new ReadGraph(g3, filename);
        System.out.println("graph2 in Sparse Graph:");
        g3.show();

        System.out.println();

        DenseGraph g4 = new DenseGraph(6, false);
        ReadGraph readGraph4 = new ReadGraph(g4, filename);
        System.out.println("graph2 in Dense Graph:");
        g4.show();
    }
}
