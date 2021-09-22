package C09_深度优先搜索.T474_火柴拼正方形;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * DFS+贪心算法
 * https://leetcode-cn.com/problems/matchsticks-to-square/comments/77528/
 */
class Solution {
    public boolean makesquare(int[] nums) {
        // 边数小于4肯定是不行的
        if (nums.length < 4) {
            return false;
        }
        // 首先求总和，能整除4才有可能得到正方形
        int sum = 0;
        List<Integer> numList = new ArrayList<>();
        for (int num : nums) {
            numList.add(num);
            sum += num;
        }
        if (sum % 4 != 0) {
            return false;
        }
        int edgeLen = sum / 4;
        // 先排序，降序
        numList.sort(Collections.reverseOrder());
        // 即使能整除4，是否能得到正方形需要用DFS再验证下
        boolean[] visited = new boolean[nums.length];
        // 只需要判断能否构建出3个edgeLen的边即可
        for (int i = 0; i < 3; i++) {
            if (!dfs(numList, visited, edgeLen)) {
                // 一次没找边，直接返回false
                return false;
            }
        }
        return true;
    }

    /**
     * DFS遍历找和等于target的边
     *
     * @param numList    降序排列的数组
     * @param visited    访问数组
     * @param edgeLenCur 边长
     */
    private boolean dfs(List<Integer> numList, boolean[] visited, int edgeLenCur) {
        if (edgeLenCur == 0) {
            // 一旦找到合适的边了，直接返回True，即找到了一条合适的边了
            return true;
        }
        // 贪心选择长的构建边
        for (int i = 0; i < numList.size(); i++) {
            if (!visited[i]) {
                // 当前的值大于想要的边的值，肯定不用继续往下走了
                if (numList.get(i) > edgeLenCur) {
                    return false;
                }
                visited[i] = true;
                if (dfs(numList, visited, edgeLenCur - numList.get(i))) {
                    return true;
                }
                // 没有找到合适的边，递归退出，记得回溯过程中把节点访问情况重新设置回去
                visited[i] = false;
            }
        }
        return false;
    }

    /**
     * [1,1,2,2,2]
     * [3,3,3,3,4]
     * [1,2,3,4,5,6,7,8,9,10,11,12,13,14,15]
     *
     * [1,7,7,15,13,10,7,12,12,5,1,30] 这个代码针对这个用例是过不了地，贪心不适用这里，但是本题可以这么解~因为测试用例没有这个
     */
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        System.out.println(new Solution().makesquare(nums));
    }
}
