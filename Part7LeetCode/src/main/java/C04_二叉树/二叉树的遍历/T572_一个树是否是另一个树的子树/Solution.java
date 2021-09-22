/***********************************************************
 * @Description : T572_一个树是否是另一个树的子树
 * https://leetcode-cn.com/problems/subtree-of-another-tree/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/2/4 17:02
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C04_二叉树.二叉树的遍历.T572_一个树是否是另一个树的子树;

import C04_二叉树.TreeNode;

public class Solution {
    public boolean isSubtree(TreeNode s, TreeNode t) {
        if (s == null) {
            return false;
        }
        return isSameTree(s, t) || isSubtree(s.left, t) || isSubtree(s.right, t);
    }

    private boolean isSameTree(TreeNode s, TreeNode t) {
        // 注意子树是指当前节点往下的所有节点
        if (s == null && t == null) {
            // 两个树都为空，认为是相等地
            return true;
        }
        if(s == null || t == null){
            // 一个为空一个不为空，肯定是不等地
            return false;
        }

        if (s.val != t.val) {
            // 两个树都不为空，先比较下根节点的值，如果不相等，直接肯定不等了，相等了才往下走
            return false;
        }
        // 递归遍历左子树和右子树
        return isSameTree(s.left, t.left) && isSameTree(s.right, t.right);
    }
}
