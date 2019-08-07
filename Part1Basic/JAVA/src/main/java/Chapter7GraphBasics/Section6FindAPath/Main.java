/***********************************************************
 * @Description : 读取图的信息
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/4/30 14:07
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter7GraphBasics.Section6FindAPath;

public class Main {
    public static void main(String[] args) {
        // graph1.txt
        System.out.println("*************************************稀疏图************************************");
        String filename1 = "src/main/java/Chapter7GraphBasics/Section6FindAPath/graph1.txt";
        SparseGraph g1 = new SparseGraph(13, false);
        ReadGraph readGraph1 = new ReadGraph(g1, filename1);
        g1.show();
        System.out.println();
        Path path1 = new Path(g1, 0);
        System.out.print("path of 0 to 3 :");
        path1.showPath(3);


        // graph2.txt
        System.out.println("*************************************稠密图************************************");
        String filename2 = "src/main/java/Chapter7GraphBasics/Section6FindAPath/graph2.txt";
        DenseGraph g2 = new DenseGraph(7, false);
        ReadGraph readGraph2 = new ReadGraph(g2, filename2);
        g2.show();
        System.out.println();
        Path path2 = new Path(g2, 0);
        System.out.print("path of 0 to 6 :");
        path2.showPath(6);

    }
}
