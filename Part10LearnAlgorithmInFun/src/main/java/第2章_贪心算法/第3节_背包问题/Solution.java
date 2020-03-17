/***********************************************************
 * @Description : 背包问题
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/3/17 23:24
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package 第2章_贪心算法.第3节_背包问题;

import java.util.*;

public class Solution {

    /**
     * 宝物对象
     */
    private static class Treasure {
        /**
         * 宝物重量
         */
        double weight;
        /**
         * 宝物的价值
         */
        double value;

        /**
         * 性价比：cost performance
         */
        double cp;
    }

    /**
     * 计算阿里巴巴最多能拿地宝物价值
     *
     * @param treasureList 宝物对象列表
     * @param m            毛驴的重量承载上限
     * @return 最大的价值
     */
    private double takeMostTreasure(List<Treasure> treasureList, double m) {
        // 按照性价比进行排序
        treasureList.sort((o1, o2) -> Double.compare(o2.cp - o1.cp, 0.0));
        double sum = 0.0;
        for (Treasure treasure : treasureList) {
            if (m > treasure.weight) {
                m -= treasure.weight;
                sum += treasure.value;
            } else {
                // 如果宝物的重量大于毛驴剩下地承载能力。cp表示单位重量的价值即性价比
                sum += m * treasure.cp;
                break;
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 宝物数量
        int n = sc.nextInt();
        // 毛驴的承载能力
        double m = sc.nextDouble();
        // 下面读入每个获取的重量和价值，并计算性价比
        List<Treasure> treasureList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            Treasure treasure = new Treasure();
            treasure.weight = sc.nextDouble();
            treasure.value = sc.nextDouble();
            // 计算性价比
            treasure.cp = treasure.value / treasure.weight;
            treasureList.add(treasure);
        }
        double sum = new Solution().takeMostTreasure(treasureList, m);
        System.out.println("装入宝物的最大价值是：" + sum);
    }
}
