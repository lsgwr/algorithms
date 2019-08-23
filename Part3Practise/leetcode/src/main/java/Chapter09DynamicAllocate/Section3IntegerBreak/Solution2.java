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

    private int intBreak(int n) {
        num++;
        if (n == 1) {
            return 1;
        }
        // 整数乘积的最大值,初始化要很小
        int mulMax = -1;
        if (memo[n] == -1) {
            for (int i = 1; i < n - 1; i++) {
                // 如果当前不再继续拆分地话
                mulMax = Math.max(mulMax, i * (n - i));
                // n = i + (n - i)，和继续拆分的进行比较，看最大值
                mulMax = Math.max(mulMax, i * intBreak(n - i));
                memo[n] = mulMax;
            }
        }
        return memo[n];
    }

    public int integerBreak(int n) {
        return intBreak(n);
    }

    public static void main(String[] args) {
        int n = 10;
        memo = new int[n + 1];
        Arrays.fill(memo, -1);
        int mulMax = new Solution2().integerBreak(n);
        System.out.println(n + "拆分后的整数的最大乘积是：" + mulMax);
        System.out.println("共进入递归函数：" + num + "次");
    }
}

/**
 * 输出为：
 * <p>
 * 10拆分后的整数的最大乘积是：36
 * 共进入递归函数：37次
 */
