# 07_spfa

## 题目
### [AcWing 851.spfa求最短路](https://www.acwing.com/problem/content/853/)
```java
// 参考博客：http://keyblog.cn/article-21.html

import java.io.*;
import java.util.*;

public class Main {
    static class ShortestPathSpfa {
        private int[] dist; // 起始点start到每个顶点的最短距离
        private boolean[] inQueue; // 用queue地contains方法会超时，所以这里用哈希数据来代替

        public ShortestPathSpfa(int start) {
            dist = new int[n + 1];
            Arrays.fill(dist, INF);
            dist[start] = 0;
            inQueue = new boolean[n + 1];
            spfa(start);
        }

        /**
         * 1.每次从队列中取出一个节点v，遍历与v相通的w节点，查询比对  "w的长度" 和 "v的长度 + X与Y的长度"
         * 如果X的长度+ X与Y的长度 > Y的长度,说明需要更新操作。
         * 1）存入最短路。
         * 2）由于改变了原有的长度，所以需要往后更新，与这个节点相连的最短路。(即：判断下是否在队列，在就不用重复，不在就加入队列，等待更新)。
         * 3）在这期间可以记录这个节点的进队次数，判断是否存在负环。
         * <p>
         * 2.直到队空。
         *
         * @param start
         */
        public void spfa(int start) {
            Queue<Integer> q = new ArrayDeque<>();
            q.add(start);
            inQueue[start] = true;
            while (!q.isEmpty()) {
                int v = q.poll();
                inQueue[v] = false;
                for (int w : adj[v].keySet()) {
                    if (dist[w] > dist[v] + getWeight(v, w)) {
                        dist[w] = dist[v] + getWeight(v, w);
                        if (!inQueue[w]) {
                            q.add(w); // 队列中没有才加入
                            inQueue[w] = true;
                        }
                    }
                }
            }
        }

        public int shortestDistanceTo(int v) {
            return dist[v];
        }
    }

    private static TreeMap<Integer, Integer>[] adj;
    private static int n, m, INF = 0x3f3f3f3f;

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
        ShortestPathSpfa spfa = new ShortestPathSpfa(1);
        int dis = spfa.shortestDistanceTo(n);
        if (dis > INF / 2) System.out.println("impossible"); // 存在负权环或者没有直接退出
        else System.out.println(dis);
    }
}
```

### [AcWing 852.spfa判断负环](https://www.acwing.com/problem/content/854/)
```java
// 参考博客：http://keyblog.cn/article-21.html

import java.io.*;
import java.util.*;

public class Main {
    static class ShortestPathSpfa {
        private int[] dist; // 起始点start到每个顶点的最短距离
        private boolean[] inQueue; // 用queue地contains方法会超时，所以这里用哈希数据来代替
        private int[] num;
        private boolean neg = false; // 默认一开始不存在负环

        public ShortestPathSpfa(int start) {
            dist = new int[n + 1];
            Arrays.fill(dist, INF);
            dist[start] = 0;
            inQueue = new boolean[n + 1];
            num = new int[n + 1];
            spfa(start);
        }

        /**
         * 1.每次从队列中取出一个节点v，遍历与v相通的w节点，查询比对  "w的长度" 和 "v的长度 + X与Y的长度"
         * 如果X的长度+ X与Y的长度 > Y的长度,说明需要更新操作。
         * 1）存入最短路。
         * 2）由于改变了原有的长度，所以需要往后更新，与这个节点相连的最短路。(即：判断下是否在队列，在就不用重复，不在就加入队列，等待更新)。
         * 3）在这期间可以记录这个节点的进队次数，判断是否存在负环, 判断有无负环：如果某个点进入队列的次数超过N次则存在负环
         * <p>
         * 2.直到队空。
         *
         * @param start 求最短距离的起点
         */
        public void spfa(int start) {
            Queue<Integer> q = new ArrayDeque<>();
            // 判整个图的负环要将每个节点都加入
            for (int i = 1; i <= n; i++) {
                q.add(i);
                inQueue[i] = true;
            }
            while (!q.isEmpty()) {
                int v = q.poll();
                inQueue[v] = false;
                for (int w : adj[v].keySet()) {
                    if (dist[w] > dist[v] + getWeight(v, w)) {
                        dist[w] = dist[v] + getWeight(v, w);
                        num[w]++;
                        if (num[w] >= n) { // 存在负环的话也不用继续求最短距离了，不存在的
                            neg = true;
                            return;
                        }
                        if (!inQueue[w]) {
                            q.add(w); // 队列中没有才加入
                            inQueue[w] = true;
                        }
                    }
                }
            }
        }

        public boolean hasNegativeCircle() {
            return neg;
        }
    }

    private static TreeMap<Integer, Integer>[] adj;
    private static int n, m, INF = 0x3f3f3f3f;

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
        ShortestPathSpfa spfa = new ShortestPathSpfa(1);
        System.out.println(spfa.hasNegativeCircle() ? "Yes" : "No");
    }
}
```