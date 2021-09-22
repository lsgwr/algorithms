/***********************************************************
 * @Description : 104.二叉树的最大深度
 * https://leetcode-cn.com/problems/maximum-depth-of-binary-tree/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/30 22:11
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C04_二叉树.二叉树递归.T104_二叉树的最大深度;

import C04_二叉树.TreeNode;

class Solution {
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
