/***********************************************************
 * @Description : 1.两数之和
 * https://leetcode-cn.com/problems/two-sum/
 * 思路：遍历数组，把当前元素前面的元素们作为搜索区间~
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/28 15:52
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package 线性表.数组.T1_两数之和;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            // 此时map中的元素一定是nums[i]之前的元素，index不为null地话，i肯定大于index
            Integer index = map.get(target - nums[i]);
            if (index != null) {
                return new int[]{index, i};
            }
            // 没找到地话就向后扩展搜索区间
            map.put(nums[i], i);
        }
        return null;
    }
}
