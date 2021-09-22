/***********************************************************
 * @Description : 反转链表
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/17 22:40
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter05LinkedList.LeetCode206ReverseLinkedList;

import Chapter05LinkedList.LinkedListTool;
import Chapter05LinkedList.ListNode;

class Solution {
    public ListNode reverseList(ListNode head) {
        // 初始化
        ListNode pre = null;
        ListNode cur = head;
        // 只要还没遍历到最后就一直往后遍历,cur不能为空,cur.next没事，下一轮while循环就跳出了
        while (cur != null) {
            // 临时保存当前节点的下一个节点，作为下一步的cur
            ListNode next = cur.next;
            // 当前节点的指针指向前一个节点，这是链表反转的关键
            cur.next = pre;
            // 前一个节点的指针往后移
            pre = cur;
            // 更新当前节点
            cur = next;
        }
        return pre;
    }

    public static void main(String[] args) {
        int[] nums = {5, 4, 3, 2, 1};
        ListNode head = LinkedListTool.create(nums);
        LinkedListTool.show(head);
        // 返回新链表的dead
        ListNode headReverse = new Solution().reverseList(head);
        LinkedListTool.show(headReverse);
    }
}
