/***********************************************************
 * @Description : 按照左右半区的方式重新组合单链表
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/2/4 15:28
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package 第2章_链表问题.按照左右半区的方式重新组合单链表;

import 第2章_链表问题.ListNode;

public class Solution {
    public void relocate(ListNode head) {
        if (head == null || head.next == null) {
            return;
        }
        // 快慢指针法
        ListNode mid = head;
        ListNode right = head.next;
        while (right.next != null && right.next.next != null) {
            mid = mid.next;
            right = right.next.next;
        }
        right = mid.next;
        mid.next = null;
        mergeLR(head, right);
    }

    private void mergeLR(ListNode left, ListNode right) {
        ListNode next = null;
        while (left.next != null) {
            next = right.next;
            right.next = left.next;
            left.next = right;
            left = right.next;
            right = next;
        }
        left.next = right;
    }
}
