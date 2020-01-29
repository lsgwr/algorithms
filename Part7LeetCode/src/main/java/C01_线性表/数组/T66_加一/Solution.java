/***********************************************************
 * @Description : 66.加1
 * https://leetcode-cn.com/problems/plus-one/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/29 12:57
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C01_线性表.数组.T66_加一;

import java.math.BigDecimal;

class Solution {
    public int[] plusOne(int[] digits) {
        StringBuilder numTmp = new StringBuilder();
        for (int digit : digits) {
            numTmp.append(digit);
        }
        BigDecimal num = new BigDecimal(numTmp.toString());
        BigDecimal result = num.add(new BigDecimal("1"));
        String[] arr = result.toString().split("");
        int[] nums = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            nums[i] = Integer.parseInt(arr[i]);
        }
        return nums;
    }
}
