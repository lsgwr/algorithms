/***********************************************************
 * @Description : 491. 递增子序列
 * https://leetcode-cn.com/problems/increasing-subsequences/
 *
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/4/19 16:36
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C09_深度优先搜索.T491_递增子序列;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution {
    public List<List<Integer>> findSubsequences(int[] nums) {
        List<List<Integer>> resultAll = new ArrayList<>();
        List<Integer> result = new ArrayList<>();
        dfs(0, nums, result, resultAll);
        return resultAll;
    }

    /**
     * DFS遍历到的坐标
     *
     * @param cur       DFS遍历到的节点
     * @param nums      原始数组
     * @param result    当前的路径
     * @param resultAll 最终的结果
     */
    private void dfs(int cur, int[] nums, List<Integer> result, List<List<Integer>> resultAll) {
        if (result.size() > 1) {
            // 把当前的递增序列加进去
            resultAll.add(new ArrayList<>(result));
        }
        Set<Integer> set = new HashSet<>();
        // i后面所有的点都作为邻接点继续往下进行dfs
        for (int i = cur; i < nums.length; i++) {
            if (set.contains(nums[i])) {
                // 在同一序列上相同的数字往后都是一样的，不用再重新加入了
                continue;
            }
            if (result.size() == 0 || nums[i] >= result.get(result.size() - 1)) {
                set.add(nums[i]);
                result.add(nums[i]);
                dfs(i + 1, nums, result, resultAll);
                result.remove(result.size() - 1);
            }
        }
    }

    /**
     * {4, 6, 7, 7}
     * {4, 3, 2, 1}
     */
    public static void main(String[] args) {
        int[] nums = {4, 6, 7, 7};
        System.out.println(new Solution().findSubsequences(nums));
    }
}
