/***********************************************************
 * @Description : 102.二叉树的层次遍历
 * https://leetcode-cn.com/problems/binary-tree-level-order-traversal/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/30 19:55
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C04_二叉树.二叉树的遍历.T102_二叉树的层次遍历;

import C04_二叉树.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();
        if(root == null){
            return list;
        }
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        while(!queue.isEmpty()){
            int size = queue.size();
            // 一次性把当前层的节点全部弹出
            List<Integer> levelList = new ArrayList<>();
            while(size-- > 0){
                TreeNode node = queue.remove();
                levelList.add(node.val);
                if(node.left != null){
                    queue.add(node.left);
                }
                if(node.right != null){
                    queue.add(node.right);
                }
            }
            list.add(levelList);
        }
        return list;
    }
}
