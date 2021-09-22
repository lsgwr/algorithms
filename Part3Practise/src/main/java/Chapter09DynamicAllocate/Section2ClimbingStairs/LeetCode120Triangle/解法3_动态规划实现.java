/***********************************************************
 * @Description : 120.三角形最小路径和的递归实现
 * 动态递归实现
 * 执行用时 : 4 ms , 在所有 Java 提交中击败了 49.70% 的用户 内存消耗 : 37.2 MB , 在所有 Java 提交中击败了 71.92% 的用户
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/26 11:25
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter09DynamicAllocate.Section2ClimbingStairs.LeetCode120Triangle;

import java.util.List;

public class 解法3_动态规划实现 {
    public int minimumTotal(List<List<Integer>> triangle) {
        // 行数
        int R = triangle.size();
        // 最大的列数
        int C = triangle.get(triangle.size() - 1).size();
        // 记忆每个点的向上的最小路径和，按照最大的来
        int[][] memo = new int[R][C];
        memo[0][0] = triangle.get(0).get(0);
        // 动态规划，从第2行开始
        for (int i = 1; i < triangle.size(); i++) {
            List<Integer> rowList = triangle.get(i);
            for (int j = 0; j < rowList.size(); j++) {
                if (j == 0) {
                    memo[i][j] = memo[i - 1][j] + triangle.get(i).get(j);
                    continue;
                }
                if (j == rowList.size() - 1) {
                    memo[i][j] = memo[i - 1][j - 1] + triangle.get(i).get(j);
                    continue;
                }
                //计算当前点的最小值，如果没有计算过才进行计算
                memo[i][j] = Math.min(memo[i - 1][j], memo[i - 1][j - 1]) + triangle.get(i).get(j);
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
}
