/***********************************************************
 * @Description : 67.二进制求和
 * 模拟下二进制的进位即可
 * https://leetcode-cn.com/problems/add-binary/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/30 11:25
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C02_字符串.T67_二进制求和;

/**
 * Add Binary
 * 时间复杂度O(n)， 空间复杂度O(1)
 */
class Solution {
    public String addBinary(String a, String b) {
        StringBuilder result = new StringBuilder();
        int i = a.length() - 1;
        int j = b.length() - 1;
        // 进位
        int carry = 0;
        while (i >= 0 || j >= 0 || carry > 0) {
            int valueA = i < 0 ? 0 : a.charAt(i--) - '0';
            int valueB = j < 0 ? 0 : b.charAt(j--) - '0';
            int sum = valueA + valueB + carry;
            result.insert(0, Character.forDigit(sum % 2, 10));
            // 二进制中sum最大就是3
            carry = sum / 2;
        }
        return result.toString();
    }
}
