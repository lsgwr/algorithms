/***********************************************************
 * @Description : T333_最大BST子树
 * https://leetcode-cn.com/problems/largest-bst-subtree/comments/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/2/4 19:55
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C04_二叉树.二叉树的遍历.T333_最大BST子树;

import C04_二叉树.TreeNode;

/**
 * dfs判断树中所有子树是否为BST，如果是BST，则计算该子树的节点个数，保存在结果变量中，取最大值即可。
 */
public class Solution {
    public int largestBSTSubtree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (isBST(root)) {
            // 每次递归到一个BST，就更新下最大深度
            return getCount(root);
        }
        int L = largestBSTSubtree(root.left);
        int R = largestBSTSubtree(root.right);
        return Math.max(L, R);
    }

    private boolean isBST(TreeNode root) {
        return isBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    /**
     * 判断当前树是否是BST
     * @param root 当前树根节点
     * @param min 当前树的最小节点都要大于的值
     * @param max 当前树的最大节点都要小于的值
     * @return 是否是BST
     */
    private boolean isBST(TreeNode root, int min, int max) {
        if (root == null) {
            return true;
        }
        // BST应该满足左子树的节点都小于根节点的值，右子树的节点都大于根节点的值
        return min < root.val && root.val < max && isBST(root.left, min, root.val) && isBST(root.right, root.val, max);
    }

    /**
     * 获取当前BST的节点树
     */
    private int getCount(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + getCount(root.left) + getCount(root.right);
    }
}
