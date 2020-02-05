/***********************************************************
 * @Description : 518 零钱兑换II
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/2/5 15:57
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C12_动态规划.T518_零钱兑换II;

/**
 * 超时了
 */
class 暴力递归实现 {
    public int change(int amount, int[] coins) {
        if (coins.length == 0 || amount < 0) {
            return 0;
        }
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
                result += process(coins, index + 1, amount - coins[index] * i);
            }
        }
        return result;
    }
}
