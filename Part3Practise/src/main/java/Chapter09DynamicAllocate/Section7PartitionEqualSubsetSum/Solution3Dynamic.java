/***********************************************************
 * @Description : 416.分割等和子集 动态规划解决
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2019/8/25 23:40
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter09DynamicAllocate.Section7PartitionEqualSubsetSum;


import java.util.Arrays;

public class Solution3Dynamic {

    /**
     * nums[i]表示第i个物体的重量
     *
     * @param nums 存放物体重量的数组
     * @return 是否存在等和子集
     */
    public boolean canPartition(int[] nums) {

        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            assert nums[i] > 0;
            sum += nums[i];
        }
        // 能整除2才能分成两个相等的子集
        if (sum % 2 != 0) {
            return false;
        }
        // 数组长度
        int n = nums.length;
        // 背包的容量，除以2是因为要看是否可以划分为两个相等的子集
        int C = sum / 2;
        boolean[] memo = new boolean[C + 1];
        // 数组初始化为false
        Arrays.fill(memo, false);
        for (int i = 0; i < C + 1; i++) {
            memo[i] = (nums[0] == i);
        }

        // 背包问题的核心过程
        for (int i = 0; i < n; i++) { // i代表物体编号
            for (int j = C; j >= nums[i]; j--) { // j代表当前背包的容量
                memo[j] = memo[j] || memo[j - nums[i]];
            }
        }

        return memo[C];
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 5, 11, 5};
        boolean canPartition1 = new Solution3Dynamic().canPartition(nums1);
        System.out.println(canPartition1);

        int[] nums2 = {1, 2, 3, 5};
        boolean canPartition2 = new Solution3Dynamic().canPartition(nums2);
        System.out.println(canPartition2);
    }
}
