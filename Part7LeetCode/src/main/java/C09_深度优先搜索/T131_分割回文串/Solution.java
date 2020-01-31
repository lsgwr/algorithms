/***********************************************************
 * @Description : 131.分割回文串
 * https://leetcode-cn.com/problems/palindrome-partitioning/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/31 17:05
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C09_深度优先搜索.T131_分割回文串;

import java.util.ArrayList;
import java.util.List;

class Solution {
    /**
     * 参考LeetCode125.验证回文串
     */
    public boolean isPalindrome(String s) {
        s = s.toLowerCase();
        int l = 0;
        int r = s.length() - 1;
        while (l < r) {
            // 只要还没相遇就接着往下走
            if (!Character.isLetterOrDigit(s.charAt(l))) {
                // 左边的字符不是字母或数字
                l++;
                continue;
            }
            if (!Character.isLetterOrDigit(s.charAt(r))) {
                // 右边的字符不是字母或数字
                r--;
                continue;
            }
            // 左右两边都是字母或数字，只要不相等就说明不是回文
            if (s.charAt(l) != s.charAt(r)) {
                return false;
            }
            // 相等地话，继续向中间靠拢
            l++;
            r--;
        }
        return true;
    }

    /**
     * 获取本次循环中的回文串
     *
     * @param s           原始字符串
     * @param palindromes 本次循环中的回文串
     * @param result      存储所有回文串列表的列表
     */
    void getAllPalindromes(String s, List<String> palindromes, List<List<String>> result) {
        if ("".equals(s)) {
            // 当到头时，把回文串列表加入到result中并返回，列表是引用传值，所以必须new一个list
            result.add(new ArrayList<>(palindromes));
            return;
        }
        for (int i = 1; i <= s.length(); i++) {
            String tmp = s.substring(0, i);
            if (!isPalindrome(tmp)) {
                // 不是回文串就直接退出本层递归
                continue;
            }
            palindromes.add(tmp);
            getAllPalindromes(s.substring(i), palindromes, result);
            // 退出本层递归，需要移除一个回文串
            palindromes.remove(palindromes.size() - 1);
        }
    }

    public List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();
        List<String> palindromes = new ArrayList<>();
        getAllPalindromes(s, palindromes, result);
        return result;
    }
}
