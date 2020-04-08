// 图中需要考虑并列
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

    private List<Integer> result;

    private int numCourses;

    /**
     * 根据先决条件构造邻接矩阵，在DFS过程中判断是否存在环，存在环则不能修完所有课程
     */
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        this.numCourses = numCourses;
        // 不存在环，而且路径存在则返回结果
        int[] resultArr = new int[numCourses];
        // 不能为null
        if (prerequisites == null || prerequisites.length == 0 || prerequisites[0].length == 0) {
            for (int i = 0; i < numCourses; i++) {
                // 没有任何选课的限制时，直接顺序返回即可
                resultArr[i] = i;
            }
            return resultArr;
        }
        // 初始化访问数组，用图的顶点个数来访问
        visited = new boolean[numCourses];
        // 初始化访问数组，用图的顶点个数来访问
        onPath = new boolean[numCourses];
        // 记录学完一个课程后下面可以学的课程列表，即邻接表
        Map<Integer, Set<Integer>> mapAdj = new HashMap<>();
        for (int[] prerequisite : prerequisites) {
            Set<Integer> adj;
            if (mapAdj.get(prerequisite[1]) == null) {
                // 不存在这个键的邻接表则要新建
                adj = new HashSet<>();
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
                Set<Integer> path = new HashSet<>();
                // 先把起点加入进去
                path.add(v);
                if (dfs(v, mapAdj, path)) {
                    hasCycle = true;
                    break;
                }
            }
        }
        // 没有环说明可以修完所有课程把result返回即可
        if (!hasCycle && result != null) {
            for (int i = 0; i < result.size(); i++) {
                resultArr[i] = result.get(i);
            }
        } else {
            return new int[]{};
        }
        return resultArr;
    }

    /**
     * 从顶点v出发，进行DFS，顺便检测当前图是否有环
     *
     * @param v 当前遍历到的点
     *          return        当前的图是否有环
     */
    private boolean dfs(int v, Map<Integer, Set<Integer>> mapAdj, Set<Integer> path) {
        visited[v] = true;
        onPath[v] = true;
        if (mapAdj.get(v) != null) {
            // 把v的所有邻接点加入进去
            path.addAll(mapAdj.get(v));
            // 一旦找到符合条件的路径，就设置到result全局变量中，result设置一次即可
            if (result == null && path.size() == numCourses) {
                // 复制一份到result
                result = new ArrayList<>(path);
            }
            for (Integer w : mapAdj.get(v)) {
                if (!visited[w]) {
                    // w点没被访问的话就递归接着访问
                    if (dfs(w, mapAdj, path)) {
                        // dfs递归往下访问，遇到有环就可以直接返回true了
                        return true;
                    }
                } else if (onPath[w]) {
                    //这个else分支就是有向图环检测的核心，即在我们的向前路径onPath上。不用无向图中的w!=parent是因为有向图中一条边如果是双向地也可以认为有环
                    return true;
                }
            }
            // 本次递归完毕，要把所有邻接点移走
            path.removeAll(mapAdj.get(v));
        }
        // 递归回退时把标记取消
        visited[v] = false;
        onPath[v] = false;
        return false;
    }
}
/**
* int numCourses = 4;
* int[][] prerequisites = {{1, 0}, {2, 0}, {3, 1}, {3, 2}};
*
* int numCourses = 2;
* int[][] prerequisites = {{0, 1}};
*/