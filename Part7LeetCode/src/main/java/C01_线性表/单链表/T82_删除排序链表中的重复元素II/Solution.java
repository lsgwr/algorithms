/***********************************************************
 * @Description : 82.删除排序链表中的重复元素II
 * https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list-ii/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/29 21:11
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C01_线性表.单链表.T82_删除排序链表中的重复元素II;

import C01_线性表.单链表.ListNode;

class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null){
            return null;
        }
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode cur = dummyHead;
        while (cur.next!=null && cur.next.next != null) {
            if (cur.next.val == cur.next.next.val) {
                // 重复元素的起点
                ListNode del = cur.next;
                while (del.next != null && del.val == del.next.val) {
                    del = del.next;
                }
                // while退出时，del走到了最后一个重复元素的位置，在这里设置下
                cur.next = del.next;
            } else {
                cur = cur.next;
            }
        }
        return dummyHead.next;
    }
}
