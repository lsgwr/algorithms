import java.util.*;
import java.io.*;

public class Main {
    private static int n; // 一共的顶点数
    private static Map<Integer, List<Integer>> adj = new HashMap<>(); // 记录子节点(可能有多个)
    private static boolean[] visited;
    private static Map<String, Integer> cntMap = new HashMap<>(); // 以A-->B作为键，值为以B作为根节点的树的高度

    // DFS求当前的联通分量内点的个数，point是当前遍历到的点
    public static int dfs(int point) {
        int result = 1; // 至少当前节点算一个节点
        visited[point] = true;
        if (adj.get(point).size() == 1) return 1; // 只有叶子节点只有一个邻接点
        for (int nextPoint : adj.get(point)) {
            if (!visited[nextPoint]) {
                String key = point + "==>" + nextPoint;
                // 当前节点加上子树的节点数
                if (cntMap.get(key) == null) {
                    result += dfs(nextPoint);
                    cntMap.put(key, result);
                } else {
                    result += cntMap.get(key);
                }
            }
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader b = new BufferedReader(new InputStreamReader(System.in));
        String s = b.readLine();
        n = Integer.parseInt(s);

        for (int i = 0; i < n - 1; i++) {
            String[] edge = b.readLine().split(" ");
            int edge0 = Integer.parseInt(edge[0]);
            int edge1 = Integer.parseInt(edge[1]);
            if (adj.get(edge0) == null) {
                adj.put(edge0, new ArrayList<>());
            }
            adj.get(edge0).add(edge1);
            if (adj.get(edge1) == null) {
                adj.put(edge1, new ArrayList<>());
            }
            adj.get(edge1).add(edge0);
        }

        int nodeCntMaxMin = Integer.MAX_VALUE;
        // 每次尝试删除一个点，BFS求各个联通分量的最大顶点数，取所有删除点的情况的最小值，把本次删除的顶点输出
        for (int i = 1; i <= n; i++) {
            int nodeCntMax = 0;
            visited = new boolean[n + 1]; // 记录访问数组，用户BFS遍历过程中防止重复遍历
            visited[i] = true; // 防止下面再重复判断了
            // 因为是个树，i删除后，以i的邻接点开始遍历联通分量即可(i有几个邻接点就形成了几个联通分量)。而且树种不可能有孤立节点，所以不用担心adj.get(i)为null
            for (int j : adj.get(i)) nodeCntMax = Math.max(nodeCntMax, dfs(j));
            if (nodeCntMax < nodeCntMaxMin) nodeCntMaxMin = nodeCntMax;
        }
        System.out.println(nodeCntMaxMin);
    }
}