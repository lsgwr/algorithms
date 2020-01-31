/***********************************************************
 * @Description : 330.按要求补齐数组
 *
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/31 18:33
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C11_贪心法.T330_按要求补齐数组;

/**
 * https://leetcode-cn.com/problems/patching-array/solution/an-yao-qiu-bu-qi-shu-zu-by-leetcode/
 */
public class Solution {
    public int minPatches(int[] nums, int n) {
        int patches = 0, i = 0;
        // use long to avoid integer overflow error
        long miss = 1;
        while (miss <= n) {
            // miss is covered
            if (i < nums.length && nums[i] <= miss) {
                miss += nums[i++];
            } else { // patch miss to the array
                miss += miss;
                patches++; // increase the answer
            }
        }
        return patches;
    }
}
