/***********************************************************
 * @Description : 有向图欧拉回路检测，满足如下3个条件
 *                   1.有向
 *                   2.联通
 *                   3.每个点的入度等于出度
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2019/12/25 17:15
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter13DirectedGraph.Section05EulerLoopDirected;

import Chapter02GraphExpress.Graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GraphDFSEulerLoopDirected {
    private Graph graph;
    /**
     * 存储欧拉回路的结果
     */
    List<Integer> loop = new ArrayList<>();

    public GraphDFSEulerLoopDirected(Graph graph, int start) {
        if (!graph.isDirected()) {
            throw new RuntimeException("本类仅支持有向图");
        }
        // 因为我们实现了Graph的深拷贝(clone函数)，所以赋值时是值传递，下面的修改不会影响原始的Graph
        this.graph = (Graph) graph.clone();
        if (!hasEulerLoop()) {
            return;
        }
        dfs(start);
        // 有向图中是分顺序地，所以最后结果需要逆序下
        Collections.reverse(loop);
    }

    /**
     * 判断图中是否有欧拉回路
     */
    public boolean hasEulerLoop() {
        // 1.检测联通性 Todo:有向图中联通性和无向图不同，本章尾才会介绍，先跳过
//        GraphDFS4ConnectedComponents cc = new GraphDFS4ConnectedComponents(graph);
//        if (cc.getConnectedComponentCount() > 1) {
//            // 多于一个联通分量肯定就不包含欧拉回路了
//            return false;
//        }
        // 2.所有的点的度必须都是偶数
        for (int v = 0; v < graph.V(); v++) {
            // 入度等于出度才说明有欧拉回路
            if (graph.inDegree(v)!=graph.outDegree(v)) {
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
        while (graph.outDegree(v) != 0) {
            int w = graph.adj(v).iterator().next();
            graph.removeEdge(v, w);
            dfs(w);
        }
        loop.add(v);
    }

    public List<Integer> getLoop() {
        return loop;
    }
}
