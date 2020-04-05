/***********************************************************
 * @Description : 133.克隆图
 * https://leetcode-cn.com/problems/clone-graph/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/31 21:43
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C13_图.其他.T133_克隆图;

import java.util.*;

class Node {
    public int val;
    public List<Node> neighbors;

    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }

    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }

    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}

/**
 * Clone Graph
 * BFS， 时间复杂度O(n)， 空间复杂度O(n)
 */
public class Solution {
    public Node cloneGraph(Node node) {
        if (node == null) {
            return null;
        }
        // key is original node， value is copied node
        HashMap<Node, Node> visited = new HashMap<>();
        // each node in queue is already copied itself
        // but neighbors are not copied yet
        Queue<Node> q = new LinkedList<>();
        q.offer(node);
        visited.put(node, new Node(node.val));
        while (!q.isEmpty()) {
            Node cur = q.poll();
            for (Node nbr : cur.neighbors) {
                // a copy already exists
                if (visited.containsKey(nbr)) {
                    visited.get(cur).neighbors.add(visited.get(nbr));
                } else {
                    Node newNode =
                            new Node(nbr.val);
                    visited.put(nbr, newNode);
                    visited.get(cur).neighbors.add(newNode);
                    q.offer(nbr);
                }
            }
        }
        return visited.get(node);
    }
}
