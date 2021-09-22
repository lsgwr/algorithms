/***********************************************************
 * @Description : 单链表的选择排序
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/2/4 12:03
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package 第2章_链表问题.单链表的选择排序;

import 第2章_链表问题.ListNode;

/**
 * 每次从未排序的节点中选择值最小的节点，拿出来连接到已经排序部分的尾部
 */
public class Solution {
    public ListNode selectionSort(ListNode head) {
        // 排序部分尾部
        ListNode tail = null;
        // 未排序部分头部
        ListNode cur = head;
        // 最小节点的前一个节点
        ListNode smallestPre = null;
        // 最小的节点
        ListNode smallest = null;
        while (cur != null) {
            smallest = cur;
            smallestPre = getSmallestPre(cur);
            if (smallestPre != null) {
                // 把本轮的最小值拿出来
                smallest = smallestPre.next;
                smallestPre.next = smallest.next;
            }
            cur = cur == smallest ? cur.next : cur;
            if (tail == null) {
                head = smallest;
            } else {
                tail.next = smallest;
            }
            tail = smallest;
        }
        return head;
    }

    /**
     * 获取最小节点的前一个节点
     */
    private ListNode getSmallestPre(ListNode head) {
        ListNode smallestPre = null;
        ListNode smallest = head;
        ListNode pre = head;
        ListNode cur = pre.next;
        while (cur != null) {
            if (cur.val < smallest.val) {
                smallestPre = pre;
                smallest = cur;
            }
            pre = cur;
            cur = cur.next;
        }
        return smallestPre;
    }
}
