# 09_Prim

## 题目
### [AcWing 858.Prim算法求最小生成树](https://www.acwing.com/problem/content/860/)
```java
import java.io.*;
import java.util.*;

public class Main {
    private static int n, m;
    private static TreeMap<Integer, Integer>[] adj;
    private static int[] dist; // dist[i]表示上一个确定下来为最小生成树顶点的点到i的最小距离
    private static boolean[] st; // st是spanning tree的意思。st[i]为true表示顶点i已经确定为最终的最小生成树的一部分了
    private static final int INF = 0x3f3f3f3f;

    public static int prim() {
        int res = 0;
        // 每次都基于上一次的确定的位置找最短横切边
        for (int i = 0; i < n; i++) {
            int v = -1;
            // 每次都找最小权值点
            for (int w = 1; w <= n; w++) {
                if (!st[w] && (v == -1 || dist[w] < dist[v])) v = w;
            }
            if (i > 0 && dist[v] == INF) return INF;
            if (i > 0) res += dist[v]; // v确定为最小生成树的一个点了，结果中需要把边的权重加入进来
            for (int w = 1; w <= n; w++) {
                dist[w] = Math.min(dist[w], getWeight(v, w));
            }
            st[v] = true;
        }
        return res;
    }

    /* 添加边,在顶点v和顶点w之间建立一条边 */
    public static void addEdge(int v, int w, int weight) {
        // v=w会生成自环边
        if (v == w) return; // 自环直接跳过
        if (!hasEdge(v, w)) adj[v].put(w, weight); // 无重边直接插入
        else adj[v].put(w, Math.min(weight, adj[v].get(w))); // // 有重边，则用最小值进行更新
        // 无向图需要加两个边
        if (!hasEdge(w, v)) adj[w].put(v, weight); // 无重边直接插入
        else adj[w].put(v, Math.min(weight, adj[w].get(v))); // // 有重边，则用最小值进行更新
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
        // 这条边不存在就返回个极大值，方便后面求最小生成树
        return INF;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader b = new BufferedReader(new InputStreamReader(System.in));
        String[] s = b.readLine().split(" ");
        n = Integer.parseInt(s[0]); // n个顶点，下标从1开始
        m = Integer.parseInt(s[1]); // m条边，第3个参数是权值

        // 泛型数组需要强制转换，可以认为是Java语言的缺陷
        adj = (TreeMap<Integer, Integer>[]) new TreeMap[n + 1];
        for (int i = 1; i <= n; i++) adj[i] = new TreeMap<>(); // 初始化邻接表
        dist = new int[n + 1];
        Arrays.fill(dist, INF);
        st = new boolean[n + 1];

        // 添加边
        for (int i = 0; i < m; i++) {
            String[] edgeStr = b.readLine().split(" ");
            int v = Integer.parseInt(edgeStr[0]);
            int w = Integer.parseInt(edgeStr[1]);
            int weight = Integer.parseInt(edgeStr[2]);
            addEdge(v, w, weight);
        }
        int result = prim();
        if (result == INF) System.out.println("impossible");
        else System.out.println(result);
    }
}
```
