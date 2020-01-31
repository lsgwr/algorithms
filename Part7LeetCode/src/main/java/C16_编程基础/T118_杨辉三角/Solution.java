/***********************************************************
 * @Description : 118.杨辉三角
 * https://leetcode-cn.com/problems/pascals-triangle/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/31 23:25
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C16_编程基础.T118_杨辉三角;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Pascal's Triangle
 * 时间复杂度O(n^2)， 空间复杂度O(n)
 */
public class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new ArrayList<>();
        if (numRows == 0) {
            return result;
        }
        //first row
        result.add(new ArrayList<>(Arrays.asList(1)));
        for (int i = 2; i <= numRows; ++i) {
            // 本行
            Integer[] current = new Integer[i];
            Arrays.fill(current, 1);
            // 上一行
            List<Integer> prev = result.get(i - 2);
            for (int j = 1; j < i - 1; ++j) {
                // 左上角和右上角之和
                current[j] = prev.get(j - 1) + prev.get(j);
            }
            result.add(new ArrayList<>(Arrays.asList(current)));
        }
        return result;
    }
}
