/***********************************************************
 * @Description : 274.H指数
 * https://leetcode-cn.com/problems/h-index/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/31 09:41
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C05_排序.计数排序.T274_H指数;

/**
 * H-Index
 * Time complexity: O(n), Space complexity: O(n)
 */
public class Solution {
    public int hIndex(int[] citations) {
        final int n = citations.length + 1;
        final int[] histogram = new int[n + 1];
        for (int x : citations) {
            ++histogram[Math.min(x, n)];
        }
        // current number of papers
        int sum = 0;
        for (int i = n; i > 0; --i) {
            sum += histogram[i];
            if (sum >= i) {
                return i;
            }
        }
        return 0;
    }
}
