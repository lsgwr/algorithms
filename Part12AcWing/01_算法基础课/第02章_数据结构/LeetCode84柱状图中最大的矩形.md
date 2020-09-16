```txt
给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。

求在该柱状图中，能够勾勒出来的矩形的最大面积。

 



以上是柱状图的示例，其中每个柱子的宽度为 1，给定的高度为 [2,1,5,6,2,3]。

 



图中阴影部分为所能勾勒出的最大矩形面积，其面积为 10 个单位。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/largest-rectangle-in-histogram
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
```

```java
/**
 * 向某个元素的两边分别找第一个小于自己的元素的下标(用单调栈来做)，(左右邻近的最小元素的下标差 - 1)*当前元素的值 = 包含当前元素的最大面积
 */
class Solution {

    // 向右查找第一个比当前元素小的元素(维护一个单调递增栈，从右向左遍历)，记下其坐标(栈中要存下标了)，然后更新一次最大面积
    public int largestRectangleArea(int[] nums) {
        Stack<Integer> st = new Stack<>();
        int res = 0;
        
        int[] nextSmallerIndexs = new int[nums.length];
        for (int i = nums.length - 1; i >= 0; i--) {
            // 只要栈不为空，且栈顶元素不比nums[i]小，则弹出栈顶元素。每个元素入栈一次出栈一次，所以时间复杂度为O(n))
            while (!st.isEmpty() && nums[i] <= nums[st.peek()]) st.pop();
            nextSmallerIndexs[i] = st.isEmpty() ? nums.length : st.peek(); // 右侧没有比当前元素小的元素，则面积可以更新到最后一个元素的后面一个位置
            st.push(i);
        }

        st.clear();

        int[] preSmallerIndexs = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            // 只要栈不为空，且栈顶元素不比nums[i]小，则弹出栈顶元素。每个元素入栈一次出栈一次，所以时间复杂度为O(n)
            while (!st.isEmpty() && nums[i] <= nums[st.peek()]) st.pop();
            preSmallerIndexs[i] = st.isEmpty() ? -1 : st.peek(); // 左侧没有比当前元素小的元素，则面积可以更新到最后一个元素的后面一个位置
            st.push(i);
        }

        for (int i = 0; i < nums.length; i++) res = Math.max((nextSmallerIndexs[i] - preSmallerIndexs[i] - 1) * nums[i], res);
        return res;
    }
}
```