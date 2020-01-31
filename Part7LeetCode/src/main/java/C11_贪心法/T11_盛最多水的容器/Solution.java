/***********************************************************
 * @Description : 11.盛最多水的容器
 * https://leetcode-cn.com/problems/container-with-most-water/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/31 18:29
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C11_贪心法.T11_盛最多水的容器;

class Solution {
    public int maxArea(int[] height) {
        int maxArea = 0;
        int l = 0;
        int r = height.length - 1;
        while (l < r) {
            int area = Math.min(height[l], height[r]) * (r - l);
            // 更新最大值
            maxArea = Math.max(area, maxArea);
            // height小地向内移动，贪心算法。如果求最小值就是height大地向内移动
            if (height[l] < height[r]) {
                l++;
            } else {
                r--;
            }
        }
        return maxArea;
    }
}
