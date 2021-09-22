/***********************************************************
 * @Description : 112.路径总和
 * https://leetcode-cn.com/problems/path-sum/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/30 22:14
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C04_二叉树.二叉树递归.T112_路径总和;

import C04_二叉树.TreeNode;

class Solution {
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }

        if (root.left == null && root.right == null) {
            // 注意题目中有说路径终点必须是叶子节点
            return root.val == sum;
        }

        // 总和减去当前节点的值，然后看当前节点是否存在子树的节点和等于前面的新sum
        if (hasPathSum(root.left, sum - root.val)) {
            return true;
        }
        if (hasPathSum(root.right, sum - root.val)) {
            return true;
        }

        // 两侧都没找到返回false
        return false;
    }
}
