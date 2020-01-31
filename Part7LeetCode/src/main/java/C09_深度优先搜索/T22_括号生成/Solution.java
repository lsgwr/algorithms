/***********************************************************
 * @Description : 22.括号生成
 * https://leetcode-cn.com/problems/generate-parentheses/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/31 17:26
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C09_深度优先搜索.T22_括号生成;

import java.util.ArrayList;
import java.util.List;

/**
 * 小括号串是一个递归结构， 跟单链表、 二叉树等递归结构一样， 首先想到用递归。
 * 一步步构造字符串。 当左括号出现次数 <n 时， 就可以放置新的左括号。 当右括号出现次数小于左括号出
 * 现次数时， 就可以放置新的右括号
 * <p>
 * 时间复杂度O(n)， 空间复杂度O(n)
 */
public class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        StringBuilder path = new StringBuilder();
        if (n > 0) {
            generate(n, path, result, 0, 0);
        }
        return result;
    }

    /**
     * l 表示 ( 出现的次数, r 表示 ) 出现的次数
     */
    private static void generate(int n, StringBuilder path, List<String> result, int l, int r) {
        if (l == n) {
            StringBuilder sb = new StringBuilder(path);
            for (int i = 0; i < n - r; ++i) {
                sb.append(')');
            }
            result.add(sb.toString());
            return;
        }
        path.append('(');
        generate(n, path, result, l + 1, r);
        path.deleteCharAt(path.length() - 1);
        if (l > r) {
            path.append(')');
            generate(n, path, result, l, r + 1);
            path.deleteCharAt(path.length() - 1);
        }
    }
}
