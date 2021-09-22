/***********************************************************
 * @Description : 105.从前序与中序遍历序列构造二叉树
 * https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/30 21:27
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C04_二叉树.二叉树的构建.T105_从前序与中序遍历序列构造二叉树;

import C04_二叉树.TreeNode;

/**
 * Construct Binary Tree from Preorder and Inorder Traversal
 * 递归， 时间复杂度O(n)， 空间复杂度O(\logn)
 */
public class Solution {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return buildTree(preorder, 0, preorder.length, inorder, 0, inorder.length);
    }

    TreeNode buildTree(int[] preorder, int begin1, int end1, int[] inorder, int begin2, int end2) {
        if (begin1 == end1) {
            return null;
        }
        if (begin2 == end2) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[begin1]);
        int inRootPos = find(inorder, begin2, end2, preorder[begin1]);
        int leftSize = inRootPos - begin2;
        root.left = buildTree(preorder, begin1 + 1, begin1 + leftSize + 1, inorder, begin2, begin2 + leftSize);
        root.right = buildTree(preorder, begin1 + leftSize + 1, end1, inorder, inRootPos + 1, end2);
        return root;
    }

    private static int find(int[] array, int begin, int end, int val) {
        for (int i = begin; i < end; ++i) {
            if (array[i] == val) {
                return i;
            }
        }
        return -1;
    }
}
