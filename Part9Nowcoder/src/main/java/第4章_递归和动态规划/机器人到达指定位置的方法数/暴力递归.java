/***********************************************************
 * @Description : 机器人到达指定位置的方法数
 * https://blog.csdn.net/u012292754/article/details/90169610
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/2/5 11:06
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package 第4章_递归和动态规划.机器人到达指定位置的方法数;

public class 暴力递归 {
    /**
     * 只能在1~N的位置上移动，当前在cur位置上，走完rest步之后，停在P位置的方法数作为返回值返回
     *
     * @param N    位置在1~N，固定参数
     * @param cur  当前在cur位置，可变参数
     * @param rest 还剩rest步没走，可变参数
     * @param P    最终目标位置是p，固定参数
     * @return 停在P位置的方法数
     */
    private int walk(int N, int cur, int rest, int P) {
        if (rest == 0) {
            // 最终是在当前位置就返回从上个位置到P走了1步，否则返回0表示走不到P
            return cur == P ? 1 : 0;
        }
        if (cur == 1) {
            // 当前位置在1上，在开头的位置上，只能往后面的位置2走
            return walk(N, 2, rest - 1, P);
        }
        if (cur == N) {
            // 当前位置在N上，在结尾的位置上，只能往前面的位置N-1走
            return walk(N, N - 1, rest - 1, P);
        }
        // 在中间位置时，往前走还是往后走都行，方法数等于两者之和
        return walk(N, cur + 1, rest - 1, P) + walk(N, cur - 1, rest - 1, P);
    }

    /**
     * 机器人从位置M出发，经过走K步，正好能到达位置P的方法数，及其人只能在1~N的范围内走
     *
     * @param N 位置在1~N，固定参数
     * @param M 开始时机器人在的位置
     * @param K 机器人必须走地步数，不能多也不能少
     * @param P 最终目标位置是p，固定参数
     * @return 满足上述限制条件下能到达位置P的方法数
     */
    public int walkWays(int N, int M, int K, int P) {
        // 四个参数必须在合适的范围内
        if (N < 2 || K < 1 || M < 1 || M > N || P < 1 || P > N) {
            return 0;
        }
        return walk(N, M, K, P);
    }

    public static void main(String[] args) {
        int ways = new 暴力递归().walkWays(5, 2, 3, 3);
        // 结果是3
        System.out.println("满足条件地步数：" + ways);
    }
}
