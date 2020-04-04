/***********************************************************
 * @Description : 203.删除链表中等于指定值的所有元素
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/4/4 10:11
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C01_线性表.单链表.T203_删除链表中等于指定值的所有元素;

import C01_线性表.单链表.ListNode;

class Solution {
    public ListNode removeElements(ListNode head, int val) {
        if (head == null){
            return null;
        }
        head.next = removeElements(head.next, val);
        return head.val == val ? head.next : head;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 6, 3, 4, 5, 6};
        ListNode head = new ListNode(nums);
        System.out.println(head);
        ListNode result = (new Solution()).removeElements(head, 6);
        System.out.println(result);
    }
}
