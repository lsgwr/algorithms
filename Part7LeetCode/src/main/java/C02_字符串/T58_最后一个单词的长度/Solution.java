/***********************************************************
 * @Description : 58.最后一个单词的长度
 * https://leetcode-cn.com/problems/length-of-last-word/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/30 16:35
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C02_字符串.T58_最后一个单词的长度;

/**
 * 考虑" a "和"sds"这样的用例
 */
class Solution {
    public int lengthOfLastWord(String s) {
        s = s.trim();
        int index = s.lastIndexOf(" ");
        if(index >= 0){
            return s.length() - index - 1;
        }else {
            if(!"".equals(s.trim())){
                return s.length();
            }else {
                return 0;
            }
        }
    }
}
