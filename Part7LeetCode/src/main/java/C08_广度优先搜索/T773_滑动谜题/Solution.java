 /**
 * @description: 773. 滑动谜题
 * 在一个 2 x 3 的板上（board）有 5 块砖瓦，用数字 1~5 来表示, 以及一块空缺用 0 来表示.
 * 一次移动定义为选择 0 与一个相邻的数字（上下左右）进行交换.
 * 最终当板 board 的结果是 [[1,2,3],[4,5,0]] 谜板被解开。
 * 给出一个谜板的初始状态，返回最少可以通过多少次移动解开谜板，如果不能解开谜板，则返回 -1 。
 * <p>
 * 基于BFS的非递归字符串实现
 * @author : 梁山广(Laing Shan Guang)
 * @date : 2019-12-17 15:31
 * @email : liangshanguang2@gmail.com
 ***********************************************************/
// package Chapter07AISearchAndBFS.Solution6SlidingPuzzle;
 package C08_广度优先搜索.T773_滑动谜题;
import java.util.*;

public class Solution {
    private static final int ROW = 2;
    private static final int COL = 3;
    private String init = "";
    private static final String TARGET = "123450";
    private HashMap<String, Boolean> visited = new HashMap<>();
    private HashMap<String, String> pre = new HashMap<>();

    public int slidingPuzzle(int[][] board) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                sb.append(board[i][j]);
            }
        }
        this.init = sb.toString();
        if (this.init.equals(TARGET)) {
            return 0;
        }
        bfs();
        return getShortestPathLen();
    }

    /**
     * 交换字符串两个位置的字符，返回一个新的字符串
     *
     * @param str    字符串
     * @param index1 索引1
     * @param index2 索引2
     * @return 交换后的字符串，一个新的字符串
     */
    private String swap(String str, int index1, int index2) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            if (i == index1) {
                sb.append(str.charAt(index2));
                continue;
            }
            if (i == index2) {
                sb.append(str.charAt(index1));
                continue;
            }
            sb.append(str.charAt(i));
        }
        return sb.toString();
    }

    /**
     * 根据当前滑动块的状态获取其0滑动
     *
     * @param cur 当前滑动块的状态
     * @return
     */
    private List<String> getAdjs(String cur) {
        // 获取邻接点，0所在的位置不同，邻接点的数目也不同，每个都要单独判断
        List<String> adjs = new ArrayList<>();
        // 字符0所在的位置
        int zeroIndex = cur.indexOf('0');
        switch (zeroIndex) {
            case 0:
                adjs.add(swap(cur, zeroIndex, 1));
                adjs.add(swap(cur, zeroIndex, 3));
                break;
            case 1:
                adjs.add(swap(cur, zeroIndex, 0));
                adjs.add(swap(cur, zeroIndex, 2));
                adjs.add(swap(cur, zeroIndex, 4));
                break;
            case 2:
                adjs.add(swap(cur, zeroIndex, 1));
                adjs.add(swap(cur, zeroIndex, 5));
                break;
            case 3:
                adjs.add(swap(cur, zeroIndex, 0));
                adjs.add(swap(cur, zeroIndex, 4));
                break;
            case 4:
                adjs.add(swap(cur, zeroIndex, 1));
                adjs.add(swap(cur, zeroIndex, 3));
                adjs.add(swap(cur, zeroIndex, 5));
                break;
            case 5:
                adjs.add(swap(cur, zeroIndex, 2));
                adjs.add(swap(cur, zeroIndex, 4));
                break;
            default:
                break;
        }
        return adjs;
    }

    public void bfs() {
        Queue<String> queue = new ArrayDeque<>();
        queue.add(init);
        visited.put(init, true);
        while (!queue.isEmpty()) {
            // 获取当前状态
            String cur = queue.remove();
            // 获取邻接点列表
            List<String> adjs = getAdjs(cur);
            for (String adj : adjs) {
                // 等于null表示未被访问。true代表已经被访问，这里不能用==true，因为一旦获取到null，会报空指针异常
                if (visited.get(adj) == null) {
                    queue.add(adj);
                    visited.put(adj, true);
                    // 设置当前遍历到地邻接点的父节点
                    pre.put(adj, cur);
                    // 遍历到我们想要的目标就退出
                    if (adj.equals(TARGET)) {
                        return;
                    }
                }
            }
        }
    }

    private int getShortestPathLen() {
        // 遍历pre数组，获取路径长度
        // 没有遍历到想要的目标就返回-1
        if (visited.get(TARGET) == null) {
            return -1;
        }
        String cur = TARGET;
        // 初始化为1
        int len = 0;
        while (!cur.equals(init)) {
            cur = pre.get(cur);
            len++;
        }
        return len;
    }

     public static void main(String[] args) {
         int[][] board = {{3, 2, 4}, {1, 5, 0}};
         Solution solution = new Solution();
         System.out.println(solution.slidingPuzzle(board));
     }
}