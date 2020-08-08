# Flood Fill
## `自己做出来地`[AcWing 1097.池塘计数](https://www.acwing.com/problem/content/1099/)
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

## `自己做出来地`[AcWing 1098.城堡问题](https://www.acwing.com/problem/content/1100/)
> 教训：联通分量内的个数累计以及节点访问数组visited的更新必须在`加入队列`的时候进行
```java
import java.util.*;

public class Main {
    static int m, n;
    static boolean[][][] grid;
    final static int[] dirs = {1, 2, 4, 8}; // 西墙、北墙、东墙、南墙
    // 把1、2、4、8和具体的方向关联起来
    final static int[][] dirsMap = {{0, 0}, {0, -1}, {-1, 0}, {0, 0}, {0, 1}, {0, 0}, {0, 0}, {0, 0}, {1, 0}, {0, 0}};
    static boolean[][] visited;

    // n的第k位的值是否为1
    static boolean getNBit(int n, int k) {
        return (n >> k & 1) == 1;
    }

    static boolean inGrid(int r, int c) {
        return r >= 0 && r < m && c >= 0 && c < n;
    }

    //  求每一个联通分量内点的个数
    static int bfs(int rStart, int cStart) {
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{rStart, cStart});
        visited[rStart][cStart] = true;
        int res = 1; // 初始时联通分量肯定至少有起点这个点
        while(!q.isEmpty()) {
            int[] point = q.remove();
            int r = point[0];
            int c = point[1];
            for(int dir : dirs) {
                int rNext = r + dirsMap[dir][0];
                int cNext = c + dirsMap[dir][1];
                if(inGrid(rNext, cNext) && !visited[rNext][cNext] && !grid[r][c][dir]) { // 在栅格内+没被访问+没有墙挡着
                    q.add(new int[]{rNext, cNext});
                    visited[rNext][cNext] = true;
                    res++;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        m = sc.nextInt();
        n = sc.nextInt();
        visited = new boolean[m][n];
        grid = new boolean[m][n][9]; // 9是为了记录四个方向上是否有墙，有墙为true
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int P = sc.nextInt();
                grid[i][j][1] = getNBit(P, 0); // 西墙
                grid[i][j][2] = getNBit(P, 1); // 北墙
                grid[i][j][4] = getNBit(P, 2); // 东墙
                grid[i][j][8] = getNBit(P, 3); // 南墙
            }
        }

        int cnt = 0, max = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j]) {
                    max = Math.max(max, bfs(i, j));
                    cnt++;
                }
            }
        }
        System.out.println(cnt);
        System.out.println(max);
    }
}
```