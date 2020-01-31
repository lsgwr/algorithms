/***********************************************************
 * @Description : 149.直线上最多的点数
 * https://leetcode-cn.com/problems/max-points-on-a-line/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/31 23:50
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C16_编程基础.T149_直线上最多的点数;

import java.util.HashMap;
import java.util.Map;

class Solution {
    public int maxPoints(int[][] points) {
        int max = 0;
        if (points.length < 3) {
            return points.length;
        }
        for (int i = 0; i < points.length; i++) {
            // 到i的斜率和斜率相等的点的个数组成的映射
            Map<Double, Integer> mapKPoints = new HashMap<>();
            // 和points[i]的横纵坐标都相同的点,初始至少有自己
            int sameCount = 1;
            for (int j = i + 1; j < points.length; j++) {
                // 斜率可能是浮点数
                double k = Integer.MAX_VALUE;
                if (points[i][1] != points[j][1]) {
                    k = (points[i][0] - points[j][0]) * 1.0 / (points[i][1] - points[j][1]);
                } else {
                    if (points[i][0] == points[j][0]) {
                        // 横纵坐标都相等，那么这样的点可以加到任何一条和i相连的直线上
                        sameCount++;
                        continue;
                    }
                }
                // 下面这一行是为了解决用例[[4,0],[4,-1],[4,5]] ，Java中0.0和-0.0是不同的，https://blog.csdn.net/ouy5933/article/details/72461239
                k += 0.0;
                if (mapKPoints.get(k) == null) {
                    mapKPoints.put(k, 1);
                } else {
                    mapKPoints.put(k, mapKPoints.get(k) + 1);
                }
            }
            // 统计到points[i]距离相等的点数
            for (Double k : mapKPoints.keySet()) {
                int cnt = mapKPoints.get(k);
                // 要算上当前点points[i]，所以要cnt+1
                max = Math.max(cnt + sameCount, max);
            }
            // 全部都是一个点的用例：[[1,1],[1,1],[1,1]]
            max = Math.max(sameCount, max);
        }
        return max;
    }
}