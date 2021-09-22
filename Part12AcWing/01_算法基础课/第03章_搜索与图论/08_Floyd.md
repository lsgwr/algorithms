# 08_Floyd

## 题目
### [AcWing 854.Floyd求最短路](https://www.acwing.com/problem/content/856/)
```java
import java.io.*;
import java.util.*;

public class Main {
    static class ShortestPathAllFloyd {
        /**
         * 存储所有点对的最短路径，distances[v][w]表示v到w的最短路径
         */
        private int[][] distances;

        /**
         * 是否有负权环
         */
        private boolean negativeCycle;

        public ShortestPathAllFloyd() {
            floyd();
        }

        /**
         * Floyd算法求所有点对的最短路径
         */
        public void floyd() {
            int V = n + 1;
            distances = new int[V][V];
            for (int i = 1; i < V; i++) {
                Arrays.fill(distances[i], Integer.MAX_VALUE);
            }
            for (int v = 1; v < V; v++) {
                // 自己到自己的权值就是0
                distances[v][v] = 0;
                for (int w : adj[v].keySet()) {
                    // v到邻接边的距离初始化为边v-w的权重
                    distances[v][w] = getWeight(v, w);
                }
            }
            for (int t = 0; t < V; t++) {
                for (int v = 0; v < V; v++) {
                    for (int w = 0; w < V; w++) {
                        // 边v-t和边t-w必须已经被访问了，初始值不再是Integer.MAX_VALUE
                        if (distances[v][t] != Integer.MAX_VALUE && distances[t][w] != Integer.MAX_VALUE) {
                            // 如果松弛一条边，v-t-w的距离小于v-w，则进行最小距离的更新
                            if (distances[v][t] + distances[t][w] < distances[v][w]) {
                                // 更新最小距离
                                distances[v][w] = distances[v][t] + distances[t][w];
                            }
                        }
                    }
                }
            }
            for (int v = 0; v < V; v++) {
                if (distances[v][v] < 0) {
                    negativeCycle = true;
                }
            }
        }

        /**
         * 图中是否有负权环
         *
         * @return
         */
        public boolean hasNegativeCycle() {
            return negativeCycle;
        }

        /**
         * 判断顶点v和顶点w是否是联通，不是Integer.MAX_VALUE表示更新过了，即v和w是联通的
         */
        public boolean isConnectedTo(int v, int w) {
            if (negativeCycle) {
                throw new RuntimeException("图中存在负权环！");
            }
            return distances[v][w] != Integer.MAX_VALUE;
        }

        /**
         * 顶点v和w之间的最小距离
         */
        public int shortestDistanceBetween(int v, int w) {
            if (negativeCycle) {
                throw new RuntimeException("图中存在负权环！");
            }
            return distances[v][w];
        }
    }

    private static TreeMap<Integer, Integer>[] adj;
    private static int n, m, k;

    /* 添加边,在顶点v和顶点w之间建立一条边 */
    public static void addEdge(int v, int w, int weight) {
        // v=w会生成自环边
        if (v == w) return; // 自环直接跳过
        if (!hasEdge(v, w)) adj[v].put(w, weight); // 无重边直接插入
        else adj[v].put(w, Math.min(weight, adj[v].get(w))); // // 有重边，则用最小值进行更新
    }

    /* v和w之间是否存在边 */
    public static boolean hasEdge(int v, int w) {
        // v的邻接表中是否有w
        return adj[v].containsKey(w);
    }

    /* v和w之间的边的权重 */
    public static int getWeight(int v, int w) {
        if (hasEdge(v, w)) {
            return adj[v].get(w);
        }
        throw new IllegalArgumentException("边" + v + "-" + w + "不存在！");
    }

    public static void main(String[] args) throws IOException {
        BufferedReader b = new BufferedReader(new InputStreamReader(System.in));
        String[] s = b.readLine().split(" ");
        n = Integer.parseInt(s[0]); // n个顶点，下标从1开始
        m = Integer.parseInt(s[1]); // m条边，第3个参数是权值
        k = Integer.parseInt(s[2]); // 有几个插叙

        // 泛型数组需要强制转换，可以认为是Java语言的缺陷
        adj = (TreeMap<Integer, Integer>[]) new TreeMap[n + 1];
        for (int i = 1; i <= n; i++) adj[i] = new TreeMap<>(); // 初始化邻接表

        // 添加边
        for (int i = 0; i < m; i++) {
            String[] edgeStr = b.readLine().split(" ");
            int v = Integer.parseInt(edgeStr[0]);
            int w = Integer.parseInt(edgeStr[1]);
            int weight = Integer.parseInt(edgeStr[2]);
            addEdge(v, w, weight);
        }
        ShortestPathAllFloyd floyd = new ShortestPathAllFloyd();

        while (k-- > 0) {
            String[] pointsStr = b.readLine().split(" ");
            int from = Integer.parseInt(pointsStr[0]);
            int to = Integer.parseInt(pointsStr[1]);
            int dis = floyd.shortestDistanceBetween(from, to);
            if (dis == Integer.MAX_VALUE) System.out.println("impossible");
            else System.out.println(dis);
        }
    }
}
```