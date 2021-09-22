# 06_Dijkstra

## 题目
### [AcWing 849.Dijkstra求最短路I](https://www.acwing.com/problem/content/851/)
```java
import java.io.*;
import java.util.*;

public class Main {
    static class ShortestPathDijkstra {
        private int start; // 求最短路径的起点，用户指定
        private int[] distances; // 各个定点到起始点的距离
        private boolean[] findShortest; // 是否找到了顶点到起始点的最小距离值

        public ShortestPathDijkstra(int start) {
            this.start = start;
            distances = new int[n + 1];
            Arrays.fill(distances, Integer.MAX_VALUE);
            // 起始点到起始点的最短距离为0
            distances[start] = 0;
            // 初始化所有的节点都没找到最短路径
            findShortest = new boolean[n + 1];
            Arrays.fill(findShortest, false);
            dijkstra();
        }

        public void dijkstra() {
            while (true) {
                /* 步骤1：确认一个顶点的距离值为到顶点的最小距离值 */
                // 本次循环的最小值的顶点编号
                int curV = -1;
                // 本次循环的最小值顶点对应的最小值
                int curDis = Integer.MAX_VALUE;
                for (int v = 1; v <= n; v++) {
                    // 遍历所有没有确定到起始点最小距离值的顶点,不断更新这些点中到起始点距离最小的点和其距离值
                    if (!findShortest[v] && distances[v] < curDis) {
                        curV = v;
                        curDis = distances[v];
                    }
                }
                // 所有的点的顶点都已经找到到起始点的最小距离值了，就退出for循环
                if (curV == -1) break;
                // 0到curV的最短路径就确定了
                findShortest[curV] = true;

                /* 步骤2：根据上面确认的最小距离值的顶点，更新起始点到其邻接点的距离值 */
                for (int w : adj[curV].keySet()) {
                    if (!findShortest[w] && distances[curV] + getWeight(curV, w) < distances[w]) {
                        distances[w] = distances[curV] + getWeight(curV, w);
                    }
                }
            }
        }

        // 起点start到定点v的最小距离值
        public int shortestDistanceTo(int v) {
            return distances[v];
        }
    }

    private static TreeMap<Integer, Integer>[] adj;
    private static int n;

    // 添加边,在顶点v和顶点w之间建立一条边
    public static void addEdge(int v, int w, int weight) {
        // v=w会生成自环边
        if (v == w) return; // 自环直接跳过
        if (hasEdge(v, w)) adj[v].put(w, Math.min(weight, adj[v].get(w))); // 有重边，则用较小值进行更新
        else adj[v].put(w, weight); // 无重边直接插入
    }

    // v和w之间是否存在边
    public static boolean hasEdge(int v, int w) {
        // v的邻接表中是否有w
        return adj[v].containsKey(w);
    }

    //  v和w之间的边的权重
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
        int m = Integer.parseInt(s[1]); // m条边，第3个参数是权值

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
        ShortestPathDijkstra dijkstra = new ShortestPathDijkstra(1);
        int dis = dijkstra.shortestDistanceTo(n);
        if (dis == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(dis);
    }
}
```

### [AcWing 850.Dijkstra求最短路 II](https://www.acwing.com/problem/content/description/852/)
```java
import java.io.*;
import java.util.*;

public class Main {
    static class ShortestPathDijkstra {
        private int start; // 求最短路径的起点，用户指定
        private int[] distances; // 各个顶点到起始点的距离
        private boolean[] findShortest; // 是否找到了顶点到起始点的最小距离值
        private int[] pre; // 记录访问顺序的数组

        // 存储顶点和顶点到起始点start的最小距离值(临时和最终的)
        private class Node implements Comparable<Node> {
            int v; // 节点编号
            int distance; // 节点v到起始点start的最小距离值(临时或最终的)

            public Node(int v, int distance) {
                this.v = v;
                this.distance = distance;
            }

            @Override
            public int compareTo(Node that) {
                return this.distance - that.distance;
            }
        }

        public ShortestPathDijkstra(int start) {
            this.start = start;
            distances = new int[n + 1];
            Arrays.fill(distances, Integer.MAX_VALUE);
            // 初始化访问顺序数组
            pre = new int[n + 1];
            Arrays.fill(pre, -1);
            // 起始点到起始点的最短距离为0
            distances[start] = 0;
            // 起始点的上一个访问节点认为是自己
            pre[start] = start;
            // 初始化所有的节点都没找到最短路径
            findShortest = new boolean[n + 1];
            Arrays.fill(findShortest, false);
            dijkstra();
        }

        public void dijkstra() {
            PriorityQueue<Node> pq = new PriorityQueue<>();
            pq.add(new Node(start, 0));
            while (!pq.isEmpty()) {
                /* 步骤1：确认一个顶点的距离值为到顶点的最小距离值 */
                // 复杂度是log级别的
                int curV = pq.remove().v;
                // 因为一个节点可能因为更新最小距离值而被加入多次，所以pq弹出节点可能弹出已经确定最小值的点，
                // 因此我们需要判断下，是地话就直接跳过
                if (findShortest[curV]) continue;
                // 0到curV的最短路径就确定了
                findShortest[curV] = true;

                /* 步骤2：根据上面确认的最小距离值的顶点，更新起始点到其邻接点的距离值 */
                for (int w : adj[curV].keySet()) {
                    if (!findShortest[w] && distances[curV] + getWeight(curV, w) < distances[w]) {
                        distances[w] = distances[curV] + getWeight(curV, w);
                        // 可能一个顶点会被加入多次，但是不影响，因为每次pq取出地都是最小值，
                        // 而我们每次加入地一个新的节点的重复值只会更小，所以取最小值的时候一定能取到我们新加入地值
                        pq.add(new Node(w, distances[w]));
                        pre[w] = curV;
                    }
                }
            }
        }

        /* 起点start到定点v的最小距离值 */ 
        public int shortestDistanceTo(int v) {
            return distances[v];
        }
    }

    private static TreeMap<Integer, Integer>[] adj;
    private static int n;

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
        int m = Integer.parseInt(s[1]); // m条边，第3个参数是权值

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
        ShortestPathDijkstra dijkstra = new ShortestPathDijkstra(1);
        int dis = dijkstra.shortestDistanceTo(n);
        if (dis == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(dis);
    }
}
```