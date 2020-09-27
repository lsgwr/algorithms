# IDA*
> 参考文章：https://blog.csdn.net/sslz_fsy/article/details/82154996

## 题目
### [AcWing 180.排书](https://www.acwing.com/problem/content/182/)
```txt
给定n本书，编号为1-n。

在初始状态下，书是任意排列的。

在每一次操作中，可以抽取其中连续的一段，再把这段插入到其他某个位置。

我们的目标状态是把书按照1-n的顺序依次排列。

求最少需要多少次操作。

输入格式
第一行包含整数T，表示共有T组测试数据。

每组数据包含两行，第一行为整数n，表示书的数量。

第二行为n个整数，表示1-n的一种任意排列。

同行数之间用空格隔开。

输出格式
每组数据输出一个最少操作次数。

如果最少操作次数大于或等于5次，则输出”5 or more”。

每个结果占一行。
```

> 参考：https://www.acwing.com/solution/content/13186/

```java
import java.io.*;
import java.util.*;

class Main {
    static int N = 20;
    static char[] cy = new char[N];
    static int n;
    static String st, ed;
    static int res;

    static int extend(Queue<String> q, Map<String, Integer> d1, Map<String, Integer> d2) {

        int sz = q.size();

        while (sz-- > 0) {
            String t = q.poll();

            char[] a = t.toCharArray();
            for (int len = 1; len <= n; len++) {
                for (int l = 0; l + len - 1 < n; l++) {
                    int r = l + len - 1;
                    for (int k = r + 1; k < n; k++) {
                        cy = Arrays.copyOf(a, N);
                        int y = l;
                        for (int x = r + 1; x <= k; x++) a[y++] = cy[x];
                        for (int x = l; x <= r; x++) a[y++] = cy[x];
                        String cur = "";
                        for (int i = 0; i < n; i++) cur += a[i];
                        a = Arrays.copyOf(cy, N);
                        if (d2.containsKey(cur)) return d1.get(t) + d2.get(cur) + 1;
                        if (d1.containsKey(cur)) continue;
                        q.add(cur);
                        d1.put(cur, d1.get(t) + 1);
                    }
                }
            }
        }

        return 5;
    }

    static int bfs() {

        Map<String, Integer> d1 = new HashMap<String, Integer>();
        Queue<String> q1 = new LinkedList<String>();
        Map<String, Integer> d2 = new HashMap<String, Integer>();
        Queue<String> q2 = new LinkedList<String>();

        q1.add(st); d1.put(st, 0);
        q2.add(ed); d2.put(ed, 0);

        if (st.equals(ed)) return 0;

        int cnt = 4; // 限制扩展4次,保证q1和q2各扩展两层
        while (cnt-- > 0) {

            int t = -1;
            if (q1.size() <= q2.size()) t = extend(q1, d1, d2);
            else t = extend(q2, d2, d1);

            if (t < 5) return t;
        }

        return 5;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(in.readLine());

        while (T-- > 0) {
            n = Integer.parseInt(in.readLine());
            String[] cur = in.readLine().split(" ");
            st = "";
            ed = "";
            for (int i = 0; i < n; i++) {
                int tmp = Integer.parseInt(cur[i]);
                st += (char) (tmp + 'A' - 1);
                ed += (char) (i + 'A');
            }

            int t = bfs();

            if (t >= 5) System.out.println("5 or more");
            else System.out.println(t);
        }
    }
}
```


### [AcWing 181.回转游戏](https://www.acwing.com/problem/content/183/)
```txt
如下图所示，有一个“#”形的棋盘，上面有1,2,3三种数字各8个。

给定8种操作，分别为图中的A~H。

这些操作会按照图中字母和箭头所指明的方向，把一条长为8的序列循环移动1个单位。

例如下图最左边的“#”形棋盘执行操作A后，会变为下图中间的“#”形棋盘，再执行操作C后会变成下图最右边的“#”形棋盘。

给定一个初始状态，请使用最少的操作次数，使“#”形棋盘最中间的8个格子里的数字相同。
```

> 参考：https://www.acwing.com/solution/content/4056/

```cpp
#include <cstdio>
#include <cstring>
#include <iostream>
#include <algorithm>

using namespace std;

const int N = 24;

int q[N];
int op[8][7] = {
    {0, 2, 6, 11, 15, 20, 22},
    {1, 3, 8, 12, 17, 21, 23},
    {10, 9, 8, 7, 6, 5, 4},
    {19, 18, 17, 16, 15, 14, 13},
    {23, 21, 17, 12, 8, 3, 1},
    {22, 20, 15, 11, 6, 2, 0},
    {13, 14, 15, 16, 17, 18, 19},
    {4, 5, 6, 7, 8, 9, 10}
};
int center[8] = {6, 7, 8, 11, 12, 15, 16, 17};
int opposite[8] = {5, 4, 7, 6, 1, 0, 3, 2};

int path[100];

int f()
{
    static int sum[4];
    memset(sum, 0, sizeof sum);
    for (int i = 0; i < 8; i ++ ) sum[q[center[i]]] ++ ;

    int s = 0;
    for (int i = 1; i <= 3; i ++ ) s = max(s, sum[i]);
    return 8 - s;
}

bool check()
{
    for (int i = 1; i < 8; i ++ )
        if (q[center[i]] != q[center[0]])
            return false;
    return true;
}

void operation(int x)
{
    int t = q[op[x][0]];
    for (int i = 0; i < 6; i ++ ) q[op[x][i]] = q[op[x][i + 1]];
    q[op[x][6]] = t;
}

bool dfs(int depth, int max_depth, int last)
{
    if (depth + f() > max_depth) return false;
    if (check()) return true;

    for (int i = 0; i < 8; i ++ )
    {
        if (opposite[i] == last) continue;
        operation(i);
        path[depth] = i;
        if (dfs(depth + 1, max_depth, i)) return true;
        operation(opposite[i]);
    }

    return false;
}

int main()
{
    while (scanf("%d", &q[0]), q[0])
    {
        for (int i = 1; i < N; i ++ ) scanf("%d", &q[i]);
        int depth = 0;
        while (!dfs(0, depth, -1))
        {
            depth ++ ;
        }
        if (!depth) printf("No moves needed");
        for (int i = 0; i < depth; i ++ ) printf("%c", 'A' + path[i]);
        printf("\n%d\n", q[6]);
    }

    return 0;
}
```