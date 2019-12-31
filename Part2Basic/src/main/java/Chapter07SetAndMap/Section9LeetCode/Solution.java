/***********************************************************
 * @Description : LeetCode349 求两个数组的交集
 * https://leetcode-cn.com/problems/intersection-of-two-arrays/submissions/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2019/12/31 20:36
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter07SetAndMap.Section9LeetCode;

import java.util.Set;
import java.util.TreeSet;

class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> numSet = new TreeSet<>();
        for (int num1 : nums1) {
            for (int num2 : nums2) {
                if (num1 == num2) {
                    numSet.add(num1);
                }
            }
        }
        int[] intArr = new int[numSet.size()];
        Object[] objArr = numSet.toArray();
        for (int i = 0; i < numSet.size(); i++) {
            intArr[i] = (int) objArr[i];
        }
        return intArr;
    }
}
