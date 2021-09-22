/***********************************************************
 * @Description : 第01题_打印两个有序链表的公共部分
 * https://www.nowcoder.com/practice/8943eea40dbb4185b187d80fd050fee9
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/29 11:56
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package 第2章_链表问题.第01题_打印两个有序链表的公共部分;

import 第2章_链表问题.LinkedListTool;
import 第2章_链表问题.ListNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static List<Integer> getCommonPart(ListNode head1, ListNode head2) {
        List<Integer> result = new ArrayList<>();
        while (head1 != null && head2 != null) {
            if (head1.val < head2.val) {
                head1 = head1.next;
            } else if (head1.val > head2.val) {
                head2 = head2.next;
            } else {
                result.add(head1.val);
                head1 = head1.next;
                head2 = head2.next;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N1 = sc.nextInt();
        int[] nums1 = new int[N1];
        for (int i = 0; i < N1; i++) {
            nums1[i] = sc.nextInt();
        }
        int N2 = sc.nextInt();
        int[] nums2 = new int[N2];
        for (int i = 0; i < N2; i++) {
            nums2[i] = sc.nextInt();
        }
        ListNode head1 = LinkedListTool.create(nums1);
        ListNode head2 = LinkedListTool.create(nums2);
        List<Integer> result = getCommonPart(head1, head2);
        for (Integer num : result) {
            System.out.print(num + " ");
        }
    }
}
