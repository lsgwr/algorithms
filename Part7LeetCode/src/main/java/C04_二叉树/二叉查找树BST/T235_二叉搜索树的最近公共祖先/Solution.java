/***********************************************************
 * @Description : 235.二叉搜索树的最近公共祖先
 * https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-search-tree/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/30 22:03
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C04_二叉树.二叉查找树BST.T235_二叉搜索树的最近公共祖先;

import C04_二叉树.TreeNode;

class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        // pq均在右子树
        if (p.val > root.val && q.val > root.val) {
            return lowestCommonAncestor(root.right, p, q);
        }
        // pq均在左子树
        if (p.val < root.val && q.val < root.val) {
            return lowestCommonAncestor(root.left, p, q);
        }
        // p和q在root的两侧，当前的root肯定是最近的父节点
        return root;
    }
}
