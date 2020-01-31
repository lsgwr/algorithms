/***********************************************************
 * @Description : 179.最大数
 * https://leetcode-cn.com/problems/largest-number/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/31 10:02
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C05_排序.自定义排序.T179_最大数;

import java.util.Arrays;

/**
 * Largest Number
 * Time Complexity: O(n), Space Complexity: O(n)
 */
public class Solution {
    public String largestNumber(int[] nums) {
        final String[] strings = new String[nums.length];
        for (int i = 0; i < nums.length; ++i) {
            strings[i] = String.valueOf(nums[i]);
        }
        Arrays.sort(strings, (String s1, String s2) -> {
            String leftRight = s1 + s2;
            String rightLeft = s2 + s1;
            return -leftRight.compareTo(rightLeft);
        });
        StringBuilder sb = new StringBuilder();
        for (final String s : strings) {
            sb.append(s);
        }
        while (sb.charAt(0) == '0' && sb.length() > 1) {
            sb.deleteCharAt(0);
        }
        return sb.toString();
    }
}
