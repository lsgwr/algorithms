/***********************************************************
 * @Description : 虚拟头结点可以大大简化代码
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2019/12/29 14:18
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter05Recursion.Section4LeetCode203Recursion;

import Chapter05Recursion.Section1To2LeetCode203.ListNode;

class Solution {
    public ListNode removeElements(ListNode head, int val) {
        if (head == null) {
            return null;
        }
        head.next = removeElements(head.next, val);
        // head节点要删除就直接跳过head节点，否则就返回原来的
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