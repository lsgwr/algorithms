/***********************************************************
 * @Description : 35.搜索插入位置
 * https://leetcode-cn.com/problems/search-insert-position/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/31 10:24
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C06_查找.T35_搜索插入位置;

class Solution {
    public int searchInsert(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >= target) {
                return i;
            }
        }
        return nums.length;
    }
}
