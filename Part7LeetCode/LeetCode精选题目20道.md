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
import java.util.*;

class Solution {
    public int[][] merge(int[][] intervals) {
        // 按照区间左端点进行排序
        Arrays.sort(intervals, (o1, o2) -> o1[0] - o2[0]); // 按照区间左端点进行排序
        List<int[]> result = new ArrayList<>();
        int left = Integer.MIN_VALUE, right = Integer.MIN_VALUE;
        for (int[] interval : intervals) {
            if (right >= interval[0]) { // 当前区间的左端点 < right，则可以合并区间
                if (right > interval[1]) continue; // [left, right]完全包含interval
                right = interval[1]; // [left, right]没完全包含interval，需要更新右端点
            } else { // 一旦right比新来的区间的左端点还小，那么肯定要新开一个区间了
                if (left != Integer.MIN_VALUE) result.add(new int[]{left, right});
                left = interval[0];
                right = interval[1];
            }
        }
        if (left != Integer.MIN_VALUE) result.add(new int[]{left, right}); // 最终的left和right记得还要加进去
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
    // 注意不能用滑动窗口，滑动窗口一般是正数数组或者字符串才用(这两种情形当right++的时候，目标值增加，left++的时候，目标值减少。正数数组目标值是区间和，字符串时字符出现频率)
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
> 这个题用DFS肯定会超时，见自己的DFS超时实现：https://leetcode-cn.com/submissions/detail/110787824/

此外，此题可以得到如下经验：

+ 1.求最小值时，在邻接状态确定的情况下，优先选BFS，如LeetCode 505；在邻接状态不确定的情况下(随着到的点不同会动态变化),可是使用DFS找到所有达到目标点的情况，然后取这些方案中的最小值，如AcWing 1118.分成互质组 和 AcWing 165.小猫爬山
+ 2.这个题目的特殊情况，一个点可以经过多次，不断更新某个点的最小值，尤其是终点，千万不要一到终点就提前退出！！

```txt
由空地和墙组成的迷宫中有一个球。球可以向上下左右四个方向滚动，但在遇到墙壁前不会停止滚动。当球停下时，可以选择下一个方向。

给定球的起始位置，目的地和迷宫，找出让球停在目的地的最短距离。距离的定义是球从起始位置（不包括）到目的地（包括）经过的空地个数。如果球无法停在目的地，返回 -1。

迷宫由一个0和1的二维数组表示。 1表示墙壁，0表示空地。你可以假定迷宫的边缘都是墙壁。起始位置和目的地的坐标通过行号和列号给出。

 

示例 1:

输入 1: 迷宫由以下二维数组表示

0 0 1 0 0
0 0 0 0 0
0 0 0 1 0
1 1 0 1 1
0 0 0 0 0

输入 2: 起始位置坐标 (rowStart, colStart) = (0, 4)
输入 3: 目的地坐标 (rowDest, colDest) = (4, 4)

输出: 12

解析: 一条最短路径 : left -> down -> left -> down -> right -> down -> right。 总距离为 1 + 1 + 3 + 1 + 2 + 2 + 2 = 12。
```

```java
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

class Solution {

    int[][] grid;
    int R, C;
    final int[][] dirs = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};

    // 当前位置必须在栅格内，而且必须不是铁壁，才能继续往下走
    private boolean inGrid(int r, int c) {
        return r >= 0 && r < R && c >= 0 && c < C;
    }

    // BFS原生就是求最短距离的，这里更好一些
    private int bfs(int rStart, int cStart, int rEnd, int cEnd) {
        int[][] dis = new int[R][C]; // dis[r]][c]表示位置[r, c]的点到起点的距离
        for (int i = 0; i < R; i++) {
            Arrays.fill(dis[i], Integer.MAX_VALUE); // 要求最小值，就要初始化为最大值，因为每个点可能会更新多次
        }

        Queue<Integer> rQueue = new ArrayDeque<>();
        Queue<Integer> cQueue = new ArrayDeque<>();
        rQueue.add(rStart);
        cQueue.add(cStart);
        dis[rStart][cStart] = 0; // 起点距离起点的步数为0

        while (!rQueue.isEmpty() && !cQueue.isEmpty()) {
            int rCur = rQueue.remove();
            int cCur = cQueue.remove();
            // if (rCur == rEnd && cCur == cEnd) return dis[rEnd][cEnd]; // 可能会有多种方案都能经过重点，所以这里一定不要提前退出！！
            for (int[] dir : dirs) {
                int rNext = rCur + dir[0];
                int cNext = cCur + dir[1];
                int stepDis = 0;
                while (inGrid(rNext, cNext) && grid[rNext][cNext] == 0) { // 下一个方向是能走的条件：在栅格内且是空地
                    // 计算按照dir方向滚动最终能停在哪个点
                    // 2.尝试继续往下一个位置走
                    rNext = rNext + dir[0];
                    cNext = cNext + dir[1];
                    // 3.距离 + 1
                    stepDis++;
                }
                // 上面的while结束，[rNextFinal, cNextFinal]存储地是最后一个合法的位置的下一个位置，所以要再减回去，才是滚动后停下来的位置
                rNext = rNext - dir[0];
                cNext = cNext - dir[1];
                // 注意在一般的BFS中，我们不会经过同一个点超过一次(用visited数组来控制)，但是在这道题目中，只要从起始位置大当前位置的步数
                // 小于之前走法得到的最小步数dis[rNext][cNext]，我们就会把点(rNext, cNext)点加入到队列中，再次进行BFS
                if (dis[rCur][cCur] + stepDis < dis[rNext][cNext]) {
                    dis[rNext][cNext] = dis[rCur][cCur] + stepDis;
                    rQueue.add(rNext);
                    cQueue.add(cNext);
                }
            }
        }
        return dis[rEnd][cEnd] == Integer.MAX_VALUE ? -1 : dis[rEnd][cEnd];
    }

    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        R = maze.length;
        C = maze[0].length;
        grid = maze;
        return bfs(start[0], start[1], destination[0], destination[1]);
    }

    /**
     * [0,4] [4,4]  true
     * [4,3] [0,1]  false
     * [0,4] [1,2]  true
     */
    public static void main(String[] args) {
        int[][] maze = {{0, 0, 1, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 1, 0}, {1, 1, 0, 1, 1}, {0, 0, 0, 0, 0}};
        int[] start = {0, 4};
        int[] destination = {4, 4};
        System.out.println(new Solution().shortestDistance(maze, start, destination));
    }
}
```


### 10.[51.N皇后](https://leetcode-cn.com/problems/n-queens-ii/)
> 也是[AcWing 843](https://www.acwing.com/problem/content/845/),规则是`任意两个皇后都不能处于同一行、同一列或同一斜线上`，把check函数写好，剩下地就是不断尝试放皇后了

```txt
n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。



上图为 8 皇后问题的一种解法。

给定一个整数 n，返回所有不同的 n 皇后问题的解决方案。

每一种解法包含一个明确的 n 皇后问题的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
```

```java
import java.util.ArrayList;
import java.util.List;

class Solution {
    int n;
    boolean[][] visited; // 节点访问数组
    List<List<String>> res;

    /**
     * 检查位置(r, c)处放置皇后是否和之前放地冲突
     */
    private boolean check(int r, int c, List<int[]> points) {
        for (int[] point : points) {
            // 同一行 or 同一列则不能放，直接返回
            if (point[0] == r || point[1] == c) return false;
            // 斜率为1表明在一条斜线上，直接返回，不和上面的条件合并是为了防止point[1] - c值为0
            double k = Math.abs((point[0] - r) * 1.0 / (point[1] - c)); // 注意斜率为负数的情况
            if (k == 1.0) return false;
        }
        return true;
    }

    private void dfs(int r, int c, List<int[]> points) {
        visited[r][c] = true;
        points.add(new int[]{r, c});
        if (points.size() == n) { // 找到了一个合适的放置方案
            List<String> solution = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < n; j++) {
                    if (visited[i][j]) sb.append("Q");
                    else sb.append(".");
                }
                solution.add(sb.toString());
            }
            res.add(solution);
            return;
        }

        // 上面的点满足条件了，则下一个必须从下一行开始了
        if (r + 1 < n) { // 行必须在合适的范围
            for (int i = 0; i < n; i++) { // 固定行，遍历列
                if (!visited[r + 1][i] && check(r + 1, i, points)) {
                    dfs(r + 1, i, points);
                    visited[r + 1][i] = false;
                    points.remove(points.size() - 1);
                }
            }
        }
    }

    public List<List<String>> solveNQueens(int n) {
        this.n = n;
        res = new ArrayList<>();
        for (int i = 0; i < n; i++) { // 第一个起始的元素肯定是在第1行
            List<int[]> points = new ArrayList<>(); // 记录皇后放置的位置
            visited = new boolean[n][n]; // 每次开始的位置不一样，所以要重置访问数组
            dfs(0, i, points);
        }
        return res;
    }
}
```


### 11.[22.括号生成](https://leetcode-cn.com/problems/generate-parentheses/)
> 栈 + 暴力DFS

```txt
数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。

 

示例：

输入：n = 3
输出：["((()))", "(()())", "(())()", "()(())", "()()()"]
```

```java
import java.lang.reflect.Array;
import java.util.*;

class Solution {

    int n;
    List<String> res;
    // 校验字符集合是否符合括号匹配的规则
    boolean check(List<Character> chs) {
        Stack<Character> st = new Stack<>();
        int left = n;
        int right = n;
        for (char c : chs) {
            if (c == '(') {
                left--;
                st.push(c);
            }
            if (c == ')') {
                right--;
                if (st.isEmpty()) return false;
                st.pop();
            }
        }
        return st.isEmpty() && left == 0 && right == 0;
    }

    public List<String> generateParenthesis(int n) {
        this.n = n;
        this.res = new ArrayList<>();
        List<Character> chs = new ArrayList<>();
        dfs(chs);
        return res;
    }

    private void dfs (List<Character> chs) {
        if (chs.size() == n * 2) {
            if (!check(chs)) return;
            StringBuilder sb = new StringBuilder();
            for (Character ch : chs) {
                sb.append(ch);
            }
            res.add(sb.toString());
            return;
        }
        // 1.尝试放(
        chs.add('(');
        dfs(chs);
        chs.remove(chs.size() - 1);

        // 2.尝试放)
        chs.add(')');
        dfs(chs);
        chs.remove(chs.size() - 1);
    }
}
```
### 12.[207.课程表](https://leetcode-cn.com/problems/course-schedule/)
> 拓扑排序,知识点见[Part2BasicGraph/第13章_有向图及相关算法](../Part2BasicGraph/第13章_有向图及相关算法.md#136137-拓扑排序--仅针对有向图)

```txt
你这个学期必须选修 numCourse 门课程，记为 0 到 numCourse-1 。

在选修某些课程之前需要一些先修课程。 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示他们：[0,1]

给定课程总量以及它们的先决条件，请你判断是否可能完成所有课程的学习？


示例 1:

输入: 2, [[1,0]] 
输出: true
解释: 总共有 2 门课程。学习课程 1 之前，你需要完成课程 0。所以这是可能的。
示例 2:

输入: 2, [[1,0],[0,1]]
输出: false
解释: 总共有 2 门课程。学习课程 1 之前，你需要先完成​课程 0；并且学习课程 0 之前，你还应先完成课程 1。这是不可能的。
```


```java
class Solution {
    /**
     * 拓扑排序结果
     */
    private List<Integer> topoOrder;

    /**
     * 当前图是否有环
     */
    private boolean hasCycle = false;

    /**
     * 所有顶点的入度
     */
    private int[] inDegreesG;

    /**
     * 根据先决条件构造邻接矩阵，在DFS过程中判断是否存在环，存在环则不能修完所有课程
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // 不能为null
        if (prerequisites == null || prerequisites.length ==0 || prerequisites[0].length == 0) {
            return true;
        }
        // 记录学完一个课程后下面可以学的课程列表，即邻接表
        Map<Integer, List<Integer>> mapAdj = new HashMap<>();
        // 每个点的入度
        inDegreesG = new int[numCourses];
        for (int[] prerequisite : prerequisites) {
            List<Integer> adj;
            if (mapAdj.get(prerequisite[1]) == null) {
                // 不存在这个键的邻接表则要新建
                adj = new ArrayList<>();
                // 把新建的列表加进到map中
                mapAdj.put(prerequisite[1], adj);
            } else {
                // 之前存在这个键的邻接表，直接get
                adj = mapAdj.get(prerequisite[1]);
            }
            // prerequisite[1]  ==> prerequisite[0]
            adj.add(prerequisite[0]);
            inDegreesG[prerequisite[0]]++;
        }
        topoOrder = new ArrayList<>();
        sort(numCourses, mapAdj);
        // 没有环说明可以修完所有课程
        return !hasCycle;
    }

    /**
     * 拓扑排序核心
     */
    public void sort(int numCourses, Map<Integer, List<Integer>> mapAdj) {
        // 存储还未排序的入度为0的顶点
        Queue<Integer> queue = new ArrayDeque<>();
        int[] inDegrees = new int[numCourses];
        for (int v = 0; v < numCourses; v++) {
            inDegrees[v] = inDegreesG[v];
            if (inDegrees[v] == 0) {
                queue.add(v);
            }
        }
        while (!queue.isEmpty()) {
            int cur = queue.remove();
            topoOrder.add(cur);
            if (mapAdj.get(cur)!=null){
                for (int next : mapAdj.get(cur)) {
                    // 更新cur点的邻接点的入度
                    inDegrees[next]--;
                    if (inDegrees[next] == 0) {
                        // 更新后入度为0的顶点加入到queue中
                        queue.add(next);
                    }
                }
            }

        }
        if (topoOrder.size() != numCourses) {
            // 找不到入度为0的点但是还有点没被删除进行拓扑排序，说明图中有环
            hasCycle = true;
            // 没法进行拓扑排序就把已经加入的顶点清理掉
            topoOrder.clear();
        }
    }
}
```
### 13.[562.矩阵中最长的连续1线段](https://leetcode-cn.com/problems/longest-line-of-consecutive-one-in-matrix/)
> 暴力也能过，唉，计算机每秒执行10^7条指令，只要估算在题目限制时间内可以暴力过，就直接暴力！！哈哈

```java
给定一个01矩阵 M，找到矩阵中最长的连续1线段。这条线段可以是水平的、垂直的、对角线的或者反对角线的。

示例:

输入:
[[0,1,1,0],
 [0,1,1,0],
 [0,0,0,1]]
输出: 3
```

```java
// 考虑用例：
// [[1,1,1,1],[0,1,1,0],[0,0,0,1]]
// [[1,1,0,0,1,0,0,1,1,0],[1,0,0,1,0,1,1,1,1,1],[1,1,1,0,0,1,1,1,1,0],[0,1,1,1,0,1,1,1,1,1],[0,0,1,1,1,1,1,1,1,0],[1,1,1,1,1,1,0,1,1,1],[0,1,1,1,1,1,1,0,0,1],[1,1,1,1,1,0,0,1,1,1],[0,1,0,1,1,0,1,1,1,1],[1,1,1,0,1,0,1,1,1,1]]
class Solution {
    int R, C;
    int[][] grid;

    // 计算[r, c]点所在的水平、垂直、对角线的最长连续1线段
    private int getMax1Len(int r, int c) {
        int max = 0;
        // 1.判断水平方向
        int len = 1;
        for (int i = c + 1; i < C; i++) { // 只往右边找
            if (grid[r][i] == 1) len++;
            else break;
        }
        max = Math.max(max, len);

        // 2.判断垂直方向
        len = 1;
        for (int i = r + 1; i < R; i++) {
            if (grid[i][c] == 1) len++;
            else break;
        }
        max = Math.max(max, len);

        // 3.判断正对角线
        len = 1;
        for (int i = r + 1, j = c + 1; i < R && j < C; i++, j++) {
            if (grid[i][j] == 1) len++;
            else break;
        }
        max = Math.max(max, len);

        len = 1;
        // 4.判断反对角线
        for (int i = r + 1, j = c - 1; i < R && j >= 0; i++, j--) {
            if (grid[i][j] == 1) len++;
            else break;
        }
        max = Math.max(max, len);
        
        return max;
    }

    public int longestLine(int[][] M) {
        if (M.length == 0 || M[0].length == 0) return 0;
        R = M.length;
        C = M[0].length;
        grid = M;
        int res = 0; // 求最大值，则初始化为最小值
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (grid[r][c] != 1) continue;
                res = Math.max(res, getMax1Len(r, c));
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[][] M = {
                {1, 1, 0, 0, 1, 0, 0, 1, 1, 0},
                {1, 0, 0, 1, 0, 1, 1, 1, 1, 1},
                {1, 1, 1, 0, 0, 1, 1, 1, 1, 0},
                {0, 1, 1, 1, 0, 1, 1, 1, 1, 1},
                {0, 0, 1, 1, 1, 1, 1, 1, 1, 0},
                {1, 1, 1, 1, 1, 1, 0, 1, 1, 1},
                {0, 1, 1, 1, 1, 1, 1, 0, 0, 1},
                {1, 1, 1, 1, 1, 0, 0, 1, 1, 1},
                {0, 1, 0, 1, 1, 0, 1, 1, 1, 1},
                {1, 1, 1, 0, 1, 0, 1, 1, 1, 1}
        };
        new Solution().longestLine(M);
    }
}
```
### 14.[1477.找两个和为目标值且不重叠的子数组](https://leetcode-cn.com/problems/find-two-non-overlapping-sub-arrays-each-with-target-sum/)
> 前缀和 + 滑动窗口，类似上面的[LeetCode 325.和为K的最长子数组长度](https://leetcode-cn.com/problems/maximum-size-subarray-sum-equals-k/)

> 直接用暴力法，也过了`53/60`个用例，还行，后面再优化下

```txt
给你一个整数数组 arr 和一个整数值 target 。

请你在 arr 中找 两个互不重叠的子数组 且它们的和都等于 target 。可能会有多种方案，请你返回满足要求的两个子数组长度和的 最小值 。

请返回满足要求的最小长度和，如果无法找到这样的两个子数组，请返回 -1 。
```

```java
class Solution {
    // 前缀和 + 哈希，类似325.和为K的最长子数组长度
    public int minSumOfLengths(int[] arr, int target) {
        int[] s = new int[arr.length + 1]; // 前缀和数组
        int[] a = new int[arr.length + 1]; // 数组arr统一往后移动一位
        for (int i = 1; i <= arr.length; i++) {
            a[i] = arr[i - 1];
            s[i] = s[i - 1] + a[i];
        }

        List<int[]> intervalList = new ArrayList<>();

        // 暴力吧，改成滑动窗口兴许能过(所有元素都是正数，right++和增加,left--和减少，因此可以用滑动窗口)
        for (int i = 1; i < a.length; i++) {
            for (int j = i; j < a.length; j++) {
                if (s[j] - s[i - 1] != target) continue; // 遍历下一个区间
                intervalList.add(new int[]{i, j});
            }
        }

        // 再暴力遍历所有满足条件的集合
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < intervalList.size(); i++) {
            for (int j = i + 1; j < intervalList.size(); j++) {
                if (notIntersect(intervalList.get(i), intervalList.get(j))) {
                    res = Math.min(res, getIntervalLenSum(intervalList.get(i), intervalList.get(j)));
                }
            }
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    // 判断两个区间是否不相交
    private boolean notIntersect(int[] intervalLeft, int[] intervalRight) {
        return intervalLeft[1] < intervalRight[0]; // 因为是闭区间，所以必须严格小于，区间才不会相交
    }

    // 获取两个区间的长度和
    private int getIntervalLenSum(int[] intervalLeft, int[] intervalRight) {
        return intervalLeft[1] - intervalLeft[0] + 1 + intervalRight[1] - intervalRight[0] + 1;
    }
}
```

把暴力改成滑动窗口，又能多过4个用例 `57/60`

```java
class Solution {
    // 前缀和 + 哈希，类似325.和为K的最长子数组长度
    public int minSumOfLengths(int[] arr, int target) {
        int[] s = new int[arr.length + 1]; // 前缀和数组
        int[] a = new int[arr.length + 1]; // 数组arr统一往后移动一位
        for (int i = 1; i <= arr.length; i++) {
            a[i] = arr[i - 1];
            s[i] = s[i - 1] + a[i];
        }

        List<int[]> intervalList = new ArrayList<>();

        // 暴力求所有合为target的子区间，改成滑动窗口兴许能过(所有元素都是正数，right++和增加,left--和减少，因此可以用滑动窗口)
        int left = 0, right = 1;
        while (right < a.length) {
            if (s[right] - s[left] == target) {
                intervalList.add(new int[]{left, right}); // 对于arr来说，区间是[left, right)，左闭右开区间
            }

            while (s[right] - s[left] > target) {
                left++;
                if (s[right] - s[left] == target) {
                    intervalList.add(new int[]{left, right}); // 对于arr来说，区间是[left, right)，左闭右开区间
                }
            }
            right++;
        }

        // 再暴力遍历所有满足条件的集合
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < intervalList.size(); i++) {
            int[] iInterval = intervalList.get(i);
            if (iInterval[1] - iInterval[0] >= res) continue; // 单个元素长度超出限制就提前退出
            for (int j = i + 1; j < intervalList.size(); j++) {
                int[] jInterval = intervalList.get(j);
                if (iInterval[1] <= jInterval[0]) { // 两个区间不相交
                    res = Math.min(res, getIntervalLenSum(iInterval, jInterval));
                }
            }
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }


    // 获取两个区间的长度和
    private int getIntervalLenSum(int[] intervalLeft, int[] intervalRight) {
        return intervalLeft[1] - intervalLeft[0] + intervalRight[1] - intervalRight[0];
    }
}
```

把前缀和去掉，直接在滑动窗口中维护sum，又多过了1个用例 `58/60`

```java
class Solution {
    // 前缀和 + 哈希，类似325.和为K的最长子数组长度
    public int minSumOfLengths(int[] arr, int target) {
        List<int[]> intervalList = new ArrayList<>(); // 存储所有和为target的区间左右端点
        // 暴力求所有合为target的子区间，改成滑动窗口兴许能过(所有元素都是正数，right++和增加,left--和减少，因此可以用滑动窗口)
        int left = 0, right = 0, sum = 0;
        while (right < arr.length) { // 注意滑动窗口都是增删元素，然后再移动left或right指针
            sum += arr[right]; // 右侧移入新的元素
            right++;
            if (sum == target) {
                intervalList.add(new int[]{left, right}); // 对于arr来说，区间是[left, right)，左闭右开区间
            }

            while (sum > target) {
                sum -= arr[left]; // 左侧移走一个元素
                left++;
                if (sum == target) { // 不断的减也可能减
                    intervalList.add(new int[]{left, right}); // 对于arr来说，区间是[left, right)，左闭右开区间
                }
            }
        }

        // 再暴力遍历所有满足条件的集合
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < intervalList.size(); i++) {
            int[] iInterval = intervalList.get(i);
            if (iInterval[1] - iInterval[0] >= res) continue; // 单个元素长度超出限制就提前退出
            for (int j = i + 1; j < intervalList.size(); j++) {
                int[] jInterval = intervalList.get(j);
                if (iInterval[1] <= jInterval[0]) { // 两个区间不相交
                    res = Math.min(res, getIntervalLenSum(iInterval, jInterval));
                }
            }
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }


    // 获取两个区间的长度和
    private int getIntervalLenSum(int[] intervalLeft, int[] intervalRight) {
        return intervalLeft[1] - intervalLeft[0] + intervalRight[1] - intervalRight[0];
    }
}
```

> 对得到的和为target的区间进行排序，这样一旦满足条件的res，后面的都不需要遍历了，可以提前break，性能大大提高

```java
import java.util.*;

// 参考评论区：https://leetcode-cn.com/problems/find-two-non-overlapping-sub-arrays-each-with-target-sum/solution/find-two-by-ikaruga/
class Solution {
    // 前缀和 + 哈希，类似325.和为K的最长子数组长度
    public int minSumOfLengths(int[] arr, int target) {
        List<int[]> intervalList = new ArrayList<>();
        // 完全按照labuladong总结的滑动窗口模板来做的：(所有元素都是正数，right++则sum增加,left++则sum减少，因此可以用滑动窗口)
        int left = 0, right = 0, sum = 0;
        while (right < arr.length) { // 注意滑动窗口都是增删元素，然后再移动left或right指针
            sum += arr[right]; // 右侧移入新的元素
            right++;
            if (sum == target) {
                intervalList.add(new int[]{right - left, left}); // 对于arr来说，区间是[left, right)，左闭右开区间，所以区间长度正好是right - left
            }

            while (sum > target) {
                sum -= arr[left]; // 左侧移走一个元素
                left++;
                if (sum == target) { // 不断的减也可能减到目标值
                    intervalList.add(new int[]{right - left, left}); // 对于arr来说，区间是[left, right)，左闭右开区间，所以区间长度正好是right - left
                }
            }
        }

        intervalList.sort(((o1, o2) -> o1[0] - o2[0])); // 按照区间长度排序，这个用法很好，我们不用单独建PII类了

        // 再暴力遍历所有满足条件的集合
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < intervalList.size(); i++) {
            int[] interval1 = intervalList.get(i);
            if (interval1[0] * 2 >= res) break;// ans是两个长度之和，如果遍历到有超过这个长度的，后面的肯定更大了，就无需遍历了

            for (int j = i + 1; j < intervalList.size(); j++) {
                int[] interval2 = intervalList.get(j); // 获得另一个满足条件的区间
                if (interval1[1] < interval2[1] && interval1[1] + interval1[0] > interval2[1]) continue; // 区间1在左，和区间2相交
                if (interval2[1] < interval1[1] && interval2[1] + interval2[0] > interval1[1]) continue; // 区间2在左，和区间1相交
                res = Math.min(res, interval1[0] + interval2[0]); // 区间不相交才可以更新区间长度之和
                break; // 长度经过排序之后，后面的区间长度肯定更大，即一定比res大了，因此不需要遍历了
            }
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }
}
```

### 15.[1405.最长快乐字符串](https://leetcode-cn.com/problems/longest-happy-string/)
> 考察点 字符串， 贪心算法

```txt
如果字符串中不含有任何 'aaa'，'bbb' 或 'ccc' 这样的字符串作为子串，那么该字符串就是一个「快乐字符串」。

给你三个整数 a，b ，c，请你返回 任意一个 满足下列全部条件的字符串 s：

s 是一个尽可能长的快乐字符串。
s 中 最多 有a 个字母 'a'、b 个字母 'b'、c 个字母 'c' 。
s 中只含有 'a'、'b' 、'c' 三种字母。
如果不存在这样的字符串 s ，请返回一个空字符串 ""。


示例 1：

输入：a = 1, b = 1, c = 7
输出："ccaccbcc"
解释："ccbccacc" 也是一种正确答案。
示例 2：

输入：a = 2, b = 2, c = 1
输出："aabbc"
示例 3：

输入：a = 7, b = 1, c = 0
输出："aabaa"
解释：这是该测试用例的唯一正确答案。
```

> 先DFS暴力做了一下，过了1/3的用例

```java
class Solution {
    int aMax, bMax, cMax;
    int aCnt = 0, bCnt = 0, cCnt = 0;
    String result = "";
    private void dfs(String cur, List<String> list) {
        if (cur.equals("a")) aCnt++;
        if (cur.equals("b")) bCnt++;
        if (cur.equals("c")) cCnt++;
        list.add(cur);
        String str = String.join("", list);
        if (aCnt > aMax || bCnt > bMax || cCnt > cMax || 
            str.contains("aaa") || str.contains("bbb") || str.contains("ccc")) {
            return; // 一旦不满足，直接退出
        }

        if (str.length() > result.length()) result = str; // 更新最大值
        
        dfs("a", list);
        list.remove(list.size() - 1);
        aCnt--;

        dfs("b", list);
        list.remove(list.size() - 1);
        bCnt--;

        dfs("c", list);
        list.remove(list.size() - 1);
        cCnt--;
    }
    public String longestDiverseString(int a, int b, int c) {
        aMax = a;
        bMax = b;
        cMax = c;
        // 先暴力
        List<String> cList = new ArrayList<>();
        dfs("", cList);
        return result; // 得到一个最大值即可
    }
}
```

> 贪心：每轮放置字符时优先先放剩余次数最多的, 如果上次放的2个字符和剩余个数最多的字符相同，则放置次多的字符

参考：https://leetcode-cn.com/problems/longest-happy-string/solution/tan-xin-suan-fa-you-ya-jie-fa-by-bigpotato-3/

```java
import java.util.Arrays;

class Solution {
    class PII {
        char c;
        int cnt;

        public PII(char c, int cnt) {
            this.c = c;
            this.cnt = cnt;
        }
    }

    public String longestDiverseString(int a, int b, int c) {
        PII[] piis = new PII[3];
        piis[0] = new PII('a', a);
        piis[1] = new PII('b', b);
        piis[2] = new PII('c', c);

        StringBuilder sb = new StringBuilder();
        while (true) {
            Arrays.sort(piis, (o1, o2) -> o2.cnt - o1.cnt); // 按照使用次数降序排序列，每次使用后都要重新排队
            // 先放最多的，如果前面放的两个字符和剩余个数最多的字符相同，则防止次多的字符
            if (sb.length() >= 2 && sb.charAt(sb.length() - 1) == piis[0].c && sb.charAt(sb.length() - 2) == piis[0].c) {
                if (piis[1].cnt > 0) {
                    sb.append(piis[1].c);
                    piis[1].cnt--;
                } else {
                    break;
                }
            } else {
                if (piis[0].cnt > 0) {
                    sb.append(piis[0].c);
                    piis[0].cnt--;
                } else {
                    break;
                }
            }
        }
        return sb.toString();
    }
}
```

### 16.[990.等式方程的可满足性](https://leetcode-cn.com/problems/satisfiability-of-equality-equations/)
> 考察点：并查集/图，借助了自己总结的并查集类

```txt
给定一个由表示变量之间关系的字符串方程组成的数组，每个字符串方程 equations[i] 的长度为 4，并采用两种不同的形式之一："a==b" 或 "a!=b"。在这里，a 和 b 是小写字母（不一定不同），表示单字母变量名。

只有当可以将整数分配给变量名，以便满足所有给定的方程时才返回 true，否则返回 false。 

 

示例 1：

输入：["a==b","b!=a"]
输出：false
解释：如果我们指定，a = 1 且 b = 1，那么可以满足第一个方程，但无法满足第二个方程。没有办法分配变量同时满足这两个方程。
示例 2：

输入：["b==a","a==b"]
输出：true
解释：我们可以指定 a = 1 且 b = 1 以满足满足这两个方程。
示例 3：

输入：["a==b","b==c","a==c"]
输出：true
示例 4：

输入：["a==b","b!=c","c==a"]
输出：false
示例 5：

输入：["c==c","b==d","x!=z"]
输出：true
 

提示：

1 <= equations.length <= 500
equations[i].length == 4
equations[i][0] 和 equations[i][3] 是小写字母
equations[i][1] 要么是 '='，要么是 '!'
equations[i][2] 是 '='
```

```java
class UnionFind {
    /**
     * 记录每个节点在联通分量中的父节点
     */
    private int[] parent;

    /**
     * rank[i]表示节点i所在的联通分量树的层数/高度/深度
     */
    private int[] rank;

    public UnionFind(int size) {
        this.parent = new int[size];
        this.rank = new int[size];
        for (int i = 0; i < parent.length; i++) {
            // 初始化时每个顶点的父节点都认为是自己
            parent[i] = i;
            // 初始时所有元素都是互不相连地，所以每个元素都是一个并查集，每个并查集只有一个元素,也就是一层
            rank[i] = 1;
        }
    }

    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    public void unionElements(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot) {
            // p和q在一个联通分量内，不需要union了，直接退出
            return;
        }
        // 不在一个并查集内的话，只需要把两个根节点连接起来即可
        // 第5节：根据层数优化。下面按照两个并并查集的层数(rank[i])的大小决定谁连接谁(层数少地连接层数多地)
        if (rank[pRoot] < rank[qRoot]) { // p所在的并查集层数小于q所在的并查集层数，p指向q
            // p所在的并查集连接q所在的并查集，rank[root]取两者中层数较大地，并不需要维护rank
            parent[pRoot] = qRoot;
        } else if (rank[pRoot] > rank[qRoot]) { // p所在的并查集层数大于q所在的并查集层数,q指向p
            // p所在的并查集连接q所在的并查集，rank[root]取两者中层数较大地，并不需要维护rank
            // q所在的并查集连接p所在的并查集
            parent[qRoot] = pRoot;
        } else { // p所在的并查集层数等于q所在的并查集层数,谁指向谁都行，这里选p指向q
            //当 rank[pRoot] = rank[qRoot];
            parent[pRoot] = qRoot;
            // 两个层级相等的并查集树根节点相连，层数一定增长1，所以把新的并查集层数+1
            rank[qRoot] += 1;
        }
    }

    public int getSize() {
        return parent.length;
    }

    /**
     * 获取元素i所属的联通分量的根节点，因为是树，所以查找的时间复杂度是O(logn)
     *
     * @param i 元素，即parent数组的下标，用来唯一标识一个元素，即parent数组的下标既是索引又是元素
     * @return i所属的联通分量的根节点
     */
    private int find(int i) {
        if (i < 0 || i >= parent.length) {
            throw new IllegalArgumentException("传入的索引超出了数组范围！");
        }
        // 当i的父节点是自己时说明达到了根节点
        while (parent[i] != i) {
            // 第6节：路径压缩
            parent[i] = parent[parent[i]];
            i = parent[i];
        }
        return i;
    }
}
class Solution {
    public boolean equationsPossible(String[] equations) {
        UnionFind uf = new UnionFind(equations.length * 2); // 最多的联通分量的个数
        int cnt = 0;
        Map<Character, Integer> map = new HashMap<>(); // 记录字符对应的整数映射(不用的字符用0~cnt来表示)
        for (String equation : equations) {
            char left = equation.charAt(0); // 左操作符
            char right = equation.charAt(3); // 右操作符
            if (map.get(left) == null) map.put(left, cnt++);
            if (map.get(right) == null) map.put(right, cnt++);
            String operator = equation.substring(1, 3); // 获取操作符
            if (operator.equals("==")) uf.unionElements(map.get(left), map.get(right));
        }

        // 再判断所有不等式
        for (String equation : equations) {
            char left = equation.charAt(0); // 左操作符
            char right = equation.charAt(3); // 右操作符
            String operator = equation.substring(1, 3); // 获取操作符
            if (operator.equals("!=")) {
                if (uf.isConnected(map.get(left), map.get(right))) return false;
            }
        }

        return true;
    }
}
```

### 17.[1129.颜色交替的最短路径](https://leetcode-cn.com/problems/shortest-path-with-alternating-colors/)
> 考察点：广度优先搜索/图
```java
import java.util.*;

class Solution {
    final int RED = 0;
    final int BLUE = 1;
    TreeMap<Integer, Integer>[] adj;
    int[] dis;
    int n;
    int MAX;

    public int[] shortestAlternatingPaths(int n, int[][] red_edges, int[][] blue_edges) {
        this.n = n;
        dis = new int[n]; // 结果数组
        Arrays.fill(dis, Integer.MAX_VALUE / 2); // 求最小值，所以要初始化为最大值
        adj = new TreeMap[n];
        MAX = red_edges.length + blue_edges.length; // 最长的路径可能是多少，用于剪枝，防止自环

        for (int i = 0; i < n; i++) {
            adj[i] = new TreeMap<>();
        }

        for (int[] red_edge : red_edges) {
            adj[red_edge[0]].put(red_edge[1], RED);
        }

        for (int[] blue_edge : blue_edges) {
            adj[blue_edge[0]].put(blue_edge[1], BLUE);
        }

        dis[0] = 0; // 0到0的节点为0
        // 类似求解二分图吧，需要把父遍历点传进去
        dfs(0, 0); // 起点的parent认为就是自己
        // 把所有的Integer.MAX_VALUE改成-1
        for (int i = 0; i < n; i++) {
            if (dis[i] == Integer.MAX_VALUE / 2) dis[i] = -1;
        }
        return dis;
    }

    private void dfs(int v, int parent) {
        if (dis[v] > MAX) return; // 防止自环边或者平行边
        for (int w : adj[v].keySet()) { // 获取v的所有邻接边
            if (v == parent) { // parent是root地话，直接遍历所有root的邻接点都可以
                if ((dis[v] + 1) < dis[w]) {
                    dis[w] = dis[v] + 1;
                    dfs(w, v);
                }
            } else {
                if ((adj[parent].get(v) + adj[v].get(w)) == 1 && (dis[v] + 1) < dis[w]) { // 颜色必须交替
                    dis[w] = dis[v] + 1;
                    dfs(w, v);
                    dis[w] = dis[v] + 1;
                }
            }
        }
    }


    public static void main(String[] args) {
        int n = 3;
        int[][] red_edges = {{0, 1}, {1, 2}};
        int[][] blue_edges = {};
        new Solution().shortestAlternatingPaths(n, red_edges, blue_edges);
    }
}
```

> 参考的代码

```java
class Solution {
    public int[] shortestAlternatingPaths(int n, int[][] A, int[][] B) {
        boolean graph[][][] = new boolean[2][n][n];
        for(int t[]: A) {
            graph[0][t[0]][t[1]] = true;
        }
        for(int t[]: B) {
            graph[1][t[0]][t[1]] = true;
        }
        int distance1[] = new int[n], distance2[] = new int[n], res[] = new int[n], d = 1;
        Queue <Integer> queue1 = new LinkedList<> (), queue2 = new LinkedList<> (), queue = queue2;
        queue1.offer(0);
        queue2.offer(0);
        boolean flag = false, color = true;
        while(!queue1.isEmpty() || !queue2.isEmpty()) {
            queue = (queue == queue1) ? queue2 : queue1;
            int size = queue.size();
            for(int i = 0; i < size; i++) {
                int cur = queue.poll(), distance[] = color ? distance1 : distance2;
                for(int nxt = 0; nxt < n; nxt++) {
                    if(distance[nxt] != 0 || !graph[color ? 0 : 1][cur][nxt]) 
                      continue;
                    graph[color ? 0 : 1][cur][nxt] = false;
                    distance[nxt] = d;
                    queue.offer(nxt);
                }
            }
            if(flag) d++;
            else color = !color;
            flag = !flag;
        }
        for(int i = 1; i < n; i++) {
            if(distance1[i] == 0 && distance2[i] == 0) res[i] = -1;
            else if(distance1[i] == 0) res[i] = distance2[i];
            else if(distance2[i] == 0) res[i] = distance1[i];
            else res[i] = Math.min(distance1[i], distance2[i]);
        }
        return res;
    }
}
```

### 18.[1190.反转每对括号间的子串](https://leetcode-cn.com/problems/reverse-substrings-between-each-pair-of-parentheses/)
> 考察点 字符串/栈

```txt
给出一个字符串 s（仅含有小写英文字母和括号）。

请你按照从括号内到外的顺序，逐层反转每对匹配括号中的字符串，并返回最终的结果。

注意，您的结果中 不应 包含任何括号。

 

示例 1：

输入：s = "(abcd)"
输出："dcba"
示例 2：

输入：s = "(u(love)i)"
输出："iloveu"
示例 3：

输入：s = "(ed(et(oc))el)"
输出："leetcode"
示例 4：

输入：s = "a(bcdefghijkl(mno)p)q"
输出："apmnolkjihgfedcbq"
 

提示：

0 <= s.length <= 2000
s 中只有小写英文字母和括号
我们确保所有括号都是成对出现的

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/reverse-substrings-between-each-pair-of-parentheses
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
```

```java
class Solution {
    public String reverseParentheses(String s) {
        
        StringBuilder sb = new StringBuilder();
        char[] arr = s.toCharArray();
        Stack<Integer> stack = new Stack<>();
        
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == '(') stack.push(i);
            if (arr[i] == ')')reverse(arr, stack.pop() + 1, i - 1);
        }
        
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != ')' && arr[i] != '(') {
                sb.append(arr[i]);
            }
        }
            
        return sb.toString();
    }
    
    public void reverse(char[] arr, int left, int right) {
        while (right > left) {
            char tmp = arr[left];
            arr[left] = arr[right];
            arr[right] = tmp;
            right--;
            left++;
        }
    }
}
```

### 19.[LCP12.小张刷题计划](https://leetcode-cn.com/problems/xiao-zhang-shua-ti-ji-hua/)
> 考察点 数组/二分法

```txt
为了提高自己的代码能力，小张制定了 LeetCode 刷题计划，他选中了 LeetCode 题库中的 n 道题，编号从 0 到 n-1，并计划在 m 天内按照题目编号顺序刷完所有的题目（注意，小张不能用多天完成同一题）。

在小张刷题计划中，小张需要用 time[i] 的时间完成编号 i 的题目。此外，小张还可以使用场外求助功能，通过询问他的好朋友小杨题目的解法，可以省去该题的做题时间。为了防止“小张刷题计划”变成“小杨刷题计划”，小张每天最多使用一次求助。

我们定义 m 天中做题时间最多的一天耗时为 T（小杨完成的题目不计入做题总时间）。请你帮小张求出最小的 T是多少。

示例 1：

输入：time = [1,2,3,3], m = 2

输出：3

解释：第一天小张完成前三题，其中第三题找小杨帮忙；第二天完成第四题，并且找小杨帮忙。这样做题时间最多的一天花费了 3 的时间，并且这个值是最小的。

示例 2：

输入：time = [999,999,999], m = 4

输出：0

解释：在前三天中，小张每天求助小杨一次，这样他可以在三天内完成所有的题目并不花任何时间。

 

限制：

1 <= time.length <= 10^5
1 <= time[i] <= 10000
1 <= m <= 1000

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/xiao-zhang-shua-ti-ji-hua
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
```

```java
class Solution {
	public int minTime(int[] time, int m) {
		int len = time.length, lt = Integer.MAX_VALUE, rt = 0, mid, res = 0;
		if (m >= len)
			return 0;
		for (int i = 0; i < len; i++) {
			lt = Math.min(lt, time[i]);
			rt += time[i];
		}
		while (lt <= rt) {
			mid = (lt + rt) >> 1;
			if (check(time, len, mid, m)) { // 切割得多了，调大下界
				lt = mid + 1;
			} else { // 切割得可能少了，调小上界
				rt = mid - 1;
				res = mid; // 同时记录夹逼值
			}
		}
		return res;
	}
	private boolean check(int[] time, int len, int limit, int m) {
		int cnt = 1, sum = time[0], maxVal = time[0]; // 初始化为1，且当前子数组最大值初始化为第一个元素
		for (int i = 1; i < len; i++) { // 从第二个元素开始遍历
			sum += time[i];
			maxVal = Math.max(maxVal, time[i]);
			if (sum - maxVal > limit) { // 划分 第 cnt + 1 个子数组（新的子数组的第一个元素）
				cnt++;
				maxVal = sum = time[i];
			}
			if (cnt > m) // 当划分的子数组个数超过m时，直接返回true
				return true;
		}
		return false; // 找到一种可能的分割方案
	}
}
```

### 20.[1419.数青蛙](https://leetcode-cn.com/problems/minimum-number-of-frogs-croaking/)
> 中等；考察点 字符串

```txt
给你一个字符串 croakOfFrogs，它表示不同青蛙发出的蛙鸣声（字符串 "croak" ）的组合。由于同一时间可以有多只青蛙呱呱作响，所以 croakOfFrogs 中会混合多个 “croak” 。请你返回模拟字符串中所有蛙鸣所需不同青蛙的最少数目。

注意：要想发出蛙鸣 "croak"，青蛙必须 依序 输出 ‘c’, ’r’, ’o’, ’a’, ’k’ 这 5 个字母。如果没有输出全部五个字母，那么它就不会发出声音。

如果字符串 croakOfFrogs 不是由若干有效的 "croak" 字符混合而成，请返回 -1 。

 

示例 1：

输入：croakOfFrogs = "croakcroak"
输出：1 
解释：一只青蛙 “呱呱” 两次
示例 2：

输入：croakOfFrogs = "crcoakroak"
输出：2 
解释：最少需要两只青蛙，“呱呱” 声用黑体标注
第一只青蛙 "crcoakroak"
第二只青蛙 "crcoakroak"
示例 3：

输入：croakOfFrogs = "croakcrook"
输出：-1
解释：给出的字符串不是 "croak" 的有效组合。
示例 4：

输入：croakOfFrogs = "croakcroa"
输出：-1

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/minimum-number-of-frogs-croaking
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
```

```java
class Solution {
    public int minNumberOfFrogs(String croakOfFrogs) {
        int len = croakOfFrogs.length();
        if (len % 5 != 0 || croakOfFrogs.charAt(0) != 'c' || croakOfFrogs.charAt(len - 1) != 'k') return -1;
        char[] ch = croakOfFrogs.toCharArray();
        int[] num = new int[127];
        int count = 1;
        for (char c: ch) {
            num[c]++;
            //如果数组不是非递增的，那么就是无效数据
            if (!(num['c'] >= num['r'] && num['r'] >= num['o'] && num['o'] >= num['a'] && num['a'] >= num['k']))
                return -1;
            //显然，当字符串为c时，青蛙数量才会增加
            if (c == 'c') {
                count = Math.max(count, num['c'] - num['k']);
            }
        }
        return count;
    }
}
```