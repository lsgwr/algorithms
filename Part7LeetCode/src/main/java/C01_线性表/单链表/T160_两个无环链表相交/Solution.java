/***********************************************************
 * @Description : T160_两个无环链表相交
 * https://leetcode-cn.com/problems/intersection-of-two-linked-lists/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/2/4 10:18
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C01_线性表.单链表.T160_两个无环链表相交;

import C01_线性表.单链表.ListNode;

public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if(headA == null || headB == null){
            return null;
        }
        ListNode curA = headA;
        ListNode curB = headB;
        int n = 0;
        while(curA.next != null){
            n++;
            curA = curA.next;
        }
        while(curB.next != null){
            n--;
            curB = curB.next;
        }
        // 两个链表都移动到最后一个节点了，最后一个节点不同，两个链表肯定没有相交节点
        if(curA != curB){
            return null;
        }
        // 二者最后一个节点相等，说明存在相交节点，下面进行查找
        curA = n > 0 ? headA : headB;
        curB = curA == headA ? headB : headA;
        // curA要先移动的长度，这样两个链表剩下地未遍历长度相等，方便后面一起遍历
        n = Math.abs(n);
        while(n > 0){
            n--;
            curA = curA.next;
        }
        // 两个链表剩余的未遍历长度相等，一起往后一步一步走即可
        while(curA != curB){
            curA = curA.next;
            curB = curB.next;
        }
        return curA;
    }
}