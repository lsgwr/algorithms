package Chapter10GreedyAlgorithms.Section01AssignCookies;

import java.util.Arrays;

/***********************************************************
 * @note      : 分饼干问题
 * @author    : l00379880 梁山广
 * @version   : V1.0 at 2019/8/26 16:20
 ***********************************************************/


public class Solution {
    // 数组逆序
    public static void reverse(int[] arr) {
        // 数组逆序
        int temp = 0;
        for (int min = 0, max = arr.length - 1; min < max; min++, max--) {
            temp = arr[min];
            arr[min] = arr[max];
            arr[max] = temp;
        }

    }

    public int findContentChildren(int[] g, int[] s) {
        // 从大到小进行排序
        Arrays.sort(g);
        Arrays.sort(s);
        reverse(g);
        reverse(s);
        // source存储资源的数组，这里是指存储饼干信息的数组
        int si = 0;
        // 贪心指数，这里值小朋友要多少饼干才能心满意足
        int gi = 0;
        int result = 0;
        while (gi < g.length && si < s.length) {
            if (s[si] >= g[gi]) { // 最大的饼干分给最贪心的孩子
                result++;
                si++;
                gi++;
            } else {
                // 最贪心的小朋友分配不了就分配给次贪心的小朋友
                gi++;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] g1 = {1, 2, 3}, s1 = {1, 1};
        int satisfyChildren1 = new Solution().findContentChildren(g1, s1);
        System.out.println(satisfyChildren1);

        int[] g2 = {1,2}, s2 = {1,2,3};
        int satisfyChildren2 = new Solution().findContentChildren(g2, s2);
        System.out.println(satisfyChildren2);
    }
}
