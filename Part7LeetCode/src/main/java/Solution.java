import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

// https://leetcode-cn.com/problems/meeting-rooms-ii/solution/hui-yi-shi-ii-by-leetcode/
class Solution {
    static class PII implements Comparable<PII> {
        int first, second; // 会议的开始时间和结束时间

        public PII(int first, int second) {
            this.first = first;
            this.second = second;
        }

        @Override
        public int compareTo(PII o) {
            return first - o.first;
        }
    }

    public int minMeetingRooms(int[][] intervals) {
        List<PII> list = new ArrayList<>();
        for (int[] interval : intervals) {
            list.add(new PII(interval[0], interval[1])); // 根据左右端点创建PII对象
        }
        Collections.sort(list); // 按照会议开始时间排序
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (PII pii : list) {
            if (pq.isEmpty()) {
                pq.add(pii.second); // 加入结束时间
                continue;
            }
            if (pii.first >= pq.peek()) { // 新的会议开始时间在前面的最早结束的会议的后面，因此可以排在其后面，会议室并没有增加
                pq.remove(); // 弹出之前已经结束的最早的会议
                pq.add(pii.second); // 把当前的会议的结束时间加进去，表示占住了上面那个结束的会议室
            } else { // // 新的会议开始时间在前面的最早结束的会议的前面，没办法，只能加会议室了
                pq.add(pii.second);
            }
        }
        return pq.size();
    }
}