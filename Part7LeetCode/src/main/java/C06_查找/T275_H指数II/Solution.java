/***********************************************************
 * @Description : 275.H指数II
 * https://leetcode-cn.com/problems/h-index-ii/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/31 11:38
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C06_查找.T275_H指数II;

/**
 * 设数组长度为 n ， 那么 n-i 就是引用次数大于等于 nums[i] 的文章数。 如果 nums[i]<n-i ， 说
 * 明 i 是有效的H-Index, 如果一个数是H-Index， 那么最大的H-Index一定在它后面（ 因为是升序的） ， 根据
 * 这点就可以进行二分搜索了
 * <p>
 * Time complexity: O(logn), Space complexity: O(1)
 */
public class Solution {
    public int hIndex(int[] citations) {
        final int n = citations.length;
        int begin = 0;
        int end = citations.length;
        while (begin < end) {
            final int mid = begin + (end - begin) / 2;
            if (citations[mid] < n - mid) {
                begin = mid + 1;
            } else {
                end = mid;
            }
        }
        return n - begin;
    }
}
