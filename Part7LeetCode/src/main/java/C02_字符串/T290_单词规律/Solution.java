/***********************************************************
 * @Description : 290.单词规律
 * https://leetcode-cn.com/problems/word-pattern/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/30 16:43
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C02_字符串.T290_单词规律;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class Solution {
    public boolean wordPattern(String pattern, String str) {
        if("".equals(pattern) || "".equals(str)){
            return false;
        }
        String[] words = str.split(" ");
        int len = pattern.length();
        if(len != words.length){
            return false;
        }
        Set<Character> setPattern = new HashSet<>();
        Set<String> setWord = new HashSet<>();
        for(int i = 0; i < len; i++){
            setPattern.add(pattern.charAt(i));
            setWord.add(words[i]);
        }
        if(setPattern.size() != setWord.size()){
            return false;
        }
        Map<Character, String> map = new HashMap<>();
        for(int i = 0; i < len; i++){
            Character c = pattern.charAt(i);
            String word = map.get(c);
            if(word == null){
                map.put(c, words[i]);
            }else {
                // 映射在之前就存在了，但是之前对应的关系和现在不符，直接返回false
                if(!word.equals(words[i])){
                    return false;
                }
            }
        }
        return true;
    }
}
