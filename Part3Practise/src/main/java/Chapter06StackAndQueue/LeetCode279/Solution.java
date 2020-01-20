/***********************************************************
 * @Description : 279. 完全平方数
 * 借用BFS求无权图最短路径的实现得到一个数最少能拆分成多少个平方数
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/20 23:35
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter06StackAndQueue.LeetCode279;

import java.util.*;

public class Solution {
    private class Node {
        /**
         * 节点编号
         */
        int num;
        /**
         * 节点到起点的距离(无权图中就是节点个数)
         */
        int distance;

        public Node(int num, int distance) {
            this.num = num;
            this.distance = distance;
        }
    }

    public int numSquares(int n) {
        // 这里用map表达图中的一个节点，键代表节点，值代表距离起点的距离(无权图可以认为是经过的节点个数)
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(new Node(n, 0));
        boolean[] visited = new boolean[n];
        while (!queue.isEmpty()) {
            Node node = queue.remove();
            if (node.num == 0) {
                // 这里我们只用到了距离，不需要具体的路径，需要完整路径地话还需要pre数组
                return node.distance;
            }
            // 遍历邻接点，把他们加入到队列中
            for (int i = 1; node.num - i * i >= 0; i++) {
                if (!visited[node.num - i * i]) {
                    queue.add(new Node(node.num - i * i, node.distance + 1));
                    visited[node.num - i * i] = true;
                }
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        // 3
        System.out.println(new Solution().numSquares(12));
    }
}
