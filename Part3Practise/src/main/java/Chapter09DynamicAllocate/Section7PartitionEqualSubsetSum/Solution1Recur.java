/***********************************************************
 * @Description : 416.分割等和子集 递归解决
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2019/8/25 12:41
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter09DynamicAllocate.Section7PartitionEqualSubsetSum;


public class Solution1Recur {
    /**
     * 使用nums[0...index],是否可以完全填充一个容量为sum的背包
     *
     * @param nums  用于填充的数组
     * @param index 填充的范围
     * @param sum   当前背包的剩余容量
     * @return
     */
    private boolean tryPartition(int[] nums, int index, int sum) {
        if (sum == 0) {
            // 背包已经没容量了，说明填充好了，返回true即可
            return true;
        }

        if (sum < 0 || index < 0) {
            // 背包装不下了(sum<0)或者已经没物品可选(index<0)
            return false;
        }

        // 具体的逻辑
        return tryPartition(nums, index - 1, sum) || tryPartition(nums, index - 1, sum - nums[index]);
    }

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
        return tryPartition(nums, nums.length - 1, sum / 2);
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 5, 11, 5};
        boolean canPartition1 = new Solution1Recur().canPartition(nums1);
        System.out.println(canPartition1);

        int[] nums2 = {1, 2, 3, 5};
        boolean canPartition2 = new Solution1Recur().canPartition(nums2);
        System.out.println(canPartition2);
    }
}
