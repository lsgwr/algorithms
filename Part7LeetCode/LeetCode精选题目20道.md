# LeetCode精选题目20道

### 1.[56.合并区间](https://leetcode-cn.com/problems/merge-intervals/)
> 贪心

```txt
给出一个区间的集合，请合并所有重叠的区间。

示例 1:

输入: intervals = [[1,3],[2,6],[8,10],[15,18]]
输出: [[1,6],[8,10],[15,18]]
解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
示例 2:

输入: intervals = [[1,4],[4,5]]
输出: [[1,5]]
解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。
```

```java
// 考虑四个用例
// [[1,3],[2,6],[8,10],[15,18]]
// [[1,4],[4,5]]
// [[1,4],[2,3]]
// []
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Solution {
    class PII {
        int first, second;
        public PII(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }
    public int[][] merge(int[][] intervals) {
        if (intervals.length == 0 || intervals[0].length == 0) {
            return new int[0][2];
        }
        List<PII> intervalList = new ArrayList<>();
        for (int[] interval : intervals) {
            intervalList.add(new PII(interval[0], interval[1]));
        }
        // 按照区间左端点进行排序
        Collections.sort(intervalList, (o1, o2) -> o1.first - o2.first); // 按照区间左端点进行排序
        List<int[]> result = new ArrayList<>();
        PII pii = intervalList.get(0);
        int left = pii.first;
        int right = pii.second;
        for (int i = 1; i < intervalList.size(); i++) {
            pii = intervalList.get(i);
            if (right >= pii.first) { // 如果新来的区间的左端点小于right，则可以合并区间
                if (right > pii.second) continue; // [left, right]完全包含pii
                right = pii.second;
            } else { // 一旦right比新来的区间的左端点还小，那么肯定要新算一个区间了
                result.add(new int[]{left, right});
                left = pii.first;
                right = pii.second;
            }
        }
        result.add(new int[]{left, right});
        int[][] res = new int[result.size()][2];
        for (int i = 0; i < result.size(); i++) {
            res[i] = result.get(i);
        }
        return res;
    }
}
```

### 2.[325.和为K的最长子数组长度](https://leetcode-cn.com/problems/maximum-size-subarray-sum-equals-k/)
> 前缀和 + 哈希 + 贪心
```txt
给定一个数组 nums 和一个目标值 k，找到和等于 k 的最长子数组长度。如果不存在任意一个符合要求的子数组，则返回 0。

注意:
 nums 数组的总和是一定在 32 位有符号整数范围之内的。

示例 1:

输入: nums = [1, -1, 5, -2, 3], k = 3
输出: 4 
解释: 子数组 [1, -1, 5, -2] 和等于 3，且长度最长。
示例 2:

输入: nums = [-2, -1, 2, 1], k = 1
输出: 2 
解释: 子数组 [-1, 2] 和等于 1，且长度最长。
```

> 暴力解法如下（用到了前缀数组）：

```java
class Solution {
    public int maxSubArrayLen(int[] nums, int k) {
        if (nums.length == 0) return 0;
        int N = nums.length + 1;
        int[] a = new int[N];
        int[] S = new int[N];
        for (int i = 1; i < N; i++) { // 对原数组重新赋值，下标 + 1才能方便前缀和计算
            a[i] = nums[i - 1];
            S[i] = S[i - 1] + a[i];
        }

        int res = 0;
        for (int i = 1; i < N; i++) {
            for (int j = i; j < N; j++) { // 一定注意这里是j = i而不是j = i + 1,
                if (S[j] - S[i - 1] == k) {
                    res = Math.max(res, j - i + 1);
                }
            }
        }
        return res;
    }
}
```

> 前缀和 + 哈希 + 贪心

```java
class Solution {
    // 注意不能用滑动窗口，滑动窗口一般是有序数组或者字符串才用
    public int maxSubArrayLen(int[] nums, int k) {
        if (nums.length == 0) return 0;
        int N = nums.length + 1;
        int[] a = new int[N];
        int[] S = new int[N];
        for (int i = 1; i < N; i++) {
            a[i] = nums[i - 1];
            S[i] = S[i - 1] + a[i];
        }

        // 遍历前缀和，同时构建map，键为前缀和1到i的前缀和即S[i] - S[0] = S[i]，值为i
        Map<Integer, Integer> mapSIndex = new HashMap<>();
        int res = 0;
        for (int i = 0; i < S.length; i++) {
            // 需要在map中匹配的剩余值
            int target = S[i] - k;
            // 如果能匹配到剩余值对应的map的key，那么key对应的的值value就是要找的区间做端点(i是右端点)
            if (mapSIndex.containsKey(target)) {
                int index = mapSIndex.get(target); // [index, i)就是我们要找的区间
                res = Math.max(res, i - index);
            }

            // 如果前缀和从未出现过，则加入当前前缀和的最优索引，注意如果这个前缀和的值之前出现过则不能覆盖(贪心思想：先加入的前缀和对应的索引肯定更小)
            if (!mapSIndex.containsKey(S[i])) {
                mapSIndex.put(S[i], i);
            }
        }
        return res;
    }
}
```


### 3.[739.每日温度](https://leetcode-cn.com/problems/daily-temperatures/)
> 单调栈，找出每个元素右边第一个比它大的元素的下标

```txt
请根据每日 气温 列表，重新生成一个列表。对应位置的输出为：要想观测到更高的气温，至少需要等待的天数。如果气温在这之后都不会升高，请在该位置用 0 来代替。

例如，给定一个列表 temperatures = [73, 74, 75, 71, 69, 72, 76, 73]，你的输出应该是 [1, 1, 4, 2, 1, 1, 0, 0]。

提示：气温 列表长度的范围是 [1, 30000]。每个气温的值的均为华氏度，都是在 [30, 100] 范围内的整数。
```

```java
class Solution {
    // 找出每个元素右边第一个比它大的元素的下标
    int[] nextGreaterElement(int[] nums) {
        Stack<Integer> st = new Stack<>();
        int[] res = new int[nums.length];
        for (int i = nums.length - 1; i >= 0; i--) {
            // 只要栈不为空，且栈顶元素比当前元素小，则弹出栈顶元素。每个元素入栈一次出栈一次，所以时间复杂度为O(n)
            while (!st.isEmpty() && nums[i] >= nums[st.peek()]) st.pop();
            int nearestMax = st.isEmpty() ? -1 : st.peek();
            res[i] = nearestMax == -1 ? 0 : (nearestMax - i);
            st.push(i); // 插入x仍然保持住单调递减栈的特性
        }
        return res;
    }

    public int[] dailyTemperatures(int[] nums) {
        return nextGreaterElement(nums);
    }
}
```

### 4.[554.砖墙](https://leetcode-cn.com/problems/brick-wall/)
> // 计算每行所有缝隙位置(距离最左侧的距离)，枚举所有缝隙位置去划线，通过二分法确定当前的线是否穿过砖

```txt
你的面前有一堵矩形的、由多行砖块组成的砖墙。 这些砖块高度相同但是宽度不同。你现在要画一条自顶向下的、穿过最少砖块的垂线。

砖墙由行的列表表示。 每一行都是一个代表从左至右每块砖的宽度的整数列表。

如果你画的线只是从砖块的边缘经过，就不算穿过这块砖。你需要找出怎样画才能使这条线穿过的砖块数量最少，并且返回穿过的砖块数量。

你不能沿着墙的两个垂直边缘之一画线，这样显然是没有穿过一块砖的。
```

```java
// 需要重点关注的用例：
// [[1,2,2,1],[3,1,2],[1,3,2],[2,4],[3,1,2],[1,3,1,1]] 输出为2
// [[1, 1, 1, 1, 1, 1, 1, 1, 1, 1], [1, 1, 1, 1, 1, 1, 1, 1, 1, 1], [1, 1, 1, 1, 1, 1, 1, 1, 1, 1]] 结果为0
// [[1],[1],[1]]  结果为3
class Solution {

    // 判断缝隙位置是否在当前行存在
    private boolean binarySearch(List<Integer> rowCracks, int crack) {
        int l = 0;
        int r = rowCracks.size();
        while (l < r) {
            int mid = l + r >> 1;
            if (rowCracks.get(mid) > crack) r = mid;
            else if (rowCracks.get(mid) < crack) l = mid + 1;
            else return true; // 找到缝隙缝隙了
        }
        return false; // 没找到缝隙
    }

    public int leastBricks(List<List<Integer>> wall) {
        Set<Integer> crackAllSet = new HashSet<>(); // 所有行不重复的缝隙集合
        List<List<Integer>> rowCracksList = new ArrayList<>();
        for (int i = 0; i < wall.size(); i++) {
            rowCracksList.add(new ArrayList<>());
            int sum = 0; // 存储缝隙的位置
            List<Integer> wallRow = wall.get(i); // 第i行墙的情况
            for (int j = 0; j < wallRow.size() - 1; j++) {
                sum += wallRow.get(j); // 获取当前的裂缝距最左端的距离
                rowCracksList.get(i).add(sum);
                crackAllSet.add(sum); // 累计所有的缝隙
            }
        }

        int res = wall.size(); // 初始化为最多穿过的砖数(即为行数)，不要初始化为其他值，否则可能找不到缝隙，比如用例 [[1],[1],[1]]，结果应该是 3
        // 遍历所有的缝隙，通过二分法判断缝隙能够穿过某一行
        for (int crack : crackAllSet) {
            int wallsThrough = wall.size(); // 初始化默认穿过所有的砖 
            for (List<Integer> rowCracks : rowCracksList) {
                if (binarySearch(rowCracks, crack)) { // 找到缝隙了，没有穿过当前行的砖
                    wallsThrough--;
                }
            }
            res = Math.min(res,  wallsThrough);
        }
        return res;
    }
}
```
### 5.[76.最小子串覆盖](https://leetcode-cn.com/problems/minimum-window-substring/submissions/)
> 滑动窗口

```txt
给你一个字符串 S、一个字符串 T 。请你设计一种算法，可以在 O(n) 的时间复杂度内，从字符串 S 里面找出：包含 T 所有字符的最小子串。


示例：

输入：S = "ADOBECODEBANC", T = "ABC"
输出："BANC"
```

```java
class Solution {
    public String minWindow(String s, String t) {
        char[] sa = s.toCharArray();
        char[] ta = t.toCharArray();
        Map<Character, Integer> need = new HashMap<>(); // T中字符出现次数
        Map<Character, Integer> window = new HashMap<>(); // 「窗口」中的相应字符的出现次数
        for (char c : ta) {
            if (need.get(c) == null) need.put(c, 0);
            else need.put(c, need.get(c) + 1);
        }

        int left = 0, right = 0; // 使用left和right变量初始化窗口的两端，不要忘了，区间[left, right)是左闭右开的，所以初始情况下窗口没有包含任何元素：
        int valid = 0; // 窗口中满足need条件的字符个数
        int start = 0, len = Integer.MAX_VALUE; // 最小覆盖子串的起始索引及长度
        while (right < s.length()) {
            char c = sa[right]; // c是将移入窗口的字符
            right++; // 右边界右移，扩大窗口
            // Todo: 进行窗口内数据的一些列更新
            if (need.containsKey(c)) {
                if (window.get(c) == null) window.put(c, 0);
                else window.put(c, window.get(c) + 1);
                if (window.get(c).equals(need.get(c))) valid++; // 一个字符频率相等，说明当前字符c满足情况了。这里一定要用equals而不是==，否则会有大用例通过不了
            }

            // 判断左侧窗口是否要收缩
            while (valid == need.size()) { // 所有字符都在滑动窗口内找到了
                // Todo: 在这里更新最小覆盖字符串相关参数
                if (right - left < len) {
                    start = left;
                    len = right - left;
                }
                char d = sa[left]; // d是即将移出窗口的字符
                left++; // 左边界右移，窗口缩小
                if (need.containsKey(d)) {
                    if (window.get(d).equals(need.get(d))) valid--; // 左边界字符正要满足滑动窗口包含目标值，删除一个左边界可能会影响。这里一定要用equals而不是==，否则会有大用例通过不了
                    window.put(d, window.get(d) - 1); // 更新当前点的字符d的频率
                }
            }
        }
        return len == Integer.MAX_VALUE ? "" : s.substring(start, start + len); // 注意是开始索引和结束索引取字符串
    }
}
```
### 6.[438.找到字符串中所有字母异位词](https://leetcode-cn.com/problems/find-all-anagrams-in-a-string/)
> 和上面的题几乎完全一样，就是变换了下要返回的数据，把start依次加入到结果列表中并返回即可

```txt
给定一个字符串 s 和一个非空字符串 p，找到 s 中所有是 p 的字母异位词的子串，返回这些子串的起始索引。

字符串只包含小写英文字母，并且字符串 s 和 p 的长度都不超过 20100。

说明：

字母异位词指字母相同，但排列不同的字符串。
不考虑答案输出的顺序。
```

```java
class Solution {
    public List<Integer> findAnagrams(String s, String t) {
        char[] sa = s.toCharArray();
        char[] ta = t.toCharArray();
        Map<Character, Integer> need = new HashMap<>(); // T中字符出现次数
        Map<Character, Integer> window = new HashMap<>(); // 「窗口」中的相应字符的出现次数
        for (char c : ta) {
            if (need.get(c) == null) need.put(c, 0);
            else need.put(c, need.get(c) + 1);
        }

        int left = 0, right = 0; // 使用left和right变量初始化窗口的两端，不要忘了，区间[left, right)是左闭右开的，所以初始情况下窗口没有包含任何元素：
        int valid = 0; // 窗口中满足need条件的字符个数
        int start = 0, len = t.length(); // 目标字符串的长度
        List<Integer> res = new ArrayList<>(); // 存储满足调试的开始索引
        while (right < s.length()) {
            char c = sa[right]; // c是将移入窗口的字符
            right++; // 右边界右移，扩大窗口
            // Todo: 进行窗口内数据的一些列更新
            if (need.containsKey(c)) {
                if (window.get(c) == null) window.put(c, 0);
                else window.put(c, window.get(c) + 1);
                if (window.get(c).equals(need.get(c))) valid++; // 一个字符频率相等，说明当前字符c满足情况了。这里一定要用equals而不是==，否则会有大用例通过不了
            }

            // 判断左侧窗口是否要收缩
            while (valid == need.size()) { // 所有字符都在滑动窗口内找到了
                // Todo: 在这里更新最小覆盖字符串相关参数
                if (right - left == len) {
                    start = left;
                    res.add(start);
                }
                char d = sa[left]; // d是即将移出窗口的字符
                left++; // 左边界右移，窗口缩小
                if (need.containsKey(d)) {
                    if (window.get(d).equals(need.get(d))) valid--; // 左边界字符正要满足滑动窗口包含目标值，删除一个左边界可能会影响。这里一定要用equals而不是==，否则会有大用例通过不了
                    window.put(d, window.get(d) - 1); // 更新当前点的字符d的频率
                }
            }
        }
        return res;
    }
}
```
### 7.[200.岛屿数量](https://leetcode-cn.com/problems/number-of-islands/)
> DFS求联通分量的个数

```txt
给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。

岛屿总是被水包围，并且每座岛屿只能由水平方向或竖直方向上相邻的陆地连接形成。

此外，你可以假设该网格的四条边均被水包围。
```
```java
class Solution {
    private int Rs;
    private int Cs;
    private char[][] grid;
    private boolean[][] visited;
    private final int[][] dirs = {{0, 1},{1, 0}, {0, -1}, {-1, 0}};
    private int ccCount;

    private boolean inGrid(int r, int c){
        return r >= 0 && r < Rs && c >= 0 && c < Cs;
    }

    public int numIslands(char[][] grid) {
        if(grid == null || grid.length == 0){
            return 0;
        }
        Rs = grid.length;
        Cs = grid[0].length;
        this.grid = grid;
        visited = new boolean[Rs][Cs];
        ccCount = 0;
        for(int r = 0; r < Rs; r++){
            for(int c = 0; c < Cs; c++){
                if(!visited[r][c] && grid[r][c] == '1'){
                    dfs(r, c);
                    ccCount++;
                }
            }
        }
        return ccCount;
    }

    private void dfs(int r, int c){
        visited[r][c] = true;
        for(int[] dir : dirs){
            int rNext = r + dir[0];
            int cNext = c + dir[1];
            if(inGrid(rNext, cNext) && !visited[rNext][cNext] && grid[rNext][cNext] == '1'){
                dfs(rNext, cNext);
            }
        }
    }
}
```

### 8.[1219.黄金矿工](https://leetcode-cn.com/problems/path-with-maximum-gold/)
> DFS求最大值，很简单
```java
你要开发一座金矿，地质勘测学家已经探明了这座金矿中的资源分布，并用大小为 m * n 的网格 grid 进行了标注。每个单元格中的整数就表示这一单元格中的黄金数量；如果该单元格是空的，那么就是 0。

为了使收益最大化，矿工需要按以下规则来开采黄金：

每当矿工进入一个单元，就会收集该单元格中的所有黄金。
矿工每次可以从当前位置向上下左右四个方向走。
每个单元格只能被开采（进入）一次。
不得开采（进入）黄金数目为 0 的单元格。
矿工可以从网格中 任意一个 有黄金的单元格出发或者是停止。
```

```java
class Solution {
    int R, C;
    int[][] grid;
    boolean[][] visited;
    int res = 0; // 黄金的最大值
    final int[][] dirs = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};

    public int getMaximumGold(int[][] grid) {
        this.R = grid.length;
        this.C = grid[0].length;
        this.grid = grid;

        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (grid[r][c] != 0) {
                    visited = new boolean[R][C];
                    dfs(r, c, grid[r][c]); // 每次DFS都刷新最大可以得到的黄金量
                }
            }
        }
        return res;
    }

    private void dfs(int rCur, int cCur, int goldTotal) {
        visited[rCur][cCur] = true;
        res = Math.max(res, goldTotal); // 一共的黄金量去更新最大的黄金量

        for (int[] dir : dirs) {
            int rNext = rCur + dir[0];
            int cNext = cCur + dir[1];
            if (inGrid(rNext, cNext) && !visited[rNext][cNext] && grid[rNext][cNext] != 0) {
                dfs(rNext, cNext, goldTotal + grid[rNext][cNext]);
                visited[rNext][cNext] = false;
            }
        }
    }

    private boolean inGrid(int r, int c) {
        return r >= 0 && r < R && c >= 0 && c < C;
    }
}
```

### 9.[505.迷宫II](https://leetcode-cn.com/problems/the-maze-ii/)
### 10.[51.N皇后](https://leetcode-cn.com/problems/n-queens-ii/)
### 11.[22.括号生成](https://leetcode-cn.com/problems/generate-parentheses/)
### 12.[207.课程表](https://leetcode-cn.com/problems/course-schedule/)
### 13.[562.矩阵中最长的连续1线段](https://leetcode-cn.com/problems/longest-line-of-consecutive-one-in-matrix/)
### 14.[1477.找两个和为目标值且不重叠的子数组](https://leetcode-cn.com/problems/find-two-non-overlapping-sub-arrays-each-with-target-sum/)
### 15.[1405.最长快乐字符串](https://leetcode-cn.com/problems/longest-happy-string/)
> 考察点 字符串， 贪心算法

### 16.[990.等式方程的可满足性](https://leetcode-cn.com/problems/satisfiability-of-equality-equations/)
> 考察点：并查集/图

### 17.[1129.颜色交替的最短路径](https://leetcode-cn.com/problems/shortest-path-with-alternating-colors/)
> 考察点：广度优先搜索/图

### 18.[1190.反转每对括号间的子串](https://leetcode-cn.com/problems/reverse-substrings-between-each-pair-of-parentheses/)
> 考察点 字符串/栈

### 19.[LCP12.小张刷题计划](https://leetcode-cn.com/problems/xiao-zhang-shua-ti-ji-hua/)
> 考察点 数组/二分法

### 20.[1419.数青蛙](https://leetcode-cn.com/problems/minimum-number-of-frogs-croaking/)
> 中等；考察点 字符串
