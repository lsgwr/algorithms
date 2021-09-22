/***********************************************************
 * @Description : 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 *                简化解法：双指针法，把非零元素不断前移，把剩下的补零。没有使用任何辅助空间
 *  https://leetcode-cn.com/problems/move-zeroes/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/4/6 10:45
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C01_线性表.数组.T283_移动零;

import java.util.Arrays;

class Solution {
    /**
     * 简化解法：双指针法，把非零元素不断前移，把剩下的补零。没有使用任何辅助空间
     */
    public void moveZeroes(int[] nums) {
        // k代表nums中，[0...k)的元素均为非0元素
        int k = 0;
        // 遍历到第i个元素后，保证[0...i)中所有非0元素都按照顺序排列在[0...k)中
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[k++] = nums[i];
            }
        }
        // 将nums中的剩余位置补零
        for (int i = k; i < nums.length; i++) {
            nums[i] = 0;
        }
    }

    public static void main(String[] args) {
        int[] nums = {0, 1, 0, 3, 12};
        new Solution().moveZeroes(nums);
        System.out.println(Arrays.toString(nums));
    }
}
