/**
 * @description 农夫需要把狼、羊、菜和自己运到对岸去，需要满足如下限制条件
 * 1.只有农夫能够划船，而且船比较小，除农夫外每次只能运一样东西
 * 2.如果没有农夫看着，羊会偷吃菜，狼会吃羊
 * 请考虑一种方法，让农夫能安全地安排这些东西和他自己过河
 * <p/>
 * 基于BFS的状态压缩字符串实现，核心解题思路：农夫不和羊在一起的时候，羊不能和狼或菜在一起
 * 把当前岸的物体作为状态，同时记录农夫是往对岸去还是回来
 * @author : 梁山广(Laing Shan Guang)
 * @date : 2019-12-18 17:34
 * @email : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter07AISearchAndBFS.Section4And5IQ;

import java.util.*;

public class FarmerCrossRiver {

    /**
     * 0表示在本岸，1表示在对岸，对应的下表分别代表农夫、狼、羊、菜
     */
    private static final String START = "0000";
    /**
     * 目标状态
     */
    private static final String FINAL = "1111";

    /**
     * 是否被访问
     */
    private HashMap<String, Boolean> visited = new HashMap<>();
    /**
     * 记录父访问节点
     */
    private HashMap<String, String> pre = new HashMap<>();

    /**
     * 判断要转移的下一个状态是否安全
     *
     * @param state "xxxx"格式的状态字符串
     * @return 安全返回true，不安全返回false
     */
    private boolean isSafe(String state) {
        // 农夫不和羊在一起的时候，羊不能和狼或菜在一起.羊是不安全状态的交集，所以羊是切入点
        char farmer = state.charAt(0);
        char woof = state.charAt(1);
        char sheep = state.charAt(2);
        char vegetable = state.charAt(3);

        if (farmer != sheep) {
            // 农夫不和羊在一起的时候，羊不能和狼或菜在一起,在一起就是不安全。农夫和羊在一起的所有情况都是安全的
            return (sheep != woof) && (sheep != vegetable);
        }
        return true;
    }

    /**
     * 把index处的字符1变成0或者0变成1
     *
     * @param str   字符串
     * @param index 要取反的索引
     * @return 取反后的新字符串
     */
    private String toggle(String str, int index) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (i == index) {
                if (c == '1') {
                    sb.append('0');
                } else if (c == '0') {
                    sb.append('1');
                }
                continue;
            }
            sb.append(c);
        }
        return sb.toString();
    }

    /**
     * 获取当前状态的邻接表，农民取反或保持原样 * 其他几个取反
     *
     * @param cur 当前状态
     * @return 邻接表
     */
    private List<String> getAdjs(String cur) {
        List<String> adjs = new ArrayList<>();
        List<String> adjsSafe = new ArrayList<>();
        // 情况1：农名空跑，只对农民取反
        adjs.add(toggle(cur, 0));
        // 2.情况2：农名带着东西跑，每次换两个标记位
        cur = toggle(cur, 0);
        adjs.add(toggle(cur, 1));
        adjs.add(toggle(cur, 2));
        adjs.add(toggle(cur, 3));
        // 去除不安全的状态
        for (String adj : adjs) {
            if (isSafe(adj)) {
                adjsSafe.add(adj);
            }
        }
        return adjsSafe;
    }

    /**
     * 广度优先遍历找最优转换路径
     */
    private void bfs() {
        Queue<String> queue = new ArrayDeque<>();
        queue.add(START);
        visited.put(START, true);
        while (!queue.isEmpty()) {
            // 当前状态
            String cur = queue.remove();
            // 求当前状态的邻接点
            List<String> adjs = getAdjs(cur);
            for (String adj : adjs) {
                if (visited.get(adj) == null) {
                    // 等于空表示该状态没被访问过
                    queue.add(adj);
                    visited.put(adj, true);
                    pre.put(adj, cur);
                    if (adj.equals(FINAL)) {
                        return;
                    }
                }
            }
        }
    }

    /**
     * 起点到终点的路径，每个元素都是"xxxx"的格式
     */
    public List<String> getPath() {
        List<String> path = new ArrayList<>();
        if (visited.get(FINAL) == null) {
            // 没到终点表示没有办法可以把所有物体运过去
            return null;
        }
        String cur = FINAL;
        while (!cur.equals(START)) {
            path.add(cur);
            cur = pre.get(cur);
        }
        path.add(START);
        Collections.reverse(path);
        return path;
    }

    public static void main(String[] args) {
        FarmerCrossRiver farmerCrossRiver = new FarmerCrossRiver();
        farmerCrossRiver.bfs();
        List<String> path = farmerCrossRiver.getPath();
        System.out.println(path);
    }
}
/**
 * 状态转换顺序是：
 * <p>
 * [0000, 1010, 0010, 1110, 0100, 1101, 0101, 1111]
 */