/***********************************************************
 * @Description : 26.删除排序数组中的重复项
 * https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array/
 * 给定一个排序数组，你需要在 原地 删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。
 * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
 *
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/4/6 10:50
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C01_线性表.数组.T26_删除排序数组中的重复项;

class Solution {
    public int removeDuplicates(int[] nums) {
        int k = 0;
        for(int i = 1; i< nums.length; i++){
            // 有序数组，所以直接和前面的比较就知道有没有排序了
            if(nums[i] != nums[k]){
                nums[++k] = nums[i];
            }
        }
        // k从0开始，所以数组长度最后要+1
        return k + 1;
    }
}
