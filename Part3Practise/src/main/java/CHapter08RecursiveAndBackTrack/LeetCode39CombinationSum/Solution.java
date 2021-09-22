/***********************************************************
 * @Description : 39.组合总和
 * https://leetcode-cn.com/problems/combination-sum/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/24 22:59
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package CHapter08RecursiveAndBackTrack.LeetCode39CombinationSum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {

    private void calCombinations(int[] nums, List<Integer> curList, int start, int target, List<List<Integer>> result) {
        if (target < 0) {
            return;
        }
        if (target == 0) {
            result.add(new ArrayList<>(curList));
            return;
        }
        // 从start开始是为了能重复使用start位置的元素
        for (int i = start; i < nums.length; i++) {
            curList.add(nums[i]);
            // 下一层递归还是用这些元素，通过索引来遍历子数组而不要额外建立空间
            calCombinations(nums, curList, i, target - nums[i], result);
            // 递归退出就删除一个元素
            curList.remove(curList.size() - 1);
        }
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        // 排序后输出结果比较统一
        Arrays.sort(candidates);
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> curList = new ArrayList<>();
        calCombinations(candidates, curList, 0, target, result);
        return result;
    }

    /**
     * [[2, 2, 3], [2, 5], [7]]   ==>  [[2, 2, 3], [2, 5], [7]]
     */
    public static void main(String[] args) {
        int[] candidates = {3, 2, 5};
        int target = 8;
        System.out.println(new Solution().combinationSum(candidates, target));
    }
}
