# Flood Fill
## [AcWing 1097.池塘计数](https://www.acwing.com/problem/content/1099/)
```java
import java.util.*;

// 求联通分量的个数
public class Main {
    static char[][] grid;
    static int N, M;
    static boolean[][] visited;
    static final int[][] dirs = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}, {-1, 1}, {1, -1}, {1, 1}, {-1, -1}};
    
    static boolean inGrid(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < M;
    }
    
    static void dfs(int r, int c) {
        visited[r][c] = true;
        
        for(int[] dir : dirs) {
            int rNext = r + dir[0];
            int cNext = c + dir[1];
            if(inGrid(rNext, cNext) && !visited[rNext][cNext] && grid[rNext][cNext] == 'W') {
                dfs(rNext, cNext);
            }
        }
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        grid = new char[N][M];
        for (int i = 0; i < N; i++) grid[i] = sc.next().trim().toCharArray(); // 字符输入
        visited = new boolean[N][M];
        int res = 0;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(!visited[i][j] && grid[i][j] == 'W') {
                    dfs(i, j);
                    res++;
                }
            }
        }
        System.out.println(res);
    }
}
```