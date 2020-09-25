# 01 Flood Fill
## `自己做出来地`[AcWing 1097.池塘计数](https://www.acwing.com/problem/content/1099/)
```txt
农夫约翰有一片 N∗M 的矩形土地。

最近，由于降雨的原因，部分土地被水淹没了。

现在用一个字符矩阵来表示他的土地。

每个单元格内，如果包含雨水，则用”W”表示，如果不含雨水，则用”.”表示。

现在，约翰想知道他的土地中形成了多少片池塘。

每组相连的积水单元格集合可以看作是一片池塘。

每个单元格视为与其上、下、左、右、左上、右上、左下、右下八个邻近单元格相连。

请你输出共有多少片池塘，即矩阵中共有多少片相连的”W”块。

输入格式
第一行包含两个整数 N 和 M。

接下来 N 行，每行包含 M 个字符，字符为”W”或”.”，用以表示矩形土地的积水状况，字符之间没有空格。

输出格式
输出一个整数，表示池塘数目。

数据范围
1≤N,M≤1000
输入样例：
10 12
W........WW.
.WWW.....WWW
....WW...WW.
.........WW.
.........W..
..W......W..
.W.W.....WW.
W.W.W.....W.
.W.W......W.
..W.......W.
输出样例：
3
```

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

```txt
    1   2   3   4   5   6   7  
   #############################
 1 #   |   #   |   #   |   |   #
   #####---#####---#---#####---#
 2 #   #   |   #   #   #   #   #
   #---#####---#####---#####---#
 3 #   |   |   #   #   #   #   #
   #---#########---#####---#---#
 4 #   #   |   |   |   |   #   #
   #############################
           (图 1)

   #  = Wall   
   |  = No wall
   -  = No wall

   方向：上北下南左西右东。
图1是一个城堡的地形图。

请你编写一个程序，计算城堡一共有多少房间，最大的房间有多大。

城堡被分割成 m∗n个方格区域，每个方格区域可以有0~4面墙。

注意：墙体厚度忽略不计。

输入格式
第一行包含两个整数 m 和 n，分别表示城堡南北方向的长度和东西方向的长度。

接下来 m 行，每行包含 n 个整数，每个整数都表示平面图对应位置的方块的墙的特征。

每个方块中墙的特征由数字 P 来描述，我们用1表示西墙，2表示北墙，4表示东墙，8表示南墙，P 为该方块包含墙的数字之和。

例如，如果一个方块的 P 为3，则 3 = 1 + 2，该方块包含西墙和北墙。

城堡的内墙被计算两次，方块(1,1)的南墙同时也是方块(2,1)的北墙。

输入的数据保证城堡至少有两个房间。

输出格式
共两行，第一行输出房间总数，第二行输出最大房间的面积（方块数）。

数据范围
1≤m,n≤50,
0≤P≤15
输入样例：
4 7 
11 6 11 6 3 10 6 
7 9 6 13 5 15 5 
1 10 12 7 13 7 5 
13 11 10 8 10 12 13 
输出样例：
5
9
```

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

```txt
FGD小朋友特别喜欢爬山，在爬山的时候他就在研究山峰和山谷。

为了能够对旅程有一个安排，他想知道山峰和山谷的数量。

给定一个地图，为FGD想要旅行的区域，地图被分为 n×n 的网格，每个格子 (i,j) 的高度 w(i,j) 是给定的。

若两个格子有公共顶点，那么它们就是相邻的格子，如与 (i,j) 相邻的格子有(i−1,j−1),(i−1,j),(i−1,j+1),(i,j−1),(i,j+1),(i+1,j−1),(i+1,j),(i+1,j+1)。

我们定义一个格子的集合 S 为山峰（山谷）当且仅当：

S 的所有格子都有相同的高度。
S 的所有格子都连通。
对于 s 属于 S，与 s 相邻的 s′ 不属于 S，都有 ws>ws′（山峰），或者 ws<ws′（山谷）。
如果周围不存在相邻区域，则同时将其视为山峰和山谷。
你的任务是，对于给定的地图，求出山峰和山谷的数量，如果所有格子都有相同的高度，那么整个地图即是山峰，又是山谷。

输入格式
第一行包含一个正整数 n，表示地图的大小。

接下来一个 n×n 的矩阵，表示地图上每个格子的高度 w。

输出格式
共一行，包含两个整数，表示山峰和山谷的数量。

数据范围
1≤n≤1000,
0≤w≤109
输入样例1：
5
8 8 8 7 7
7 7 8 8 7
7 7 7 7 7
7 8 8 7 8
7 8 8 8 8
输出样例1：
2 1
输入样例2：
5
5 7 8 3 1
5 5 7 6 6
6 6 6 2 8
5 7 2 5 8
7 1 0 1 7
输出样例2：
3 3
样例解释
样例1：

1.png

样例2：

2.png
```

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