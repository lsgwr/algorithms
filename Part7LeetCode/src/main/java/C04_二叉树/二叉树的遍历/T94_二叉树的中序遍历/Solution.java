/***********************************************************
 * @Description : 
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/30 19:31
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C04_二叉树.二叉树的遍历.T94_二叉树的中序遍历;

import C04_二叉树.TreeNode;

import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        inorder(root, list);
        return list;
    }

    /// 遍历以node作为根节点的子树
    private void inorder(TreeNode node, List<Integer> list) {
        // 1.递归终止条件
        if (node == null) {
            return;
        }
        // 2.递归具体逻辑
        inorder(node.left, list);
        list.add(node.val);
        inorder(node.right, list);
    }
}
