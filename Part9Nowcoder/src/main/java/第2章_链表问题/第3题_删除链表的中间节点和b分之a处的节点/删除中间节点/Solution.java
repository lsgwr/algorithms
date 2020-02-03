/***********************************************************
 * @Description : 删除链表的中间节点，类似leetCode 876
 * https://www.nowcoder.com/practice/0796dbf0eb054716937b0b82e0671c5f
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/2/3 17:46
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package 第2章_链表问题.第3题_删除链表的中间节点和b分之a处的节点.删除中间节点;

import 第2章_链表问题.LinkedListTool;
import 第2章_链表问题.ListNode;

import java.util.Scanner;

public class Solution {
    /**
     * 快慢指针法，快指针一次两步，慢指针一次一步，快指针走到头时慢指针正好到中间
     */
    public ListNode removeMidNode(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        if (head.next.next == null) {
            return head.next;
        }
        ListNode pre = head;
        ListNode cur = head.next.next;
        while (cur.next != null && cur.next.next != null) {
            pre = pre.next;
            cur = cur.next.next;
        }
        // 快指针走到头，慢指针就是要删除的中间节点
        pre.next = pre.next.next;
        return head;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] nums = new int[N];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = sc.nextInt();
        }
        ListNode head = LinkedListTool.create(nums);
        head = new Solution().removeMidNode(head);
        while (head!=null){
            System.out.print(head.val + "\t");
            head = head.next;
        }
    }
}
/**
 * 输入：
 * 5
 * 1 2 3 4 5
 *
 * 输出：
 * 1	2	4	5
 */
