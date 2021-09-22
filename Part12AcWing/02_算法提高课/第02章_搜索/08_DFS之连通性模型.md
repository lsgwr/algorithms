# 08_DFS之连通性模型
> 连通性问题不需要回溯处理visited等数组！！本节题目比较简单，可以直接跳过

## 题目

### [AcWing 1112.迷宫](https://www.acwing.com/problem/content/1114/)
> 联通性问题一次找到即可，不需要回溯！！！回溯反而会导致超时！！此外也可以一次DFS维护int[] visited，根据visited[i]和visited[j]是否相等来判断，对于多对点求联通性非常适用

```txt
一天Extense在森林里探险的时候不小心走入了一个迷宫，迷宫可以看成是由 n∗n 的格点组成，每个格点只有2种状态，.和#，前者表示可以通行后者表示不能通行。

同时当Extense处在某个格点时，他只能移动到东南西北(或者说上下左右)四个方向之一的相邻格点上，Extense想要从点A走到点B，问在不走出迷宫的情况下能不能办到。

如果起点或者终点有一个不能通行(为#)，则看成无法办到。

注意：A、B不一定是两个不同的点。

输入格式
第1行是测试数据的组数 k，后面跟着 k 组输入。

每组测试数据的第1行是一个正整数 n，表示迷宫的规模是 n∗n 的。

接下来是一个 n∗n 的矩阵，矩阵中的元素为.或者#。

再接下来一行是 4 个整数 ha,la,hb,lb，描述 A 处在第 ha 行, 第 la 列，B 处在第 hb 行, 第 lb 列。

注意到 ha,la,hb,lb 全部是从 0 开始计数的。

输出格式
k行，每行输出对应一个输入。

能办到则输出“YES”，否则输出“NO”。

数据范围
1≤n≤100
输入样例：
2
3
.##
..#
#..
0 0 2 2
5
.....
###.#
..#..
###..
...#.
0 0 4 0
输出样例:
YES
NO
难度： 简单
时/空限制： 1s / 64MB
总通过数： 1070
总尝试数： 2700
来源： 《信息学奥赛一本通》
算法标签

```

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

```txt
有一间长方形的房子，地上铺了红色、黑色两种颜色的正方形瓷砖。

你站在其中一块黑色的瓷砖上，只能向相邻（上下左右四个方向）的黑色瓷砖移动。

请写一个程序，计算你总共能够到达多少块黑色的瓷砖。

输入格式
输入包括多个数据集合。

每个数据集合的第一行是两个整数 W 和 H，分别表示 x 方向和 y 方向瓷砖的数量。

在接下来的 H 行中，每行包括 W 个字符。每个字符表示一块瓷砖的颜色，规则如下

1）‘.’：黑色的瓷砖；
2）‘#’：白色的瓷砖；
3）‘@’：黑色的瓷砖，并且你站在这块瓷砖上。该字符在每个数据集合中唯一出现一次。

当在一行中读入的是两个零时，表示输入结束。

输出格式
对每个数据集合，分别输出一行，显示你从初始位置出发能到达的瓷砖数(记数时包括初始位置的瓷砖)。

数据范围
1≤W,H≤20
输入样例：
6 9 
....#. 
.....# 
...... 
...... 
...... 
...... 
...... 
#@...# 
.#..#. 
0 0
输出样例：
45
```

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