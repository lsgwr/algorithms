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
### 5.[76.最小子串覆盖]()
### 6.[438.找到字符串中所有字母异位词]()
### 200 岛屿数量 
leetcode 1219 黄金矿工
leetcode 505 迷宫II
leetcode 51  N皇后
leetcode 22  括号生成
leetcode 207 课程表
leetcode562  矩阵中最长的连续1线段  
leetcode1477 找两个和为目标值且不重叠的子数组
题号 1405
题目名称 最长快乐字符串（难度 中等；考察点 字符串， 贪心算法）
链接 https://leetcode-cn.com/problems/longest-happy-string/

题号 990
题目名称 等式方程的可满足性（难度 中等；考察点：并查集/图）
链接 https://leetcode-cn.com/problems/satisfiability-of-equality-equations/

题号 1129
题目名称 颜色交替的最短路径（难度 中等；考察点：广度优先搜索/图）
链接 https://leetcode-cn.com/problems/shortest-path-with-alternating-colors/

题号 1190
题目名称 反转每对括号间的子串（难度 中等；考察点 字符串/栈）
链接 https://leetcode-cn.com/problems/reverse-substrings-between-each-pair-of-parentheses/ 

题号 LCP12（难度 中等；考察点 数组/二分法）
题目名称 小张刷题计划
链接 https://leetcode-cn.com/problems/xiao-zhang-shua-ti-ji-hua/

题号 1419
题目名称 数青蛙（难度 中等；考察点 字符串）
链接 https://leetcode-cn.com/problems/minimum-number-of-frogs-croaking/
