/***********************************************************
 * @Description : 动态规划解决背包问题，时间复杂度优化
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2019/8/25 10:15
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter09DynamicAllocate.Section6KnapsackOptimize;

public class Solution3DynamicTimeOptimize {

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

        int[] memo = new int[C + 1];
        for (int j = 0; j < C + 1; j++) {
            memo[j] = -1;
        }


        for (int j = 0; j <= C; j++) {
            // 对第0个物体遍历所有的容量可能值.当容量值大于第0个物体的重量时就取底0个物体的容量值，否则取0
            memo[j] = j >= w[0] ? v[0] : 0;
        }

        // 计算0往后的物体
        for (int i = 1; i < n; i++) {
            // 循环容量C到容量w[i],容量w[i]到0就不用循环了
            for (int j = C; j >= w[i]; j--) {
                // 如果当前容量j大于等于第i个物体的重量w[i]，那么就尝试下放入新物体(需要比较两个值的较大值)
                // 此时最大价值取
                // 上一个物体加入时在容量j下的的最大价值memo[j]
                // 与
                // 新物体加入后的价值v[i] + memo[j-w[i]]
                // 两者中的较大值
                memo[j] = Math.max(memo[j], v[i] + memo[j - w[i]]);
            }
        }
        // 返回容量C的最大价值
        return memo[C];
    }

    public static void main(String[] args) {
        int[] weight = {1, 2, 3, 1, 2, 3};
        int[] value = {6, 10, 12, 6, 10, 12};
        int C = 10;
        int bestValue = new Solution3DynamicTimeOptimize().knapsack(weight, value, C);
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