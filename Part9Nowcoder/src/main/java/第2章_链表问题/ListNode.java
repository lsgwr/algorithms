/***********************************************************
 * @Description : 链表的节点
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/17 22:13
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package 第2章_链表问题;

public class ListNode {
    public int val;
    public ListNode next;

    public ListNode(int x) {
        val = x;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        ListNode cur = this;
        while (cur != null) {
            sb.append(cur.val).append(" -> ");
            cur = cur.next;
        }
        sb.append("NULL");
        return sb.toString();
    }
}
