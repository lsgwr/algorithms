/***********************************************************
 * @Description : 215. 数组中的第K个最大元素
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/13 22:46
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter03.LeetCode215;

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

    public static void main(String[] args) {
        int[] nums = {3, 2, 1, 5, 6, 4};
        int k = 2;
        System.out.println(new Solution().findKthLargest(nums, k));
    }
}
