/***********************************************************
 * @Description : 广度优先遍历,不一定能实现最短路径，与邻接点入队的顺序有关
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/5/1 00:01
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter7GraphBasics.Section7BFSShortestPath;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.Vector;

public class ShortestPath {
    private Graph graph;
    /**
     * 起始点,后面会求出这个源到其他任意一个节点的路径
     */
    private int source;
    private boolean[] visited;
    /**
     * from[i]表示访问i节点是从哪一个节点过来地
     */
    private int[] from;
    /**
     * source节点到每一个节点的最短距离
     */
    private int[] order;


    ShortestPath(Graph graph, int source) {
        this.graph = graph;

        // 算法初始化
        // 确保source在合适的范围内
        assert (source >= 0 && source < graph.V());
        visited = new boolean[graph.V()];
        from = new int[graph.V()];
        order = new int[graph.V()];
        for (int i = 0; i < graph.V(); ++i) {
            visited[i] = false;
            from[i] = -1;
            order[i] = -1;
        }
        // 设置路径开始的点的坐标
        this.source = source;

        // 无向图的最短路径算法,类似二叉搜索树的levelOrder(层序遍历，维护一个队列即可).注意LinkList是无序地，不能当队列用，老师的代码是错地
        // LinkedList<Integer> q = new LinkedList<>();
        Queue<Integer> q = new LinkedList<>();
        q.offer(source);
        visited[source] = true;
        // 起点到起点的距离为0
        order[source] = 0;
        while (!q.isEmpty()) {
            int v = q.poll();
            // 遍历邻接点
            for (Integer i : graph.adj(v)) {
                if (!visited[i]) {
                    // 元素入队
                    q.offer(i);
                    visited[i] = true;
                    // 访问的i节点是从v节点过来地
                    from[i] = v;
                    // 此时边还没有权重.最终的order[i]表示从source到i的距离
                    order[i] = order[v] + 1;
                }
            }
        }
    }


    /**
     * 起点source到点w之间是否有路径存在
     */
    public boolean hasPathTo(int w) {
        // 保证w不越界
        assert (w >= 0 && w < graph.V());
        // 访问过说明在一个联通分量内，source肯定是可以和w有条路径连到一起地
        return visited[w];
    }

    /**
     * 获取source到w的路径到vec中
     */
    public Vector<Integer> pathTo(int w) {
        assert (hasPathTo(w));
        // System.out.println(order[w]); // order[w]表示从source到w点的最短距离，上面构造函数处理地不对
        // 因为路径是倒推地，所以先压入栈中，然后再弹出到vector中顺序就正常了
        Stack<Integer> pathStack = new Stack<>();
        // 路径上的点初始化
        int pathPoint = w;
        // 倒叙查找路径
        while (pathPoint != -1) {
            pathStack.push(pathPoint);
            // 不断往前找
            pathPoint = from[pathPoint];
        }

        Vector<Integer> vec = new Vector<>();
        while (!pathStack.empty()) {
            vec.add(pathStack.pop());
        }
        return vec;
    }

    /**
     * 打印出source到w的路径
     */
    void showPath(int w) {
        assert (hasPathTo(w));
        Vector<Integer> vec = pathTo(w);
        for (int i = 0; i < vec.size(); ++i) {
            System.out.print(vec.elementAt(i));
            if (i == vec.size() - 1) {
                // 到达最后一个元素打个回车就行了
                System.out.println();
            } else {
                System.out.print(" -> ");
            }
        }
    }

    /**
     * 查询source到w的最短路径的长度
     */
    public int length(int w) {
        // 保证w不越界
        assert (w >= 0 && w < graph.V());
        return order[w];
    }
}
