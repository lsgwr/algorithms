/***********************************************************
 * @Description : 150.逆波兰表达式求值
 * https://leetcode-cn.com/problems/evaluate-reverse-polish-notation/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/30 18:17
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C03_栈和队列.T150_逆波兰表达式求值;

import java.util.Stack;

/**
 * Max Points on a Line
 * 迭代， 时间复杂度O(n)， 空间复杂度O(logn)
 */
class Solution {
    public int evalRPN(String[] tokens) {
        Stack<String> s = new Stack<>();
        for (String token : tokens) {
            if (!isOperator(token)) {
                s.push(token);
            } else {
                int y = Integer.parseInt(s.pop());
                int x = Integer.parseInt(s.pop());
                switch (token.charAt(0)) {
                    case '+':
                        x += y;
                        break;
                    case '-':
                        x -= y;
                        break;
                    case '*':
                        x *= y;
                        break;
                    default:
                        x /= y;
                }
                s.push(String.valueOf(x));
            }
        }
        return Integer.parseInt(s.peek());
    }

    private static boolean isOperator(final String op) {
        String OPS = "+-*/";
        return op.length() == 1 && OPS.contains(op);
    }
}
