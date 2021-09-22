/***********************************************************
 * @Description : 75.颜色分类
 * https://leetcode-cn.com/problems/sort-colors/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/31 09:19
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C05_排序.快速排序.T75_颜色分类;

class Solution {
    void swap(int[] nums, int i, int j) {
        int tmp = nums[j];
        nums[j] = nums[i];
        nums[i] = tmp;
    }

    public void sortColors(int[] nums) {
        // nums[0...zero] == 0
        int zero = -1;
        // nums[two...n-1] == 2
        int two = nums.length;
        for (int i = 0; i < two; ) {
            if (nums[i] == 1) {
                i++;
            } else if (nums[i] == 2) {
                // 等于2时i不需要继续前移
                swap(nums, i, --two);
            } else {
                // nums[i] ==0
                swap(nums, ++zero, i++);
            }
        }
    }
}
