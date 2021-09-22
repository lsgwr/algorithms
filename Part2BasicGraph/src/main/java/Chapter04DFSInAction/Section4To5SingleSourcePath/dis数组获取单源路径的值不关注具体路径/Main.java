/***********************************************************
 * @Description : 
 * @author      : 梁山广(Liang Shan Guang)
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/

import com.huawei.l00379880.Chapter02GraphExpress.Graph;
import com.huawei.l00379880.Chapter02GraphExpress.ReadGraph;

public class Main {
    public static void main(String[] args) {
        String filepath = "src/main/java/com/huawei/l00379880/Chapter04DFSInAction/S01联通分量计数/graphNotConnected.txt";
        Graph graph = new Graph(false);
        ReadGraph.init(graph, filepath);
        GraphDFS单源路径问题 graphDFS单源路径问题 = new GraphDFS单源路径问题(graph, 0);
        System.out.println("0-->6的路径是：" + graphDFS单源路径问题.getPath(6) + ",距离是" + graphDFS单源路径问题.getDistance(6));
        System.out.println("0-->5的路径是：" + graphDFS单源路径问题.getPath(5) + ",距离是" + graphDFS单源路径问题.getDistance(5));
    }
}
/**
 * 顶点数V = 7, 边数E = 6
 * 0-->6的路径是：[0, 1, 3, 2, 6],距离是4
 * 0-->5的路径是：null,距离是-1
 */