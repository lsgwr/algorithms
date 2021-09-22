/***********************************************************
 * @Description : 
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/18 0:56
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter05LinkedList.LeetCode83RemoveDuplicated;

import Chapter05LinkedList.LinkedListTool;
import Chapter05LinkedList.ListNode;

class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode cur = head;
        while (cur != null) {
            if (cur.next != null && cur.val == cur.next.val) {
                cur.next = cur.next.next;
            }else {
                cur = cur.next;
            }
        }
        return head;
    }

    /**
     * 1->1->2  ==> 1->2
     * [] -> []
     * [1,1,1,1] ==> [1]
     * {1,1,2,3,3}  ==> [1, 2, 3]
     */
    public static void main(String[] args) {
        int[] nums = {1,1,2,3,3};
        ListNode head = LinkedListTool.create(nums);
        LinkedListTool.show(head);
        ListNode newHead = new Solution().deleteDuplicates(head);
        LinkedListTool.show(head);
    }
}
