package C09_深度优先搜索.T430_扁平化多级双向链表;

import java.util.ArrayList;
import java.util.List;

/**
 * Definition for a Node.
 */
class Node {
    public int val;
    public Node prev;
    public Node next;
    public Node child;
};

class Solution {
    public Node flatten(Node head) {
        if (head == null) {
            return null;
        }
        // DFS遍历链表得到扁平结果后再创建链表把值写入进去
        List<Integer> orderList = new ArrayList<>();
        dfs(head, orderList);
        // 设置虚拟头结点发，防止需要特殊处理空节点和单节点的情况
        Node newHead = new Node();
        newHead.val = head.val;
        // 定义当前节点的指针
        Node cur = newHead;
        for (int i = 1; i < orderList.size(); i++) {
            Node newNode = new Node();
            newNode.val = orderList.get(i);
            // 设置双向指针
            cur.next = newNode;
            newNode.prev = cur;
            // 更新cur
            cur = newNode;
        }
        return newHead;
    }

    private void dfs(Node head, List<Integer> orderList) {
        if (head == null) {
            return;
        }
        // 没遍历到一个节点就把当前的节点值加入到列表中
        orderList.add(head.val);
        // 有child节点先遍历child节点
        if (head.child != null) {
            dfs(head.child, orderList);
        }
        // next节点肯定是要遍历地
        dfs(head.next, orderList);
    }
}
