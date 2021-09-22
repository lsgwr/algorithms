/***********************************************************
 * @Description : 42. 接雨水
 * https://leetcode-cn.com/problems/trapping-rain-water/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/28 21:44
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C01_线性表.数组.T42_接雨水;

/**
 * Trapping Rain Water
 * 思路1， 时间复杂度O(n)， 空间复杂度O(n)
 * 对于每个柱子， 找到其左右两边最高的柱子， 该柱子能容纳的面积就是 min(max_left, max_right) - height 。 所以，
 * 1. 从左往右扫描一遍， 对于每个柱子， 求左边最大高度(比较当前柱子和其左侧柱子，取较大者的高度)；
 * 2. 从右往左扫描一遍， 对于每个柱子， 求右边最大高度(比较当前柱子和其右侧柱子，取较大者的高度)；
 * 3. 再扫描一遍， 每个柱子的面积=min(max_left, max_right)-height，把每个柱子的面积累加得到sum就是最大接水量。
 */
public class Solution {
    public int trap(int[] heights) {
        final int N = heights.length;
        int[] leftPeak = new int[N];
        int[] rightPeak = new int[N];
        for (int i = 1; i < N; i++) {
            leftPeak[i] = Math.max(leftPeak[i - 1], heights[i - 1]);
        }
        for (int i = N - 2; i >= 0; i--) {
            rightPeak[i] = Math.max(rightPeak[i + 1], heights[i + 1]);
        }
        int sum = 0;
        for (int i = 0; i < N; i++) {
            int height = Math.min(leftPeak[i], rightPeak[i]);
            if (height > heights[i]) {
                sum += height - heights[i];
            }
        }
        return sum;
    }
}