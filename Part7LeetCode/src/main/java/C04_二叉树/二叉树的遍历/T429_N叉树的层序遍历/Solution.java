/***********************************************************
 * @Description : https://leetcode-cn.com/problems/n-ary-tree-level-order-traversal/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/4/4 22:48
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C04_二叉树.二叉树的遍历.T429_N叉树的层序遍历;


import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};

class Solution {
    /**
     * N叉树的层序遍历
     *          1
     *        / | \
     *       /  |  \
     *      3   2  4
     *     / \
     *    5  6
     * 层序遍历结果：[[1], [3,2,4], [5,6]]
     * @param root N叉树的根节点
     */
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> result = new ArrayList<>();
        if(root == null){
            return result;
        }
        List<Node> levelList = new ArrayList<>();
        levelList.add(root);
        while(!levelList.isEmpty()){
            List<Integer> levelResult = new ArrayList<>();
            List<Node> newLevelList = new ArrayList<>();
            for(Node node : levelList){
                levelResult.add(node.val);
                for(Node child : node.children){
                    newLevelList.add(child);
                }
            }
            result.add(levelResult);
            levelList = newLevelList;
        }
        return result;
    }
}
