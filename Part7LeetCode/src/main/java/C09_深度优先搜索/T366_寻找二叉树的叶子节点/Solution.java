/***********************************************************
 * @Description : 366. 寻找二叉树的叶子节点
 * https://leetcode-cn.com/problems/find-leaves-of-binary-tree/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/4/16 0:01
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C09_深度优先搜索.T366_寻找二叉树的叶子节点;

import C04_二叉树.TreeNode;

import java.util.ArrayList;
import java.util.List;

class Solution {
    /**
     * DFS回溯过程中删除节点
     *
     * @param root 根节点
     * @return 每轮删除的根节点
     */
    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> leafsList = new ArrayList<>();
        while (root != null) {
            // 只要root不为null，就一直往下进行
            List<Integer> leafs = new ArrayList<>();
            dfs(root, leafs);
            leafsList.add(leafs);
        }
        return leafsList;
    }

    private void dfs(TreeNode root, List<Integer> leafs) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            // 到了叶子节点
            leafs.add(root.val);
            // 删除当前叶子节点
            root = null;
            return;
        }
        dfs(root.left, leafs);
        dfs(root.right, leafs);
    }
}
