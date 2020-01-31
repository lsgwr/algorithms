/***********************************************************
 * @Description : 89.格雷编码
 * https://leetcode-cn.com/problems/gray-code/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/31 22:01
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C14_位操作.T89_格雷编码;

import java.util.ArrayList;
import java.util.List;

/**
 * Gray Code
 * reflect-and-prefix method
 * 时间复杂度O(2^n)， 空间复杂度O(1)
 */
public class Solution {
    public List<Integer> grayCode(int n) {
        final int size = 1 << n;
        List<Integer> result = new ArrayList<>(size);
        result.add(0);
        for (int i = 0; i < n; i++) {
            final int highestBit = 1 << i;
            // 要反着遍历， 才能对称
            for (int j = result.size() - 1; j >= 0; j--)
            {
                result.add(highestBit | result.get(j));
            }
        }
        return result;
    }
}
