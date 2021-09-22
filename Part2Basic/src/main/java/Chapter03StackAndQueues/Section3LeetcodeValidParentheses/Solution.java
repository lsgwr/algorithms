/***********************************************************
 * @Description : 括号匹配问题，用地JDK自带的Stack
 *                Given a string containing just the characters
 *                '(',')','{','}','['and']', determine if the input string is valid.
 *                The brackets must close in the correct order,"()"and"()[]{}"are
 *                all valid but"(]"and"([)]"are not.
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/5/13 21:33
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter03StackAndQueues.Section3LeetcodeValidParentheses;


import java.util.Stack;

public class Solution {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            // 遇到左括号就入栈
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) {
                    // 栈为空肯定是不匹配了
                    return false;
                }
                char topChar = stack.pop();
                // 判断三种括号类型
                if (c == ')' && topChar != '(') {
                    return false;
                }
                if (c == ']' && topChar != '[') {
                    return false;
                }
                if (c == '}' && topChar != '{') {
                    return false;
                }
            }
        }
        // 匹配到最后栈还是不为空，及时前面都匹配成功了，结果还是匹配失败。
        // 为空地话才表明前面都匹配成功了，而且没有剩余括号要匹配了
        return stack.isEmpty();
    }
}
