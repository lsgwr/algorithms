/***********************************************************
 * @Description : 143.重排链表
 * https://leetcode-cn.com/problems/reorder-list/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/29 21:56
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C01_线性表.单链表.T143_重排链表;

import C01_线性表.单链表.ListNode;

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

    public void reorderList(ListNode head) {
        if (head == null || head.next == null) {
            return;
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
        ListNode preM = head;
        for (int i = 2; i < m; i++) {
            // for循环结束，mNodePre就是第m个节点前一个节点的位置
            preM = preM.next;
        }
        // 要和m节点进行位置交换的节点
        ListNode pre = head;
        // pre != preM是为了防止偶数的最后一对，这一对不用交换
        while (preM.next != null && pre != preM) {
            // 下面对调链表中的两个节点，参考 第24号问题：Swap Nodes in Pairs
            // 1.更新两个要进行交换的节点，各自的前面那个节点分别是pre和preM
            ListNode node1 = pre.next;
            ListNode node2 = preM.next;

            // 把node2插入到node1前面
            preM.next = node2.next;
            pre.next = node2;
            node2.next = node1;
            // preM不用换，还可以用之前的？
            // pre变成交换后的node2的下一个节点，直接后面就是要进行交换的另一个节点
            pre = node2.next;
        }
    }
}
