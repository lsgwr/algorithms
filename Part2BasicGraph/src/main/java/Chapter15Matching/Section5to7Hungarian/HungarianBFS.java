/***********************************************************
 * @Description : 基于图的BFS遍历实现的匈牙利算法(Hungarian算法)
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2019/12/28 0:29
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter15Matching.Section5to7Hungarian;

import Chapter02GraphExpress.Graph;
import Chapter04DFSInAction.Section10BiPartitionDetect.GraphDFSBiPartitionDetect;

import java.util.*;

public class HungarianBFS {
    /**
     * 要找最大匹配的二分图
     */
    private Graph graph;
    /**
     * 最大匹配的值
     */
    private int maxMatch = 0;

    /**
     * match[v]=w表示顶点v在图中匹配的顶点是w
     */
    private int[] matching;

    public HungarianBFS(Graph graph) {
        GraphDFSBiPartitionDetect biPartitionDetect = new GraphDFSBiPartitionDetect(graph);
        if (!biPartitionDetect.isBiPartition()) {
            throw new IllegalArgumentException("匹配问题必须针对地是二分图!");
        }
        this.graph = graph;
        // 二分图中的顶点颜色区分，一半为0(左侧)，一半为1(右侧)
        int[] colors = biPartitionDetect.getColors();
        int V = graph.V();
        matching = new int[V];
        Arrays.fill(matching, -1);
        for (int v = 0; v < V; v++) {
            // 只遍历二分图中左侧还未被遍历的点(颜色为0)
            if (colors[v] == 0 && matching[v] == -1) {
                // 每个左侧的点都进行一次bfs来找增广路径
                if (bfs(v)) {
                    // 本地bfs找到了一条增广路径
                    maxMatch++;
                }
            }
        }
    }

    /**
     * bfs找增广路径
     *
     * @param source bfs遍历的起点
     * @return 本次bfs是否找到了增广路径
     */
    private boolean bfs(int source) {
        Queue<Integer> queue = new ArrayDeque<>();
        int V = graph.V();
        // 记录节点访问顺序的数组，每次bfs都需要新建自己的pre
        int[] pre = new int[V];
        Arrays.fill(pre, -1);
        queue.add(source);
        // 起点的上一个访问节点认为是自己
        pre[source] = source;
        while (!queue.isEmpty()) {
            // v一定要用二分图左侧的顶点(本类中0标记)
            int v = queue.remove();
            for (int w : graph.adj(v)) {
                // w是二分图右侧的顶点
                if (pre[w] == -1) {
                    if (matching[w] != -1) {
                        // w已经匹配
                        pre[w] = v;
                        // 和w匹配的边的前一条边记为w
                        pre[matching[w]] = w;
                        // 添加左侧的点到queue中，queue中只存左侧的点
                        queue.add(matching[w]);
                    } else {
                        // 在二分图右侧找到了一个未匹配的点，即找到了增广路径
                        pre[w] = v;
                        List<Integer> augPath = getAugPath(pre, v, w);
                        // 匈牙利算法核心：匹配状态取反。即匹配边变非匹配边、非匹配边变匹配边
                        for (int i = 0; i < augPath.size(); i += 2) {
                            matching[augPath.get(i)] = augPath.get(i + 1);
                            matching[augPath.get(i + 1)] = augPath.get(i);
                        }
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * 获取增广路径详情
     *
     * @param pre   节点访问顺序数组
     * @param start 路径起点
     * @param end   路径终点
     * @return 路径上的点的顺序列表
     */
    private List<Integer> getAugPath(int[] pre, int start, int end) {

        List<Integer> path = new ArrayList<>();
        int cur = end;
        while (cur != start) {
            path.add(cur);
            cur = pre[cur];
        }
        path.add(start);
        // 增广路径无论正反都是增广路径，所以这里逆序与否均可了
        Collections.reverse(path);
        return path;
    }

    /**
     * 获取最大匹配数
     */
    public int getMaxMatch() {
        return maxMatch;
    }

    /**
     * 当前的最大匹配是否是完全匹配(也可以叫完全匹配，即所有的点都有二分图另一侧的点和自己匹配而且匹配对互不干扰O)
     */
    public boolean isPrefectMatch() {
        return maxMatch * 2 == graph.V();
    }
}
