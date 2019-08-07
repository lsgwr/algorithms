/***********************************************************
 * @Description : 读取图的信息
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/4/30 14:07
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter7GraphBasics.Section5ConnectedConponents;

public class Main {
    public static void main(String[] args) {
        // graph1.txt
        System.out.println("*************************************稀疏图************************************");
        String filename1 = "src/main/java/Chapter7GraphBasics/Section5ConnectedConponents/graph1.txt";
        SparseGraph g1 = new SparseGraph(13, false);
        ReadGraph readGraph1 = new ReadGraph(g1, filename1);
        g1.show();
        Components component1 = new Components(g1);
        System.out.println("TestG1.txt, Components Count: " + component1.count());
        System.out.println();

        // graph2.txt
        System.out.println("*************************************稠密图************************************");
        String filename2 = "src/main/java/Chapter7GraphBasics/Section5ConnectedConponents/graph2.txt";
        DenseGraph g2 = new DenseGraph(6, false);
        ReadGraph readGraph2 = new ReadGraph(g2, filename2);
        g2.show();
        Components component2 = new Components(g2);
        System.out.println("TestG2.txt, Components Count: " + component2.count());
    }
}
