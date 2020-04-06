/***********************************************************
 * @Description : 80.删除排序数组中的重复项II
 * https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array-ii/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/4/6 10:52
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C01_线性表.数组.T80_删除排序数组中的重复项II;

class Solution {
    public int removeDuplicates(int[] nums) {
        int k = 0;
        for(int i = 1; i< nums.length; i++){
            // 有序数组，所以直接和最新的k位置的num比较,不相等肯定就可以加入了
            if(nums[i] != nums[k]){
                nums[++k] = nums[i];
            }else{
                // 因为最多允许有两个重复元素，所以即使相等，也还要和k-1位置的num进行比较
                if(k > 0){
                    if(nums[i] != nums[k - 1]){
                        nums[++k] = nums[i];
                    }
                }else{ // k=0，这时遍历到第2个元素，即使和第一个相等也是一定要加入地
                    nums[++k] = nums[i];
                }
            }

        }
        // k从0开始，所以数组长度最后要+1
        return k + 1;
    }
}
