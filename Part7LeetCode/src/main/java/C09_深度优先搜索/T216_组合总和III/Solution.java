/***********************************************************
 * @Description : 216.组合总和III
 * https://leetcode-cn.com/problems/combination-sum-iii/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/31 17:21
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C09_深度优先搜索.T216_组合总和III;

import java.util.ArrayList;
import java.util.List;

class Solution {
    private void calCombinations(int[] nums, List<Integer> curList, int start, int target, List<List<Integer>> result, int k) {
        if (target < 0) {
            return;
        }
        if (target == 0) {
            if(curList.size() == k){
                result.add(new ArrayList<>(curList));
            }
            return;
        }
        // 从start开始是为了能重复使用start位置的元素
        for (int i = start; i < nums.length; i++) {
            curList.add(nums[i]);
            // 下一层递归还是用这些元素，通过索引来遍历子数组而不要额外建立空间
            calCombinations(nums, curList, i + 1, target - nums[i], result, k);
            // 递归退出就删除一个元素
            curList.remove(curList.size() - 1);
        }
    }

    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> curList = new ArrayList<>();
        int[] nums = {1,2,3,4,5,6,7,8,9};
        calCombinations(nums, curList, 0, n, result, k);
        return result;
    }
}
