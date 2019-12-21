/***********************************************************
 * @Description : 基于DFS的回溯法找哈密尔顿路径
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2019-12-20 11:24
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter09HamiltonLoop.Section8StateCompression;

import Chapter02GraphExpress.Graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GraphDFSHamiltonPath {
    private int START;
    private Graph graph;

    /**
     * 存储顶点是否被访问的数组
     */
    private int visited;

    /**
     * 记录顶点的访问顺序pre[w]=w表示w的上一个访问节点是v
     */
    private int[] pre;

    /**
     * 记录回到起点START顶点的前一个顶点，如果DFS执行完end不是-1了表明图中存在哈密尔顿路径
     */
    private int end = -1;

    /**
     * 存放图的深度优先遍历的结果
     */
    private List<Integer> orderList = new ArrayList<>();

    public GraphDFSHamiltonPath(Graph graph, int start) {
        this.graph = graph;
        // 初始化访问变量，未访问时所有位置都是0，0代表false
        visited = 0;
        pre = new int[graph.V()];
        this.START = start;
        // 根据哈密尔顿路径的特点，根据传入的其实点开始进行DFS遍历
        dfs(START, START, graph.V());
    }

    public Iterable<Integer> getOrderList() {
        return orderList;
    }

    /**
     * 返回哈密尔顿路径
     */
    public List<Integer> getPath() {
        List<Integer> path = new ArrayList<>();
        if (end == -1) {
            // 等于-1表示没找到哈密尔顿路径
            return path;
        }
        // cur初始等于哈密尔顿路径的最后一个节点
        int cur = end;

        while (cur != START) {
            // 没到起点就一直往前遍历
            path.add(cur);
            cur = pre[cur];
        }
        // 设置起点
        path.add(START);
        Collections.reverse(path);
        return path;
    }

    /**
     * DFS遍历，过程中使用回溯法找到哈密尔顿路径
     *
     * @param v      当前递归遍历到的节点
     * @param parent v的上一个访问节点
     * @param left   本次递归还剩下多少元素没被访问，一定注意left是本层递归中的局部变量，
     *               不是在所有递归中都有效地！！回退到上一层递归left的意义就变了！！每层递归都有自己的left!!!
     * @return 是否找到了哈密尔顿路径
     */
    private boolean dfs(int v, int parent, int left) {
        visited = StateCompression.setTrue(visited, v);
        orderList.add(v);
        pre[v] = parent;
        left--;
        for (Integer w : graph.adj(v)) {
            if (!StateCompression.isTrue(visited, w)) {
                // w点没被访问的话就递归接着访问
                if (dfs(w, v, left)) {
                    // 遍历过程中任何一层递归返回True说明找到了哈密尔顿路径
                    return true;
                }
            } else {
                // 如果w还没被访问
                if (left == 0) { // 所有节点都已经被访问了
                    // 如果DFS执行完end不是-1了表明图中存在哈密尔顿路径，返回True即可
                    end = v;
                    return true;
                }
            }
        }
        // 没找到要回退，所以要把v点设置为未被访问过，即设置为False
        visited = StateCompression.setFalse(visited, v);
        return false;
    }
}
