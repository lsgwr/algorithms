class Solution {
     /**
     * 存储邻接表
     */
    private TreeSet<Integer>[] adj;

    /**
     * 存储顶点是否被访问的数组
     */
    private boolean[] visited;

    /**
     * 连通分量计数器
     */
    private int connectedComponentCount;

    /**
     * 联通分量有且只有一个+ 没有环，才能组成一个树
     */
    public int countComponents(int N, int[][] edges) {
        
        if (edges == null || edges.length == 0 || edges[0].length == 0) {
            // 没有边时，有多少个点就有多少个联通分量
            return N;
        }
        
        // 初始化访问数组，用图的顶点个数来访问
        visited = new boolean[N];
        // 泛型数组需要强制转换，可以认为是Java语言的缺陷
        adj = (TreeSet<Integer>[]) new TreeSet[N];
        for (int i = 0; i < N; i++) {
            // 每个顶点都有一组邻边组成邻接表，用TreeSet可以提高性能
            adj[i] = new TreeSet<>();
        }

        // 构建邻接表
        for (int[] edge : edges) {
            int v = edge[0];
            int w = edge[1];
            // 无向图需要加两条边
            adj[v].add(w);
            adj[w].add(v);
        }

         // 从dfs(0)改成下面的代码，可以支持非连通的图
        for (int v = 0; v < N; v++) {
            if (!visited[v]) {
                dfs(v);
                // 当退出递归时，相当于结束了一个连通图的遍历，所以连通分量数加1
                connectedComponentCount++;
            }
        }
        return connectedComponentCount;
    }

    private void dfs(int v) {
        visited[v] = true;
        for (Integer w : adj[v]) {
            if (!visited[w]) {
                // w点没被访问的话就递归接着访问
                dfs(w);
            }
        }
    }
}
/**
5
[[0,1],[1,2],[3,4]]

1
[]
*/