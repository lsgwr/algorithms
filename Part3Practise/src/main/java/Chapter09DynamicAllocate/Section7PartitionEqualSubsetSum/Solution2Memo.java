/***********************************************************
 * @Description : 416.分割等和子集 递归+记忆数组解决
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2019/8/25 23:22
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter09DynamicAllocate.Section7PartitionEqualSubsetSum;


public class Solution2Memo {
    /**
     * -1表示未计算；0表示不可以填充；1表示可以填充
     */
    public int[][] memo;

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

        if (memo[index][sum] != -1) {
            // 说明点已经被访问了
            return memo[index][sum] == 1;
        }

        // 具体的逻辑
        boolean partition = tryPartition(nums, index - 1, sum) || tryPartition(nums, index - 1, sum - nums[index]);
        memo[index][sum] = partition ? 1 : 0;
        return memo[index][sum] == 1;
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

        memo = new int[nums.length][sum / 2 + 1];
        // 初始化这个二维数组
        for (int i = 0; i < memo.length; i++) {
            for (int j = 0; j < memo[i].length; j++) {
                memo[i][j] = -1;
            }
        }

        return tryPartition(nums, nums.length - 1, sum / 2);
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 5, 11, 5};
        boolean canPartition1 = new Solution2Memo().canPartition(nums1);
        System.out.println(canPartition1);

        int[] nums2 = {1, 2, 3, 5};
        boolean canPartition2 = new Solution2Memo().canPartition(nums2);
        System.out.println(canPartition2);
    }
}
