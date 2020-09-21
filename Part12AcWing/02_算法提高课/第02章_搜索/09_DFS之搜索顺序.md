# 09_DFS之搜索顺序

## 题目

### [AcWing 1116.马走日](https://www.acwing.com/problem/content/1118/)
> 体会马走日的走法而得到的dirs数组；找到所有方案或最佳方案，就必须用回溯了
```java
import java.util.*;

class Main {

    static final int[][] dirs = {{1, 2}, {2, 1}, {-1, -2}, {-2, -1}, {1, -2}, {-2, 1}, {-1, 2}, {2, -1}};

    static int R, C;

    static boolean[][] visited;

    static int result = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        while (T-- > 0) {
            R = sc.nextInt();
            C = sc.nextInt();
            int rStart = sc.nextInt();
            int cStart = sc.nextInt();
            result = 0;
            visited = new boolean[R][C];
            dfs(rStart, cStart, 1); // 下标务必从1开始！！
            System.out.println(result);
        }
    }

    private static boolean inGrid(int r, int c) {
        return r >= 0 && r < R && c >= 0 && c < C;
    }

    private static void dfs(int rCur, int cCur, int steps) {
        visited[rCur][cCur] = true;
        if (steps == R * C) {
            result++;
            return; // 步数到了就该回溯了
        }
        for (int[] dir : dirs) {
            int rNext = rCur + dir[0];
            int cNext = cCur + dir[1];
            if (inGrid(rNext, cNext) && !visited[rNext][cNext]) {
                dfs(rNext, cNext, steps + 1);
                visited[rNext][cNext] = false; // 找到所有方案或最佳方案，就必须用回溯了
            }
        }
    }
}
```