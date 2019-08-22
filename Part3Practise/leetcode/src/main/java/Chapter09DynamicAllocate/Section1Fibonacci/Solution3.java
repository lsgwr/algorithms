/***********************************************************
 * @Description :斐波那契数列求和,自下而上地解决问题，即动态规划
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2019/8/22 23:57
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter09DynamicAllocate.Section1Fibonacci;

import java.util.ArrayList;
import java.util.List;

public class Solution3 {

    /**
     * 统计进入了递归多少次
     */
    private int num = 0;

    public int getNum() {
        return num;
    }

    /**
     * 自下而上地解决问题，也称动态规划，有点像数学归纳法
     */

    public int fib(int n) {
        List<Integer> memo = new ArrayList<>(n + 1);
        ;
        for (int i = 0; i < n + 1; i++) {
            memo.add(-1);
        }
        num++;
        memo.set(0, 0);
        memo.set(1, 1);

        for (int i = 2; i <= n; i++) {
            memo.set(i, memo.get(i - 1) + memo.get(i - 2));
        }
        return memo.get(n);
    }

    public static void main(String[] args) {
        int n = 1000;
        Solution3 solution = new Solution3();
        long startTime = System.currentTimeMillis();
        int result = solution.fib(n);
        long endTime = System.currentTimeMillis();
        System.out.println("fib(" + n + ") = " + result);
        System.out.println("time : " + (endTime - startTime) + " ms");
        System.out.println("run function fib() " + solution.getNum() + " times.");
    }
}
/**
 * 输出结果如下(自下而上地解决问题，也称动态规划，可看到效率比Solution2的记忆搜索效率还高)：
 * <p>
 * fib(1000) = 1556111435
 * time : 2 ms
 * run function fib() 1 times.
 */
