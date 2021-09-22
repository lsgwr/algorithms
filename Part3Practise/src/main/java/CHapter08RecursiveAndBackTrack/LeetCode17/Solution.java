/***********************************************************
 * @Description : 
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/24 10:49
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package CHapter08RecursiveAndBackTrack.LeetCode17;

import java.util.ArrayList;
import java.util.List;

class Solution {
    // 每个按键(数组的下标)对应的可能的字符串，0和1不对应任何字符，所以这里为空
    String[] letterMap = {
            "", // 0
            "", // 1
            "abc", // 2
            "def", // 3
            "ghi", // 4
            "jkl", // 5
            "mno", // 6
            "pqrs", // 7
            "tuv",  // 8
            "wxyz"  // 9
    };

    public List<String> letterCombinations(String digits) {
        // 存储最终结果的列表
        List<String> result = new ArrayList<>();
        if ("".equals(digits)){
            return result;
        }
        findCombinations(digits, 0, "", result);
        return result;
    }

    /**
     * 寻找digits[index]匹配的字母，获得digits[0...index]对应的解
     *
     * @param digits 原始数字字符串
     * @param index 要看digits的哪一个数字
     * @param s      s保存了此时从digits[0...index-1]翻译得到的一个字母字符串
     * @param result 保存最终可能的字符串
     */
    private void findCombinations(String digits, int index, String s, List<String> result) {
        // 所有数字都遍历完了，递归退出
        if (index == digits.length()) {
            result.add(s);
            return;
        }
        // 拿到index对应的数字字符
        char c = digits.charAt(index);
        // 获取当前数字字符可能对应的键盘上的字符串
        String lettersStr = letterMap[c - '0'];
        // 第当前数字对应的字符串进行遍历拼接
        for (int i = 0; i < lettersStr.length(); i++) {
            findCombinations(digits, index + 1, s + lettersStr.charAt(i), result);
        }
    }
}
