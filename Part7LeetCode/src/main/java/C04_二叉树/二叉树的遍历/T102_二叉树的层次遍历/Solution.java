/***********************************************************
 * @Description : 102.二叉树的层次遍历
 * https://leetcode-cn.com/problems/binary-tree-level-order-traversal/
 广度优先遍历的一种特殊情形，即不同的层进行区分，所有需要一次性把所有上一轮加入的所有邻接点弹出，
 再把下一轮所有的邻接点一次性加入，和994号问题腐烂的橘子类似
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/30 19:55
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C04_二叉树.二叉树的遍历.T102_二叉树的层次遍历;

import C04_二叉树.TreeNode;

import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if(root == null){
            return result;
        }
        List<TreeNode> nodeList = new ArrayList<>();
        nodeList.add(root);
        while(!nodeList.isEmpty()){
            List<Integer> resultLevel = new ArrayList<>();
            List<TreeNode> newNodeList = new ArrayList<>();
            for(TreeNode node : nodeList){
                resultLevel.add(node.val);
                if(node.left != null){
                    newNodeList.add(node.left);
                }
                if(node.right != null){
                    newNodeList.add(node.right);
                }
            }
            result.add(resultLevel);
            nodeList = new ArrayList<>(newNodeList);
        }
        return result;
    }
}
