/***********************************************************
 * @Description : 25.K个一组翻转链表
 * https://leetcode-cn.com/problems/reverse-nodes-in-k-group/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/29 21:28
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C01_线性表.单链表.T25_K个一组翻转链表;

import C01_线性表.单链表.ListNode;

class Solution {
    /**
     * 参考 92. 反转部分链表 II，把第m个元素到第n个元素及之间的元素逆序
     */
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

    public ListNode reverseKGroup(ListNode head, int k) {
        if(head == null){
            return null;
        }
        // 链表节点的个数
        int len = 1;
        ListNode cur = head;
        while (cur.next != null) {
            cur = cur.next;
            len++;
        }
        // 累计要进行多少轮翻转
        int cnt = len / k;
        for (int i = 0; i < cnt; i++) {
            int m = 1 + k * i;
            int n = 1 + k * (i + 1);
            head = reverseBetween(head, m, n - 1);
        }
        return head;
    }
}
