/***********************************************************
 * @Description : 虚拟头结点可以大大简化代码
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2019/12/29 14:18
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter05Recursion.Section4LeetCode203Recursion;

import Chapter05Recursion.Section1To2LeetCode203.ListNode;

class SolutionDebug {
    /**
     * 删除链表中值为val的所有元素
     *
     * @param head  链表的头结点
     * @param val   要删除的节点的值
     * @param depth 递归的深度
     * @return
     */
    public ListNode removeElements(ListNode head, int val, int depth) {
        System.out.print(generateRecursionDepthStr(depth));
        System.out.println("第" + depth + "层递归：head=" + head + "，要找的值为节点值为：" + val);
        if (head == null) {
            return null;
        }
        head.next = removeElements(head.next, val, depth + 1);
        System.out.print(generateRecursionDepthStr(depth));
        System.out.println("第" + depth + "层递归：head=" + head + "，要找的值为节点值为：" + val);
        // head节点要删除就直接跳过head节点，否则就返回原来的
        return head.val == val ? head.next : head;
    }

    /**
     * 生成递归深度的字符串
     *
     * @param depth 递归层数
     * @return depth*2个"--"
     */
    private String generateRecursionDepthStr(int depth) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            sb.append("--");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 6, 3, 4, 5, 6};
        ListNode head = new ListNode(nums);
        ListNode result = (new SolutionDebug()).removeElements(head, 6, 1);
        System.out.println();
        System.out.println("递归结束，结果为：：" + result);
    }
}
/**
 * --第1层递归：head=1->2->6->3->4->5->6->NULL，要找的值为节点值为：6
 * ----第2层递归：head=2->6->3->4->5->6->NULL，要找的值为节点值为：6
 * ------第3层递归：head=6->3->4->5->6->NULL，要找的值为节点值为：6
 * --------第4层递归：head=3->4->5->6->NULL，要找的值为节点值为：6
 * ----------第5层递归：head=4->5->6->NULL，要找的值为节点值为：6
 * ------------第6层递归：head=5->6->NULL，要找的值为节点值为：6
 * --------------第7层递归：head=6->NULL，要找的值为节点值为：6
 * ----------------第8层递归：head=null，要找的值为节点值为：6
 * --------------第7层递归：head=6->NULL，要找的值为节点值为：6
 * ------------第6层递归：head=5->NULL，要找的值为节点值为：6
 * ----------第5层递归：head=4->5->NULL，要找的值为节点值为：6
 * --------第4层递归：head=3->4->5->NULL，要找的值为节点值为：6
 * ------第3层递归：head=6->3->4->5->NULL，要找的值为节点值为：6
 * ----第2层递归：head=2->3->4->5->NULL，要找的值为节点值为：6
 * --第1层递归：head=1->2->3->4->5->NULL，要找的值为节点值为：6
 *
 * 递归结束，结果为：：1->2->3->4->5->NULL
 */