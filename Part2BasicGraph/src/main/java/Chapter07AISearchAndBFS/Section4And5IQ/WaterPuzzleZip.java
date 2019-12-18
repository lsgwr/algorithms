/* @description : 有两个水桶，一个装5升，一个装3升，怎么利用这两个水桶，得到4升水？
 * 基于压缩状态的BFS实现，需要开比较多地高维数组
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2019-12-18 11:10
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter07AISearchAndBFS.Section4And5IQ;

import java.util.*;

public class WaterPuzzleZip {

    /**
     * 节点是否被访问,最大长度设置为100，因为每个水桶的水容量都在10以下,两个水桶即10*10=100
     */
    private boolean[] visited = new boolean[100];

    /**
     * 记录每个节点的上一个访问节点，限制是水桶容量都在10以下，两个水桶即10*10=100
     */
    private int[] pre = new int[100];

    private int end = -1;

    /**
     * 广度优先遍历的过程
     */
    public void bfs() {
        Queue<Integer> queue = new ArrayDeque<>();
        // 初始地时候两个水桶都为空
        int water = 0;
        queue.add(water);
        // 加入到队列就认为是被访问过了
        visited[water] = true;
        // 队列不为空
        while (!queue.isEmpty()) {
            // 出栈
            int waterCur = queue.remove();
            // 第1个水桶的水量
            int a = waterCur / 10;
            // 第2个水桶的水量
            int b = waterCur % 10;
            // 下面获取当前状态的邻接状态
            List<Integer> adjs = new ArrayList<>();
            // 情况1：第一个水桶加满水，第二个水桶不变
            int adj1 = 5 * 10 + b;
            adjs.add(adj1);
            // 情况2：第一个水桶不变，第二个水桶加满水
            int adj2 = a * 10 + 3;
            adjs.add(adj2);
            // 情况3：第一个水桶清空，第二个水桶不变
            int adj3 = 0 * 10 + b;
            adjs.add(adj3);
            // 情况4：第一个水桶不变，第二个水桶清空
            int adj4 = a * 10 + 0;
            adjs.add(adj4);
            // 情况5：第1个水桶往第2个水桶倒水，能倒满就倒满，倒满不了就全倒进去.取这两种倒法的较小值
            int pour1 = Math.min(a, 3 - b);
            int adj5 = (a - pour1) * 10 + (b + pour1);
            adjs.add(adj5);
            // 情况6：第2个水桶往第1个水桶倒水，能倒满就倒满，倒满不了就全倒进去.取这两种倒法的较小值
            int pour2 = Math.min(5 - a, b);
            int adj6 = (a + pour2) * 10 + (b - pour2);
            adjs.add(adj6);

            // 遍历所有当前状态的连接点
            for (int adj : adjs) {
                // 如果当前状态没被访问地话
                if (!visited[adj]) {
                    queue.add(adj);
                    // 加入到队列就认为是被访问过了
                    visited[adj] = true;
                    // 设置当前点的上一个邻接点
                    pre[adj] = waterCur;
                    // 遍历到任何一个桶中有4升水就直接退出，最终状态只有这一个点
                    if (adj / 10 == 4 || adj % 10 == 4) {
                        end = adj;
                        return;
                    }
                }
            }
        }
    }

    private List<Integer> getPath() {
        List<Integer> path = new ArrayList<>();
        if (end == -1) {
            // 达不到想要的状态
            return null;
        }
        int cur = end;
        path.add(cur);
        // 只要没到起点(都为0)就接着赋值
        while (cur != 0) {
            cur = pre[cur];
            path.add(cur);
        }
        // 颠倒下顺序，编程从起点到终点的过程
        Collections.reverse(path);
        return path;
    }

    public static void main(String[] args) {
        WaterPuzzleZip waterPuzzle = new WaterPuzzleZip();
        waterPuzzle.bfs();
        List<Integer> path = waterPuzzle.getPath();
        if (path == null) {
            System.out.println("现有条件完成不了目标");
        } else {
            System.out.print("找到倒水的策略啦：");
            for (int cur : path) {
                System.out.print("(" + cur / 10 + ", " + cur % 10 + ") ");
            }
        }
    }
}
/**
 * 找到倒水的策略啦：(0, 0) (5, 0) (2, 3) (2, 0) (0, 2) (5, 2) (4, 3)
 */