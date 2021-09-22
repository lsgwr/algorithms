/***********************************************************
 * @Description : 226.翻转二叉树
 * https://leetcode-cn.com/problems/invert-binary-tree/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/30 20:13
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C04_二叉树.二叉树的遍历.T226_翻转二叉树;

import C04_二叉树.TreeNode;

class Solution {
    public void swap(TreeNode root) {
        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;
    }

    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        invertTree(root.left);
        invertTree(root.right);
        swap(root);
        return root;
    }
}
