/***********************************************************
 * @Description : 删除b分之a处的节点
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/2/3 18:32
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package 第2章_链表问题.第3题_删除链表的中间节点和b分之a处的节点.删除b分之a处的节点;

import 第2章_链表问题.LinkedListTool;
import 第2章_链表问题.ListNode;

import java.util.Scanner;

public class Solution {
    public ListNode removeByRatio(ListNode head, int a, int b) {
        if (a < 1 || a > b) {
            // 比例范围过小或过大都不合理
            return head;
        }
        int n = 0;
        ListNode cur = head;
        while (cur != null) {
            n++;
            cur = cur.next;
        }
        double ratio = (a * n) * 1.0 / b;
        // 比例向上取整，决定应该删除那个元素
        n = (int) Math.ceil(ratio);
        if (n == 1) {
            // 删除头元素地话
            head = head.next;
        }
        if (n > 1) {
            cur = head;
            while (--n != 1) {
                cur = cur.next;
            }
            cur.next = cur.next.next;
        }
        return head;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int a = sc.nextInt();
        int b = sc.nextInt();
        int[] nums = new int[N];
        for (int i = 0; i < N; i++) {
            nums[i] = sc.nextInt();
        }
        ListNode head = LinkedListTool.create(nums);
        head = new Solution().removeByRatio(head, a, b);
        while (head != null) {
            System.out.print(head.val + "\t");
            head = head.next;
        }
    }
}
/**
 * 输入：
 * 5 2 3
 * 1 2 3 4 5
 * <p>
 * 输出：
 * 1	2	3	5
 */