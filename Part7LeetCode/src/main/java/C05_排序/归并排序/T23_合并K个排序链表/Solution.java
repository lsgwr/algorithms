/***********************************************************
 * @Description : 23.合并K个排序链表
 * https://leetcode-cn.com/problems/merge-k-sorted-lists/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/30 22:47
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C05_排序.归并排序.T23_合并K个排序链表;

import C01_线性表.单链表.ListNode;

import java.util.PriorityQueue;

class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        ListNode dummyHead = new ListNode(0);
        ListNode cur = dummyHead;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (ListNode list : lists) {
            cur = list;
            while (cur != null) {
                pq.add(cur.val);
                cur = cur.next;
            }
        }
        cur = dummyHead;
        while (!pq.isEmpty()) {
            cur.next = new ListNode(pq.remove());
            cur = cur.next;
        }
        return dummyHead.next;
    }
}
