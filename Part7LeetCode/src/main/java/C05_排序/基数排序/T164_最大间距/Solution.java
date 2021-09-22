/***********************************************************
 * @Description : 164.最大间距
 * https://leetcode-cn.com/problems/maximum-gap/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/31 09:50
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C05_排序.基数排序.T164_最大间距;

import java.util.ArrayList;

/**
 * Maximum Gap
 * Radix Sort 基数排序
 * Time Complexity: O(nd), Space Complexity: O(n+d)
 */
public class Solution {
    public int maximumGap(int[] nums) {
        if (nums.length < 2) {
            return 0;
        }
        radixSort(nums);
        int maxDiff = Integer.MIN_VALUE;
        for (int i = 1; i < nums.length; ++i) {
            maxDiff = Math.max(maxDiff, nums[i] - nums[i - 1]);
        }
        return maxDiff;
    }

    private static void radixSort(int[] nums) {
        int minValue = Integer.MAX_VALUE;
        int maxValue = Integer.MIN_VALUE;
        for (int i : nums) {
            minValue = Math.min(minValue, i);
            maxValue = Math.max(maxValue, i);
        }
        final int D = Integer.toString(maxValue - minValue).length();
        final ArrayList<Integer>[] buckets = new ArrayList[10];
        for (int i = 0; i < buckets.length; ++i) {
            buckets[i] = new ArrayList<>();
        }
        for (int i = 0; i < D; ++i) {
            for (int x : nums) {
                final int index = getDigit(x - minValue, i);
                final ArrayList<Integer> bucket = buckets[index];
                bucket.add(x);
            }
            int index = 0;
            for (ArrayList<Integer> bucket : buckets) {
                for (int x : bucket) {
                    nums[index++] = x;
                }
                bucket.clear();
            }
        }
    }

    /**
     * get the i-th digit of n
     */
    private static int getDigit(int n, int i) {
        for (int j = 0; j < i; ++j) {
            n /= 10;
        }
        return n % 10;
    }
}
