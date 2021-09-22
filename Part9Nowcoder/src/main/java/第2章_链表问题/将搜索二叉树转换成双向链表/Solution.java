/***********************************************************
 * @Description : 将搜索二叉树转换成双向链表
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/2/4 11:51
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package 第2章_链表问题.将搜索二叉树转换成双向链表;

import 第2章_链表问题.ListNode;

import java.util.ArrayDeque;
import java.util.Queue;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

public class Solution {
    public TreeNode convert(TreeNode root) {
        Queue<TreeNode> queue = new ArrayDeque<>();
        // 中序遍历的结果就是双向链表的存储顺序
        inOrder(root, queue);
        TreeNode head = queue.remove();
        TreeNode pre = head;
        pre.left = null;
        TreeNode cur = null;
        while (!queue.isEmpty()) {
            cur = queue.remove();
            pre.right = cur;
            cur.left = pre;
            pre = cur;
        }
        pre.right = null;
        return head;
    }

    private void inOrder(TreeNode root, Queue<TreeNode> queue) {
        if (root == null) {
            return;
        }
        inOrder(root.left, queue);
        queue.add(root);
        inOrder(root.right, queue);
    }
}
