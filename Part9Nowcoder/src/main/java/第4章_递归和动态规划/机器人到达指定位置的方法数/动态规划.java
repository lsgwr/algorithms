/***********************************************************
 * @Description : 机器人到达指定位置的方法数
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/2/5 11:48
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package 第4章_递归和动态规划.机器人到达指定位置的方法数;

public class 动态规划 {
    /**
     * 机器人从位置M出发，经过走K步，正好能到达位置P的方法数，及其人只能在1~N的范围内走
     *
     * @param N 位置在1~N，固定参数
     * @param M 开始时机器人在的位置,M的范围为[1,N]
     * @param K 机器人必须走地步数，不能多也不能少
     * @param P 最终目标位置是p，固定参数
     * @return 满足上述限制条件下能到达位置P的方法数
     */
    public int walkWays(int N, int M, int K, int P) {
        // 四个参数必须在合适的范围内
        if (N < 2 || K < 1 || M < 1 || M > N || P < 1 || P > N) {
            return 0;
        }
        // d[i][j]表示只走i步到达位置j的方法数
        int[][] dp = new int[K + 1][N + 1];
        // 只走0步到达位置P的方法肯定只有一种，即起点就是M就是P
        dp[0][P] = 1;
        for (int i = 1; i <= K; i++) {
            for (int j = 1; j <= N; j++) {
                if (j == 1) {
                    // 目标位置在最左侧，方法数就等于最左侧位置右侧的2的方法数(走i-1步到达2位置，再走一步就到达1了)
                    dp[i][j] = dp[i - 1][2];
                } else if (j == N) {
                    // 目标位置在最右侧，方法数就等于最右侧左边的相邻定点的方法数(走i-1步到达N-1位置，再走一步就到达N了)
                    dp[i][j] = dp[i - 1][N - 1];
                } else {
                    // 目标位置在中间，那么向两边拓展，等于走i-1步左右两个相邻节点，再走一步就到达位置j了，方法数就等于到达这两个位置的方法数总和
                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j + 1];
                }
            }
        }
        return dp[K][M];
    }

    public static void main(String[] args) {
        int ways = new 动态规划().walkWays(5, 2, 3, 3);
        // 结果是3
        System.out.println("满足条件地步数：" + ways);
    }
}
