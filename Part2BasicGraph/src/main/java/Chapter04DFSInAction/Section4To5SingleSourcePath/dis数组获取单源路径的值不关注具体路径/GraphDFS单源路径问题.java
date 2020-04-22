/***********************************************************
 * @Description : 单源路径问题
 * @author      : 梁山广(Liang Shan Guang)
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/

import com.huawei.l00379880.Chapter02GraphExpress.Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class GraphDFS单源路径问题 {
    /**
     * 构造的图对象
     */
    private Graph graph;

    /**
     * 节点访问情况
     */
    private boolean[] visited;

    /**
     * 记录访问顺序，pre[w] = v 表示w的上一个访问节点是v
     */
    private int[] pre;

    /**
     * 距离数组，dis[v]表示v到DFS遍历起点的距离
     */
    private int[] dis;

    /**
     * 遍历的起点
     */
    private int src;

    public GraphDFS单源路径问题(Graph graph, int src) {
        this.graph = graph;
        visited = new boolean[graph.V()];
        pre = new int[graph.V()];
        Arrays.fill(pre, -1);
        dis = new int[graph.V()];
        Arrays.fill(dis, -1);
        // 起点到起点的距离为0，还是显式初始化好
        this.src = src;
        dis[src] = 0;
        dfs(src);
    }

    private void dfs(int v) {
        visited[v] = true;
        for (int w : graph.adj(v)) {
            if (!visited[w]) {
                pre[w] = v;
                dis[w] = dis[v] + 1;
                dfs(w);
            }
        }
    }

    /**
     * 获取终点dst到起点src的路径
     *
     * @param dst 终点
     * @return list列表
     */
    public List<Integer> getPath(int dst) {
        if (pre[dst] == -1) {
            return null;
        }
        List<Integer> path = new ArrayList<>();
        int cur = dst;
        while (cur != src) {
            path.add(cur);
            // 不停往前找
            cur = pre[cur];
        }
        path.add(src);
        Collections.reverse(path);
        return path;
    }

    /**
     * 获取dst到src的距离值，虽然可以用上面的getPath得到的list长度来判定，
     * 但是很多题目可能只关心距离值，不关系具体的点，这是用这个dis数组是最合适的
     *
     * @param dst
     * @return
     */
    public int getDistance(int dst) {
        return dis[dst];
    }
}
