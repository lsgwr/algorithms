/***********************************************************
 * @Description : 30.串联所有单词的子串
 * https://leetcode-cn.com/problems/substring-with-concatenation-of-all-words/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/31 23:21
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C16_编程基础.T30_串联所有单词的子串;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Substring with Concatenation of All Words
 * 时间复杂度O(n*m)， 空间复杂度O(m)
 */
public class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<>();
        if ("".equals(s)|| words.length==0){
            return result;
        }
        final int wordLength = words[0].length();
        final int catLength = wordLength * words.length;

        if (s.length() < catLength) {
            return result;
        }
        HashMap<String, Integer> wordCount = new HashMap<>();
        for (String word : words) {
            wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
        }
        for (int i = 0; i <= s.length() - catLength; ++i) {
            HashMap<String, Integer> unused = new HashMap<>(wordCount);
            for (int j = i; j < i + catLength; j += wordLength) {
                final String key = s.substring(j, j + wordLength);
                final int pos = unused.getOrDefault(key, -1);
                if (pos == -1 || pos == 0) {
                    break;
                }
                unused.put(key, pos - 1);
                if (pos - 1 == 0) {
                    unused.remove(key);
                }
            }
            if (unused.size() == 0) {
                result.add(i);
            }
        }
        return result;
    }
}
