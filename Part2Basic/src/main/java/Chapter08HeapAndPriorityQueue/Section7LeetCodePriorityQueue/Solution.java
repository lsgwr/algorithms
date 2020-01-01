/***********************************************************
 * @Description : 
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/1 18:22
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter08HeapAndPriorityQueue.Section7LeetCodePriorityQueue;

import java.util.*;

class Solution {
    public List<Integer> topKFrequent(int[] nums, int k) {
        // 元素和出现频次的映射Map
        Map<Integer, Integer> mapNumFreq = new HashMap<>();
        for (int num : nums) {
            // 更新map里面的值
            if (mapNumFreq.get(num) == null) {
                mapNumFreq.put(num, 1);
            } else {
                mapNumFreq.put(num, mapNumFreq.get(num) + 1);
            }
        }
        PriorityQueue<Integer> pq = new PriorityQueue<>(k, new Comparator<Integer>() {
            @Override
            public int compare(Integer key1, Integer key2) {
                // 自定义比较器，找出现频次较高地需要每次弹出最小值
                return mapNumFreq.get(key1) - mapNumFreq.get(key2);
            }
        });
        for (Integer key : mapNumFreq.keySet()) {
            if (pq.size() == k) {
                // 满了需要和栈顶元素比较，比栈顶元素大就加入到队列中
                if (mapNumFreq.get(key) > mapNumFreq.get(pq.peek())) {
                    pq.poll();
                    pq.add(key);
                }
            } else {
                // 优先队列没满就直接加入
                pq.add(key);
            }

        }
        List<Integer> result = new ArrayList<>();
        while (!pq.isEmpty()) {
            result.add(pq.poll());
        }
        // 从高频到低频
        Collections.reverse(result);
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 2, 2, 3};
        int k = 2;
        System.out.println(new Solution().topKFrequent(nums, k));
    }
}
/***
 * [1, 2]
 */
