/***********************************************************
 * @Description : 128. 最长连续序列
 * https://leetcode-cn.com/problems/longest-consecutive-sequence/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/28 15:29
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C01_线性表.数组.T128_最长连续子序列;

import java.util.HashSet;
import java.util.Set;

class Solution {
    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        // 记录nums中每个数字的连续子序列中的最大值
        int longest = 0;
        for (int num : nums) {
            // num至少属于连续序列的一员
            int length = 1;
            // 向右侧找
            for (int j = num + 1; set.contains(j); j++){
                set.remove(j);
                length++;
            }
            // 向左侧找
            for (int j = num - 1; set.contains(j); j--){
                set.remove(j);
                length++;
            }
            // 和前面num咋最长子序列做比较，取较大地
            longest = Math.max(length, longest);
        }
        return longest;
    }
}
