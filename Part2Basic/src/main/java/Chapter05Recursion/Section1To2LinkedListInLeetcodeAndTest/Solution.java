/***********************************************************
 * @Description : 
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/5/16 20:46
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter05Recursion.Section1To2LinkedListInLeetcodeAndTest;

class Solution {
    public ListNode removeElements(ListNode head, int val) {
        // 如果头节点就是要删除的节点
        while (head != null && head.val == val) {
            // 暂存头结点
            ListNode delNode = head;
            // 头指针后移以为
            head = head.next;
            // 置为null，就会被JVM自动回收了
            delNode.next = null;
        }
        // 如果所有的节点都和要删除的节点相等，经过上面的循环，到这里链表已经为空了
        if (head == null) {
            return null;
        }

        // 如果中间遇到相等的节点，经过前面对head的过滤，此时的head一定不等于val，即一定不是要删除的节点了，我们可以放心往下找了
        ListNode prev = head;
        while (prev.next != null) {
            // 如果中间遇到相等的值的话
            if (prev.next.val == val) {
                ListNode delNode = prev.next;
                prev.next = delNode.next;
                // 置为null，即脱离原链表，就会被JVM自动回收了
                delNode.next = null;
            } else {
                // 如果没遇到的话，就继续往下走
                prev = prev.next;
            }
        }
        return head;
    }
}