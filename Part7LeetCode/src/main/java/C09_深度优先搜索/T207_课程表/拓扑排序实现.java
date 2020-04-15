package C09_深度优先搜索.T207_课程表;

import java.util.*;

class SolutionTopoSort {
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
     * 根据先决条件构造邻接矩阵，在DFS过程中判断是否存在环，存在环则不能修完所有课程
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // 不能为null
        if (prerequisites == null || prerequisites.length ==0 || prerequisites[0].length == 0) {
            return true;
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
        return !hasCycle;
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
            if (mapAdj.get(cur)!=null){
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
}