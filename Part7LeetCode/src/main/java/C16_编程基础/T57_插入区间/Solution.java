/***********************************************************
 * @Description : 57.插入区间
 *
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/31 23:03
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C16_编程基础.T57_插入区间;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/insert-interval/solution/he-bing-yu-cha-ru-tong-shi-jin-xing-hao-shi-1ms-by/
 * <p>
 * 遍历旧的区间数组，如果新区间的start比当前区间的start要小，则插入新区间，然后再添加当前区间；否则直接依次添加旧的区间数组元素。
 * <p>
 * 实现方式1：每次循环都判断新区间是否已添加，没添加，则在新区间的start比当前区间的start要小时添加（这样执行用时为2ms）
 * <p>
 * 实现方式2：分两次循环，代码如下
 */
class Solution {

    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> result = new ArrayList<>();
        int[] lastInterval = null;
        int i = 0;
        for (; i < intervals.length; i++) {
            if (newInterval[0] < intervals[i][0]) {
                // 新区间的start比当前区间的start要小，则插入新区间
                lastInterval = addOrUpdate(result, lastInterval, newInterval);
                break;
            }
            lastInterval = addOrUpdate(result, lastInterval, intervals[i]);
        }
        if (i == intervals.length) {
            // 将新区间添加到最后
            addOrUpdate(result, lastInterval, newInterval);
        } else {
            // 新区间已插入，添加后续的区间
            for (; i < intervals.length; i++) {
                lastInterval = addOrUpdate(result, lastInterval, intervals[i]);
            }
        }
        return result.toArray(new int[0][]);
    }

    private int[] addOrUpdate(List<int[]> result, int[] lastInterval, int[] newInterval) {
        if (lastInterval == null || lastInterval[1] < newInterval[0]) {
            lastInterval = newInterval;
            result.add(newInterval);
        } else if (lastInterval[1] < newInterval[1]) {
            lastInterval[1] = newInterval[1];
        }
        return lastInterval;
    }

}