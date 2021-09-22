/***********************************************************
 * @Description : https://leetcode-cn.com/problems/n-ary-tree-preorder-traversal/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/4/4 22:38
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C04_二叉树.二叉树的遍历.T589_N叉树的前序遍历;

import java.util.ArrayList;
import java.util.List;

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
    public List<Integer> preorder(Node root) {
        List<Integer> list = new ArrayList<>();
        preorder(root, list);
        return list;
    }

    private void preorder(Node node, List<Integer> list){
        if(node == null){
            return;
        }
        list.add(node.val);
        for(Node child : node.children){
            preorder(child, list);
        }
    }
}
