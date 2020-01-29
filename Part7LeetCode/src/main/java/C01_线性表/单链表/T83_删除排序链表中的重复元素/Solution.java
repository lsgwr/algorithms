/***********************************************************
 * @Description : 83.删除排序链表中的重复元素
 * https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/29 21:10
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C01_线性表.单链表.T83_删除排序链表中的重复元素;

import C01_线性表.单链表.ListNode;

class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode cur = head;
        while (cur != null) {
            if (cur.next != null && cur.val == cur.next.val) {
                cur.next = cur.next.next;
            }else {
                cur = cur.next;
            }
        }
        return head;
    }
}
