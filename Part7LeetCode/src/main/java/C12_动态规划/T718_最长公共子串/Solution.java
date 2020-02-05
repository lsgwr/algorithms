/***********************************************************
 * @Description : T718_最长公共子串/子数组，和第1143号问题的思想类似
 * https://leetcode-cn.com/problems/maximum-length-of-repeated-subarray/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/2/5 22:28
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C12_动态规划.T718_最长公共子串;

public class Solution {
    public int findLength(int[] A, int[] B) {
        int M = A.length;
        int N = B.length;
        if (M == 0 || N == 0) {
            return 0;
        }
        // dp[i][j]表示令A[i]和B[j]作为A[0...i]和B[0...j]的最长子串的最后一个元素时，最长子序列的长度
        int[][] dp = new int[M][N];
        // 第1行赋值，表示A[0...i]和B[0]的最长子串，如果A[i]==B[0]则dp[i][0]为1(最长子串在只有B[0]下最长也就为1了)，否则为0
        for (int i = 0; i < M; i++) {
            // 注意这里下标从0开始，下面地从1开始，dp[0][0]不要单独赋值，要循环一行或一列才能得出判断
            if (A[i] == B[0]) {
                dp[i][0] = 1;
            }
        }
        // 第1列赋值，表示A[0]和B[0...i]的最长子串，如果A[0]==B[i]则dp[0][i]为1(最长子串在只有A[0]下最长也就为1了)，否则为0
        for (int j = 0; j < N; j++) {
            // 注意这里下标从0开始，下面地从1开始，dp[0][0]不要单独赋值，要循环一行或一列才能得出判断
            if (A[0] == B[j]) {
                dp[0][j] = 1;
            }
        }
        // 非第1行和第1列的内部元素，即A[0...i]和B[0...j]，如果各自最后一个元素A[i]==B[j]两者才可能有公共子串(取上一步A[0...i-1]和B[0...j-1]的和加1)，否则为0
        for (int i = 1; i < M; i++) {
            for (int j = 1; j < N; j++) {
                if (A[i] == B[j]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }
            }
        }

        int max = 0;
        // 从dp中找到最大值
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (dp[i][j] > max){
                    max = dp[i][j];
                }
            }
        }
        // 这里是返回最大值，而不是最后一个值dp[M-1][N-1]
        return max;
    }

    public static void main(String[] args) {
        int[] A = {1, 2, 3, 2, 1};
        int[] B = {3, 2, 1, 4, 7};
        int length = new Solution().findLength(A, B);
        System.out.println(length);
    }
}
