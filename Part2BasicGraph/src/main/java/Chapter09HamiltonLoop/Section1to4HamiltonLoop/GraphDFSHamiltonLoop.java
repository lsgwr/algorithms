/***********************************************************
 * @Description : 基于DFS的回溯法找哈密尔顿回路
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2019-12-20 11:24
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter09HamiltonLoop.Section1to4HamiltonLoop;

import Chapter02GraphExpress.Graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GraphDFSHamiltonLoop {
    private static final int START = 0;
    private Graph graph;

    /**
     * 存储顶点是否被访问的数组
     */
    private boolean[] visited;

    /**
     * 记录顶点的访问顺序pre[w]=w表示w的上一个访问节点是v
     */
    private int[] pre;

    /**
     * 记录回到起点START顶点的前一个顶点，如果DFS执行完end不是-1了表明图中存在哈密尔顿回路
     */
    private int end = -1;

    /**
     * 存放图的深度优先遍历的结果
     */
    private List<Integer> orderList = new ArrayList<>();

    public GraphDFSHamiltonLoop(Graph graph) {
        this.graph = graph;
        // 初始化访问数组，用图的顶点个数来访问
        visited = new boolean[graph.V()];
        pre = new int[graph.V()];
        // 根据哈密尔顿回路的特点，回溯法找从1个顶点开始遍历即可
        dfs(START, START, graph.V());
    }

    public Iterable<Integer> getOrderList() {
        return orderList;
    }

    /**
     * 返回哈密尔顿回路
     */
    public List<Integer> getLoop() {
        List<Integer> path = new ArrayList<>();
        if (end == -1) {
            // 等于-1表示没找到哈密尔顿环
            return path;
        }
        // cur初始等于哈密尔顿环回到起点的前一个点
        int cur = end;

        while (cur != START) {
            // 没到起点就一直往前遍历
            path.add(cur);
            cur = pre[cur];
        }
        // 设置起点
        path.add(START);
        Collections.reverse(path);
        // 终点为起点
        path.add(START);
        return path;
    }

    /**
     * DFS遍历，过程中使用回溯法找到哈密尔顿回路
     *
     * @param v      当前递归遍历到的节点
     * @param parent v的上一个访问节点
     * @param left   本次递归还剩下多少元素没被访问，一定注意left是本层递归中的局部变量，
     *               不是在所有递归中都有效地！！回退到上一层递归left的意义就变了！！每层递归都有自己的left!!!
     * @return 是否找到了哈密尔顿回路
     */
    private boolean dfs(int v, int parent, int left) {
        visited[v] = true;
        orderList.add(v);
        pre[v] = parent;
        left--;
        for (Integer w : graph.adj(v)) {
            if (!visited[w]) {
                // w点没被访问的话就递归接着访问
                if (dfs(w, v, left)) {
                    // 遍历过程中任何一层递归返回True说明找到了哈密尔顿回路
                    return true;
                }
            } else {
                // 如果w已经被访问，且w回到了起始点+恰好所有定点都被访问，说明存在哈密尔顿回路
                if (w == START && left == 0) {
                    // 记录回到起点START顶点的前一个顶点，如果DFS执行完end不是-1了表明图中存在哈密尔顿回路
                    end = v;
                    // 如果回到了遍历起点并且所有节点都已经被访问了，说明存在哈密尔顿回路
                    return true;
                }
            }
        }
        // 没找到要回退，所以要把v点设置为未被访问过，即设置为False
        visited[v] = false;
        return false;
    }
}
