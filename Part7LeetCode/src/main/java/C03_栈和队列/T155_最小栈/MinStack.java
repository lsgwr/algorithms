/***********************************************************
 * @Description : 155.最小栈
 * https://leetcode-cn.com/problems/min-stack/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/30 17:30
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C03_栈和队列.T155_最小栈;

import java.util.Stack;

/**
 * 用一个辅助栈，保存每步的最小值
 **/
class MinStack {
    /**
     * 原始数据栈
     */
    private Stack<Integer> stack;
    /**
     * 保存每步最小值的栈
     */
    private Stack<Integer> stackMin;

    /**
     * initialize your data structure here.
     */
    public MinStack() {
        stack = new Stack<>();
        stackMin = new Stack<>();
    }

    public void push(int x) {
        stack.push(x);
        int minVal = stackMin.isEmpty() ? x : Math.min(stackMin.peek(), x);
        stackMin.push(minVal);
    }

    public void pop() {
        stack.pop();
        stackMin.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return stackMin.peek();
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
