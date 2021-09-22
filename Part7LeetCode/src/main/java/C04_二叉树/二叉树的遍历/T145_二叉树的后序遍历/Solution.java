/***********************************************************
 * @Description : 145.二叉树的后序遍历
 * https://leetcode-cn.com/problems/binary-tree-postorder-traversal/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/30 19:32
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C04_二叉树.二叉树的遍历.T145_二叉树的后序遍历;

import C04_二叉树.TreeNode;

import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        postorder(root, list);
        return list;
    }

    /// 遍历以node作为根节点的子树
    private void postorder(TreeNode node, List<Integer> list){
        // 1.递归终止条件
        if(node == null){
            return;
        }
        // 2.递归具体逻辑
        postorder(node.left, list);
        postorder(node.right, list);
        list.add(node.val);
    }
}
