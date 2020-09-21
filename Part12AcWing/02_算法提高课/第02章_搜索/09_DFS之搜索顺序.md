# 09_DFS之搜索顺序

## 题目

### [AcWing 1116.马走日](https://www.acwing.com/problem/content/1118/)
> 体会马走日的走法而得到的dirs数组；找到所有方案或最佳方案，就必须用回溯了
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
