package Chapter03StackAndQueues.JavaBuiltIn;

import java.util.ArrayDeque;

/**
 * ArrayDeque作为队列使用
 */
public class ArrayDequeQueue {
    public static void main(String[] args) {
        ArrayDeque<String> queue = new ArrayDeque<>();
        // 入队
        queue.offer("Java");
        queue.offer("Python");
        queue.offer("C++");
        System.out.println(queue);

        // 出队
        System.out.println(queue.poll());
        System.out.println(queue);
        // 打印队首元素，但是并不出队
        System.out.println(queue.peek());
        System.out.println(queue);
    }
}
