/***********************************************************
 * @Description : 27. 移除元素
 * https://leetcode-cn.com/problems/remove-element/
 * 给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素，并返回移除后数组的新长度。
 * 不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并 原地 修改输入数组。
 * 元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
 *
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/4/6 10:48
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C01_线性表.数组.T27_移除元素;

class Solution {
    public int removeElement(int[] nums, int val) {
        int k = 0;
        for(int i = 0; i < nums.length; i++){
            if(nums[i] != val){
                nums[k++] = nums[i];
            }
        }
        return k;
    }
}
