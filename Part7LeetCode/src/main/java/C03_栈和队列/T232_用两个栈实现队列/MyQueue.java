/***********************************************************
 * @Description : 232.用两个栈实现队列
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/30 18:36
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C03_栈和队列.T232_用两个栈实现队列;

import java.util.Stack;

/**
 * 类似T225.用队列实现栈
 * <p>
 * 可以用两个栈， s 和 tmp ， s 存放元素， tmp 用来作中转。
 * push(x) ， 先将 s 中的元素全部弹出来， 存入 tmp ， 把 x push 到 tmp ， 然后把 tmp 中的元素全部弹出来， 存入 s
 * pop() ， 直接将 s 的栈顶元素弹出来即可
 * <p>
 * 该算法 push 的算法复杂度是 O(n) , pop 的算法复杂度 O(1)
 */
class MyQueue {
    Stack<Integer> stack;
    Stack<Integer> stackTmp;

    /**
     * Initialize your data structure here.
     */
    public MyQueue() {
        stack = new Stack<>();
        stackTmp = new Stack<>();
    }

    /**
     * Push element x to the back of queue.
     */
    public void push(int x) {
        while (!stack.isEmpty()) {
            stackTmp.push(stack.pop());
        }
        stackTmp.push(x);
        while (!stackTmp.isEmpty()) {
            stack.push(stackTmp.pop());
        }
    }

    /**
     * Removes the element from in front of queue and returns that element.
     */
    public int pop() {
        return stack.pop();
    }

    /**
     * Get the front element.
     */
    public int peek() {
        return stack.peek();
    }

    /**
     * Returns whether the queue is empty.
     */
    public boolean empty() {
        return stack.isEmpty();
    }
}

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */
