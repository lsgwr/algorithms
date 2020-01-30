/***********************************************************
 * @Description : 225.用两个队列实现栈
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/30 18:28
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C03_栈和队列.T225_用队列实现栈;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 可以用两个队列， q 和 tmp ， q 存放元素， tmp 用来作中转。push(x) ， 先将 x push 到 tmp ，
 * 然后把 q 中的元素全部弹出来， 存入 tmp ， 最后切换 q 和 tmp
 * <p>
 * 该算法 push 的算法复杂度是 O(n) , pop 的算法复杂度 O(1)
 */
class MyStack {
    private Queue<Integer> queue;
    private Queue<Integer> queueTmp;

    /**
     * Initialize your data structure here.
     */
    public MyStack() {
        queue = new ArrayDeque<>();
        queueTmp = new ArrayDeque<>();
    }

    /**
     * Push element x onto stack.
     */
    public void push(int x) {
        queueTmp.add(x);
        while (!queue.isEmpty()) {
            queueTmp.add(queue.remove());
        }
        Queue<Integer> tmp = queue;
        queue = queueTmp;
        queueTmp = tmp;
    }

    /**
     * Removes the element on top of the stack and returns that element.
     */
    public int pop() {
        return queue.remove();
    }

    /**
     * Get the top element.
     */
    public int top() {
        return queue.peek();
    }

    /**
     * Returns whether the stack is empty.
     */
    public boolean empty() {
        return queue.isEmpty();
    }
}

/**
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */
