/***********************************************************
 * @Description : 752. 打开转盘锁 基于非递归的bfs求最短路径长度的问题(即本题中的旋转次数)
 * 你有一个带有四个圆形拨轮的转盘锁。每个拨轮都有10个数字： '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' 。
 * 每个拨轮可以自由旋转：例如把 '9' 变为  '0'，'0' 变为 '9' 。每次旋转都只能旋转一个拨轮的一位数字。
 * 锁的初始数字为 '0000' ，一个代表四个拨轮的数字的字符串。
 * 列表 deadends 包含了一组死亡数字，一旦拨轮的数字和列表里的任何一个元素相同，这个锁将会被永久锁定，无法再被旋转。
 * 字符串 target 代表可以解锁的数字，你需要给出最小的旋转次数，如果无论如何不能解锁，返回 -1。
 *
 * 示例 1:
 * 输入：deadends = ["0201","0101","0102","1212","2002"], target = "0202"
 * 输出：6
 * 解释：
 * 可能的移动序列为 "0000" -> "1000" -> "1100" -> "1200" -> "1201" -> "1202" -> "0202"。
 * 注意 "0000" -> "0001" -> "0002" -> "0102" -> "0202" 这样的序列是不能解锁的，
 * 因为当拨动到 "0102" 时这个锁就会被锁定。
 *
 * 解题思路：
 * 转盘锁一共有10*10*10*10=10000中可能性，每个可能性都是一个状态，都可以看做图的一个顶点。以0000为例，
 * 每个0都可能向前拨1位或者向后拨1位，所以每个状态共有4*2=8个邻接状态，不同的点可能拨到同一个状态，
 * 显然这些状态之间的关系组成了一个庞大的图，下面我们要做地就是用bfs找指定点之间的路径，并且不能遍历deadends里面的点
 *
 * 其实和上一节的八联通问题很像
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2019-12-17 15:31
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter07AISearchAndBFS.Section2And3RotaryLock;

import java.util.*;

public class SolutionBFS {
    /**
     * 转盘锁的数字长度
     */
    private static final int LEN = 4;

    /**
     * 锁的初始数字
     */
    private static final String START = "0000";

    /**
     * 死亡数字
     */
    private HashSet<String> deadends = new HashSet<>();

    /**
     * 存储节点是否被访问过的map
     */
    private HashMap<String, Boolean> visited = new HashMap<>();

    /**
     * 存储所有节点的上一个访问节点的map
     */
    private HashMap<String, String> pre = new HashMap<>();

    /**
     * 拼接转盘锁转动后的数字
     *
     * @param index 转动的位置
     * @param c     转动后的值
     * @param state 原转盘所的状态
     * @return 转动后的状态
     */
    private String concatAdjState(int index, String c, String state) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < LEN; i++) {
            if (index == i) {
                sb.append(c);
            } else {
                sb.append(state.charAt(i));
            }
        }
        return sb.toString();
    }

    /**
     * 根据锁的当前状态获取到其邻接状态
     *
     * @param state 锁的当前状态，会在调用函数里保证不是死亡数字
     * @return 不包含死亡数字的邻接状态
     */
    private HashSet<String> getAdjs(String state) {
        HashSet<String> adjs = new HashSet<>();
        // 分成4位数字
        String[] numsStrArr = state.split("");
        for (int i = 0; i < LEN; i++) {
            String str = numsStrArr[i];
            // 当前位的数字
            int num = Integer.parseInt(str);
            // 1.往前转1位
            if (num == 9) {
                // 9往前转1位成为0
                adjs.add(concatAdjState(i, "0", state));
            } else {
                adjs.add(concatAdjState(i, (num + 1) + "", state));
            }
            // 2.往后转1位
            if (num == 0) {
                // 0往后转1位成为9
                adjs.add(concatAdjState(i, "9", state));
            } else {
                adjs.add(concatAdjState(i, (num - 1) + "", state));
            }
        }
        // 到这里已经获取了当前转盘锁状态的所有邻接状态，下面就是去除掉死亡数字了
        HashSet<String> intersectionSet = new HashSet<>(adjs);
        intersectionSet.retainAll(deadends);
        // 邻接点去除掉所有的死亡数字
        adjs.removeAll(intersectionSet);
        return adjs;
    }

    public int openLock(String[] deadends, String target) {
        // 初始化死亡数字列表
        this.deadends.addAll(Arrays.asList(deadends));
        if (this.deadends.contains(START)) {
            // 如果包含起始点地话就直接死亡退出
            return -1;
        }
        // 广度遍历一遍
        bfs();
        // 获取到target的最短路径长度(即最小旋转次数)，邻接图里的所有点都是可以连续旋转得到的
        return getShortestPathLen(target);
    }

    private void bfs() {
        Queue<String> queue = new ArrayDeque<>();
        queue.add(START);
        // 设置0000点已经被访问了
        visited.put(START, true);
        // 起点的上一个访问节点是自己
        pre.put(START, START);
        // 队列不为空就一直往下遍历
        while (!queue.isEmpty()) {
            // 弹出队列头元素
            String numStr = queue.remove();
            // 不是死亡数字才进行bfs遍历
            if (!deadends.contains(numStr)) {
                // 获取所有的邻接点
                HashSet<String> adjs = getAdjs(numStr);
                // 遍历当前点的所有邻接点
                for (String adj : adjs) {
                    if (visited.get(adj) == null) {
                        // 没被访问过地话就加入到队列中
                        queue.add(adj);
                        // 设置为已经访问
                        visited.put(adj, true);
                        // 设置当前节点的上一个访问节点
                        pre.put(adj, numStr);
                    }
                }
            }
        }
    }

    /**
     * 获取初始点到target的最短路径长度即最小旋转次数
     *
     * @param target 目标数字
     * @return 最小长度即最小旋转次数
     */
    private int getShortestPathLen(String target) {
        if (visited.get(target) == null) {
            // 目标点没被访问过，就没有路径，直接返回-1
            return -1;
        }
        // 逆向获取bfs路径长度
        int len = 0;
        String state = target;
        // 只要没遍历到初始数字，就继续往前找
        while (!state.equals(START)) {
            // 往前遍历，获取上一个节点
            state = pre.get(state);
            len++;
        }
        return len;
    }

    public static void main(String[] args) {
        String[] deadends = {"8887","8889","8878","8898","8788","8988","7888","9888"};
        String target = "8888";
        SolutionBFS solution = new SolutionBFS();
        System.out.println(solution.openLock(deadends, target));
    }
}
