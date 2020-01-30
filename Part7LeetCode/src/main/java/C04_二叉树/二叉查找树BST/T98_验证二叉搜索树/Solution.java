/***********************************************************
 * @Description : 98.验证二叉搜索树
 * https://leetcode-cn.com/problems/validate-binary-search-tree/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/30 21:50
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C04_二叉树.二叉查找树BST.T98_验证二叉搜索树;

import C04_二叉树.TreeNode;

import java.util.ArrayList;
import java.util.List;

class Solution {
    /**
     * 中序遍历以node作为根节点的二分搜索树
     */
    private void inOrder(TreeNode node, List<Integer> list) {
        // 递归终止条件
        if (node == null) {
            // 遍历到null节点就返回上一层递归
            return;
        }

        // 递归组成逻辑
        // 2.遍历左子树
        inOrder(node.left, list);
        // 1.访问当前节点。需要存储时可以放到list中
        list.add(node.val);
        // 3.遍历右子树
        inOrder(node.right, list);
    }

    /**
     * 中序遍历的结果是有序地，则说明二叉树是一个二叉搜索树
     */
    public boolean isValidBST(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        inOrder(root, list);
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i) <= list.get(i - 1)) {
                return false;
            }
        }
        return true;
    }
}
