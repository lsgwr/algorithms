# 09_DFS之搜索顺序

## 题目

### [AcWing 1116.马走日](https://www.acwing.com/problem/content/1118/)
> 体会马走日的走法而得到的dirs数组；找到所有方案或最佳方案，就必须用回溯了

```txt
马在中国象棋以日字形规则移动。

请编写一段程序，给定 n∗m 大小的棋盘，以及马的初始位置 (x，y)，要求不能重复经过棋盘上的同一个点，计算马可以有多少途径遍历棋盘上的所有点。

输入格式
第一行为整数 T，表示测试数据组数。

每一组测试数据包含一行，为四个整数，分别为棋盘的大小以及初始位置坐标 n,m,x,y。

输出格式
每组测试数据包含一行，为一个整数，表示马能遍历棋盘的途径总数，若无法遍历棋盘上的所有点则输出 0。

数据范围
1≤T≤9,
1≤m,n≤9,
0≤x≤n−1,
0≤y≤m−1
输入样例：
1
5 4 0 0
输出样例：
32
```

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

### [AcWing 1117.单词接龙](https://www.acwing.com/problem/content/1119/)
> 求两个单词相交长度的时候返回单地是最小值，有点贪心思想的意思

```java

import java.util.*;

class Main {

    static String[] words;

    static int wordsCnt;

    static boolean[] visited;

    static String result = "";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        wordsCnt = Integer.parseInt(sc.nextLine());
        words = new String[wordsCnt * 2];
        for (int i = 0; i < wordsCnt; i++) {
            words[i] = sc.nextLine().trim();
        }
        for (int i = wordsCnt; i < words.length; i++) {
            words[i] = words[i - wordsCnt];
        }

        String start = sc.nextLine().trim();
        for (int i = 0; i < wordsCnt; i++) {
            if (words[i].startsWith(start)) {
                visited = new boolean[wordsCnt * 2];
                String dragon = words[i];
                dfs(i, dragon);
            }
        }
        System.out.println(result.length());
    }

    /**
     * 求两个单词的首尾相交的长度
     *
     * @param dragon 首词
     * @param word   尾词
     * @return 两个单词相交的长度(最大长度)，不相交返回0
     */
    static int getIntersectLen(String dragon, String word) {
        for (int i = 1; i < word.length(); i++) { // 不用i <= word.length()是因为即使整个word作为dragon的后缀，也没法用
            if (dragon.endsWith(word.substring(0, i))) {
                return i; // 返回最少地相交长度，贪心算法，这样结果的长度最长
            }
        }
        return 0; // 没找到相交区域，返回0
    }

    /**
     * @param wordIndex 当前遍历到的单词的索引
     * @param dragon    当前的成语接龙的字符串
     * @return 当前轮能获取到的最大长度
     */
    private static void dfs(int wordIndex, String dragon) {
        if (dragon.length() > result.length()) result = dragon;
        visited[wordIndex] = true; // 单词访问过了，就标记为true
        for (int i = 0; i < words.length; i++) {
            if (visited[i]) {
                continue; // 这个单词用过了，直接返回
            }
            int interLen = getIntersectLen(dragon, words[i]);
            if (interLen == 0) {
                continue; // 重合部分长度为必须大于0 而且不能完全等于words[i]才会继续
            }
            dfs(i, dragon.substring(0, dragon.length() - interLen) + words[i]);
            visited[i] = false; // 当前的单词没有拼到龙上，则标志位要改回去
        }
    }
}
```

### [AcWing 1118.分成互质组](https://www.acwing.com/problem/content/1120/)
> 参考地：https://www.acwing.com/solution/content/10364/ 这个自己没做出来，可以当成了模板题！！需要好好学习下！！！

边分组边枚举分组的思想很值得借鉴！！！

```java
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Main {
    static int n;

    static int[] nums;

    static boolean[][] isPrime; // isPrime[i][j]表示nums中下标为i和下标为j的元素是否互质

    static boolean[] visited;

    static int res = 10; // 求最小分组个数，那么初始化时要为最大值

    static int len = 0; // 当遍历完所有元素时，分成了多少组

    static List<Integer>[] mutualPrimeList;

    // 互质为求其最大公约数，当最大公约数为1时，则两个数互质
    static boolean isMutualPrime(int n, int m) {
        while (m > 0) {
            int t = n % m;
            n = m;
            m = t; // 当m = 0说明两个数存在倍数关系
        }
        return n == 1;
    }

    /**
     * 判断下标为x的元素是否和list中的元素对应nums中的值互质(list是前面的元素已经分好的互质数集合)
     * list中是存储地元素在nums中的下标
     */
    static boolean isMutualPrimes(List<Integer> list, int j) {
        for (int i : list) {
            if (!isPrime[i][j]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        nums = new int[n];
        isPrime = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }
        // 记录互斥的数据对，后面DFS时不用每次都计算一遍了
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (isMutualPrime(nums[i], nums[j])) {
                    isPrime[i][j] = true;
                }
            }
        }
        visited = new boolean[n]; // 记录某个下标是否以及被使用了，枚举每一种
        mutualPrimeList = new List[10]; // 最多分成10组
        for (int i = 0; i < 10; i++) {
            mutualPrimeList[i] = new ArrayList<>();
        }

        dfs(0);
        System.out.println(res);
    }

    // 枚举每个元素属于哪一个组(前面已经分好的组)
    private static void dfs(int index) {
        if (index == n) {
            res = Math.min(res, len);
            return;
        }
        
        if (len >= res) return; // 剪枝操作，一旦当前组数大于前面已有的最小组数，继续DFS后续还会增大，因此可以直接退出了

        for (int i = 0; i < len; i++) { // 尝试把index放入前面分好的组
            if (isMutualPrimes(mutualPrimeList[i], index)) { // 判断index对应的元素是否能放入当前的互质列表
                mutualPrimeList[i].add(index); // 可以地话就放入进去，开启下一轮的DFS
                dfs(index + 1);
                mutualPrimeList[i].remove(mutualPrimeList[i].size() - 1); // 回溯过程中删除前面加的元素
            }
        }

        // 如果上面不能划分进去已有的互质集合，那么就要划分新的集合了
        mutualPrimeList[len].add(index);
        len++;
        dfs(index + 1);
        len--; // 回溯过程中，需要删除数组扩容
        mutualPrimeList[len].remove(mutualPrimeList[len].size() - 1);
    }
}
```