/***********************************************************
 * @Description : 删除无序链表中值重复出现的节点
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/2/4 11:34
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package 第2章_链表问题.删除无序链表中值重复出现的节点;

import 第2章_链表问题.ListNode;

import java.util.HashSet;
import java.util.Set;

public class Solution {
    public void removeDup(ListNode head) {
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        Set<Integer> set = new HashSet<>();
        ListNode pre = dummyHead;
        ListNode cur = pre.next;
        while (cur != null) {
            if (set.contains(cur.val)) {
                // 节点值之前存在，就删除节点cur
                pre.next = cur.next;
            } else {
                // 节点值之前不存在就加入到set中
                set.add(cur.val);
                pre = cur;
            }
            cur = cur.next;
        }
    }
}
