/***********************************************************
 * @Description : 第09题_复制带随机指针的链表，LeetCode 138
 * 参考教程：https://leetcode-cn.com/problems/copy-list-with-random-pointer/solution/fu-zhi-dai-sui-ji-zhi-zhen-de-lian-biao-by-leetcod/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/2/3 23:15
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package 第2章_链表问题.第09题_复制带随机指针的链表;

import java.util.HashMap;
import java.util.Map;

class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}

public class Solution {
    public Node copyRandomList(Node head) {
        Map<Node, Node> map = new HashMap<>();
        Node cur = head;
        while(cur != null){
            // 1.复制节点
            map.put(cur, new Node(cur.val));
            cur = cur.next;
        }
        cur = head;
        while(cur != null){
            // 2.复制rand指针
            map.get(cur).next = map.get(cur.next);
            // 3.赋值random指针
            map.get(cur).random = map.get(cur.random);
            cur = cur.next;
        }
        return map.get(head);
    }
}
