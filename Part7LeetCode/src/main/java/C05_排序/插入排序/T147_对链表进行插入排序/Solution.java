/***********************************************************
 * @Description : 147.对链表进行插入排序
 * https://leetcode-cn.com/problems/insertion-sort-list/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/30 22:40
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C05_排序.插入排序.T147_对链表进行插入排序;

import C01_线性表.单链表.ListNode;

class Solution {
    public ListNode insertionSortList(ListNode head) {
        // 初始化虚拟头结点为最小节点
        ListNode dummyHead = new ListNode(Integer.MIN_VALUE);
        dummyHead.next = head;
        ListNode cur = dummyHead;
        while (cur.next != null) {
            // pre记录需要插入位置的前面的节点
            ListNode pre = dummyHead;
            boolean swap = false;
            // 遍历cur之前的节点，找到cur所指的节点应该插入的位置
            while (pre != cur) {
                if (pre.next.val > cur.next.val) {
                    // 存储要进行插入的节点
                    ListNode tmp = cur.next;
                    // 移除cur.next节点
                    cur.next = cur.next.next;
                    // 下面3行是把cur.next移动到合适位置
                    ListNode tmp2 = pre.next;
                    pre.next = tmp;
                    tmp.next = tmp2;
                    // 是否经过了元素插入
                    swap = true;
                    // 插入完毕，提前退出
                    break;
                }
                pre = pre.next;
            }
            if (!swap) {
                // 如果没有经过元素置换，cur是不需要移动
                cur = cur.next;
            }
        }
        return dummyHead.next;
    }
}
