/***********************************************************
 * @Description : 394.字符串解码
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/3/24 22:43
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C02_字符串.T394_字符串解码;

import java.util.Stack;

class Solution {
    public String decodeString(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        // 存储[和]下标的栈
        Stack<Integer> stack = new Stack<>();
        // 只要栈不为空就继续进行检索和替换
        while (s.contains("[") && s.contains("]")) {
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '[') {
                    stack.push(i);
                }
                if (s.charAt(i) == ']') {
                    // 匹配的[的下标
                    int lIndex = stack.pop();
                    // 取得中间的待乘以指定次数字符串
                    String tmp = s.substring(lIndex + 1, i);
                    // 整数的左边界
                    int intL = lIndex - 1;
                    while (intL >= 0 && (s.charAt(intL) - '0' >= 0) && (s.charAt(intL) - '0' <= 9)) {
                        intL--;
                    }
                    // 获取了该重复的次数
                    int cnt = Integer.parseInt(s.substring(intL + 1, lIndex));
                    String decodeStr = "";
                    for (int j = 0; j < cnt; j++) {
                        // 不断拼接中间的字符串
                        decodeStr += tmp;
                    }
                    // 更新s
                    if (i + 1 < s.length()) {
                        s = s.substring(0, intL + 1) + decodeStr + s.substring(i + 1);
                        // 更新一次s就要退出for循环，重新从最外层的while开始
                        break;
                    } else {
                        s = s.substring(0, intL + 1) + decodeStr;
                        // ]为最后一个字符了，直接返回就好啦
                        return s;
                    }
                }
            }
        }
        return s;
    }

    public static void main(String[] args) {
        String s = "3[a]2[bc]";
        System.out.println(new Solution().decodeString(s));
    }
}
