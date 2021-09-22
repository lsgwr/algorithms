/***********************************************************
 * @Description : 给定四个包含整数的数组列表 A , B , C , D ,计算有多少个元组 (i, j, k, l) ，使得 A[i] + B[j] + C[k] + D[l] = 0
 * 解题思路：把C+D的组合放入查找表中，通过查找A+B是否等于-(C+D)
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2019/8/20 23:52
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter04.TwoSum2;

import java.util.HashMap;
import java.util.Map;

/*
 *时间复杂度是：O(n^2)
 *空间复杂度是：O(n^2)
 */
class Solution {
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        Map<Integer, Integer> record = new HashMap<>();
        for (int i = 0; i < C.length; i++) {
            for (int j = 0; j < D.length; j++) {
                if (record.get(C[i] + D[j]) == null) {
                    record.put(C[i] + D[j], 1);
                } else {
                    record.put(C[i] + D[j], record.get(C[i] + D[j]) + 1);
                }
            }
        }
        int result = 0;
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < B.length; j++) {
                if (record.containsKey(-A[i] - B[j])) {
                    result += record.get(-A[i] - B[j]);
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] A = {1, 2};
        int[] B = {-2, -1};
        int[] C = {-1, 2};
        int[] D = {0, 2};
        int result = new Solution().fourSumCount(A, B, C, D);
        System.out.println(result);
    }
}
