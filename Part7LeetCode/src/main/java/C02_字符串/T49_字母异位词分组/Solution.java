/***********************************************************
 * @Description : 
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/30 13:15
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C02_字符串.T49_字母异位词分组;

import java.util.*;

/**
 * 回文构词法有一个特点： 单词里的字母的种类和数目没有改变， 只是改变了字母的排列顺序。
 * 因此， 将几个单词按照字母顺序排序后， 若它们相等， 则它们属于同一组 anagrams
 */
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> result = new ArrayList<>();
        Map<String, List<String>> anagramsMap = new HashMap<>();
        for (int i = 0; i < strs.length; i++) {
            char[] chs = strs[i].toCharArray();
            Arrays.sort(chs);
            String strSorted = new String(chs);
            if (anagramsMap.get(strSorted) == null) {
                List<String> anagrams = new ArrayList<>();
                anagrams.add(strs[i]);
                anagramsMap.put(strSorted, anagrams);
            } else {
                List<String> anagrams = anagramsMap.get(strSorted);
                anagrams.add(strs[i]);
                anagramsMap.put(strSorted, anagrams);
            }
        }

        for (String key : anagramsMap.keySet()) {
            result.add(anagramsMap.get(key));
        }
        return result;
    }
}
