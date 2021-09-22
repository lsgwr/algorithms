/***********************************************************
 * @Description : 230.二叉搜索树中第K小的元素
 * https://leetcode-cn.com/problems/kth-smallest-element-in-a-bst/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/30 22:04
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C04_二叉树.二叉查找树BST.T230_二叉搜索树中第K小的元素;

import C04_二叉树.TreeNode;

class Solution {
    int i = 0;
    int kth = 0;
    int kthSmallestVal = 0;

    public int kthSmallest(TreeNode root, int k) {
        kth = k;
        inOrder(root);
        return kthSmallestVal;
    }

    /**
     * 中序遍历的结果是升序排列的，所以找到第k小的元素就是累计中序遍历到额递归层数
     */
    private void inOrder(TreeNode node) {
        if (node == null) {
            return;
        }
        inOrder(node.left);
        i++;
        if (i == kth) {
            kthSmallestVal = node.val;
        }
        inOrder(node.right);
    }
}
