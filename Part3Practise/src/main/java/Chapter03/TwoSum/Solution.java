/***********************************************************
 * @Description : 给定一个已按照升序排列 的有序数组，找到两个数使得它们相加之和等于目标数。
 * 可以用使用两个指针，从两边向中间靠近，直到获取nums[i] + nums[j] = target，这个方法又称对撞指针法，下面有实现
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2019/8/19 23:48
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter03.TwoSum;

import java.util.Arrays;

class Solution {
    public int[] twoSum(int[] numbers, int target) {
        int l = 0, r = numbers.length - 1;
        while (l < r) {
            if (numbers[l] + numbers[r] == target) {
                // 注意返回地是下标，而且是从1开始
                return new int[]{l + 1, r + 1};
            } else if (numbers[l] + numbers[r] < target) {
                l++;
            } else {
                r--;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        int[] numbers = {2, 7, 11, 15};
        int target = 9;
        int[] result = new Solution().twoSum(numbers, target);
        System.out.println(Arrays.toString(result));
    }
}
