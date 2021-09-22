# 02_BFS

## 题目
### [AcWing 844.走迷宫](https://www.acwing.com/problem/content/description/846/)

> 用个dis数组可能更好？

```txt
给定一个n*m的二维整数数组，用来表示一个迷宫，数组中只包含0或1，其中0表示可以走的路，1表示不可通过的墙壁。

最初，有一个人位于左上角(1, 1)处，已知该人每次可以向上、下、左、右任意一个方向移动一个位置。

请问，该人从左上角移动至右下角(n, m)处，至少需要移动多少次。

数据保证(1, 1)处和(n, m)处的数字为0，且一定至少存在一条通路。

输入格式
第一行包含两个整数n和m。

接下来n行，每行包含m个整数（0或1），表示完整的二维数组迷宫。

输出格式
输出一个整数，表示从左上角移动至右下角的最少移动次数。
```

```java
// BFS求最短路
import java.util.*;

public class Main {
    private static int[][] grid;
    private static boolean[][] visited;
    private static int n, m;
    private static final int[][] dirs = {{1, 0}, {0, 1}, {0, -1}, {-1, 0}};
    private static int[][][] pre;
    
    public static boolean inGrid(int r, int c) {
        return r >=0 && r < n && c >=0 && c < m;
    }
    
    public static void bfs() {
        Queue<int[]> q = new ArrayDeque<>();
        visited[0][0] = true;
        q.add(new int[]{0, 0}); // 加入起始点
        while(!q.isEmpty()) {
            int[] point = q.remove();
            for(int[] dir : dirs) {
                int rNext = point[0] + dir[0];
                int cNext = point[1] + dir[1];
                if(inGrid(rNext, cNext) && !visited[rNext][cNext] && grid[rNext][cNext] == 0) {
                    pre[rNext][cNext] = new int[]{point[0], point[1]}; // 设置节点的上一个访问节点
                    if(rNext == n - 1 && cNext == m - 1) return; // 找到对应的点
                    visited[rNext][cNext] = true;
                    q.add(new int[]{rNext, cNext});
                }
            }
        }
    }
    
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        n = s.nextInt();
        m = s.nextInt();
        grid = new int[n][m];
        visited = new boolean[n][m];
        pre = new int[n][m][2]; // 每个点的前一个访问节点
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                grid[i][j] = s.nextInt();
                pre[i][j] = new int[]{-1, -1}; // 初始化为-1，00会被认为是起点
            }
        }
        bfs();
        int r = n - 1, c = m - 1, cnt = 0;
        while(pre[r][c][0] != -1 && pre[r][c][1] != -1) {
            int r_tmp = r, c_tmp = c;
            r = pre[r_tmp][c_tmp][0];
            c = pre[r_tmp][c_tmp][1];
            cnt++;
            if(r == 0 && c == 0) System.out.println(cnt);
        }
    }
}
```

### [AcWing 845.八数码](https://www.acwing.com/problem/content/847/)

```txt
在一个3×3的网格中，1~8这8个数字和一个“x”恰好不重不漏地分布在这3×3的网格中。

例如：

1 2 3
x 4 6
7 5 8
在游戏过程中，可以把“x”与其上、下、左、右四个方向之一的数字交换（如果存在）。

我们的目的是通过交换，使得网格变为如下排列（称为正确排列）：

1 2 3
4 5 6
7 8 x
例如，示例中图形就可以通过让“x”先后与右、下、右三个方向的数字交换成功得到正确排列。

交换过程如下：

1 2 3   1 2 3   1 2 3   1 2 3
x 4 6   4 x 6   4 5 6   4 5 6
7 5 8   7 5 8   7 x 8   7 8 x
现在，给你一个初始网格，请你求出得到正确排列至少需要进行多少次交换。

输入格式
输入占一行，将3×3的初始网格描绘出来。

例如，如果初始网格如下所示：
1 2 3

x 4 6

7 5 8

则输入为：1 2 3 x 4 6 7 5 8

输出格式
输出占一行，包含一个整数，表示最少交换次数。

如果不存在解决方案，则输出”-1”。

输入样例：
2  3  4  1  5  x  7  6  8 
输出样例
19
```

```java
// 将每一串字符都想象成一个点，已知起点和终点,每一次更新相邻的节点，采用bfs得到到达终点的最短路径，然后这里使用map来存储对应字符串和对应的遍历顺序

import java.io.*;
import java.util.*;

public class Main {
    private static final int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    private static String start = "";
    private static final String end = "12345678x";
    private static final Map<String, Integer> map = new HashMap<>();

    private static boolean inGrid(int r, int c) {
        return r >= 0 && r < 3 && c >= 0 && c < 3;
    }

    public static int bfs() {
        Queue<String> q = new ArrayDeque<>();
        q.add(start); // 初始字符串
        map.put(start, 0); // 起始位置交换次数为0
        while (!q.isEmpty()) {
            String str = q.remove(); // 当前点的位置
            if (str.equals(end)) return map.get(str);
            int index = str.indexOf('x'); // 找到当前x的位置
            int r = index / 3;
            int c = index % 3;
            for (int[] dir : dirs) {
                int rNext = r + dir[0];
                int cNext = c + dir[1];
                int indexNext = rNext * 3 + cNext; // 下一个x可能的位置
                if (inGrid(rNext, cNext)) { // 满足条件，往下遍历
                    // 交换字符
                    char[] cArr = str.toCharArray();
                    swap(cArr, index, indexNext);
                    // 符合条件，计算出下一个字符串
                    String strNext = new String(cArr);
                    if (map.get(strNext) == null) { // 当前字符串没有走到过，才会继续沿着该字符串往下遍历
                        map.put(strNext, map.get(str) + 1);
                        q.add(strNext);
                    }
                }
            }
        }
        return -1;
    }

    private static void swap(char[] cArr, int x, int y) {
        char tmp = cArr[x];
        cArr[x] = cArr[y];
        cArr[y] = tmp;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        start = bf.readLine().replaceAll(" ", "");
        System.out.println(bfs());
    }
}
```