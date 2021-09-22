# 多源BFS
> 一般尝试从终点向起点遍历(`即逆向思维，这点非常重要！`)，这样不用考虑多点之间的覆盖问题，一次性把所有终点加入进BFS的Queue，进行一遍BFS，最终的dis数组就是我们要的最小距离，和自己第一次专业级考试的第一题完全相同。在时间和空间上，逆向都由于正向数十倍！
 
> 参考博客

+ [【力扣1162】 多源BFS](https://zhuanlan.zhihu.com/p/130229427)
+ [地图分析--多源BFS](https://blog.csdn.net/ATFWUS/article/details/105184960)

> 更多本类的题目

+ [LeetCode 1162.地图分析](https://leetcode-cn.com/problems/as-far-from-land-as-possible/)
+ [LeetCode 499.迷宫 III](https://leetcode-cn.com/problems/the-maze-iii/)

## `自己没做出来，正向做法超时了`[AcWing 173.矩阵距离](https://www.acwing.com/problem/content/description/175/)

这道题目我们主要要注意转换原题,原题告诉我们要求最短路,这个我们不能改变,但是原题说让我们求每个数与1的距离,那么我们只需要记住一点,那就是BFS具有层次单调性,且最重要的是天生自带flood-fill问题的解法.
flood-fill问题:一个起点到其他位置的最少步数.
这道题目我们完全可以认为是多起点问题,也就是说,我们直接将所有为1的点,加入到状态队列之中,那么这道题目就解决了.

```txt
给定一个N行M列的01矩阵A，A[i][j] 与 A[k][l] 之间的曼哈顿距离定义为：

dist(A[i][j],A[k][l])=|i−k|+|j−l|
输出一个N行M列的整数矩阵B，其中：

B[i][j]=min1≤x≤N,1≤y≤M,A[x][y]=1dist(A[i][j],A[x][y])
输入格式
第一行两个整数n,m。

接下来一个N行M列的01矩阵，数字之间没有空格。

输出格式
一个N行M列的矩阵B，相邻两个整数之间用一个空格隔开。

数据范围
1≤N,M≤1000
输入样例：
3 4
0001
0011
0110
输出样例：
3 2 1 0
2 1 0 0
1 0 0 1
难度： 简单
时/空限制： 1s / 64MB
总通过数： 1654
总尝试数： 2783
来源： 《算法竞赛进阶指南》, 小马智行面试题
算法标签
```

```java
// 多源BFS要学会从终点开始遍历，互相之间不用覆盖即可。即逆向思维
import java.util.*;

// 在栅格中，字符为1且距离(i, j)最近的距离的点。即从(i, j)开始bfs，找第一个遇到点
public class Main {
    static int N, M;
    static char[][] grid;
    static int[][] dis;
    static boolean[][] visited;
    final static int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    
    static boolean inGrid(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < M;
    }
    
    static int bfs() {
        Queue<int[]> q = new ArrayDeque<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (grid[i][j] == '1') {
                    q.add(new int[]{i, j});
                    visited[i][j] = true;
                }
            }
        }
        
        while (!q.isEmpty()) {
            int[] point = q.remove();
            for (int[] dir : dirs) {
                int rNext = point[0] + dir[0];
                int cNext = point[1] + dir[1];
                if (inGrid(rNext, cNext) && !visited[rNext][cNext]) {
                    q.add(new int[]{rNext, cNext});
                    visited[rNext][cNext] = true;
                    dis[rNext][cNext] = dis[point[0]][point[1]] + 1;
                }
            }
        }
        return 0;
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        dis = new int[N][M];
        grid = new char[N][M];
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) grid[i] = sc.next().trim().toCharArray();
        bfs();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) sb.append(dis[i][j]).append(" ");
            if (i != N - 1) sb.append("\n");
        }
        System.out.println(sb.toString());
    }
}
```