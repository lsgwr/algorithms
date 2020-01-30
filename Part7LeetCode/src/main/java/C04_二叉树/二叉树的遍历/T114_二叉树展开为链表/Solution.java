/***********************************************************
 * @Description : 114.二叉树展开为链表
 * https://leetcode-cn.com/problems/flatten-binary-tree-to-linked-list/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/30 21:10
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C04_二叉树.二叉树的遍历.T114_二叉树展开为链表;

import C04_二叉树.TreeNode;

/**
 * Flatten Binary Tree to Linked List
 * 递归1版1， 时间复杂度O(n)， 空间复杂度O(logn)
 */
public class Solution {
    public void flatten(TreeNode root) {
        if (root == null) {
            return; // 终止条件
        }
        flatten(root.left);
        flatten(root.right);
        if (root.left == null) {
            return;
        }
        // 三方合并， 将左子树所形成的链表插入到root和root->right之间
        TreeNode p = root.left;
        while (p.right != null) {
            //寻找左链表最后一个节点
            p = p.right;
        }
        p.right = root.right;
        root.right = root.left;
        root.left = null;
    }
}
