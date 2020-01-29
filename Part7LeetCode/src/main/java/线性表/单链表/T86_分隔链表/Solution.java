/***********************************************************
 * @Description : 86.分隔链表
 * https://leetcode-cn.com/problems/partition-list/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/29 21:07
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package 线性表.单链表.T86_分隔链表;

import 线性表.单链表.ListNode;

// 核心思想是把小于x的节点和大于等于x的节点拆成两个链表，最后把后者连接到前者形成一条新的链表就是满足题目的链表
class Solution {
    public ListNode partition(ListNode head, int x) {
        // 存储所有大于等于x的节点的链表的虚拟头结点
        ListNode bigHead = new ListNode(0);
        // 存储所有小于x的节点的链表的虚拟头结点
        ListNode smallHead = new ListNode(0);
        // 上面两个链表的指针，用于插入元素
        ListNode big = bigHead;
        ListNode small = smallHead;
        while(head != null){
            if(head.val < x){
                // 插入节点到小元素链表
                small.next = head;
                // 指针往后移动
                small = small.next;
            }else {
                // 插入节点到大元素链表
                big.next = head;
                big = big.next;
            }
            head = head.next;
        }
        // 把大元素连接到小元素链表后面
        small.next = bigHead.next;
        // 大元素链表的尾部要清空下，防止还连着些乱七八糟的节点
        big.next = null;
        // 返回小元素链表的头结点，即虚拟头结点的下一个节点
        return smallHead.next;
    }
}
