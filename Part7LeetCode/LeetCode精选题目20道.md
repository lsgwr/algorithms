# LeetCode精选题目20道

## 1.[56.合并区间](https://leetcode-cn.com/problems/merge-intervals/)
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