/***********************************************************
 * @Description : 93.解码
 * https://leetcode-cn.com/problems/decode-ways/
 * 动态递归实现
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/26 17:30
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter09DynamicAllocate.Section3IntegerBreak.LeetCode91解码方法;

public class 动态递归实现 {
    public int numDecodings(String s) {
        int n = s.length();
        if (n == 0) {
            return 0;
        }
        int[] memo = new int[n + 1];
        memo[n] = 1;
        memo[n - 1] = s.charAt(n - 1) != '0' ? 1 : 0;
        for (int i = n - 2; i >= 0; i--) {
            if (s.charAt(i) != '0') {
                int num = Integer.parseInt(s.substring(i, i + 2));
                if (num <= 26) {
                    memo[i] = memo[i + 1] + memo[i + 2];
                } else {
                    memo[i] = memo[i + 1];
                }
            }
        }
        return memo[0];
    }

    public static void main(String[] args) {
        // 6
        String s = "1243423435465";
        System.out.println(new 动态递归实现().numDecodings(s));
    }
}
