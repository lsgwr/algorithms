/***********************************************************
 * @Description : 
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/3/17 22:41
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package 第2章_贪心算法.第2节_最优装载问题;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    private int maxLoad(int[] w, int c) {
        // 按照古董重量排序
        Arrays.sort(w);
        // tmp为已经装载到船上的重量
        double tmp = 0.0;
        // ans为已装载的古董个数
        int ans = 0;
        for (int value : w) {
            tmp += value;
            if (tmp <= c) {
                ans++;
            } else {
                // 一旦超出容量了，立马退出循环
                break;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 船的载重量
        int c = sc.nextInt();
        // 古董个数
        int n = sc.nextInt();
        // 获取重量数组
        int[] w = new int[n];
        for (int i = 0; i < n; i++) {
            w[i] = sc.nextInt();
        }
        int ans = new Main().maxLoad(w, c);
        System.out.println("能装入的古董最大数量为：" + ans);
    }
}
/**
 * 输入：
 * 30 8
 * 4 10 7 11 3 5 14 2
 * <p>
 * 输出：
 * 能装入的古董最大数量为：5
 */
