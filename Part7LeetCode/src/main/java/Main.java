import java.util.*;

public class Main {
    final static int[][] dirs = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
    static int[][] w;
    static int n;
    static List<List<int[]>> ccs = new ArrayList<>(); // 存储所有联通分量
    static boolean[][] visited;

    static boolean inGrid(int r, int c) {
        return r >= 0 && r < n && c >= 0 && c < n;
    }

    // BFS记录所有的联通分量
    static void bfs(int rStart, int cStart) {
        List<int[]> cc = new ArrayList<>(); // 存储联通分量内点的情况
        Queue<int[]> q = new ArrayDeque<>(); // 队列存储入队情况
        q.add(new int[]{rStart, cStart});
        cc.add(new int[]{rStart, cStart});
        visited[rStart][cStart] = true; // 必须在加入队列后进行记录
        while (!q.isEmpty()) {
            int[] point = q.remove();
            int r = point[0];
            int c = point[1];
            for (int[] dir : dirs) {
                int rNext = r + dir[0];
                int cNext = c + dir[1];
                while (inGrid(rNext, cNext) && !visited[rNext][cNext] && w[r][c] == w[rNext][cNext]) {
                    q.add(new int[]{rNext, cNext});
                    cc.add(new int[]{rNext, cNext});
                    visited[rNext][cNext] = true;
                }
            }
        }
        ccs.add(cc);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        visited = new boolean[n][n];
        w = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) w[i][j] = sc.nextInt();
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j]) bfs(i, j);
            }
        }
        // Todo：遍历所有联通分量，统计山峰和山谷
        System.out.println(ccs);
    }
}