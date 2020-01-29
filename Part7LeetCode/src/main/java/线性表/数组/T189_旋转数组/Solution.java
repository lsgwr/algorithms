/***********************************************************
 * @Description : 189.旋转数组
 * https://leetcode-cn.com/problems/rotate-array/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/29 18:42
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package 线性表.数组.T189_旋转数组;

/**
 * 在倒数第k个位置把数组拆分成两部分，对调下位置后再连接到一起即可
 */
class Solution {
    public void rotate(int[] nums, int k) {
        if (k == 0) {
            return;
        }
        int N = nums.length;
        k = k % N;
        int[] numsL = new int[k];
        int[] numsR = new int[N - k];
        for (int i = 0; i < N; i++) {
            if (i < N - k) {
                numsR[i] = nums[i];
            } else {
                numsL[i - (N - k)] = nums[i];
            }
        }
        for (int i = 0; i < N; i++) {
            if (i < k) {
                nums[i] = numsL[i];
            } else {
                nums[i] = numsR[i - k];
            }
        }
    }
}
