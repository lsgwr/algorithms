/***********************************************************
 * @Description : 给定一个整数数组和一个整数 k，判断数组中是否存在两个不同的索引 i 和 j，使得 nums [i] = nums [j]，并且 i 和 j 的差的绝对值最大为 k
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2019/8/21 08:16
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter04.ContainDuplicate;

import java.util.HashSet;
import java.util.Set;

/**
 * 时间复杂度O(n),空间复杂度O(k)
 */
public class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Set<Integer> record = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (record.contains(nums[i])) {
                // 前面加入的元素包含nums[k了]
                return true;
            }
            record.add(nums[i]);
            // 保持record中不超过k个元素，即i和j的索引最大差值不大于k，超过k时抹掉最左边的元素即可
            if (record.size() == k + 1) {
                record.remove(nums[i - k]);
            }
        }
        // 遍历到最后还找不到就返回false
        return false;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 1};
        int k = 3;
        boolean contain = new Solution().containsNearbyDuplicate(nums, k);
        System.out.println(contain);

    }
}
