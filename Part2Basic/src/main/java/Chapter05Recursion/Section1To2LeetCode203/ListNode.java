/***********************************************************
 * @Description : 完善官方给与的链表
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/5/16 20:45
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter05Recursion.Section1To2LeetCode203;

public class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }

    /**
     * 链表节点的构造函数
     * 使用arr为参数，创建一个链表，当前的ListNode为链表头结点
     */
    public ListNode(int[] arr) {

        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("arr can not be empty");
        }

        this.val = arr[0];
        ListNode cur = this;
        for (int i = 1; i < arr.length; i++) {
            cur.next = new ListNode(arr[i]);
            // cur指向cur.next
            cur = cur.next;
        }
    }

    /**
     * 以当前节点为头结点的链表信息字符串
     */
    @Override
    public String toString() {

        StringBuilder s = new StringBuilder();
        ListNode cur = this;
        while (cur != null) {
            s.append(cur.val).append("->");
            // cur指向cur.next
            cur = cur.next;
        }
        s.append("NULL");
        return s.toString();
    }
}
