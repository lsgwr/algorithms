/***********************************************************
 * @Description : 6.Z字形变换
 * https://leetcode-cn.com/problems/zigzag-conversion/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/31 23:39
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C16_编程基础.T6_Z字形变换;

/**
 * ZigZag Conversion
 * 时间复杂度O(n)， 空间复杂度O(1)
 */
public class Solution {
    public String convert(String s, int numRows) {
        if (numRows <= 1 || s.length() <= 1) {
            return s;
        }
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < numRows; i++) {
            for (int j = 0, index = i; index < s.length();
                 j++, index = (2 * numRows - 2) * j + i) {
                // 垂直元素
                result.append(s.charAt(index));
                // 斜对角元素
                if (i == 0 || i == numRows - 1) {
                    continue;
                }
                if (index + (numRows - i - 1) * 2 < s.length()) {
                    result.append(s.charAt(index + (numRows - i - 1) * 2));
                }
            }
        }
        return result.toString();
    }
}
