/***********************************************************
 * @Description : 100.相同的树
 * https://leetcode-cn.com/problems/same-tree/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/30 20:44
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C04_二叉树.二叉树的遍历.T100_相同的树;

import C04_二叉树.TreeNode;

class Solution {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p != null && q != null && p.val == q.val) {
            return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
        } else {
            return false;
        }
    }
}
