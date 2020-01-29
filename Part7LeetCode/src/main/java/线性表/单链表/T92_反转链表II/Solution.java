/***********************************************************
 * @Description : 92. 反转链表 II
 * https://leetcode-cn.com/problems/reverse-linked-list-ii/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/29 21:04
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package 线性表.单链表.T92_反转链表II;

import 线性表.单链表.ListNode;

class Solution {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        // 创建虚拟头结点,防止一些null导致的问题
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode cur = dummyHead;
        for (int i = 1; i < m; i++) {
            // 找到了m的上一个节点
            cur = cur.next;
        }
        // node.next就是要翻转的起点，mHead表示开始翻转的起点
        ListNode mHead = cur.next;
        ListNode next = null;
        ListNode pre = null;
        // 翻转m到n这一段，起点是mHead，参考206题
        for (int i = m; i <= n; i++) {
            next = mHead.next;
            mHead.next = pre;
            pre = mHead;
            mHead = next;
        }
        // m位置的节点指向n位置的下一个节点
        cur.next.next = next;
        // m前一个节点指向n位置处的节点
        cur.next = pre;
        // 返回虚拟头结点的下一个节点即新的头结点
        return dummyHead.next;
    }
}
