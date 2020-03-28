/***********************************************************
 * @Description : 会议安排
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/3/28 21:59
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package 第2章_贪心算法.第4节_会议安排;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Solution {
    static class Meet {
        public Meet() {
        }

        public Meet(int beg, int end, int num) {
            this.beg = beg;
            this.end = end;
            this.num = num;
        }

        // 会议开始时间
        int beg;
        // 会议结束时间
        int end;
        // 会议编号
        int num;
    }

    /**
     * 会议安排的最大数目
     *
     * @param meets 输入的会议列表
     * @return 最大的安排的会议的数目
     */
    public int maxMeetingsArrange(Meet[] meets) {
        // 1.对会议按照结束时间排序
        Arrays.sort(meets, new Comparator<Meet>() {
            @Override
            public int compare(Meet meet1, Meet meet2) {
                if (meet1.end == meet2.end) {
                    return meet2.beg - meet1.beg;
                }
                return meet1.end - meet2.end;
            }
        });
        System.out.println("排完续的会议时间如下：");
        System.out.println("会议编号  开始时间  结束时间");
        for (Meet meet : meets) {
            System.out.println("  " + meet.num + "\t\t\t" + meet.beg + "\t\t\t" + meet.end);
        }

        // 2.贪心算法
        System.out.println("-----------------选择会议的过程如下：--------------------");
        // 上来先选中第1个会议
        System.out.println("选中第" + meets[0].num + "个会议");
        // 贪心算法选择后面的会议
        int result = 1;
        // 第一个会议的结束时间，方便和后面的会议比较防止会议重叠
        int last = meets[0].end;
        for (int i = 1; i < meets.length; i++) {
            if (meets[i].beg >= last) {
                // 贪心算法选择后面的会议：如果会议i开始时间大于等于上一个选中的会议的结束时间
                result++;
                last = meets[i].end;
                System.out.println(" 选择第" + meets[i].num + "个会议");
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 要输入的会议总数
        int n = sc.nextInt();
        // 声明n个会议的数组
        Meet[] meets = new Meet[n];
        for (int i = 0; i < n; i++) {
            meets[i] = new Meet(sc.nextInt(), sc.nextInt(), i + 1);
        }
        int maxMeetingsArrange = new Solution().maxMeetingsArrange(meets);
        System.out.println("最多可以安排" + maxMeetingsArrange + "个会议");
    }
}
/**
输入如下：
10
3 6
1 4
5 7
2 5
5 9
3 8
8 11
6 10
8 12
12 14

输出如下：
 排完续的会议时间如下：
 会议编号  开始时间  结束时间
 2			1			4
 4			2			5
 1			3			6
 3			5			7
 6			3			8
 5			5			9
 8			6			10
 7			8			11
 9			8			12
 10			12			14
 -----------------选择会议的过程如下：--------------------
 选中第2个会议
 选择第3个会议
 选择第7个会议
 选择第10个会议
 最多可以安排4个会议
 **/
