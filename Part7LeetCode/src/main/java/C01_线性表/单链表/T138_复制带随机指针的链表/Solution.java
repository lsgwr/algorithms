/***********************************************************
 * @Description : 138.复制带随机指针的链表
 * https://leetcode-cn.com/problems/copy-list-with-random-pointer/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/29 21:31
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C01_线性表.单链表.T138_复制带随机指针的链表;

class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}

/**
 * 两遍扫描， 时间复杂度O(n)， 空间复杂度O(1)
 */
class Solution {
    public Node copyRandomList(Node head) {
        for (Node cur = head; cur != null; ) {
            Node node = new Node(cur.val);
            node.next = cur.next;
            cur.next = node;
            cur = node.next;
        }
        for (Node cur = head; cur != null; ) {
            if (cur.random != null) {
                cur.next.random = cur.random.next;
            }
            cur = cur.next.next;
        }
        // 分拆两个单链表
        Node dummy = new Node(-1);
        for (Node cur = head, new_cur = dummy;
             cur != null; ) {
            new_cur.next = cur.next;
            new_cur = new_cur.next;
            cur.next = cur.next.next;
            cur = cur.next;
        }
        return dummy.next;
    }
}
