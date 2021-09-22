/***********************************************************
 * @Description : 深度优先遍历(支持连通图和非连通图)
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2019-08-06 23:20
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter04DFSInAction.Section4To5SingleSourcePath;

import Chapter02GraphExpress.Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class GraphDFSSingleSourcePath {
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
     * 基于每个访问的顶点的上一个访问顶点
     */
    private int[] pre;

    /**
     * 存放图的深度优先遍历的结果
     */
    private List<Integer> orderList = new ArrayList<>();

    public GraphDFSSingleSourcePath(Graph graph, int source) {
        // 验证起点是否在合理范围
        graph.validateVertex(source);
        this.graph = graph;
        this.source = source;
        // 初始化访问数组，用图的顶点个数来访问
        this.visited = new boolean[graph.V()];
        this.pre = new int[graph.V()];
        // pre数组全部初始化为-1
        Arrays.fill(this.pre, -1);
        // 因为单源路径问题是和连通分量无关的，只需要在source所在的联通分量内找到target的路径，所以不需要考虑所有的联通分量，即dfs()要用最早没有考虑连通分量的那版
        // 第一次进入时源头source的parent设置为了自己，实际设置成任何值都是可以的，我们在path函数中找到source就行了，并没有涉及source的父节点
        dfs(source, source);
    }

    public Iterable<Integer> getOrderList() {
        return orderList;
    }

    /**
     * 判断图的遍历起点是否和target点连通，实际只需要看下visit[v]是否为true即可，为true表示在一个连通分量上，肯定是连通地
     *
     * @param target 目标定点索引
     * @return 判断图的遍历起点是否和target点连通
     */
    public boolean isSourceConnectedTo(int target) {
        graph.validateVertex(target);
        return visited[target];
    }

    /**
     * 找到起点source到目标定点target的路径
     *
     * @param target 目标定点
     * @return 可迭代的对象，一般是集合，用于存储source到target的完整路径
     */
    public Iterable<Integer> path(int target) {
        List<Integer> pathList = new ArrayList<>();
        // source到target有路径才进行路径查找
        if (isSourceConnectedTo(target)) {
            // 用pre数组从target一直找到source点，记录下中间经过的所有点，就是要求的单源路径
            int current = target;
            while (current != source) {
                pathList.add(current);
                // 不断向前找元素
                current = pre[current];
            }
            // 起点要加上
            pathList.add(source);
            // 因为是从source到target的路径，所以要逆序下
            Collections.reverse(pathList);
            return pathList;
        } else {
            // 没有路径就直接返回空集合
            return pathList;
        }
    }

    /**
     * 记录每个顶点的前面一个被访问的顶点即parent的DFS实现
     *
     * @param v      当前顶点
     * @param parent v的前面一个被访问的节点
     */
    private void dfs(int v, int parent) {
        visited[v] = true;
        orderList.add(v);
        // 记录每个定点的上一个访问节点
        pre[v] = parent;
        for (Integer w : graph.adj(v)) {
            if (!visited[w]) {
                // w点没被访问的话就递归接着访问
                dfs(w, v);
            }
        }
    }
}
