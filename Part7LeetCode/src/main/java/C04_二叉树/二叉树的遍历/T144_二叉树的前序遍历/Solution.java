/***********************************************************
 * @Description : 144.二叉树的前序遍历
 * https://leetcode-cn.com/problems/binary-tree-preorder-traversal/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/30 19:24
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C04_二叉树.二叉树的遍历.T144_二叉树的前序遍历;

import C04_二叉树.TreeNode;

import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        preorder(root, list);
        return list;
    }

    /**
     * 遍历以node作为根节点的子树
     */
    private void preorder(TreeNode node, List<Integer> list) {
        // 1.递归终止条件
        if (node == null) {
            return;
        }
        list.add(node.val);
        preorder(node.left, list);
        preorder(node.right, list);
    }
}
