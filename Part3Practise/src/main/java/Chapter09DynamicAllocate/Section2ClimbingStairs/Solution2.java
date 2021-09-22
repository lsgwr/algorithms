/***********************************************************
 * @Description : 递归解决爬楼梯问题，使用记忆数组memo防止递归到重复子问题
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2019/8/23 08:21
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter09DynamicAllocate.Section2ClimbingStairs;


import java.util.Arrays;

public class Solution2 {


    public static int num;
    /**
     * 记忆数组memory，用于存储子问题是否已经被访问
     */
    public static int[] memo;

    public int climbStairs(int n) {
        num++;
        if (n == 0) {
            return 1;
        }
        if (n == 1) {
            return 1;
        }

        if (memo[n] == -1) {
            memo[n] = climbStairs(n - 1) + climbStairs(n - 2);
        }
        return memo[n];
    }

    public static void main(String[] args) {
        int n = 20;
        memo = new int[n + 1];
        Arrays.fill(memo, -1);
        int ways = new Solution2().climbStairs(n);
        System.out.println("一共有" + ways + "种爬楼梯的方法");
        System.out.println("一共进入递归函数" + num + "次");
    }
}

/**
 * 输出如下(可以看到记忆数组节省了大量进入递归的操作)：
 * <p>
 * 一共有10946种爬楼梯的方法
 * 一共进入递归函数39次
 */
