# 10_Kruskal

## 题目
### [AcWing 859.Kruskal算法求最小生成树](https://www.acwing.com/problem/content/861/)
```java
import java.io.*;
import java.util.*;

public class Main {
    private static int n, m;
    private static Edge[] edges;
    private static int[] parent;

    static class Edge {
        int v, w, weight;

        public Edge(int v, int w, int weight) {
            this.v = v;
            this.w = w;
            this.weight = weight;
        }
    }

    public static int kruskal() {
        for (int i = 1; i < n; i++) parent[i] = i;
        int res = 0, cnt = 0;
        for (int i = 0; i < m; i++) {
            int v = edges[i].v;
            int w = edges[i].w;
            int weight = edges[i].weight;
            v = find(v);
            w = find(w);
            if (v != w) {
                parent[v] = w;
                res += weight;
                cnt++;
            }
        }
        if (cnt < n - 1) return -1;
        else return res;
    }

    // 并查集，获取x所在树的根节点
    static int find(int x) {
        if (parent[x] != x) parent[x] = find(parent[x]);
        return parent[x];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader b = new BufferedReader(new InputStreamReader(System.in));
        String[] s = b.readLine().split(" ");
        n = Integer.parseInt(s[0]); // n个顶点，下标从1开始
        m = Integer.parseInt(s[1]); // m条边，第3个参数是权值
        edges = new Edge[m]; // m条边
        parent = new int[n + 1]; // 用于并查集，记录每个顶点的根节点

        // 添加边
        for (int i = 0; i < m; i++) {
            String[] edgeStr = b.readLine().split(" ");
            int v = Integer.parseInt(edgeStr[0]);
            int w = Integer.parseInt(edgeStr[1]);
            int weight = Integer.parseInt(edgeStr[2]);
            edges[i] = new Edge(v, w, weight);
        }

        // 贪心算法，按照边的权重排序
        Arrays.sort(edges, new Comparator<Edge>() {
            @Override
            public int compare(Edge edge1, Edge edge2) {
                return edge1.weight - edge2.weight;
            }
        });
        int result = kruskal();
        if (result == -1) System.out.println("impossible");
        else System.out.println(result);
    }
}
```