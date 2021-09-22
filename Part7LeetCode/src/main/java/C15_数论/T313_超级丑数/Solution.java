/***********************************************************
 * @Description : 313.超级丑数
 * https://leetcode-cn.com/problems/super-ugly-number/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/31 22:39
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C15_数论.T313_超级丑数;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Super Ugly Number
 * Time complexity: O(n), Space complexity: O(n)
 */
public class Solution {
    public int nthSuperUglyNumber(int n, int[] primes) {
        final int[] nums = new int[n];
        // 1 is the first ugly number
        nums[0] = 1;
        final Queue<Node> q = new PriorityQueue<>();
        for (int prime : primes) {
            q.add(new Node(0, prime, prime));
        }
        for (int i = 1; i < n; ++i) {
            // get the min element and add to nums
            Node node = q.peek();
            nums[i] = node.val;
            // update top elements
            do {
                node = q.poll();
                node.val = nums[++node.index] * node.prime;
                // push it back
                q.add(node);
                // prevent duplicate
            } while (!q.isEmpty() && q.peek().val == nums[i]);
        }
        return nums[n - 1];
    }

    static class Node implements Comparable<Node> {
        private int index;
        private int val;
        private int prime;

        public Node(int index, int val, int prime) {
            this.index = index;
            this.val = val;
            this.prime = prime;
        }

        public int compareTo(Node other) {
            return this.val - other.val;
        }
    }
}
