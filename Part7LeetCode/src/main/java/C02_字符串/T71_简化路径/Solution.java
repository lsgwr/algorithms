/***********************************************************
 * @Description : 71.简化路径
 * https://leetcode-cn.com/problems/simplify-path/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/30 13:22
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C02_字符串.T71_简化路径;

import java.util.Stack;

/**
 * Simplify Path
 * 时间复杂度O(n)， 空间复杂度O(n)
 * 1.此题主要考察的是栈,所以定义一个辅助栈;
 * 2.先把字符串以"/"为分隔符分割成数组,此时数组有"路径"、""、"."、".."这四种情况;
 * 3.遍历数组,当s[i].equals("..")并且栈不空时pop,当!s[i].equals("") && !s[i].equals(".") && !s[i].equals(".."),即s[i]是路径入栈;
 * 4.栈空,返回"/",栈非空,用StringBuffer做一个连接返回即可;
 * 5完结。
 * <p>
 * 链接：https://leetcode-cn.com/problems/simplify-path/solution/java-yi-dong-yi-jie-xiao-lu-gao-by-spirit-9-18/
 */
class Solution {
    public String simplifyPath(String path) {
        String[] s = path.split("/");
        Stack<String> stack = new Stack<>();

        for (String item : s) {
            if (!stack.isEmpty() && "..".equals(item)) {
                stack.pop();
            } else if (!"".equals(item) && !".".equals(item) && !"..".equals(item)) {
                stack.push(item);
            }
        }
        if (stack.isEmpty()) {
            return "/";
        }

        StringBuilder res = new StringBuilder();
        for (String value : stack) {
            res.append("/").append(value);
        }
        return res.toString();
    }
}
