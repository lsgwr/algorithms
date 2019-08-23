/***********************************************************
 * @Description : 递归解决爬楼梯问题
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2019/8/23 08:11
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter09DynamicAllocate.Section2ClimbingStairs;

public class Solution1 {

    int calcWays(int n) {
        if (n == 0) {
            return 1;
        }
        if (n == 1) {
            return 1;
        }

        return calcWays(n - 1) + calcWays(n - 2);
    }

    public int climbStairs(int n) {
        return calcWays(n);
    }

    public static void main(String[] args) {
        int ways = new Solution1().climbStairs(10);
        System.out.println(ways);
    }
}
