/***********************************************************
 * @Description : 
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/3/29 20:32
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package 第4章_动态规划.第5节_游艇租赁;

import java.util.Scanner;

public class Main {
    private final int ms = 1000;
    /**
     * r[i][j]、m[i][j]、s[i][j]表示站i到站j的租金,
     */
    private int[][] r = new int[ms][ms];
    private int[][] m = new int[ms][ms];
    private int[][] s = new int[ms][ms];

    /**
     * 站点数
     */
    private int n;

    /**
     * 计算从起点到终点的最少的租金
     */
    public int rent() {
        Scanner sc = new Scanner(System.in);
        // 站点的个数
        int n = sc.nextInt();
        // 输入租金
        for (int i = 1; i <= n; i++) {
            for (int j = i + 1; j <= n; j++) {
                r[i][j] = sc.nextInt();
                m[i][j] = r[i][j];
            }
        }

        // 动态规划求最少租金
        for (int d = 3; d <= n; d++) {
            // 将问题分为小规模为d
            for (int i = 1; i <= n - d + 1; i++) {
                int j = i + d - 1;
                for (int k = i + 1; k < j; k++) {
                    // 记录每一个小规模内的最优解
                    int tmp = m[i][k] + m[k][j];
                    if (tmp < m[i][j]) {
                        m[i][j] = tmp;
                        s[i][j] = k;
                    }
                }
            }
        }
        System.out.print("最少的租金经过的站点为：1");
        // 打印详细的租赁过程
        print(1, n);
        System.out.println();
        return m[1][n];
    }

    void print(int i, int j) {
        if (s[i][j] == 0) {
            System.out.print("--" + j);
            return;
        }
        print(i, s[i][j]);
        print(s[i][j], j);
    }

    public static void main(String[] args) {
        Main main = new Main();
        // 最小租金
        int minRent = main.rent();
        // 详细的租金方案
        System.out.println("最少的租金为：" + minRent);
    }
}
/**
 * 最少的租金经过的站点为：1--2--4--6
 * 最少的租金为：15
 */
