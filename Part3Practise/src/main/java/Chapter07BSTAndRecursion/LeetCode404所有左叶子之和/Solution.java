/***********************************************************
 * @Description : 404.所有左叶子节点之和
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/22 0:09
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter07BSTAndRecursion.LeetCode404所有左叶子之和;

import Chapter07BSTAndRecursion.TreeNode;

class Solution {
    public int sumOfLeftLeaves(TreeNode root) {
        // 递归具体逻辑
        return sumOfLeftLeaves(root, false);
    }

    private int sumOfLeftLeaves(TreeNode node, boolean isLeft) {
        // 1.递归退出条件
        if (node == null) {
            return 0;
        }
        // 左右子树都为空表明是叶子节点
        if (node.left == null && node.right == null) {
            // 是左子节点才返回节点的值
            if (isLeft) {
                return node.val;
            } else {
                // 右子节点就返回0,不影响最终的和
                return 0;
            }
        }

        // 2.递归具体逻辑
        return sumOfLeftLeaves(node.left, true) + sumOfLeftLeaves(node.right, false);
    }
}
