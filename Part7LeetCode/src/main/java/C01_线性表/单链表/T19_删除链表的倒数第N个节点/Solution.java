/***********************************************************
 * @Description : 19.删除链表的倒数第N个节点
 * https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/29 21:24
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C01_线性表.单链表.T19_删除链表的倒数第N个节点;

import C01_线性表.单链表.ListNode;

class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        // p和q初始化为dummyHead可以防止很多null的异常
        ListNode p = dummyHead;
        ListNode q = dummyHead;
        for(int i = 0; i < n + 1; i++){
            // q移动到和p间距为n的地方
            q = q.next;
        }
        while(q != null){
            p = p.next;
            q = q.next;
        }
        // q到达null了，此时p就是要删除的节点的上一个节点
        p.next = p.next.next;
        return dummyHead.next;
    }
}
