/***********************************************************
 * @Description : 链表的中间节点
 * 快慢指针法，快指针一次两步，慢指针一次一步，快指针走到头时慢指针正好到中间
 * https://leetcode-cn.com/problems/middle-of-the-linked-list/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/2/3 18:18
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C01_线性表.单链表.T876_链表的中间节点;

import C01_线性表.单链表.ListNode;

class Solution {
    public ListNode middleNode(ListNode head) {
        // 快慢指针法
        ListNode slow = head;
        ListNode fast = head;
        int i = 0;
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
            i++;
        }
        return slow;
    }
}
