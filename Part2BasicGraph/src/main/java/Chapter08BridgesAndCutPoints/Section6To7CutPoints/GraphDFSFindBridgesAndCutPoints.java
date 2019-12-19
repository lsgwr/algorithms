/***********************************************************
 * @Description : 基于递归DFS实现地查找桥和割点算法
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2019-12-19 23:36
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter08BridgesAndCutPoints.Section6To7CutPoints;

import Chapter02GraphExpress.Graph;
import Chapter08BridgesAndCutPoints.Section1To4Bridges.Edge;

import java.util.ArrayList;
import java.util.List;

public class GraphDFSFindBridgesAndCutPoints {
    private Graph graph;

    /**
     * 存储顶点是否被访问的数组
     */
    private boolean[] visited;

    /**
     * 存放图的深度优先遍历的结果
     */
    private List<Integer> orderList = new ArrayList<>();

    /**
     * 记录每个顶点的遍历序号
     */
    private int[] ord;

    /**
     * 记录已经访问地定点数
     */
    private int cnt = 0;

    /**
     * low[v]表示DFS过程中，顶点v能到达的最小ord值(也可以认为是能联通的最远的节点)
     */
    private int[] low;

    /**
     * 检测到的桥
     */
    private List<Edge> bridges = new ArrayList<>();

    /**
     * 割点
     */
    private List<Integer> cutPoints = new ArrayList<>();

    public GraphDFSFindBridgesAndCutPoints(Graph graph) {
        this.graph = graph;
        // 初始化访问数组，用图的顶点个数来访问
        visited = new boolean[graph.V()];
        ord = new int[graph.V()];
        low = new int[graph.V()];
        // 从dfs(0)改成下面的代码，可以支持非连通的图,不用考虑连通分量的时候直接用dfs(v)即可
        for (int v = 0; v < graph.V(); v++) {
            if (!visited[v]) {
                dfs(v, v);
            }
        }
    }

    /**
     * 获取dfs的遍历结果
     */
    public Iterable<Integer> getOrderList() {
        return orderList;
    }

    /**
     * 获取割点列表
     */
    public List<Integer> getCutPoints() {
        return cutPoints;
    }

    /**
     * 获取图中所有的桥
     */
    public List<Edge> getBridges() {
        return bridges;
    }

    /**
     * dfs递归，顺便获取访问路径和桥
     *
     * @param v      当前访问的节点
     * @param parent 当前访问节点的上一个访问节点
     */
    private void dfs(int v, int parent) {
        visited[v] = true;
        orderList.add(v);
        ord[v] = cnt;
        low[v] = cnt;

        // v节点的在DFS遍历树中的子节点数
        int childCnt = 0;
        for (Integer w : graph.adj(v)) {
            if (!visited[w]) {
                // 找到一个未被访问的点
                cnt++;
                // 节点v多个了子节点,v有效范围是v极其邻接点遍历的过程
                childCnt++;
                // w点没被访问的话就递归接着访问
                dfs(w, v);
                // 1.找桥：退出一层递归时，判断是否是桥，是桥就把边v-w加入到bridges中,不是桥需要更新当前节点v的low值为low[v]和low[w]的较小值
                if (low[w] > ord[v]) {
                    bridges.add(new Edge(v, w));
                } else {
                    low[v] = Math.min(low[v], low[w]);
                }
                // 2.找割点
                if (v == parent) {
                    // 两个相等代表v是根节点，从当前类构造函数可以可以看出
                    if (childCnt > 1) {
                        // 根节点有两个及以上的遍历树种子节点，则说明根节点是割点
                        cutPoints.add(v);
                    }
                } else {
                    // 不是根节点时，对于v有一个孩子节点w，如果满足`low[w] >= ord[v]`，则v是割点
                    if (low[w] >= ord[v]) {
                        if (!cutPoints.contains(v)) {
                            // 之前没加过这个节点才加入
                            cutPoints.add(v);
                        }
                    }
                }

            } else {
                // visited[w]=true而且w不是v的父节点parent，说明存在环，更新low[v]为low[v]和Low[w]的较小值
                if (w != parent) {
                    low[v] = Math.min(low[v], low[w]);
                }
            }
        }
    }
}
