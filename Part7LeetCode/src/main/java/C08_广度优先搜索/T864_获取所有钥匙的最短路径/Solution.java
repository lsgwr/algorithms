import java.util.ArrayList;
import java.util.List;

/***********************************************************
 * @Description : 864. 获取所有钥匙的最短路径
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/4/20 18:01
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/

public class Solution {
    private int R;
    private int C;
    private String[] grid;
    private int K;
    private List<Character> keyList;
    private List<Character> lockList;
    private final int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    // 遍历的起点
    private int rStart, cStart;


    private boolean inGrid(int r, int c) {
        return r >= 0 && r < R && c >= 0 && c < C;
    }

    public int shortestPathAllKeys(String[] grid) {
        R = grid.length;
        C = grid[0].length();
        this.grid = grid.;
        // 锁的数目
        int K = 0;
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (grid[r].charAt(c) == '@'){
                    // 找到起点
                    rStart = r;
                    cStart = c;
                    continue;
                }
                if (grid[r].charAt(c) >= 'A' && grid[r].charAt(c) <= 'Z') {
                    K = Math.max(K, grid[r].charAt(c) - 'A' + 1);
                }
            }
        }
        this.K = K;
        // 所有钥匙
        keyList = new ArrayList<>();
        // 所有锁
        lockList = new ArrayList<>();
        for (int k = 0; k < K; k++) {
            keyList.add((char) ('a' + k));
            lockList.add((char) ('A' + k));
        }
        // DFS获取钥匙获取顺序的全排列
        List<List<Character>> keyOrderList = new ArrayList<>();
        for (int start = 0; start < K; start++) {
            boolean[] visited = new boolean[K];
            // 当前的钥匙顺序
            List<Character> order = new ArrayList<>();
            // Todo:DFS获取全排列
            dfs(start, visited, order, keyOrderList);
        }
        // 获取钥匙的全排列了，下面按照钥匙的排列顺序依次进行bfs，比较每个钥匙排列的移动次数，取最小值即可
        int minSteps = Integer.MAX_VALUE;
        for (List<Character> keyOrder : keyOrderList) {
            int totalSteps = Integer.MAX_VALUE;
            boolean hasPath = true;
            int rStartCur = rStart;
            int cStartCur = cStart;
            for (int i = 0; i < keyOrder.size() - 1; i++) {
                boolean[][] visited = new boolean[R][C];
                // 当前用户拿到的钥匙列表
                List<Character> keys = new ArrayList<>();
                int steps = bfs(i, i + 1, visited, keys);
                if (steps == -1) {
                    // 无法到达目的地返回-1，直接退出这个排列即可
                    hasPath = false;
                    break;
                } else {
                    // 本次能从位置i到i+1，则把步数累计
                    totalSteps += steps;
                }
            }
            if (hasPath) {
                // 本次有路径，则更新最少移动次数
                minSteps = Math.min(totalSteps, minSteps);
            }
        }
        return minSteps == Integer.MAX_VALUE ? -1 : minSteps;
    }

    private void dfs(int start, boolean[] visited, List<Character> order, List<List<Character>> keyOrderList) {
        visited[start] = true;
        order.add(keyList.get(start));
        if (order.size() == K) {
            // 找到了一个排列
            List<Character> orderNew = new ArrayList<>();
            // 都要加入起点
            orderNew.add('a');
            orderNew.addAll(new ArrayList<>(order));
            keyOrderList.add(orderNew);
        }
        for (int i = 0; i < keyList.size(); i++) {
            if (!visited[i]) {
                dfs(i, visited, order, keyOrderList);
                // 回溯
                visited[i] = false;
                order.remove(order.size() - 1);
            }
        }
    }

    private int bfs(int start, int end, boolean[][] visited, List<Character> keys) {
    }
}
