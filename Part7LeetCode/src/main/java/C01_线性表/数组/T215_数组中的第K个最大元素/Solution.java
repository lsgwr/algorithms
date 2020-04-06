/***********************************************************
 * @Description : 215.数组中的第K个最大元素
 * https://leetcode-cn.com/problems/kth-largest-element-in-an-array/
 * 在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/4/6 11:04
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C01_线性表.数组.T215_数组中的第K个最大元素;

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
