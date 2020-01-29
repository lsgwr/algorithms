/***********************************************************
 * @Description : https://leetcode-cn.com/problems/reverse-linked-list/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/5 23:46
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C01_线性表.单链表.T206_反转链表;

import C01_线性表.单链表.ListNode;

class Solution {
    public ListNode reverseList(ListNode head) {
        // 初始化
        ListNode pre = null;
        ListNode cur = head;
        // 只要还没遍历到最后就一直往后遍历
        while (cur != null) {
            // 临时保存当前节点的下一个节点，作为下一步的cur
            ListNode next = cur.next;
            // 当前节点的指针指向前一个节点，这是链表反转的关键
            cur.next = pre;
            // 前一个节点的指针往后移
            pre = cur;
            // 更新当前节点
            cur = next;
        }
        return pre;
    }
}
