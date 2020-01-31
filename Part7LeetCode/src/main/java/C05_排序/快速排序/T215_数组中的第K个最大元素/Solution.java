/***********************************************************
 * @Description : 215.数组中的第K个最大元素
 * https://leetcode-cn.com/problems/kth-largest-element-in-an-array/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/31 09:32
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C05_排序.快速排序.T215_数组中的第K个最大元素;

import java.util.PriorityQueue;

class Solution {
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(k);
        for (int num : nums) {
            if (pq.size() < k) {
                pq.offer(num);
            } else {
                pq.offer(num);
                pq.poll();
            }
        }
        return pq.poll();
    }
}
