/***********************************************************
 * @Description : 虚拟头结点可以大大简化代码
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/5/16 20:46
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter05Recursion.Section1To2LeetCode203;

class Solutio2 {
    public ListNode removeElements(ListNode head, int val) {
        // 虚拟头结点，方便代码理解，不用单独考虑头节点了，都可以按照pre.next来处理
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;
        // 如果中间遇到相等的节点
        ListNode prev = dummyHead;
        while (prev.next != null) {
            // 如果中间遇到相等的值的话
            if (prev.next.val == val) {
                prev.next = prev.next.next;
            } else {
                // 如果没遇到的话，就继续往下走
                prev = prev.next;
            }
        }
        return dummyHead.next;
    }
}