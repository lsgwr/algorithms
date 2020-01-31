/***********************************************************
 * @Description : 223.矩形面积
 * https://leetcode-cn.com/problems/rectangle-area/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/31 22:25
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C14_位操作.T223_矩形面积;

/**
 * Rectangle Area
 * Time Complexity: O(1), Space Complexity: O(1)
 */
public class Solution {
    public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        final int area = (C - A) * (D - B) + (G - E) * (H - F);
        // prevent overflow
        if (C < E || G < A || D < F || H < B) {
            return area;
        }
        final int intersection = Math.max(Math.min(C, G) - Math.max(A, E), 0) *
                Math.max(Math.min(D, H) - Math.max(B, F), 0);
        return area - intersection;
    }
}
