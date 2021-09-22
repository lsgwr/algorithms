/***********************************************************
 * @Description : 234. 回文链表
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/20 11:52
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter05LinkedList.LeetCode234PalindromeLinkedList;

import Chapter05LinkedList.LinkedListTool;
import Chapter05LinkedList.ListNode;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
// 和143号问题类似，先逆转后一半的链表，然后用双指针法逐个判断是否相等即可
class Solution {
    /**
     * 参考 92. 反转部分链表 II
     */
    public ListNode reverseBetween(ListNode head, int m, int n) {
        // 创建虚拟头结点,防止一些null导致的问题
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode cur = dummyHead;
        for (int i = 1; i < m; i++) {
            // 找到了m的上一个节点
            cur = cur.next;
        }
        // node.next就是要翻转的起点，mHead表示开始翻转的起点
        ListNode mHead = cur.next;
        ListNode next = null;
        ListNode pre = null;
        // 翻转m到n这一段，起点是mHead，参考206题
        for (int i = m; i <= n; i++) {
            next = mHead.next;
            mHead.next = pre;
            pre = mHead;
            mHead = next;
        }
        // m位置的节点指向n位置的下一个节点
        cur.next.next = next;
        // m前一个节点指向n位置处的节点
        cur.next = pre;
        // 返回虚拟头结点的下一个节点即新的头结点
        return dummyHead.next;
    }

    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        ListNode cur = head;
        // n是链表的节点总数
        int m, n = 1;
        while (cur.next != null) {
            cur = cur.next;
            n++;
        }
        if (n % 2 == 0) { // 偶数个节点
            m = n / 2 + 1;
        } else { // 奇数个节点
            m = n / 2 + 2;
        }
        // 反转第m个节点到第n个节点之间的链表，包含第m个和第n个，反转后的链表刷新下head
        head = reverseBetween(head, m, n);
        // 下面利用双指针法进行二次连接
        ListNode node2 = head;
        for (int i = 1; i < m; i++) {
            // for循环结束，mNodePre就是第m个节点前一个节点的位置
            node2 = node2.next;
        }
        // 要和m节点进行位置交换的节点
        ListNode node1 = head;
        while (node2 != null) {
            if(node1.val != node2.val){
                return false;
            }
            node1 = node1.next;
            node2 = node2.next;
        }
        return true;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2};
        ListNode head = LinkedListTool.create(nums);
        boolean palindrome = new Solution().isPalindrome(head);
        System.out.println(palindrome);
    }
}
