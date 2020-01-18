/***********************************************************
 * @Description : 21.合并两个有序链表
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/18 21:35
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter05LinkedList.LeetCode21MergeTwoSortedList;

import Chapter05LinkedList.ListNode;

class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        // 合并后的链表
        ListNode dummyHead = new ListNode(0);
        // 链表的移动指针
        ListNode cur = dummyHead;
        while(l1 != null && l2 != null){
            if(l1.val < l2.val){
                cur.next = l1;
                l1 = l1.next;
            }else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }
        // 一个链表遍历完了另一个可能还有元素没遍历，把还没遍历完的链表直接挂到cur后面即可
        if(l1 == null){
            cur.next = l2;
        }else{
            cur.next = l1;
        }
        return dummyHead.next;
    }
}
