/***********************************************************
 * @Description : 拓扑排序
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2019/12/25 23:58
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter13DirectedGraph.Section06to09TopoSort;

import Chapter02GraphExpress.Graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class TopoSort {
    /**
     * 要进行拓扑排序的有向图
     */
    private Graph graph;
    /**
     * 拓扑排序结果
     */
    private List<Integer> topoOrder;

    /**
     * 当前图是否有环
     */
    private boolean hasCycle = false;

    public TopoSort(Graph graph) {
        if (!graph.isDirected()) {
            throw new RuntimeException("拓扑排序TopoSort只能用于有向图");
        }
        this.graph = graph;
        topoOrder = new ArrayList<>();
        sort();
    }

    /**
     * 拓扑排序核心
     */
    public void sort() {
        // 存储还未排序的入度为0的顶点
        Queue<Integer> queue = new ArrayDeque<>();
        int[] inDegrees = new int[graph.V()];
        for (int v = 0; v < graph.V(); v++) {
            inDegrees[v] = graph.inDegree(v);
            if (inDegrees[v] == 0) {
                queue.add(v);
            }
        }
        while (!queue.isEmpty()) {
            int cur = queue.remove();
            topoOrder.add(cur);
            for (int next : graph.adj(cur)) {
                // 更新cur点的邻接点的入度
                inDegrees[next]--;
                if (inDegrees[next] == 0) {
                    // 更新后入度为0的顶点加入到queue中
                    queue.add(next);
                }
            }
        }
        if (topoOrder.size() != graph.V()) {
            // 找不到入度为0的点但是还有点没被删除进行拓扑排序，说明图中有环
            hasCycle = true;
            // 没法进行拓扑排序就把已经加入地顶点清理掉
            topoOrder.clear();
        }
    }

    /**
     * 该图是否有环
     */
    public boolean hasCycle() {
        return hasCycle;
    }

    /**
     * 获取拓扑排序的结果
     */
    public List<Integer> getTopoOrder() {
        return topoOrder;
    }
}
