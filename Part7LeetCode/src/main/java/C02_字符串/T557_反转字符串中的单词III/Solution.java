package C02_字符串.T557_反转字符串中的单词III;

class Solution {
    public String reverseWords(String s) {
        if(s == null || s.length() == 0){
            return "";
        }
        // 分割成单词
        String[] words = s.split(" ");
        StringBuilder sb = new StringBuilder();
        for(String word : words){
            sb.append(new StringBuilder(word).reverse());
            sb.append(" ");
        }
        return sb.toString().trim();
    }
}