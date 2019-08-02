/***********************************************************
 * @Description : 图的读取和显示
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2019-08-02 07:59
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter2GraphExpress;

public class Main {
    public static void main(String[] args) {
        String filepath = "graph.txt";
        Graph graph = new Graph(7, false);
        // 把Graph需要的信息从文件中读取出来并赋值，这里实现了读文件和Graph表示的解耦
        ReadGraph.init(graph, filepath);
        System.out.println("Graph express in TreeSet:");
        graph.show();
    }
}
