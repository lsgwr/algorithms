/***********************************************************
 * @Description : 1245.树的路径
 * https://leetcode-cn.com/problems/tree-diameter/
 * 所有点都作为起始点，计算每次单元最短路径的最大值，最后总的再计算一次最大值并返回
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/4/19 12:50
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C09_深度优先搜索.T1245_树的路径;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Solution {
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
        int result = 0;
        for (int i = 0; i < edges.length + 1; i++) {
            // 从每一个点开始进行BFS
            boolean[] visited = new boolean[edges.length + 1];
            int[] dist = new int[edges.length + 1];
            // 每轮BFS都更新最大值
            result = Math.max(bfs(i, visited, dist), result);
        }
        return result;
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
        int maxDistance = 0;
        while (!queue.isEmpty()) {
            int cur = queue.remove();
            for (int next : adjs[cur]) {
                // 遍历cur的所有邻接点
                if (!visited[next]) {
                    queue.add(next);
                    visited[next] = true;
                    dist[next] = dist[cur] + 1;
                    maxDistance = Math.max(dist[next], maxDistance);
                }
            }
        }
        return maxDistance;
    }

    public static void main(String[] args) {
        int[][] edges = {{0, 1}, {0, 2}};
        System.out.println(new Solution().treeDiameter(edges));
    }
}
