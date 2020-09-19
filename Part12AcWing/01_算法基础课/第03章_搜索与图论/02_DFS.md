# 02_BFS

## 题目
### [AcWing 844.走迷宫](https://www.acwing.com/problem/content/description/846/)
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