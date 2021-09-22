/***********************************************************
 * @Description : 205.同构字符串
 * https://leetcode-cn.com/problems/isomorphic-strings/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/30 16:37
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C02_字符串.T205_同构字符串;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Isomorphic Strings
 * Time Complexity: O(n), Space Complexity: O(n)
 */
class Solution {
    public boolean isIsomorphic(String s, String t) {
        if ("".equals(s) && "".equals(t)) {
            return true;
        }
        if ("".equals(s) || "".equals(t)) {
            return false;
        }
        int len = s.length();
        if (len != t.length()) {
            return false;
        }
        Set<Character> setS = new HashSet<>();
        Set<Character> setT = new HashSet<>();
        for (int i = 0; i < len; i++) {
            setS.add(s.charAt(i));
            setT.add(t.charAt(i));
        }
        if (setS.size() != setT.size()) {
            return false;
        }
        Map<Character, Character> map = new HashMap<>();
        for (int i = 0; i < len; i++) {
            Character cS = s.charAt(i);
            Character cSMap = map.get(cS);
            Character cT = t.charAt(i);
            if (cSMap == null) {
                map.put(cS, cT);
            } else {
                // 映射在之前就存在了，但是之前对应的关系和现在不符，直接返回false
                if (!cT.equals(cSMap)) {
                    return false;
                }
            }
        }
        return true;
    }
}
