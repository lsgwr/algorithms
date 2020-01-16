/***********************************************************
 * @Description : LeetCode第15号问题：三数之和
 * https://leetcode-cn.com/problems/3sum/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/16 22:31
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter03.ThreeSum;

import java.util.*;

class Solution {
    /**
     * 这里的Two Sum和LeetCode第一题不同，这里一个target可能对应多个结果
     *
     * @param nums   要检索的数组
     * @param target 要求的目标值
     * @return 符合条件的下标对
     */
    public List<int[]> twoSum(int[] nums, int target) {
        List<int[]> resultList = new ArrayList<>();
        // 值和索引组成Map
        Map<Integer, Integer> mapValIndex = new HashMap<>();
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            Integer index = mapValIndex.get(target - nums[i]);
            if (index != null) {
                // 找到了一个满足条件地
                resultList.add(new int[]{index, i});
            }
            // 加入当前节点
            mapValIndex.put(nums[i], i);
        }
        return resultList;
    }

    /**
     * 三个数字之和
     *
     * @param nums 数组
     * @return 所有满足三个数之和的元素对
     */
    public List<List<Integer>> threeSum(int[] nums) {
        // 这个排序很重要！！可能省掉下面大量的Collections.sort()
        Arrays.sort(nums);
        Set<List<Integer>> result = new HashSet<>();
        if (nums.length < 3) {
            return new ArrayList<>();
        }
        int len = nums.length;
        for (int i = 2; i < len; i++) {
            // 时间优化
            if (nums[i] == nums[i - 1] && nums[i] == nums[i - 2]) {
                if (nums[i] == 0) {
                    // 把三个0加进去
                    result.add(new ArrayList<>(Arrays.asList(0, 0, 0)));
                }
                // 三个连续相等地，第3个开始就不用看了
                continue;
            }
            // 注意twoSum返回地是nums的索引下标
            List<int[]> twoSumResultList = twoSum(Arrays.copyOfRange(nums, 0, i), -nums[i]);
            for (int[] twoSumResult : twoSumResultList) {
                // 找到的两个索引不能和当前索引相等
                if (twoSumResult.length == 2) {
                    // 这个是set，会自动去重
                    result.add(Arrays.asList(nums[twoSumResult[0]], nums[twoSumResult[1]], nums[i]));
                }
            }
        }
        return new ArrayList<>(result);
    }

    /**
     * 输出顺序不重要，结果对就行~~
     * nums = {-1, 0, 1, 2, -1, -4}   ===> [ [-1, 0, 1], [-1, -1, 2]]
     * {-4, -2, -2, -2, 0, 1, 2, 2, 2, 3, 3, 4, 4, 6, 6}  ==> [[-4,-2,6],[-4,0,4],[-4,1,3],[-4,2,2],[-2,-2,4],[-2,0,2]]
     */
    public static void main(String[] args) {
        int[] nums = {-4, -2, -2, -2, 0, 1, 2, 2, 2, 3, 3, 4, 4, 6, 6};
        List<List<Integer>> lists = new Solution().threeSum(nums);
        System.out.println(lists);
    }
}
