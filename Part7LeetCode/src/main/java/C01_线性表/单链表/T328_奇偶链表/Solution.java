/***********************************************************
 * @Description : https://leetcode-cn.com/problems/reverse-linked-list/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/28 13:10
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C01_线性表.单链表.T328_奇偶链表;

import C01_线性表.单链表.ListNode;

class Solution {
    public ListNode oddEvenList(ListNode head) {
        // 奇数链表的虚拟头结点
        ListNode oddHead = new ListNode(0);
        // 偶数链表的虚拟头结点
        ListNode evenHead = new ListNode(0);
        // 上面两个链表的移动指针
        ListNode odd = oddHead;
        ListNode even = evenHead;
        int index = 0;
        while(head != null){
            if(index % 2 == 1){ // 奇数编号节点
                // 插入节点到奇数链表
                odd.next = head;
                // 移动指针往后一位
                odd = odd.next;
            }else { // 偶数编号节点
                even.next = head;
                even = even.next;
            }
            head = head.next;
            index++;
        }
        // 奇数链表连接到偶数链表后面
        even.next = oddHead.next;
        // 奇数链表的最后清空下，防止连接些乱七八糟的节点
        odd.next = null;
        return evenHead.next;
    }
}
