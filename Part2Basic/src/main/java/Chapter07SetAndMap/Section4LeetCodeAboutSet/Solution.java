/***********************************************************
 * @Description : LeetCode804.唯一摩尔斯密码词
 * https://leetcode-cn.com/problems/unique-morse-code-words/submissions/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2019/12/31 19:34
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter07SetAndMap.Section4LeetCodeAboutSet;

import java.util.Set;
import java.util.TreeSet;

class Solution {
    public int uniqueMorseRepresentations(String[] words) {
        String[] codecs = {".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..", "--", "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--.."};
        // 不同的翻译
        Set<String> diffTrans = new TreeSet<>();
        for (String word : words) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < word.length(); i++) {
                sb.append(codecs[word.charAt(i) - 'a']);
            }
            diffTrans.add(sb.toString());
        }
        return diffTrans.size();
    }

    public static void main(String[] args) {
        String[] words = {"gin", "zen", "gig", "msg"};
        System.out.println(new Solution().uniqueMorseRepresentations(words));
    }
}
/**
 * 2
 */