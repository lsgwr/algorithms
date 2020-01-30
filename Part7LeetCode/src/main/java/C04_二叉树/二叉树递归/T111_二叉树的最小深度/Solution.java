/***********************************************************
 * @Description : 111.二叉树的最小深度
 * https://leetcode-cn.com/problems/minimum-depth-of-binary-tree/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/30 22:10
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C04_二叉树.二叉树递归.T111_二叉树的最小深度;

import C04_二叉树.TreeNode;

class Solution {
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null || root.right == null) {
            return Math.max(minDepth(root.left), minDepth(root.right)) + 1;
        }
        return Math.min(minDepth(root.left), minDepth(root.right)) + 1;
    }
}
