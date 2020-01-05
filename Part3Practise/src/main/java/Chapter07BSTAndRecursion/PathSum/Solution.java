package Chapter07BSTAndRecursion.PathSum;

/***********************************************************
 * @note      : https://leetcode-cn.com/problems/path-sum/  112. 路径总和
 *
 * 给定一个二叉树和一个目标和，判断该树中是否存在根节点到叶子节点的路径，这条路径上所有节点值相加等于目标和。
 *
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 示例: 
 * 给定如下二叉树，以及目标和 sum = 22，
 *
 *               5
 *              / \
 *             4   8
 *            /   / \
 *           11  13  4
 *          /  \      \
 *         7    2      1
 * 返回 true, 因为存在目标和为 22 的根节点到叶子节点的路径 5->4->11->2。
 * !!!注意是叶子节点
 *
 * @author    : l00379880 梁山广
 * @version   : V1.0 at 2019/8/22 18:58
 ***********************************************************/

import Chapter07BSTAndRecursion.TreeNode;

class Solution {
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        System.out.println(root.val);

        if (root.left == null && root.right == null) {
            // 注意题目中有说路径终点必须是叶子节点
            return root.val == sum;
        }

        // 总和减去当前节点的值，然后看当前节点是否存在子树的节点和等于前面的新sum
        if (hasPathSum(root.left, sum - root.val)) {
            return true;
        }
        if (hasPathSum(root.right, sum - root.val)) {
            return true;
        }

        // 两侧都没找到返回false
        return false;
    }
}
