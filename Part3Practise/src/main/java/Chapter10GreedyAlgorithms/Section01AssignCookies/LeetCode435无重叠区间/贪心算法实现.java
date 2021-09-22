/***********************************************************
 * @Description : 435.无重叠区间
 * https://leetcode-cn.com/problems/non-overlapping-intervals/
 * 贪心算法实现：
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/28 10:30
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter10GreedyAlgorithms.Section01AssignCookies.LeetCode435无重叠区间;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class 贪心算法实现 {
    public int eraseOverlapIntervals(int[][] intervals) {
        if(intervals.length == 0){
            return 0;
        }
        // 先转换成List，注意每个interval都是个只有俩元素的数组
        List<int[]> intervalList = new ArrayList<>(Arrays.asList(intervals));
        // 自定义比较器，先比较结尾，谁大谁靠后，结尾相等地话就看开头，谁大谁靠后
        intervalList.sort((interval1, interval2) -> {
            if (interval1[1] != interval2[1]) {
                return interval1[1] - interval2[1];
            }
            return interval1[0] - interval2[0];
        });
        // 贪心算法实录：不断选取结尾最早且区间和前面不重合的区间
        // 第一个区间先选上
        int res=1;
        // i前面的区间在intervalList中的下标
        int pre = 0;
        for (int i = 1; i < intervalList.size(); i++) {
            // 前面区间的结尾比后面区间的开头还小，说明找到了一个不重叠区间
            if (intervalList.get(pre)[1] <= intervalList.get(i)[0]){
                res++;
                pre = i;
            }
        }
        return intervalList.size() - res;
    }
}
