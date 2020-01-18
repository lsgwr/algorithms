/***********************************************************
 * @Description : 第237号问题：删除链表中的元素
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/18 23:09
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter05LinkedList.LeetCode237RemoveNode;

import Chapter05LinkedList.ListNode;

class Solution {
    public void deleteNode(ListNode node) {
        // 相当于删除当前节点，因为不知道当前节点的上一个节点，所以把当前节点的下一个节点的val复制到当前节点并释放下一个节点即可
        node.val = node.next.val;
        node.next = node.next.next;
    }
}
