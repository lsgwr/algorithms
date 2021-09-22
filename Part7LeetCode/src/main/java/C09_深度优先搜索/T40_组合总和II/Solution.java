/***********************************************************
 * @Description : 40.组合总和II
 * https://leetcode-cn.com/problems/combination-sum-ii/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/31 17:20
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C09_深度优先搜索.T40_组合总和II;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    private void calCombinations(int[] nums, List<Integer> curList, int start, int target, List<List<Integer>> result) {
        if (target < 0) {
            return;
        }
        if (target == 0) {
            if (!result.contains(curList)) {
                result.add(new ArrayList<>(curList));
            }
            return;
        }
        // 从start开始是为了能重复使用start位置的元素
        for (int i = start; i < nums.length; i++) {
            curList.add(nums[i]);
            // 下一层递归还是用这些元素，通过索引来遍历子数组而不要额外建立空间
            calCombinations(nums, curList, i + 1, target - nums[i], result);
            // 递归退出就删除一个元素
            curList.remove(curList.size() - 1);
        }
    }

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        // 排序后输出结果比较统一
        Arrays.sort(candidates);
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> curList = new ArrayList<>();
        calCombinations(candidates, curList, 0, target, result);
        return result;
    }
}
