/***********************************************************
 * @Description : 68.文本左右对齐
 * https://leetcode-cn.com/problems/text-justification/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/31 23:45
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C16_编程基础.T68_文本左右对齐;

import java.util.ArrayList;
import java.util.List;

/**
 * Text Justification
 * 时间复杂度O(n)， 空间复杂度O(1)
 */
public class Solution {
    public List fullJustify(String[] words, int maxWidth) {
        List result = new ArrayList<>();
        final int n = words.length;
        // 当前行的起点， 当前长度
        int begin = 0, len = 0;
        for (int i = 0; i < n; ++i) {
            if (len + words[i].length() + (i - begin) > maxWidth) {
                result.add(connect(words, begin, i - 1, len, maxWidth, false));
                begin = i;
                len = 0;
            }
            len += words[i].length();
        }
        // 最后一行不足 maxWidth
        result.add(connect(words, begin, n - 1, len, maxWidth, true));
        return result;
    }

    /**
     * @param words  单词列表
     * @param begin  开始
     * @param end    结束
     * @param len    words[begin, end]所有单词加起来的长度
     * @param L      题目规定的一行长度
     * @param islast 是否是最后一行
     * @return 对齐后的当前行
     * @apiNote 将 words[begin, end] 连成一行
     */
    private static String connect(String[] words, int begin, int end, int len, int L, boolean islast) {
        StringBuilder sb = new StringBuilder();
        final int n = end - begin + 1;
        for (int i = 0; i < n; ++i) {
            sb.append(words[begin + i]);
            addSpaces(sb, i, n - 1, L - len, islast);
        }
        final int m = L - sb.length();
        for (int i = 0; i < m; ++i) {
            sb.append(' ');
        }
        return sb.toString();
    }

    /**
     * @param sb     一行
     * @param i      当前空隙的序号
     * @param n      空隙总数
     * @param L      总共需要添加的空额数
     * @param isLast 是否是最后一行
     * @return 无
     * @apiNote 添加空格.
     */
    private static void addSpaces(StringBuilder sb, int i, int n, int L, boolean isLast) {
        if (n < 1 || i > n - 1) {
            return;
        }
        int spaces = isLast ? 1 : (L / n + (i < (L % n) ? 1 : 0));
        for (int j = 0; j < spaces; ++j) {
            sb.append(' ');
        }
    }
}