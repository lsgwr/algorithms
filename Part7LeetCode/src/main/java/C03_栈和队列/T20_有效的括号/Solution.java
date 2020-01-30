/***********************************************************
 * @Description : 20.有效的括号
 * https://leetcode-cn.com/problems/valid-parentheses/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/30 17:33
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C03_栈和队列.T20_有效的括号;

import java.util.Stack;

class Solution {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else {
                // 还有字符串但是栈已经为空了，肯定是非法表达式
                if (stack.size()==0){
                    return false;
                }
                char cPop = stack.pop();
                if(cPop == '('){
                    if (c !=')'){
                        return false;
                    }
                }else if (cPop == '['){
                    if (c != ']'){
                        return false;
                    }
                }else if (cPop== '{'){
                    if (c != '}'){
                        return false;
                    }
                }
            }
        }
        if (stack.size() != 0) {
            return false;
        }
        return true;
    }
}
