package Chapter09DynamicAllocate.Section3IntegerBreak;

import java.util.Arrays;

/***********************************************************
 * @note      : 343. 整数拆分
 * 给定一个正整数 n，将其拆分为至少两个正整数的和，并使这些整数的乘积最大化。 返回你可以获得的最大乘积。
 * @author    : l00379880 梁山广
 * @version   : V1.0 at 2019/8/23 14:19
 ***********************************************************/
public class Solution2 {

    public static int num;

    /**
     * 记忆数组，存储各个拆分元素的访问状态
     */
    private static int[] memo;

    private int max3(int a, int b, int c) {
        return Math.max(a, Math.max(b, c));
    }

    private int intBreak(int n) {
        if (memo[n] != -1) {
            return memo[n];
        }
        num++;
        if (n == 1) {
            return 1;
        }
        // 整数乘积的最大值,初始化要很小
        int result = -1;
        // 一定注意别忘了等号
        for (int i = 1; i <= n - 1; i++) {
            // 1.如果当前不再继续拆分地话，剩下的就是n-i。此时最小值是i*(n-i)
            // 2.继续拆分地话，则递归求前一步的拆分结果，即i * integerBreak(n - i)
            result = max3(result, i * (n - i), i * intBreak(n - i));
        }
        memo[n] = result;
        return memo[n];
    }

    public int integerBreak(int n) {
        memo = new int[n + 1];
        Arrays.fill(memo, -1);
        return intBreak(n);
    }

    public static void main(String[] args) {
        int n = 10;
        int result = new Solution2().integerBreak(n);
        System.out.println(n + "拆分后的整数的最大乘积是：" + result);
        System.out.println("共进入递归函数：" + num + "次");
    }
}

/**
 * 输出为：
 * <p>
 * 10拆分后的整数的最大乘积是：36
 * 共进入递归函数：46次
 */
