# 01 Flood Fill
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

## [1106.山峰和山谷](https://www.acwing.com/problem/content/1108/)
> 还没做完
```java
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
        n = sc.nextInt();
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
        int topCnt = 0, bottomCnt = 0; // 山谷计数、山峰计数
        // Todo：遍历所有联通分量，统计山峰和山谷
        for (List<int[]> cc : ccs) {
            // 判断是山峰还是山谷
            int adjCnt = 0; // 非联通分量的相邻点的个数
            boolean isTop = false, isBottom = false; // 是山峰还是山谷
            for (int[] point : cc) {
                for (int[] dir : dirs) {
                    int rNext = point[0] + dir[0];
                    int cNext = point[1] + dir[1];
                    if (inGrid(rNext, cNext)) {
                        if (w[rNext][cNext] > w[point[0]][point[1]]) { // 非联通分量山的点大于当前点，当前联通分量为山谷
                            isBottom = true;
                            adjCnt++;
                        }
                        if (w[rNext][cNext] < w[point[0]][point[1]]) { // 非联通分量山的点小于当前点，当前联通分量为山峰
                            isTop = true;
                            adjCnt++;
                        }
                        // w[rNext][cNext] == w[point[0]][point[1]] 说明是联通分量上的点，此处不予考虑
                    }
                }
            }
            if (adjCnt > 0) { // 有邻接区域，判断是山峰还是山顶
                if (isTop && !isBottom) topCnt++;
                if (!isTop && isBottom) bottomCnt++;
            } else {
                // 周围不存在相邻区域，则同时视为山峰和山谷
                topCnt++;
                bottomCnt++;
            }
        }
        System.out.println(topCnt + " " + bottomCnt);
    }
}
```