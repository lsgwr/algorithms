/***********************************************************
 * @Description : 82.删除所有重复的节点
 * 执行时间1ms，击败了98.66%的java提交
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/18 20:21
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter05LinkedList.LeetCode82RemoveDuplicatesII;

import Chapter05LinkedList.LinkedListTool;
import Chapter05LinkedList.ListNode;

class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode cur = dummyHead;
        while (cur.next != null && cur.next.next != null) {
            if (cur.next.val == cur.next.next.val) {
                // 重复元素的起点
                ListNode del = cur.next;
                while (del.next != null && del.val == del.next.val) {
                    del = del.next;
                }
                // while退出时，del走到了最后一个重复元素的位置，在这里设置下
                cur.next = del.next;
            } else {
                cur = cur.next;
            }
        }
        return dummyHead.next;
    }

    /**
     * 特殊用例：
     * []  ==> []
     * [1] ==> [1]
     * [1, 1] ==> []  这个是 while (cur.next!=null)的来源
     *
     * [1,2,3,3,4,4,5] ==> [1, 2, 5]
     */
    public static void main(String[] args) {
        int[] nums = {1, 1};
        ListNode head = LinkedListTool.create(nums);
        ListNode newHead = new Solution().deleteDuplicates(head);
        LinkedListTool.show(newHead);
    }
}
