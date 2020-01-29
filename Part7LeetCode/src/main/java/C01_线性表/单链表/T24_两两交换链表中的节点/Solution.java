/***********************************************************
 * @Description : 24. 两两交换链表中的节点
 * https://leetcode-cn.com/problems/swap-nodes-in-pairs/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/29 21:26
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C01_线性表.单链表.T24_两两交换链表中的节点;

import C01_线性表.单链表.ListNode;

class Solution {
    public ListNode swapPairs(ListNode head) {
        // 至少要有两个点，否则直接返回
        if(head == null || head.next == null){
            return head;
        }
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        // 正在挪动的点对的前面那个元素
        ListNode pre = dummyHead;
        while(pre.next!= null && pre.next.next != null){
            // 更新node1和node2
            ListNode node1 = pre.next;
            ListNode node2 = pre.next.next;

            // 旋转两个点
            pre.next = node2;
            node1.next = node2.next;
            node2.next = node1;

            // p变成交换后靠后的那个元素
            pre = node1;
        }
        return dummyHead.next;
    }
}
