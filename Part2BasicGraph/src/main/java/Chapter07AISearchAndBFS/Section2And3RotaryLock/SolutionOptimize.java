/***********************************************************
 * @Description : 转盘锁问题-状态转为图的顶点
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2019/8/11 15:58
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter07AISearchAndBFS.Section2And3RotaryLock;

import java.util.*;

public class SolutionOptimize {
    /**
     * 单源路径的起点、目标点,这里是字符串
     */
    private String source, target;

    /**
     * 广度优先遍历的顺序结果(只有一种，不像DFS有前序、后序两种)
     */
    private List<String> orderList = new ArrayList<>();

    /**
     * 顶点的访问情况的数组，因为顶点现在用类似"6745",所以键需要改成String标识顶点，值为Boolean表示是否被访问过，默认是false
     */
    private HashMap<String, Boolean> visited = new HashMap<>();

    /**
     * pre数组是为了记录起点source到目标点target的路径(中间经过了哪些点),key表示顶点，value标识上一个访问的顶点
     */
    private HashMap<String, String> pre = new HashMap<>();

    /**
     * distance.get("xxxx")表示点顶点(或状态)到起点"0000"的距离，key标识当前顶点(或者状态),value标识当前顶点到起点的距离
     */
    private HashMap<String, Integer> distance = new HashMap<>();

    /**
     * 存放不可遍历的顶点
     */
    private HashSet<String> deadList = new HashSet<>();

    public int openLock(String[] deadends, String target) {
        this.source = "0000";
        this.target = target;
        // deadList.contains(状态)可用于盘点当前状态是否是deadend，而且contains底层是二分查找效率高
        deadList.addAll(Arrays.asList(deadends));
        // 先讨论特殊情况，起点和终点是死亡数字就直接退出
        if (deadList.contains(this.target) || deadList.contains(this.source)) {
            return -1;
        }
        // 终点就是起点，直接返回0步
        if (target.equals(this.source)) {
            return 0;
        }

        // 下面就是广度优先遍历了
        bfs();
        return shortestDistance();
    }

    /**
     * 计算当前点的邻接点
     *
     * @param v   当前遍历到的点
     * @param adj 存储v的邻接点的列表
     */
    private void getAdj(String v, List<String> adj) {
        char[] vArr = v.toCharArray();
        for (int i = 0; i < 4; i++) {
            // 记录指定位置当前的字符
            char o = vArr[i];

            // 向前拨一位
            vArr[i] = Character.forDigit((vArr[i] - '0' + 1) % 10, 10);
            adj.add(new String(vArr));
            // 字符归位
            vArr[i] = o;

            // 向后拨一位,不能用-1因为可能出现负数，所以用+9
            // vArr[i] = Character.forDigit((vArr[i] - '0' - 1) % 10, 10);
            vArr[i] = Character.forDigit((vArr[i] - '0' + 9) % 10, 10);
            adj.add(new String(vArr));
            // 字符归位
            vArr[i] = o;
        }
    }

    /**
     * 从source点开始进行广度优先遍历
     */
    private void bfs() {
        Queue<String> queue = new ArrayDeque<>();
        queue.add(source);
        // 已访问集合加入source点
        visited.put(source, true);
        // source到source的步骤距离是0
        distance.put(source, 0);
        while (!queue.isEmpty()) {
            // 当前顶点vertex
            String v = queue.remove();
            orderList.add(v);
            // 算出当前顶点的所有邻接点(字符的逻辑转换)
            List<String> adj = new ArrayList<>();
            getAdj(v, adj);
            // BFS遍历所有邻接点
            for (String w : adj) {
                // w是指v的邻接点
                if (!deadList.contains(w) && visited.get(w) == null) {
                    // 如果w顶点不在禁止访问列表中并且还没被访问
                    queue.add(w);
                    // w标记为已经访问
                    visited.put(w, true);
                    // 记录w的上一个访问节点就是v
                    pre.put(w, v);
                    // 设置当前点到起点的距离
                    distance.put(w, distance.get(v) + 1);
                    if (w.equals(target)) {
                        // 如果当前邻接点就是要找的target，直接退出,遍历结束
                        return;
                    }
                }
            }
        }
    }

    /**
     * 从source到target的最短路径值
     */
    public Integer shortestDistance() {
        Integer distance = this.distance.get(target);
        if (distance == null) {
            // 没有找到路径时返回-1
            distance = -1;
        }
        return distance;
    }

    /**
     * 判断图的遍历起点是否和target点连通，实际只需要看下visit[v]是否为true即可，为true表示在一个连通分量上，肯定是连通地
     *
     * @return 判断图的遍历起点是否和target点连通
     */
    public boolean isConnected() {
        return visited.get(target);
    }

    /**
     * 找到起点source到目标定点target的路径
     *
     * @return 可迭代的对象，一般是集合，用于存储source到target的完整路径
     */
    public Iterable<String> path() {
        List<String> pathList = new ArrayList<>();
        // source到target有路径才进行路径查找
        if (isConnected()) {
            // 用pre数组从target一直找到source点，记录下中间经过的所有点，就是要求的单源路径
            String current = target;
            while (!current.equals(source)) {
                // 没有推到起点就一直推
                pathList.add(current);
                current = pre.get(current);
            }
            // 起点要加上
            pathList.add(source);
            // 因为是从source到target的路径，所以要颠倒下
            Collections.reverse(pathList);
            return pathList;
        } else {
            // 没有路径就直接返回空集合
            return pathList;
        }
    }

    /**
     * 获取广度优先遍历的顺序
     */
    public List<String> getOrderList() {
        return orderList;
    }
}
