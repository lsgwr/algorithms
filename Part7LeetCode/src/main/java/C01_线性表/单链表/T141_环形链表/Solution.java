/***********************************************************
 * @Description : 141.环形链表
 * https://leetcode-cn.com/problems/linked-list-cycle/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/29 21:44
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C01_线性表.单链表.T141_环形链表;

import C01_线性表.单链表.ListNode;

/**
 * 最好的方法是时间复杂度 O(n) ， 空间复杂度 O(1) 的。 设置两个指针， 一个快一个慢，
 * 快的指针每次走两步， 慢的指针每次走一步， 如果快指针和慢指针相遇， 则说明有环
 */
public class Solution {
    public boolean hasCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
            if(fast == slow){
                return true;
            }
        }
        return false;
    }
}
