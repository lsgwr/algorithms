/* @description : 有两个水桶，一个装5升，一个装3升，怎么利用这两个水桶，得到4升水？
 * 基于非压缩状态的BFS实现，需要开比较多地高维数组
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2019-12-17 15:31
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter07AISearchAndBFS.Section4And5IQ;

import java.util.*;

public class WaterPuzzle {

    /**
     * 节点是否被访问,最大长度设置为10，因为每个水桶的水容量都在10以下
     */
    private boolean[][] visited = new boolean[10][10];

    /**
     * 记录每个节点的上一个访问节点，限制是水桶容量都在10以下
     */
    private int[][][] pre = new int[10][10][2];

    private int[] end;

    /**
     * 广度优先遍历的过程
     */
    public void bfs() {
        Queue<int[]> queue = new ArrayDeque<>();
        // 初始地时候两个水桶都为空
        int[] water = {0, 0};
        queue.add(water);
        // 加入到队列就认为是被访问过了
        visited[0][0] = true;
        // 队列不为空
        while (!queue.isEmpty()) {
            // 出栈
            int[] waterCur = queue.remove();
            // 下面获取当前状态的邻接状态
            List<int[]> adjs = new ArrayList<>();
            // 情况1：第一个水桶加满水，第二个水桶不变
            int[] adj1 = {5, waterCur[1]};
            adjs.add(adj1);
            // 情况2：第一个水桶不变，第二个水桶加满水
            int[] adj2 = {waterCur[0], 3};
            adjs.add(adj2);
            // 情况3：第一个水桶清空，第二个水桶不变
            int[] adj3 = {0, waterCur[1]};
            adjs.add(adj3);
            // 情况4：第一个水桶不变，第二个水桶清空
            int[] adj4 = {waterCur[0], 0};
            adjs.add(adj4);
            // 情况5：第1个水桶往第2个水桶倒水，能倒满就倒满，倒满不了就全倒进去.取这两种倒法的较小值
            int pour1 = Math.min(waterCur[0], 3 - waterCur[1]);
            int[] adj5 = {waterCur[0] - pour1, waterCur[1] + pour1};
            adjs.add(adj5);
            // 情况6：第2个水桶往第1个水桶倒水，能倒满就倒满，倒满不了就全倒进去.取这两种倒法的较小值
            int pour2 = Math.min(5 - waterCur[0], waterCur[1]);
            int[] adj6 = {waterCur[0] + pour2, waterCur[1] - pour2};
            adjs.add(adj6);

            // 遍历所有当前状态的连接点
            for (int[] adj : adjs) {
                // 如果当前状态没被访问地话
                if (!visited[adj[0]][adj[1]]) {
                    queue.add(adj);
                    // 加入到队列就认为是被访问过了
                    visited[adj[0]][adj[1]] = true;
                    // 设置当前点的上一个邻接点
                    pre[adj[0]][adj[1]][0] = waterCur[0];
                    pre[adj[0]][adj[1]][1] = waterCur[1];
                    // 遍历到任何一个桶中有4升水就直接退出，最终状态只有这一个点
                    if (adj[0] == 4 || adj[1] == 4) {
                        end = adj;
                        return;
                    }
                }
            }
        }
    }

    private List<int[]> getPath() {
        List<int[]> path = new ArrayList<>();
        if (end == null) {
            // 达不到想要的状态
            return null;
        }
        int[] cur = end;
        path.add(cur);
        // 只要没到起点(都为0)就接着赋值
        while (cur[0] != 0 || cur[1] != 0) {
            cur = pre[cur[0]][cur[1]];
            path.add(cur);
        }
        // 颠倒下顺序，编程从起点到终点的过程
        Collections.reverse(path);
        return path;
    }

    public static void main(String[] args) {
        WaterPuzzle waterPuzzle = new WaterPuzzle();
        waterPuzzle.bfs();
        List<int[]> path = waterPuzzle.getPath();
        if (path == null) {
            System.out.println("现有条件完成不了目标");
        } else {
            System.out.print("找到倒水的策略啦：");
            for (int[] cur : path) {
                System.out.print("(" + cur[0] + ", " + cur[1] + ") ");
            }
        }
    }
}
/**
 * 找到倒水的策略啦：(0, 0) (5, 0) (2, 3) (2, 0) (0, 2) (5, 2) (4, 3)
 */
