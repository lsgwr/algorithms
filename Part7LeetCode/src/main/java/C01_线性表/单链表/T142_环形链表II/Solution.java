/***********************************************************
 * @Description : 142.环形链表II
 * https://leetcode-cn.com/problems/linked-list-cycle-ii/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/29 21:48
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C01_线性表.单链表.T142_环形链表II;

import C01_线性表.单链表.ListNode;

/**
 * slow和fast相遇后，从 head开始另设一个指针slow2 ，两个慢指针每次前进一步，它俩一定会在环入口点相遇
 */
public class Solution {
    public ListNode detectCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (fast == slow) {
                ListNode slow2 = head;
                while (slow != slow2) {
                    slow = slow.next;
                    slow2 = slow2.next;
                }
                return slow2;
            }
        }
        return null;
    }
}
