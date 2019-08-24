/***********************************************************
 * @Description : 递归求解背包问题,存在重叠子问题
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2019/8/24 20:31
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter09DynamicAllocate.Section5Knapsack;

public class Solution1Recur {

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

        return bestValue(w, v, n - 1, C);
    }

    /**
     * 用 [0...index]的物品,填充容积为c的背包的最大价值
     *
     * @param w     重量weight数组
     * @param v     价值value数组
     * @param index 用 [0...index]的物品,填充容积为c的背包的最大价值
     * @param c     当前循环锁剩下的容器容量(C在当前递归的值)
     * @return [0...index]的物品, 填充容积为c的背包的最大价值
     */
    private int bestValue(int[] w, int[] v, int index, int c) {

        if (c <= 0 || index < 0) {
            return 0;
        }

        int result = bestValue(w, v, index - 1, c);
        if (c >= w[index]) {
            result = Math.max(result, v[index] + bestValue(w, v, index - 1, c - w[index]));
        }

        return result;
    }

    public static void main(String[] args) {

    }

}
