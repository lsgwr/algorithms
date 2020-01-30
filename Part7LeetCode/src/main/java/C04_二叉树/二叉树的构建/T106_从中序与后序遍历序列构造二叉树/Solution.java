/***********************************************************
 * @Description : 106.从中序与后序遍历序列构造二叉树
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/30 21:34
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C04_二叉树.二叉树的构建.T106_从中序与后序遍历序列构造二叉树;

import C04_二叉树.TreeNode;

/**
 * Construct Binary Tree from Inorder and Postorder Traversal
 * 递归， 时间复杂度O(n)， 空间复杂度O(\logn)
 */
public class Solution {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        return buildTree(inorder, 0, inorder.length, postorder, 0, postorder.length);
    }

    TreeNode buildTree(int[] inorder, int begin1, int end1, int[] postorder, int begin2, int end2) {
        if (begin1 == end1) {
            return null;
        }
        if (begin2 == end2) {
            return null;
        }
        int val = postorder[end2 - 1];
        TreeNode root = new TreeNode(val);
        int inRootPos = find(inorder, begin1, end1, val);
        int leftSize = inRootPos - begin1;
        int postLeftLast = begin2 + leftSize;
        root.left = buildTree(inorder, begin1, inRootPos, postorder, begin2, postLeftLast);
        root.right = buildTree(inorder, inRootPos + 1, end1, postorder, postLeftLast, end2 - 1);
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
