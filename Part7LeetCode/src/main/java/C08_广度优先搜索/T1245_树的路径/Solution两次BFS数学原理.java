/***********************************************************
 * @Description : 1245.树的路径
 * https://leetcode-cn.com/problems/tree-diameter/
 * 方法：先从任意一点P出发，找离它最远的点Q，再从点Q出发，找离它最远的点W，W到Q的距离就是是的直径
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/4/19 12:50
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C08_广度优先搜索.T1245_树的路径;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Set;

public class Solution两次BFS数学原理 {
    /**
     * adj[i]表示点i的所有邻接点
     */
    private Set<Integer>[] adjs;

    public int treeDiameter(int[][] edges) {
        if (edges == null || edges.length == 0) {
            return 0;
        }
        // 注意点的个数是length+1
        adjs = new Set[edges.length + 1];
        for (int i = 0; i < adjs.length; i++) {
            adjs[i] = new HashSet<>();
        }
        for (int[] edge : edges) {
            // 完善邻接表
            adjs[edge[0]].add(edge[1]);
            adjs[edge[1]].add(edge[0]);
        }
        // 从每一个点开始进行BFS
        boolean[] visited = new boolean[edges.length + 1];
        int[] dist = new int[edges.length + 1];
        int start = bfs(0, visited, dist);
        visited = new boolean[edges.length + 1];
        dist = new int[edges.length + 1];
        int end = bfs(start, visited, dist);
        // start到end的距离即为直径
        visited = new boolean[edges.length + 1];
        dist = new int[edges.length + 1];
        return bfs(start, end, visited, dist);
    }

    /**
     * 计算所有点中到起点的最大距离
     *
     * @param start   起点
     * @param visited 访问数组
     * @param dist    dist[i]表示i到start的距离
     * @return 所有点中到起点的最大距离
     */
    private int bfs(int start, int end, boolean[] visited, int[] dist) {
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        queue.add(start);
        visited[start] = true;
        // 起点到起点的距离
        dist[start] = 0;
        while (!queue.isEmpty()) {
            int cur = queue.remove();
            for (int next : adjs[cur]) {
                // 遍历cur的所有邻接点
                if (!visited[next]) {
                    queue.add(next);
                    visited[next] = true;
                    dist[next] = dist[cur] + 1;
                    if (next == end) {
                        // 到达终点直接返回
                        return dist[end];
                    }
                }
            }
        }
        // 返回最大距离对应的点
        return dist[end];
    }

    /**
     * 计算所有点中到起点的最大距离
     *
     * @param start   起点
     * @param visited 访问数组
     * @param dist    dist[i]表示i到start的距离
     * @return 所有点中到起点的最大距离
     */
    private int bfs(int start, boolean[] visited, int[] dist) {
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        queue.add(start);
        visited[start] = true;
        // 起点到起点的距离
        dist[start] = 0;
        // 最大的距离
        int maxDistance = 0;
        // 最大距离的点
        int maxDistancePoint = start;
        while (!queue.isEmpty()) {
            int cur = queue.remove();
            for (int next : adjs[cur]) {
                // 遍历cur的所有邻接点
                if (!visited[next]) {
                    queue.add(next);
                    visited[next] = true;
                    dist[next] = dist[cur] + 1;
                    if (dist[next] > maxDistance) {
                        maxDistance = Math.max(dist[next], maxDistance);
                        maxDistancePoint = next;
                    }
                }
            }
        }
        // 返回最大距离对应的点
        return maxDistancePoint;
    }

    public static void main(String[] args) {
        int[][] edges = {{0, 1}, {0, 2}};
        System.out.println(new Solution两次BFS数学原理().treeDiameter(edges));
    }
}
