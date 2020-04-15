/***********************************************************
 * @Description : 364. 加权嵌套序列和 II
 * https://leetcode-cn.com/problems/nested-list-weight-sum-ii/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/4/15 23:34
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C09_深度优先搜索.T364_加权嵌套序列和II;

import java.util.List;

class Solution {
    private int sum = 0;
    private int maxDepth = 0;
    public int depthSumInverse(List<NestedInteger> nestedList) {
        // 第一遍DFS获取最大深度
        dfs1(1, nestedList);
        // 第2遍，从最大权重往下递减
        dfs2(maxDepth, nestedList);
        return sum;
    }

    /**
     * 根据深度不断更新每层的和
     *
     * @param depth      当前递归的深度
     * @param nestedList 当前在遍历的嵌套数组
     */
    private void dfs1(int depth, List<NestedInteger> nestedList) {
        for (NestedInteger nestedInteger : nestedList) {
            if (nestedInteger.isInteger()) {
                // 一旦到了整数，就把和层次的加权成绩返回去
                if (depth > maxDepth){
                    maxDepth = depth;
                }
            } else {
                dfs1(depth + 1, nestedInteger.getList());
            }
        }
    }

    /**
     * 根据深度不断更新每层的和
     *
     * @param depth      当前递归的深度
     * @param nestedList 当前在遍历的嵌套数组
     */
    private void dfs2(int depth, List<NestedInteger> nestedList) {
        for (NestedInteger nestedInteger : nestedList) {
            if (nestedInteger.isInteger()) {
                // 一旦到了整数，就把和层次的加权成绩返回去
                sum += nestedInteger.getInteger() * depth;
            } else {
                dfs2(depth - 1, nestedInteger.getList());
            }
        }
    }
}
