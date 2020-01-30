/***********************************************************
 * @Description : 84.柱状图中最大的矩形
 * https://leetcode-cn.com/problems/largest-rectangle-in-histogram/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/30 17:56
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C03_栈和队列.T84_柱状图中最大的矩形;

import java.util.Stack;

/**
 * Largest Rectangle in Histogram
 * 时间复杂度O(n)， 空间复杂度O(n)
 * <p>
 * 简单的， 类似于 "T11.盛最多水的容器"(https://leetcode-cn.com/problems/container-with-most-water/)， 对每个柱子，
 * 左右扩展，直到碰到比自己矮的，计算这个矩形的面积， 用一个变量记录最大的面积， 复杂度 O(n^2) ， 会超时。
 * <p>
 * 可以维护一个递增的栈， 每次比较栈顶与当前元素。 如果当前元素大于栈顶元素， 则入栈，
 * 否则合并现有栈， 直至栈顶元素小于当前元素。 结尾时入栈元素0， 重复合并一次。
 */
class Solution {
    public int largestRectangleArea(int[] heights) {
        Stack<Integer> s = new Stack<>();
        int result = 0;
        int i = 0;
        while (i <= heights.length) {
            final int value = i < heights.length ? heights[i] : 0;
            if (s.isEmpty() || value > heights[s.peek()]) {
                s.push(i++);
            } else {
                int tmp = s.pop();
                result = Math.max(result, heights[tmp] * (s.isEmpty() ? i : i - s.peek() - 1));
            }
        }
        return result;
    }
}
