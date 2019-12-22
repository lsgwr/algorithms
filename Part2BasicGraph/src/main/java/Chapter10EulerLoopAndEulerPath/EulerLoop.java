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

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class EulerLoop {
    private Graph graph;

    public EulerLoop(Graph graph) {
        // 因为我们实现了Graph的深拷贝(clone函数)，所以赋值时是值传递，下面的修改不会影响原始的Graph
        this.graph = graph;
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
     * 基于非递归的DFS来实现欧拉回路的获取
     *
     * @return 欧拉回路的列表
     */
    public List<Integer> dfs() {
        // 存储最终欧拉回路的list
        List<Integer> result = new ArrayList<>();
        if (!hasEulerLoop()) {
            // 没有欧拉路径就返回null
            return result;
        }
        // 把原图深拷贝，防止影响原图对象
        Graph graph = (Graph) this.graph.clone();
        // 下面两个栈是Hierholzer算法的核心用到的两个栈
        Stack<Integer> curPath = new Stack<>();
        Stack<Integer> loop = new Stack<>();
        // 开始遍历的起点
        int vCur = 0;
        // 初始化加入下是为了能进while循环
        curPath.push(vCur);
        while (!curPath.isEmpty()) {
            if (graph.degree(vCur) != 0) {
                // 这里再加入一次0是为了最后一次pop后就进不了while循环了，所以要多个元素占着位置
                curPath.push(vCur);
                // 只要当前顶点还有临边就继续遍历
                // 获取v的最小邻接点
                int w = graph.adj(vCur).iterator().next();
                graph.removeEdge(vCur, w);
                vCur = w;
            } else {
                // 当前元素没有邻接点了，肯定是欧拉回路上的，所以加入loop
                loop.push(vCur);
                // 回退到上一个顶点
                vCur = curPath.pop();
            }
        }
        // 最终的栈loop中存储地就是欧拉回路的逆序，所以我们从栈中全pop到列表result中即可
        while (!loop.isEmpty()) {
            result.add(loop.pop());
        }
        return result;
    }
}
