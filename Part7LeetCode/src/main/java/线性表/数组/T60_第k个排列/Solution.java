/***********************************************************
 * @Description : 60.第k个排列
 * https://leetcode-cn.com/problems/permutation-sequence/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/28 20:56
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package 线性表.数组.T60_第k个排列;

import java.util.ArrayList;
import java.util.List;

/**
 * Permutation Sequence
 * 康托编码
 * 时间复杂度O(n)， 空间复杂度O(1)
 */
class Solution {
    public String getPermutation(int n, int k) {
        StringBuilder sb = new StringBuilder();
        k--;
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(i + 1);
        }
        while (!list.isEmpty()) {
            int fun = func(n-- - 1);
            sb.append(list.get(k / fun));
            list.remove(k / fun);
            k %= fun;
        }
        return sb.toString();

    }

    public int func(int n) {
        if (n == 1 || n == 0) {
            return 1;
        }
        return func(n - 1) * n;
    }
}
