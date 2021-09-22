/***********************************************************
 * @Description : 75.颜色分类
 * https://leetcode-cn.com/problems/sort-colors/
 * 给定一个包含红色、白色和蓝色，一共 n 个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
 * 此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
 * 注意:
 * 不能使用代码库中的排序函数来解决这道题。
 *
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/4/6 10:54
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C01_线性表.数组.T75_颜色分类;

import java.util.Arrays;

/**
 * 使用计数排序的两趟扫描算法
 */
class Solution {
    public void sortColors(int[] nums) {
        int[] count = {0, 0, 0};
        for (int num : nums) {
            count[num]++;
        }
        Arrays.fill(nums, 0, count[0], 0);
        Arrays.fill(nums, count[0], count[0] + count[1], 1);
        Arrays.fill(nums, count[0] + count[1], nums.length, 2);
    }

    public static void main(String[] args) {
        int[] nums = {2, 0, 2, 1, 1, 0};
        new Solution().sortColors(nums);
        System.out.println(Arrays.toString(nums));
    }
}
