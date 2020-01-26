/***********************************************************
 * @Description : 120.三角形最小路径和的递归实现
 * 纯递归实现，没考虑重复子问题，提交后超出时间限制了
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/26 10:24
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter09DynamicAllocate.Section2ClimbingStairs.LeetCode120Triangle;

import java.util.List;

public class 解法1_纯递归实现 {
    private int[][] memo;

    public int minimumTotal(List<List<Integer>> triangle) {
        // 行数
        int R = triangle.size();
        // 最大的列数
        int C = triangle.get(triangle.size() - 1).size();
        // 记忆每个点的向上的最小路径和，按照最大的来
        memo = new int[R][C];
        // 初始化记忆数组
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                memo[i][j] = -1;
            }
        }
        for (int i = 0; i < triangle.size(); i++) {
            List<Integer> rowList = triangle.get(i);
            for (int j = 0; j < rowList.size(); j++) {
                //计算当前点的最小值，如果没有计算过才进行计算
                memo[i][j] = recursive(i, j, triangle);
            }
        }
        // 从最后一行元素找到达底部的最小值
        int min = Integer.MAX_VALUE;
        for (int tmp : memo[R - 1]) {
            if (tmp < min) {
                min = tmp;
            }
        }
        return min;
    }

    private int recursive(int i, int j, List<List<Integer>> triangle) {
        if (i == 0) {
            // 此时说明(i, j)是第一个元素即(0, 0)
            return triangle.get(0).get(0);
        }
        if (j == 0) {
            // 在当前行的最左边
            return recursive(i - 1, j, triangle) + triangle.get(i).get(j);
        }
        if (j == triangle.get(i - 1).size()) {
            // 在当前行的最右边
            return recursive(i - 1, j - 1, triangle) + triangle.get(i).get(j);
        }
        return Math.min(recursive(i - 1, j - 1, triangle), recursive(i - 1, j, triangle)) + triangle.get(i).get(j);
    }
}
