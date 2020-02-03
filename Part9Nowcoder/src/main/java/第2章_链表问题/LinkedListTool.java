/***********************************************************
 * @Description : 链表的显示工具类
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/17 22:14
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package 第2章_链表问题;

public class LinkedListTool {
    /**
     * 根据数组创建链表
     *
     * @param nums 数组
     * @return 创建的链表的头节点
     */
    public static Node create(int[] nums) {
        if (nums.length == 0) {
            return null;
        }
        Node head = new Node(nums[0]);
        Node cur = head;
        for (int i = 1; i < nums.length; i++) {
            cur.next = new Node(nums[i]);
            cur = cur.next;
        }
        return head;
    }

    public static void show(Node head) {
        Node curr = head;
        while (curr != null) {
            System.out.print(curr.val + " -> ");
            curr = curr.next;
        }
        System.out.println("NULL");
    }
}
