/***********************************************************
 * @Description : 动态递归，结合如下图示来看
 * https://raw.githubusercontent.com/19920625lsg/liuyubobobo-algorithms/master/Part3Practise/leetcode/src/main/java/Chapter09DynamicAllocate/Section5Knapsack/背包问题示例.png
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2019/8/24 21:51
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter09DynamicAllocate.Section5Knapsack;

public class Main {
    public static void main(String[] args) {
        int[] weight = {1, 2, 3};
        int[] value = {6, 10, 12};
        int C = 5;
        int bestValue = new Solution3Dynamic().knapsack(weight, value, C);
        System.out.println("最大价值是：" + bestValue);
        System.out.println("动态规划不需要进入递归");
    }
}

/**
 * 输出结果是：
 * <p>
 * 最大价值是：22
 * 动态规划不需要进入递归
 */