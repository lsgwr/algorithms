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
import java.util.List;

public class Solution {
    public List<List<Integer>> findSubsequences(int[] nums) {
        List<List<Integer>> resultAll = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            List<Integer> result = new ArrayList<>();
            dfs(i, i, nums, result, resultAll);
        }
        return resultAll;
    }

    /**
     * DFS遍历到的坐标
     *
     * @param i         当前遍历到的点的下标
     * @param last      当前节点的上一个节点
     * @param nums      原始数组
     * @param result    当前的路径
     * @param resultAll 最终的结果
     */
    private void dfs(int i, int last, int[] nums, List<Integer> result, List<List<Integer>> resultAll) {
        // 能进来先把点加入进去
        if (i == last) {
            result.add(nums[i]);
        } else {
            if (nums[i] >= nums[last]) {
                result.add(nums[i]);
            }
        }
        if (result.size() > 1) {
            // 递增长度为2
            if (!resultAll.contains(result)) {
                // 把当前的递增序列加进去
                resultAll.add(new ArrayList<>(result));
            }
        }
        // i后面所有的点都作为邻接点继续往下进行dfs
        for (int j = i + 1; j < nums.length; j++) {
            dfs(j, i, nums, result, resultAll);
            if (result.size() >0){
                // DFS退出要删除最后一个元素
                result.remove(result.size() - 1);
            }
        }
    }

    /**
     * {4, 6, 7, 7}
     * {4, 3, 2, 1}
     */
    public static void main(String[] args) {
        int[] nums = {4, 3, 2, 1};
        System.out.println(new Solution().findSubsequences(nums));
    }
}
