/***********************************************************
 * @Description : 新建两个不同的链表，遍历一遍原链表后，分别存储奇数编号节点和偶数编号节点，最后把奇数链表连接到偶数链表即可
 * 和LeetCode第86号问题按照边界分成两个链表的思路基本一致
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/18 18:24
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter05LinkedList.LeetCode382OddEvenLinkedList;

import Chapter05LinkedList.ListNode;

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
