/***********************************************************
 * @Description : 238.除自身以外数组的乘积
 * https://leetcode-cn.com/problems/product-of-array-except-self/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/29 19:46
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C01_线性表.数组.T238_除自身以外数组的乘积;

/**
 * 我们以一个4个元素的数组为例， nums=[a1,a2,a3,a4] ， 要想在 O(n) 的时间内输出结果， 比较好的解
 * 决方法是提前构造好两个数组：
 * <p>
 * [1, a1, a1*a2, a1*a2*a3]
 * [a2*a3*a4, a3*a4, a4, 1]
 * <p>
 * 然后两个数组一一对应相乘， 即可得到最终结果 [a2*a3*a4, a1*a3*a4, a1*a2*a4, a1*a2*a3]
 */
class Solution {
    public int[] productExceptSelf(int[] nums) {
        int N = nums.length;
        int[] result = new int[N];
        int[] left = new int[N];
        int[] right = new int[N];
        // 下面两行初始化时必须地~~
        left[0] = 1;
        right[N - 1] = 1;
        for (int i = 1; i < N; i++) {
            left[i] = left[i - 1] * nums[i - 1];
        }
        for (int i = N - 2; i >= 0; i--) {
            right[i] = right[i + 1] * nums[i + 1];
        }
        for (int i = 0; i < N; i++) {
            result[i] = left[i] * right[i];
        }
        return result;
    }
}
