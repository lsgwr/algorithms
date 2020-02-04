/***********************************************************
 * @Description : 有序的环形列表中插入新节点
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/2/4 15:15
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package 第2章_链表问题.有序的环形列表中插入新节点;

import 第2章_链表问题.ListNode;


public class Solution {
    public ListNode insert(ListNode head, int num) {
        ListNode node = new ListNode(num);
        if (head == null) {
            // 原来的链表为空，则把新加入地节点作为一个环形列表
            node.next = node;
            return node;
        }
        ListNode pre = head;
        ListNode cur = head.next;
        while (cur != head) {
            if (pre.val <= num && cur.val >= num) {
                // 找到了合适的插入位置
                break;
            }
            pre = cur;
            cur = cur.next;
        }
        pre.next = node;
        node.next = cur;
        return head.val < num ? head : node;
    }
}
