/***********************************************************
 * @Description : 135.分发糖果
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/29 16:41
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C01_线性表.数组.T135_分发糖果;

import java.util.Arrays;

/**
 * 贪心算法：
 * 规则定义： 设学生A和学生B左右相邻，A在B左边；
 * 左规则： 当 ratings_B>ratings_A时，B的糖比A的糖数量多。
 * 右规则： 当 ratings_A>ratings_B​时，A的糖比B的糖数量多。
 * 解题思路：https://leetcode-cn.com/problems/candy/solution/candy-cong-zuo-zhi-you-cong-you-zhi-zuo-qu-zui-da-/
 */
class Solution {
    public int candy(int[] ratings) {
        int N = ratings.length;
        int[] left = new int[N];
        int[] right = new int[N];
        Arrays.fill(left, 1);
        Arrays.fill(right, 1);
        for (int i = 1; i < N; i++) {
            if (ratings[i] > ratings[i - 1]) {
                left[i] = left[i - 1] + 1;
            }
        }
        // 左遍历一次，最后一个元素肯定是定了，因为这个元素没有右边地可比较了
        int count = left[N - 1];
        for (int i = N - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                right[i] = right[i + 1] + 1;
            }
            count += Math.max(left[i], right[i]);
        }
        return count;
    }
}
