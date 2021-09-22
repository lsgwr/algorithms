/***********************************************************
 * @Description : T354_信封嵌套问题
 * https://leetcode-cn.com/problems/russian-doll-envelopes/
 * 参考第300号问题(最长上升子序列)，先把一个维度排序后，堆第二个维度排序即可
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/2/5 17:58
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C12_动态规划.T354_信封嵌套问题;

import java.util.Arrays;
import java.util.Comparator;

public class Solution {
    public int maxEnvelopes(int[][] envelopes) {
        int N = envelopes.length;
        if (N == 0) {
            return 0;
        }
        int[] LIS = new int[N];
        Arrays.fill(LIS, 1);
        // 按照长度进行排序，然后下面比较信封宽度即可！这步很关键！！
        Arrays.sort(envelopes, Comparator.comparingInt(o -> o[0]));
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < i; j++) {
                // i前面的j信封长和宽都要比i信封的小，说明是上升序列的一部分，加入到i处的LIS
                if (envelopes[j][0] < envelopes[i][0] && envelopes[j][1] < envelopes[i][1]) {
                    // 不断用更大的LIS值来更新i处的LIS
                    LIS[i] = Math.max(LIS[i], LIS[j] + 1);
                }
            }
        }
        int max = 0;
        for (int lis : LIS) {
            if (lis > max) {
                max = lis;
            }
        }
        return max;
    }
}
