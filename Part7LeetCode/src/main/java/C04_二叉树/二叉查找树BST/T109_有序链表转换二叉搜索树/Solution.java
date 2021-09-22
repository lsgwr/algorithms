/***********************************************************
 * @Description : 109.有序链表转换二叉搜索树
 * https://leetcode-cn.com/problems/convert-sorted-list-to-binary-search-tree/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/30 21:58
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C04_二叉树.二叉查找树BST.T109_有序链表转换二叉搜索树;

import C01_线性表.单链表.ListNode;
import C04_二叉树.TreeNode;

/**
 * Convert Sorted List to Binary Search Tree
 * 二分法， 类似于 108.将有序数组转换为二叉搜索树
 * 自顶向下， 时间复杂度O(nlogn)， 空间复杂度O(logn)
 */
class Solution {
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) {
            return null;
        }
        if (head.next == null) {
            return new TreeNode(head.val);
        }
        ListNode mid = cutAtMiddle(head);
        TreeNode root = new TreeNode(mid.val);
        root.left = sortedListToBST(head);
        root.right = sortedListToBST(mid.next);
        return root;
    }

    ListNode cutAtMiddle(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode fast = head;
        ListNode slow = head;
        ListNode prev_slow = head;
        while (fast != null && fast.next != null) {
            prev_slow = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        prev_slow.next = null;
        return slow;
    }
}
