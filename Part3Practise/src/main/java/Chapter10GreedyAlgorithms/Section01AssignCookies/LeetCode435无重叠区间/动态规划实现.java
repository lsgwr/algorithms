/***********************************************************
 * @Description : 435.无重叠区间
 * https://leetcode-cn.com/problems/non-overlapping-intervals/
 * 参考9.8最长上升子序列LIS的实现:
 * https://gitee.com/lsgwr/algorithms/blob/master/Part3Practise/第09章_动态规划.md#98-lis最长子序列问题-300最长上升子序列longest-increasing-subsequence
 *
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/28 10:30
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter10GreedyAlgorithms.Section01AssignCookies.LeetCode435无重叠区间;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class 动态规划实现 {
    public int eraseOverlapIntervals(int[][] intervals) {
        // 先转换成List，注意每个interval都是个只有俩元素的数组
        List<int[]> intervalList = new ArrayList<>(Arrays.asList(intervals));
        // 自定义比较器，先比较开头，谁大谁靠后，开头相等地话就看结尾，谁大谁靠后
        intervalList.sort((interval1, interval2) -> {
            if (interval1[0] != interval2[0]) {
                return interval1[0] - interval2[0];
            }
            return interval1[1] - interval2[1];
        });
        // 借鉴最长公共子序列的思想.
        // memo[i]表示用使用intervals[0...i]的区间能构成的最长不重叠区间序列
        int[] memo = new int[intervalList.size()];
        Arrays.fill(memo, 1);
        for (int i = 1; i < intervals.length; i++) {
            for (int j = 0; j < i; j++) {
                // 从i向前遍历，如果i前面找到一个区间的尾部比i所在的区间头部还小，说明找到了一个不重叠区间
                if (intervalList.get(i)[0] >= intervalList.get(j)[1]) {
                    memo[i] = Math.max(memo[i], 1 + memo[j]);
                }
            }
        }
        // 取出最大的不重叠子区间个数
        int res = 0;
        for (int num : memo) {
            if (num > res) {
                res = num;
            }
        }
        // 题目要地是删除多少个区间可以得到最大的不重叠的子区间，所以要用区间总个数减去最多的不重叠子区间个数
        return intervalList.size() - res;
    }

    public static void main(String[] args) {
        int[][] intervals = {{1,2}, {2,3}, {3,4}, {1,3}};
        System.out.println(new 动态规划实现().eraseOverlapIntervals(intervals));
    }
}
