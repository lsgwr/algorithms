/***********************************************************
 * @Description : 欧拉回路检测，满足如下3个条件
 *                  1.无向
 *                  2.联通
 *                  3.每个点的度都是偶数
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2019/12/21 19:54
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter10EulerLoopAndEulerPath;

import Chapter02GraphExpress.Graph;
import Chapter04DFSInAction.Section1ConnectedComponents.GraphDFS4ConnectedComponents;

public class EulerLoop {
    private Graph graph;

    public EulerLoop(Graph graph) {
        this.graph = graph;
    }

    /**
     * 判断图中是否有欧拉回路
     */
    public boolean hasEulerLoop() {
        // 1.检测联通性
        GraphDFS4ConnectedComponents cc = new GraphDFS4ConnectedComponents(graph);
        if (cc.getConnectedComponentCount()>1){
            // 多于一个联通分量肯定就不包含欧拉回路了
            return false;
        }
        // 2.所有的点的度必须都是偶数
        for (int v = 0; v < graph.V(); v++) {
            if (graph.degree(v)%2==1){
                // 如果有一个顶点的度是奇数，直接返回false
                return false;
            }
        }
        return true;
    }
}
