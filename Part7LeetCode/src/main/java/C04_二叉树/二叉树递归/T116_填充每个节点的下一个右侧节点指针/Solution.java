/***********************************************************
 * @Description : 116.填充每个节点的下一个右侧节点指针
 * https://leetcode-cn.com/problems/populating-next-right-pointers-in-each-node/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/30 22:22
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C04_二叉树.二叉树递归.T116_填充每个节点的下一个右侧节点指针;

class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {
    }

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};

/**
 * Populating Next Right Pointers in Each Node
 * 时间复杂度O(n)， 空间复杂度O(logn)
 */
class Solution {
    public Node connect(Node root) {
        if (root == null) {
            return root;
        }
        if (root.left != null) {
            root.left.next = root.right;
        }
        if (root.next != null && root.right != null) {
            root.right.next = root.next.left;
        }
        connect(root.left);
        connect(root.right);
        return root;
    }
}
