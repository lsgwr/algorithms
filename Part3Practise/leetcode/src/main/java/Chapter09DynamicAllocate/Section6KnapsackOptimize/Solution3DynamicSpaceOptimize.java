/***********************************************************
 * @Description : 动态规划解决背包问题，空间复杂度优化
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2019/8/24 20:31
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter09DynamicAllocate.Section6KnapsackOptimize;

public class Solution3DynamicSpaceOptimize {

    /**
     * 背包问题：在容量C下求最大价值
     *
     * @param w 重量weight数组
     * @param v 价值value数组
     * @param C 容器总容量
     * @return 背包问题的总价值
     */
    public int knapsack(int[] w, int[] v, int C) {

        if (w == null || v == null || w.length != v.length) {
            throw new IllegalArgumentException("Invalid w or v");
        }

        if (C < 0) {
            throw new IllegalArgumentException("C must be greater or equal to zero.");
        }

        int n = w.length;
        if (n == 0 || C == 0) {
            return 0;
        }

        int[][] memo = new int[n][C + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < C + 1; j++) {
                memo[i][j] = -1;
            }
        }


        for (int j = 0; j <= C; j++) {
            // 对第0个物体遍历所有的容量可能值.当容量值大于第0个物体的重量时就取底0个物体的容量值，否则取0
            memo[0][j] = j >= w[0] ? v[0] : 0;
        }

        // 计算0往后的物体
        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= C; j++) {
                if (j >= w[i]) { // 如果当前容量大于等于第i个物体的重量，那么就尝试下放入新物体
                    // 上一个物体加入时的最大价值memo[i-1][j]
                    // 与
                    // 新物体加入后的价值v[i] + memo[i-1][j-w[i]]
                    // 两者中的较大值
                    memo[i % 2][j] = Math.max(memo[(i - 1) % 2][j], v[i] + memo[(i - 1) % 2][j - w[i]]);
                } else { // 如果当前容量小于第i个物体的重量，就不用放入新物体了，直接用上一步求地最大价值即可
                    memo[i % 2][j] = memo[(i - 1) % 2][j];
                }
            }
        }
        return memo[(n - 1) % 2][C];
    }

    public static void main(String[] args) {
        int[] weight = {1, 2, 3, 1, 2, 3};
        int[] value = {6, 10, 12, 6, 10, 12};
        int C = 10;
        int bestValue = new Solution3DynamicSpaceOptimize().knapsack(weight, value, C);
        System.out.println("最大价值是：" + bestValue);
        System.out.println("动态规划不需要进入递归");
    }
}

/**
 * 输出结果为(动态规划不需要进入递归)：
 * <p>
 * 最大价值是：46
 * 动态规划不需要进入递归
 */