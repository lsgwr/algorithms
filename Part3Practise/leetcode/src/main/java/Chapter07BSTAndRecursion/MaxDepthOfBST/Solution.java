/***********************************************************
 * @Description : 计算二叉树的最大深度
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2019/8/22 00:02
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter07BSTAndRecursion.MaxDepthOfBST;

public class Solution {
    /**
     * Definition for a binary tree node
     */
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public int maxDepth(TreeNode root) {
        // 递归的退出条件
        if (root == null) {
            // 当前树的根节点为null,那么当前树的深度为0
            return 0;
        }
        // 递归计算左子树最大深度
        int lMax = maxDepth(root.left);
        // 递归计算右子树最大深度
        int rMax = maxDepth(root.right);
        // 找到左右子树深度较大地那个，加1是因为当前层在递归回退时也算一层
        return Math.max(lMax, rMax) + 1;
    }
}
