/***********************************************************
 * @Description : 120.三角形最小路径和的递归实现
 * 递归实现，考虑了重复子问题
 * 执行用时 : 3 ms , 在所有 Java 提交中击败了 83.97% 的用户 内存消耗 : 37.1 MB , 在所有 Java 提交中击败了 81.46% 的用户
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/26 11:25
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter09DynamicAllocate.Section2ClimbingStairs.LeetCode120Triangle;

import java.util.List;

public class 解法2_递归加记忆数组去除重复递归 {
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
                // Todo
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
        if (memo[i][j] != -1) {
            // 如果前面递归计算过了，这里就不用再算了，免得重复递归
            return memo[i][j];
        }
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
