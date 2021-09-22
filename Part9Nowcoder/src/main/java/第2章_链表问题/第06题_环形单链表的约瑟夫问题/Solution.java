/***********************************************************
 * @Description : 第06题_环形单链表的约瑟夫问题
 *
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/2/3 19:16
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package 第2章_链表问题.第06题_环形单链表的约瑟夫问题;

import 第2章_链表问题.ListNode;


public class Solution {
    /**
     * 没累计到m就删除一个节点
     */
    public ListNode josephusKill(ListNode head, int m) {
        if (head == null || head.next == head || m < 1) {
            return head;
        }
        ListNode cur = head.next;
        int tmp = 1;
        while (cur != head) {
            tmp++;
            cur = cur.next;
        }
        tmp = getLive(tmp, m);
        while (--tmp != 0) {
            head = head.next;
        }
        head.next = head;
        return head;
    }

    public int getLive(int i, int m) {
        if (i == 1) {
            return 1;
        }
        return (getLive(i - 1, m) + m - 1) % i + 1;
    }
}
