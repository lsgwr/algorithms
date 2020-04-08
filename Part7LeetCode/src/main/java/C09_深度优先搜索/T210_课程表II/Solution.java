import java.util.*;
// 直接把207.拓扑排序的结果返回即可
class Solution {
    /**
     * 拓扑排序结果
     */
    private List<Integer> topoOrder;

    /**
     * 当前图是否有环
     */
    private boolean hasCycle = false;

    /**
     * 所有顶点的入度
     */
    private int[] inDegreesG;

    /**
     * 根据先决条件构造邻接矩阵，在DFS过程中判断是否存在环，存在环则不能修完所有课程。在207的基础上返回拓扑排序的结果即可
     */
    public int[] findOrder(int numCourses, int[][] prerequisites) {
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
        // 记录学完一个课程后下面可以学的课程列表，即邻接表
        Map<Integer, List<Integer>> mapAdj = new HashMap<>();
        // 每个点的入度
        inDegreesG = new int[numCourses];
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
            // prerequisite[1]  ==> prerequisite[0]
            adj.add(prerequisite[0]);
            inDegreesG[prerequisite[0]]++;
        }
        topoOrder = new ArrayList<>();
        sort(numCourses, mapAdj);
        // 没有环说明可以修完所有课程
        if (hasCycle) {
            // 存在环就返回空数组
            return new int[]{};
        } else {
            // 不存在环就返回拓扑排序的顺序
            for (int i = 0; i < numCourses; i++) {
                // 没有任何选课的限制时，直接顺序返回即可
                resultArr[i] = topoOrder.get(i);
            }
            return resultArr;
        }
    }

    /**
     * 拓扑排序核心
     */
    public void sort(int numCourses, Map<Integer, List<Integer>> mapAdj) {
        // 存储还未排序的入度为0的顶点
        Queue<Integer> queue = new ArrayDeque<>();
        int[] inDegrees = new int[numCourses];
        for (int v = 0; v < numCourses; v++) {
            inDegrees[v] = inDegreesG[v];
            if (inDegrees[v] == 0) {
                queue.add(v);
            }
        }
        while (!queue.isEmpty()) {
            int cur = queue.remove();
            topoOrder.add(cur);
            if (mapAdj.get(cur) != null) {
                for (int next : mapAdj.get(cur)) {
                    // 更新cur点的邻接点的入度
                    inDegrees[next]--;
                    if (inDegrees[next] == 0) {
                        // 更新后入度为0的顶点加入到queue中
                        queue.add(next);
                    }
                }
            }

        }
        if (topoOrder.size() != numCourses) {
            // 找不到入度为0的点但是还有点没被删除进行拓扑排序，说明图中有环
            hasCycle = true;
            // 没法进行拓扑排序就把已经加入的顶点清理掉
            topoOrder.clear();
        }
    }

    /**
     * int numCourses = 4;
     * int[][] prerequisites = {{1, 0}, {2, 0}, {3, 1}, {3, 2}};
     * <p>
     * 2
     * [[1,0],[0,1]]
     * 4
     * [[1,0],[2,0],[3,1],[3,2]]
     * 2
     * [[0,1]]
     */
    public static void main(String[] args) {
        int numCourses = 2;
        int[][] prerequisites = {{0, 1}};
        System.out.println(Arrays.toString(new Solution().findOrder(numCourses, prerequisites)));
    }
}
