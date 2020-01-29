/***********************************************************
 * @Description : 134.加油站
 * https://leetcode-cn.com/problems/gas-station/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/29 16:21
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package 线性表.数组.T134_加油站;

class Solution {
    /**
     * O(N) 的解法是， 设置两个变量， sum 判断当前的指针的有效性； total 则判断整个数组是否有解， 有
     * 就返回通过 sum 得到的下标， 没有则返回-1
     *
     * @param gas  其中第 i 个加油站有汽油 gas[i] 升
     * @param cost 第 i 个加油站开往第 i+1 个加油站需要消耗汽油 cost[i] 升
     * @return 如果你可以绕环路行驶一周，则返回出发时加油站的编号，否则返回 -1
     */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int total = 0;
        int j = -1;
        int sum = 0;
        for (int i = 0; i < gas.length; i++) {
            sum += gas[i] - cost[i];
            total += gas[i] - cost[i];
            if (sum < 0) {
                // 找到第一个sum为负的路径，若整体有解地话(最终的total>=0)，则sum往后的油耗比必然为正
                j = i;
                sum = 0;
            }
        }
        return total >= 0 ? j + 1 : -1;
    }
}
