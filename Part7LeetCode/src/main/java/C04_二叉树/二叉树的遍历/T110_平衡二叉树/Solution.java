/***********************************************************
 * @Description : 110.平衡二叉树
 * https://leetcode-cn.com/problems/balanced-binary-tree/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/30 21:07
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C04_二叉树.二叉树的遍历.T110_平衡二叉树;

import C04_二叉树.TreeNode;

/**
 * 递归解决，计算子树高度，-1表示子树已经不平衡了
 */
class Solution {
    public boolean isBalanced(TreeNode root) {
        // 计算各个点的深度 ，-1代表不平衡了
        return calDepth(root) != -1;
    }

    /**
     * 计算子树高度，-1表示已经不平衡了
     */
    private int calDepth(TreeNode node) {
        if (node == null) {
            return 0;
        }
        // 递归计算左子树最大深度
        int lMax = calDepth(node.left);
        // 递归计算右子树最大深度
        int rMax = calDepth(node.right);
        // 左右子树高度差大于1时当前子树的高度返回-1
        if (lMax >= 0 && rMax >= 0 && Math.abs(lMax - rMax) <= 1) {
            return Math.max(lMax, rMax) + 1;
        } else {
            return -1;
        }
    }
}
