/***********************************************************
 * @Description : 99.恢复二叉搜索树
 * https://leetcode-cn.com/problems/recover-binary-search-tree/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/30 20:35
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C04_二叉树.二叉树的遍历.T99_恢复二叉搜索树;

import C04_二叉树.TreeNode;

/**
 * Recover Binary Search Tree
 * 中序遍历,递归,时间复杂度O(n)， 空间复杂度O(logn)
 * <p>
 * 中序递归遍历， 用两个指针存放在遍历过程中碰到的两处逆向的位置
 */
public class Solution {
    private TreeNode p1 = null;
    private TreeNode p2 = null;
    private TreeNode prev = null;

    public void recoverTree(TreeNode root) {
        inOrder(root);
        // swap
        int tmp = p1.val;
        p1.val = p2.val;
        p2.val = tmp;
    }

    private void inOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        if (root.left != null) {
            inOrder(root.left);
        }
        // 不满足二分搜索树的性质说明是异常点
        if (prev != null && root.val < prev.val) {
            if (p1 == null) {
                p1 = prev;
            }
            p2 = root;
        }
        prev = root;
        if (root.right != null) {
            inOrder(root.right);
        }
    }
}
