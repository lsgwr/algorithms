package C02_字符串.T796_判断两个字符串是否互为旋转词;

class Solution {
    public boolean rotateString(String A, String B) {
        if(A.length() == B.length()){
            return (B + B).contains(A);
        }
        return false;
    }
}
