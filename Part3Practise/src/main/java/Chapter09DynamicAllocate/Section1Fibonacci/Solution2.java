/***********************************************************
 * @Description :斐波那契数列求和,优化，避免重复递归，大大降低程序耗时
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2019/8/22 23:57
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter09DynamicAllocate.Section1Fibonacci;

import java.util.ArrayList;
import java.util.List;

public class Solution2 {

    /**
     * 统计进入了递归多少次
     */
    private int num = 0;

    public int getNum() {
        return num;
    }

    /**
     * 使用meme用户记忆递归状态，叫做记忆化搜索，也称自上而下地解决问题
     */
    private static List<Integer> memo;

    public int fib(int n) {
        num++;
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }

        if (memo.get(n) == -1) {
            memo.set(n, fib(n - 1) + fib(n - 2));
        }
        return memo.get(n);
    }

    public static void main(String[] args) {
        int n = 1000;
        memo = new ArrayList<>(n + 1);
        for (int i = 0; i < n + 1; i++) {
            memo.add(-1);
        }
        System.out.println(memo);
        Solution2 solution = new Solution2();
        long startTime = System.currentTimeMillis();
        int result = solution.fib(n);
        long endTime = System.currentTimeMillis();
        System.out.println("fib(" + n + ") = " + result);
        System.out.println("time : " + (endTime - startTime) + " ms");
        System.out.println("run function fib() " + solution.getNum() + " times.");
    }
}
/**
 * 输出结果如下(经过递归优化后，即使n到了1000，所耗费的时间仍然微乎其微)：
 * <p>
 * fib(1000) = 1556111435
 * time : 4 ms
 * run function fib() 1999 times.
 */
