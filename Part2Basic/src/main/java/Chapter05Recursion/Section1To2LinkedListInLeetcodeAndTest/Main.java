/***********************************************************
 * @Description : 测试自己实现的部分链表
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/5/16 21:15
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter05Recursion.Section1To2LinkedListInLeetcodeAndTest;

public class Main {
    public static void main(String[] args) {
        int[] nums = {1, 2, 6, 3, 4, 5, 6};
        ListNode head = new ListNode(nums);
        System.out.println(head);
        ListNode result = (new Solution()).removeElements(head, 6);
        System.out.println(result);
    }

}
