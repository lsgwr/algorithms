/***********************************************************
 * @Description : 38. 外观数列
 * https://leetcode-cn.com/problems/count-and-say/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/30 12:49
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C02_字符串.T38_外观数列;

/**
 * 题目的意思是对序列前一个数进行报数，数列第一项不是1吗，那第二项就报第一项的有1个1，输出11，
 * 然后第三项就在第二项的基础上报数，第二项是11，第三项不就是2个1么，然后输出21。
 */
class Solution {
    public String countAndSay(int n) {
        String s = "1";
        while (--n > 0) {
            s = getNext(s);
        }
        return s;
    }

    String getNext(final String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); ) {
            int j = notEqual(s, i);
            sb.append(j - i);
            sb.append(s.charAt(i));
            i = j;
        }
        return sb.toString();
    }

    /**
     * find the first char that not equal to fromIndex
     */
    private static int notEqual(final String s, int fromIndex) {
        final char target = s.charAt(fromIndex);
        int i = fromIndex;
        for (; i < s.length(); ++i) {
            if (s.charAt(i) != target) {
                break;
            }
        }
        return i;
    }
}
