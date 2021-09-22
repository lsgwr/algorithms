/***********************************************************
 * @Description : 41.缺失的第一个正数
 * https://leetcode-cn.com/problems/first-missing-positive/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/31 09:36
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C05_排序.桶排序.T41_缺失的第一个正数;

/**
 * First Missing Positive
 * 时间复杂度O(n)， 空间复杂度O(1)
 * <p>
 * 本质上是桶排序(bucket sort)， 每当 A[i]!= i+1 的时候， 将 A[i] 与 A[A[i]-1] 交换， 直到无法交换
 * 为止， 终止条件是 A[i]== A[A[i]-1]
 */
public class Solution {
    public int firstMissingPositive(int[] nums) {
        bucket_sort(nums);
        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] != (i + 1)) {
                return i + 1;
            }
        }
        return nums.length + 1;
    }

    private static void bucket_sort(int[] A) {
        final int n = A.length;
        for (int i = 0; i < n; i++) {
            while (A[i] != i + 1) {
                if (A[i] < 1 || A[i] > n || A[i] == A[A[i] - 1]) {
                    break;
                }
                // swap
                int tmp = A[i];
                A[i] = A[tmp - 1];
                A[tmp - 1] = tmp;
            }
        }
    }
}
