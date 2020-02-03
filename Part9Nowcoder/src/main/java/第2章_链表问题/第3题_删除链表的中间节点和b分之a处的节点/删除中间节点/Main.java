/***********************************************************
 * @Description : 删除链表的中间节点，类似leetCode 876
 * https://www.nowcoder.com/practice/0796dbf0eb054716937b0b82e0671c5f
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/2/3 17:46
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package 第2章_链表问题.第3题_删除链表的中间节点和b分之a处的节点.删除中间节点;

import 第2章_链表问题.Node;

public class Main {
    /**
     * 快慢指针法，快指针一次两步，慢指针一次一步，快指针走到头时慢指针正好到中间
     */
    public Node removeMidNode(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        if (head.next.next == null) {
            return head.next;
        }
        Node pre = head;
        Node cur = head.next.next;
        while (cur.next != null && cur.next.next != null) {
            pre = pre.next;
            cur = cur.next.next;
        }
        // 快指针走到头，慢指针就是要删除的中间节点
        pre.next = pre.next.next;
        return head;
    }
}
