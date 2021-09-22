/***********************************************************
 * @Description : 判断一个树是否包含另一个树的全部拓扑结构
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/2/4 17:28
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package 第3章_二叉树.判断一个树是否包含另一个树的全部拓扑结构;

import 第3章_二叉树.TreeNode;

public class Solution {
    public boolean isSubtree(TreeNode s, TreeNode t) {
        if (t == null) {
            // 空子树肯定被包含的
            return true;
        }
        if (s == null) {
            return false;
        }
        return isSameTree(s, t) || isSubtree(s.left, t) || isSubtree(s.right, t);
    }

    private boolean isSameTree(TreeNode s, TreeNode t) {
        if (t == null) {
            return true;
        }
        if (s == null || s.val != t.val) {
            return false;
        }
        return isSameTree(s.left, t.left) && isSameTree(s.right, t.right);
    }
}

