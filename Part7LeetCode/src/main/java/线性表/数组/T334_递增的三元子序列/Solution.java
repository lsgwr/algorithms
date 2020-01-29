/***********************************************************
 * @Description : 334.递增的三元子序列
 * https://leetcode-cn.com/problems/increasing-triplet-subsequence/
 * 执行用时 : 1 ms , 在所有 Java 提交中击败了 95.71% 的用户 内存消耗 : 38.8 MB , 在所有 Java 提交中击败了 24.81% 的用户
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/29 20:39
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package 线性表.数组.T334_递增的三元子序列;

/**
 * 扫描一遍数组， 用变量 x1 保存当前最小的值， 变量 x2 保存当前第二小的值。
 * 如果右面能碰到一个数大于 x2 ， 说明必然存在一个递增的三元组
 */
class Solution {
    public boolean increasingTriplet(int[] nums) {
        int min1 = Integer.MAX_VALUE;
        int min2 = Integer.MAX_VALUE;
        for (int num : nums) {
            if (num <= min1) {
                min1 = num;
            } else if (num <= min2) {
                min2 = num;
            } else {
                return true;
            }
        }
        return false;
    }
}
