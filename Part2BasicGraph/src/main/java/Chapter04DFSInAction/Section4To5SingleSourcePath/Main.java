/***********************************************************
 * @Description : 计算连通分量内的单源路径
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2019-08-08 08:24
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter04DFSInAction.Section4To5SingleSourcePath;

import Chapter02GraphExpress.Graph;
import Chapter02GraphExpress.ReadGraph;

public class Main {
    public static void main(String[] args) {
        // 求起点source到目标点target的单源路径(source就会作为DFS的起点)
        int source = 0;
        int target = 6;
        String filePath = "src/main/java/Chapter03DepthFirstTraversal/graphNotConnected.txt";
        Graph graph = new Graph(7, false);
        ReadGraph.init(graph, filePath);
        GraphDFSSingleSourcePath singleSourcePathDFS = new GraphDFSSingleSourcePath(graph, source);
        System.out.println(source + "--->" + target + "的路径:" + singleSourcePathDFS.path(target));
        // 5和0不在一个连通分量内，Section3有验证，此时路径为[]，表示无路径，所以这个方法也可以用于判断连通性，和并查集一起思考
        target = 5;
        System.out.println(source + "--->" + target + "的路径:" + singleSourcePathDFS.path(target));
    }
}

/**
 * 结果如下：
 * 顶点数V = 7, 边数E = 6
 * 0--->6的路径:[0, 1, 3, 2, 6]
 * 0--->5的路径:[]
 */