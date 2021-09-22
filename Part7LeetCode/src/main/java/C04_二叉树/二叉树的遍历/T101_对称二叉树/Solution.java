/***********************************************************
 * @Description : 101.对称二叉树
 * https://leetcode-cn.com/problems/symmetric-tree/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/30 21:03
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C04_二叉树.二叉树的遍历.T101_对称二叉树;

import C04_二叉树.TreeNode;

class Solution {
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return isSymmetric(root.left, root.right);
    }

    private boolean isSymmetric(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p != null && q != null && p.val == q.val) {
            return isSymmetric(p.left, q.right) && isSymmetric(p.right, q.left);
        } else {
            return false;
        }
    }
}
