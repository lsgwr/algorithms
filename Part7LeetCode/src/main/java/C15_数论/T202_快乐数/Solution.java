/***********************************************************
 * @Description : 202.快乐数
 * https://leetcode-cn.com/problems/happy-number/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/31 22:29
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C15_数论.T202_快乐数;

import java.util.HashSet;
import java.util.Set;

/**
 * Happy Number
 * Time complexity: ?, Space complexity: ?
 */
public class Solution {
    public boolean isHappy(int n) {
        final Set<Integer> existed = new HashSet<>();
        while (true) {
            int sum = 0;
            while (n > 0) {
                int digit = n % 10;
                sum += digit * digit;
                n /= 10;
            }
            if (existed.contains(sum)) {
                return sum == 1;
            } else {
                existed.add(sum);
                n = sum;
            }
        }
    }
}
