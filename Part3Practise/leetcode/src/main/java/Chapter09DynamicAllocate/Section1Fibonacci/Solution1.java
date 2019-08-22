/***********************************************************
 * @Description :斐波那契数列求和
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2019/8/22 23:57
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter09DynamicAllocate.Section1Fibonacci;

public class Solution1 {

    /**
     * 统计进入了递归多少次
     */
    private int num = 0;

    public int getNum() {
        return num;
    }

    public int fib(int n) {
        num++;
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }

        return fib(n - 1) + fib(n - 2);
    }

    public static void main(String[] args) {
        int n = 40;
        Solution1 solution = new Solution1();
        long startTime = System.currentTimeMillis();
        int result = solution.fib(n);
        long endTime = System.currentTimeMillis();
        System.out.println("fib(" + n + ") = " + result);
        System.out.println("time : " + (endTime - startTime) + " ms");
        System.out.println("run function fib() " + solution.getNum() + " times.");
    }
}
/**
 * 输出结果如下(随着n的翻倍，耗时是指数增加地，所以需要更高效的算法，见下面的额Solution)：
 *
 * fib(40) = 102334155
 * time : 1514 ms
 * run function fib() 331160281 times.
 */
