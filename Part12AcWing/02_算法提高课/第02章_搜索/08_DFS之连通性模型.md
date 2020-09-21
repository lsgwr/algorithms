# 08_DFS之连通性模型

## 题目

### [AcWing 1112.迷宫](https://www.acwing.com/problem/content/1114/)
> 联通性问题一次找到即可，不需要回溯！！！回溯反而会导致超时！！
```java
import java.io.*;

public class Main {
    static int R, C;

    static char[][] grid;

    static boolean[][] visited;

    static final int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    private static boolean inGrid(int r, int c) {
        return r >= 0 && r < R && c >= 0 && c < C;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int k = Integer.parseInt(bf.readLine().trim());
        while (k-- > 0) {
            R = Integer.parseInt(bf.readLine().trim());
            C = R;
            grid = new char[R][C];
            for (int i = 0; i < R; i++) {
                grid[i] = bf.readLine().toCharArray();
            }
            visited = new boolean[R][C]; // 节点访问情况
            String[] line = bf.readLine().split(" ");
            int rSrc = Integer.parseInt(line[0]);
            int cSrc = Integer.parseInt(line[1]);
            int rEnd = Integer.parseInt(line[2]);
            int cEnd = Integer.parseInt(line[3]);
            if (grid[rSrc][cSrc] == '#') {
                System.out.println("NO");
                continue;
            }
            boolean result = dfs(rSrc, cSrc, rEnd, cEnd);
            System.out.println(result ? "YES" : "NO");
        }
    }

    private static boolean dfs(int rCur, int cCur, int rEnd, int cEnd) {
        visited[rCur][cCur] = true;
        if ((rCur == rEnd) && (cCur == cEnd)) {
            return true;
        }
        for (int[] dir : dirs) {
            int rNxt = rCur + dir[0];
            int cNxt = cCur + dir[1];
            if (inGrid(rNxt, cNxt) && !visited[rNxt][cNxt] && grid[rNxt][cNxt] == '.') {
                boolean result = dfs(rNxt, cNxt, rEnd, cEnd);
                if (result) {
                    return true;
                }
                // visited[rNxt][cNxt] = false; // 回溯时记得把状态改回去 // 这一句千万不要加！！要不就直接超时了
            }
        }
        return false;
    }
}
```