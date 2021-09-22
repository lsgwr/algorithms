/***********************************************************
 * @note      : 343. 整数拆分 给定一个正整数 n，将其拆分为至少两个正整数的和，并使这些整数的乘积最大化。 返回你可以获得的最大乘积。
 * 纯递归实现，会超时(n>30时)
 * @author    : l00379880 梁山广
 * @version   : V1.0 at 2019/8/23 14:19
 ***********************************************************/
package Chapter09DynamicAllocate.Section3IntegerBreak;

public class Solution1 {

    public static int num;

    private int max3(int a, int b, int c) {
        return Math.max(a, Math.max(b, c));
    }

    private int intBreak(int n) {
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
        return result;
    }

    public int integerBreak(int n){
        return intBreak(n);
    }

    public static void main(String[] args) {
        int n = 10;
        int result = new Solution1().integerBreak(n);
        System.out.println(n + "拆分后的整数的最大乘积是：" + result);
        System.out.println("共进入递归函数：" + num + "次");
    }
}

/**
 * 输出为：
 * <p>
 * 10拆分后的整数的最大乘积是：36
 * 共进入递归函数：512次
 */
