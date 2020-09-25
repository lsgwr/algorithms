# 08_DFS之连通性模型
> 连通性问题不需要回溯处理visited等数组！！本节题目比较简单，可以直接跳过

## 题目

### [AcWing 1112.迷宫](https://www.acwing.com/problem/content/1114/)
> 联通性问题一次找到即可，不需要回溯！！！回溯反而会导致超时！！此外也可以一次DFS维护int[] visited，根据visited[i]和visited[j]是否相等来判断，对于多对点求联通性非常适用
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

### [AcWing 1113.红与黑](https://www.acwing.com/problem/content/1115/)
```java
import java.io.*;

class Main {

    static char[][] grid;

    static int R, C;

    static boolean[][] visited;

    static final int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    static int result;

    static boolean inGrid(int r, int c) {
        return r >= 0 && r < R && c >= 0 && c < C;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String lineWH = "";
        while (!(lineWH = bf.readLine()).equals("0 0")) { // 只要输入不是0 0就一直检测
            String[] WH = lineWH.split(" ");
            R = Integer.parseInt(WH[1]);
            C = Integer.parseInt(WH[0]);
            visited = new boolean[R][C];
            grid = new char[R][C];
            for (int i = 0; i < R; i++) {
                grid[i] = bf.readLine().toCharArray();
            }
            result = 0;
            for (int r = 0; r < R; r++) {
                for (int c = 0; c < C; c++) {
                    if (grid[r][c] == '@') {
                        dfs(r, c);
                        break;
                    }
                }
            }
            System.out.println(result);
        }
    }

    private static void dfs(int rCur, int cCur) {
        visited[rCur][cCur] = true;
        result++;
        for (int[] dir : dirs) {
            int rNext = rCur + dir[0];
            int cNext = cCur + dir[1];
            if (inGrid(rNext, cNext) && !visited[rNext][cNext] && grid[rNext][cNext] == '.') {
                dfs(rNext, cNext);
            }
        }
    }
}
```