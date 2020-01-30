/***********************************************************
 * @Description : 14.最长公共前缀
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/30 12:29
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C02_字符串.T14_最长公共前缀;

class Solution {
    public String longestCommonPrefix(String[] strs) {
        if(strs.length == 0){
            return "";
        }
        int minLen = Integer.MAX_VALUE;
        int max = 0;
        for(String str : strs){
            if(str.length() < minLen){
                minLen = str.length();
            }
        }
        boolean end = false;
        for(int i = 0; i < minLen; i++){
            for(String str : strs){
                if(str.charAt(i) != strs[0].charAt(i)){
                    end = true;
                    break;
                }
            }
            if(end){
                break;
            }
            max++;
        }
        return strs[0].substring(0, max);
    }
}
