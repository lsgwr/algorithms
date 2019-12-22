/***********************************************************
 * @Description : 欧拉回路检测，满足如下3个条件
 *                  1.无向
 *                  2.联通
 *                  3.每个点的度都是偶数
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2019/12/22 11:37
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter10EulerLoopAndEulerPath;

import Chapter02GraphExpress.Graph;
import Chapter04DFSInAction.Section1ConnectedComponents.GraphDFS4ConnectedComponents;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class GraphDFSEulerLoop {
    private Graph graph;
    /**
     * 存储欧拉回路的结果
     */
    List<Integer> loop = new ArrayList<>();

    public GraphDFSEulerLoop(Graph graph, int START) {
        // 因为我们实现了Graph的深拷贝(clone函数)，所以赋值时是值传递，下面的修改不会影响原始的Graph
        this.graph = (Graph) graph.clone();
        if (!hasEulerLoop()){
            return ;
        }
        dfs(START);
    }

    /**
     * 判断图中是否有欧拉回路
     */
    public boolean hasEulerLoop() {
        // 1.检测联通性
        GraphDFS4ConnectedComponents cc = new GraphDFS4ConnectedComponents(graph);
        if (cc.getConnectedComponentCount() > 1) {
            // 多于一个联通分量肯定就不包含欧拉回路了
            return false;
        }
        // 2.所有的点的度必须都是偶数
        for (int v = 0; v < graph.V(); v++) {
            if (graph.degree(v) % 2 == 1) {
                // 如果有一个顶点的度是奇数，直接返回false
                return false;
            }
        }
        return true;
    }

    /**
     * 基于递归的DFS来实现欧拉回路的获取
     *
     * @return 欧拉回路的列表
     */
    public void dfs(int v) {
       if (graph.degree(v)==0){
           loop.add(v);
       }else {
           while (graph.degree(v)!=0){
               int w = graph.adj(v).iterator().next();
               graph.removeEdge(v, w);
               dfs(w);
           }
           loop.add(v);
       }
    }

    public List<Integer> getLoop() {
        return loop;
    }
}
