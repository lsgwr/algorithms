package C09_深度优先搜索.T261_以图判树;

import java.util.TreeSet;

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
     * 是否有环
     */
    private boolean hasCycle = false;

    /**
     * 联通分量有且只有一个+ 没有环，才能组成一个树
     */
    public boolean validTree(int N, int[][] edges) {
        
        if (edges == null || edges.length == 0 || edges[0].length == 0) {
            if(N > 1){
                return false;
            }else {
                return true;
            }
            
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

        // DFS检测连通分量
        // 从dfs(0)改成下面的代码，可以支持非连通的图
        for (int v = 0; v < N; v++) {
            if (!visited[v]) {
                // 第2个参数传入v，意思是起始点的parent节点可以认为是自己
                if (dfs(v, v)) {
                    hasCycle = true;
                    break;
                }
                // 当退出递归时，相当于结束了一个连通图的遍历，所以连通分量数加1
                connectedComponentCount++;
            }
        }
        return connectedComponentCount == 1 && !hasCycle;
    }

    /**
     * 从顶点v出发，进行DFS，顺便检测当前图是否有环
     *
     * @param v      当前遍历到的点
     * @param parent v的上一个访问节点
     *               return        当前的图是否有环
     */
    private boolean dfs(int v, int parent) {
        visited[v] = true;
        for (Integer w : adj[v]) {
            if (!visited[w]) {
                // w点没被访问的话就递归接着访问
                if (dfs(w, v)) {
                    // dfs递归往下访问，遇到有环就可以直接返回true了
                    return true;
                }
            } else if (w != parent) {
                // 原理：当检测到一个节点(当前节点current)的相邻节点已经被visited但是这个相邻节点不是current的上一个visited节点，就说明图中有环了
                return true;
            }
        }
        return false;
    }

    /**
     * 5
     * [[0,1],[0,2],[0,3],[1,4]]
     * 5
     * [[0,1], [1,2], [2,3], [1,3], [1,4]]
     */
    public static void main(String[] args) {
        int N = 5;
        int[][] edges = {{0, 1}, {1, 2}, {2, 3}, {1, 3}, {1, 4}};
        System.out.println(new Solution().validTree(N, edges));
    }
}
