/***********************************************************
 * @Description : 图的读取和显示
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2019-08-02 07:59
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter02GraphExpress;

public class Main {
    public static void main(String[] args) {
        String filepath = "src/main/java/Chapter02GraphExpress/graph.txt";
        Graph graph = new Graph(false);
        // 把Graph需要的信息从文件中读取出来并赋值，这里实现了读文件和Graph表示的解耦
        ReadGraph.init(graph, filepath);
        System.out.println("Graph express in TreeSet:");
        graph.show();
    }
}
/**
 * 顶点数V = 7, 边数E = 9
 * 顶点数V = 7, 边数E = 9
 * vertex 0:	1	3
 * vertex 1:	0	2	6
 * vertex 2:	1	3	5
 * vertex 3:	0	2	4
 * vertex 4:	3	5
 * vertex 5:	2	4	6
 * vertex 6:	1	5
 */