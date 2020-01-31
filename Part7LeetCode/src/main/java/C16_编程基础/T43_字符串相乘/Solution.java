/***********************************************************
 * @Description : 43.字符串相乘
 * https://leetcode-cn.com/problems/multiply-strings/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/31 23:15
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C16_编程基础.T43_字符串相乘;

class Solution {
    public String multiply(String num1, String num2) {
        /**
         num1的第i位(高位从0开始)和num2的第j位相乘的结果在乘积中的位置是[i+j, i+j+1]
         例: 123 * 45,  123的第1位 2 和45的第0位 4 乘积 08 存放在结果的第[1, 2]位中
         index:    0 1 2 3 4

         1 2 3
         *     4 5
         ---------
         1 5
         1 0
         0 5
         ---------
         0 6 1 5
         1 2
         0 8
         0 4
         ---------
         0 5 5 3 5
         这样我们就可以单独都对每一位进行相乘计算把结果存入相应的index中
         **/

        int n1 = num1.length()-1;
        int n2 = num2.length()-1;
        if(n1 < 0 || n2 < 0) {
            return "";
        }
        int[] mul = new int[n1+n2+2];

        for(int i = n1; i >= 0; --i) {
            for(int j = n2; j >= 0; --j) {
                int bitmul = (num1.charAt(i)-'0') * (num2.charAt(j)-'0');
                // 先加低位判断是否有新的进位
                bitmul += mul[i+j+1];

                mul[i+j] += bitmul / 10;
                mul[i+j+1] = bitmul % 10;
            }
        }

        StringBuilder sb = new StringBuilder();
        int i = 0;
        // 去掉前导0
        while(i < mul.length-1 && mul[i] == 0) {
            i++;
        }
        for(; i < mul.length; ++i) {
            sb.append(mul[i]);
        }
        return sb.toString();
    }
}
