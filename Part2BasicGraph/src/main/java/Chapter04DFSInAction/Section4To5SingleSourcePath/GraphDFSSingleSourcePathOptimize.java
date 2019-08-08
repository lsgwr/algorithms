/***********************************************************
 * @Description : 单源路径问题优化：DFS遍历到target就提前退出，这样可以极大地节省递归的成本,当前图的构造就只是为了求解当前两个点的路径
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2019-08-06 23:20
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter04DFSInAction.Section8SingleSourcePathOptimize;

import Chapter02GraphExpress.Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class GraphDFSSingleSourcePathOptimize {
    private Graph graph;

    /**
     * 存储顶点是否被访问的数组
     */
    private boolean[] visited;

    /**
     * 图进行深度优先遍历的起点
     */
    private int source;

    /**
     * 要找地路径的终点
     */
    private int target;

    /**
     * 基于每个访问的顶点的上一个访问顶点
     */
    private int[] pre;

    /**
     * 存放图的深度优先遍历的结果
     */
    private List<Integer> orderList = new ArrayList<>();

    public GraphDFSSingleSourcePathOptimize(Graph graph, int source, int target) {
        // 验证起点是否在合理范围
        graph.validateVertex(source);
        this.graph = graph;
        this.source = source;
        this.target = target;
        // 初始化访问数组，用图的顶点个数来访问
        this.visited = new boolean[graph.V()];
        this.pre = new int[graph.V()];
        // pre数组全部初始化为-1
        Arrays.fill(this.pre, -1);
        // 因为单源路径问题是和连通分量无关的，所以dfs()要用最早没有考虑连通分量的那版
        dfs(source, source);
    }

    public Iterable<Integer> getOrderList() {
        return orderList;
    }

    /**
     * 在测试代码中打印visited数组，会发现我们并没有遍历所有的点(visited部分为true部分为false)
     */
    public boolean[] getVisited() {
        return visited;
    }

    /**
     * 判断图的遍历起点source是否和target点连通
     */
    public boolean isConnected() {
        graph.validateVertex(target);
        return visited[target];
    }

    /**
     * 找到起点source到目标定点target的路径
     *
     * @return 可迭代的对象，一般是集合，用于存储source到target的完整路径
     */
    public Iterable<Integer> path() {
        List<Integer> pathList = new ArrayList<>();
        // source到target有路径才进行路径查找
        if (isConnected()) {
            // 用pre数组从target一直找到source点，记录下中间经过的所有点，就是要求的单源路径
            int current = target;
            while (current != source) {
                pathList.add(current);
                current = pre[current];
            }
            // 起点要加上
            pathList.add(source);
            // 因为是从source到target的路径，所以要颠倒下
            Collections.reverse(pathList);
            return pathList;
        } else {
            // 没有路径就直接返回空集合
            return pathList;
        }
    }

    private boolean dfs(int v, int parent) {
        visited[v] = true;
        orderList.add(v);
        // 记录每个定点的上一个访问节点
        pre[v] = parent;
        // 如果当前遍历到的点就是target那么就提前退出
        if (v == target) {
            // 这里的返回是为了防止遍历target后面的节点
            return true;
        }
        for (Integer w : graph.adj(v)) {
            if (!visited[w]) {
                // w点没被访问的话就递归接着访问
                if (dfs(w, v)) {
                    // 如果找到要遍历的点了就退出(这里是为了防止遍历到target后去遍历target.previous的其他邻接点)
                    return true;
                }
            }
        }
        // 遍历到最后都没找到就返回false
        return false;
    }
}
