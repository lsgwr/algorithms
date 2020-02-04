/***********************************************************
 * @Description : T543_二叉树的直径
 * 参考地左成云代码面试指南第3章
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/2/4 21:29
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C04_二叉树.二叉树的遍历.T543_二叉树的直径;

import C04_二叉树.TreeNode;

public class Solution {
    class ReturnType {
        int maxDistance;
        int height;

        public ReturnType(int maxDistance, int height) {
            this.maxDistance = maxDistance;
            this.height = height;
        }
    }

    public ReturnType process(TreeNode root) {
        if (root == null) {
            return new ReturnType(0, 0);
        }
        ReturnType leftData = process(root.left);
        ReturnType rightData = process(root.right);
        int height = Math.max(leftData.height, rightData.height) + 1;
        int maxDistance = Math.max(leftData.height + rightData.height, Math.max(leftData.maxDistance, rightData.maxDistance));
        return new ReturnType(maxDistance, height);
    }

    public int diameterOfBinaryTree(TreeNode root) {
        return process(root).maxDistance;
    }
}
