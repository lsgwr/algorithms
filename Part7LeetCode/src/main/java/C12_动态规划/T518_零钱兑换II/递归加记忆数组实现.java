/***********************************************************
 * @Description : 518 零钱兑换II
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/2/5 15:57
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C12_动态规划.T518_零钱兑换II;

/**
 * 执行用时 : 41 ms , 在所有 Java 提交中击败了 9.52% 的用户 内存消耗 : 37 MB , 在所有 Java 提交中击败了 5.03% 的用户
 */
class 递归加记忆数组实现 {
    private int[][] memo;

    public int change(int amount, int[] coins) {
        if (coins.length == 0) {
            if(amount == 0){
                return 1;
            }else{
                return 0;
            }
        }
        memo = new int[coins.length + 1][amount + 1];
        return process(coins, 0, amount);
    }

    private int process(int[] coins, int index, int amount) {
        int result = 0;
        if (index == coins.length) {
            // 递归到最深一层了，如果剩余地amount为0正好
            result = amount == 0 ? 1 : 0;
        } else {
            for (int i = 0; coins[index] * i <= amount; i++) {
                // coins[index]不断累加个数
                int nextIndex = index + 1;
                int nextAmount = amount - coins[index] * i;
                int memoVal = memo[nextIndex][nextAmount];
                if (memoVal != 0) {
                    // 之前已经计算过这种状态了,直接累加即可
                    result += memoVal == -1 ? 0 : memoVal;
                } else {
                    // 没计算过地话就要好好计算下了
                    result += process(coins, nextIndex, nextAmount);
                }
            }
        }
        memo[index][amount] = result == 0 ? -1 : result;
        return result;
    }
}
