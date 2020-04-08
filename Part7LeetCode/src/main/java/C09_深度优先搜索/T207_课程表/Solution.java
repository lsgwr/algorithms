// 本质是有向图的环检测问题
class Solution {
    /**
     * 默认没环
     **/
    private boolean hasCycle = false;

    /**
     * 存储顶点是否被访问的数组
     */
    private boolean[] visited;

    /**
     * 记录是否在向前路径的数组，递归回退时置为false
     */
    private boolean[] onPath;

    /**
     * 根据先决条件构造邻接矩阵，在DFS过程中判断是否存在环，存在环则不能修完所有课程
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // 不能为null
        if (prerequisites == null || prerequisites.length ==0 || prerequisites[0].length == 0) {
            return true;
        }
        // 初始化访问数组，用图的顶点个数来访问
        visited = new boolean[numCourses];
        // 初始化访问数组，用图的顶点个数来访问
        onPath = new boolean[numCourses];
        // 记录学完一个课程后下面可以学的课程列表，即邻接表
        Map<Integer, List<Integer>> mapAdj = new HashMap<>();
        for (int[] prerequisite : prerequisites) {
            List<Integer> adj;
            if (mapAdj.get(prerequisite[1]) == null) {
                // 不存在这个键的邻接表则要新建
                adj = new ArrayList<>();
                // 把新建的列表加进到map中
                mapAdj.put(prerequisite[1], adj);
            } else {
                // 之前存在这个键的邻接表，直接get
                adj = mapAdj.get(prerequisite[1]);
            }
            adj.add(prerequisite[0]);
        }
        // 下面用DFS检测是否存在环
        for (int v = 0; v < numCourses; v++) {
            if (!visited[v]) {
                if (dfs(v, mapAdj)) {
                    hasCycle = true;
                    break;
                }
            }
        }
        // 没有环说明可以修完所有课程
        return !hasCycle;
    }

    /**
     * 从顶点v出发，进行DFS，顺便检测当前图是否有环
     *
     * @param v 当前遍历到的点
     *          return        当前的图是否有环
     */
    private boolean dfs(int v, Map<Integer, List<Integer>> mapAdj) {
        visited[v] = true;
        onPath[v] = true;
        if (mapAdj.get(v) == null) {
            // 如果没有邻接边，要把onPath[v]设置回去，防止影响前置路径
            onPath[v] = false;
            return false;
        }
        for (Integer w : mapAdj.get(v)) {
            if (!visited[w]) {
                // w点没被访问的话就递归接着访问
                if (dfs(w, mapAdj)) {
                    // dfs递归往下访问，遇到有环就可以直接返回true了
                    return true;
                }
            } else if (onPath[w]) {
                //这个else分支就是有向图环检测的核心，即在我们的向前路径onPath上。不用无向图中的w!=parent是因为有向图中一条边如果是双向地也可以认为有环
                return true;
            }
        }
        // 递归回退时把标记取消
        onPath[v] = false;
        return false;
    }
}
/**
* 2
* [[1,0]]
* <p>
* 2
* [[1,0],[0,1]]
* <p>
* 1
* []
* <p>
* 2
* [[0,1]]
* <p>
* 3
* [[2,0],[2,1]]
*/