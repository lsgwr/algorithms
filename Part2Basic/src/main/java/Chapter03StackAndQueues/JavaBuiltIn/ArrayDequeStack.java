package 
Chapter03StackAndQueues.JavaBuiltIn;

import java.util.ArrayDeque;

/**
 * ArrayDeque当成栈来使用
 */
public class ArrayDequeStack {

    public static void main(String[] args) {
        ArrayDeque<String> stack = new ArrayDeque<>();
        // 入栈
        stack.push("Java");
        stack.push("Python");
        stack.push("C++");
        System.out.println(stack);
        // 出栈
        System.out.println(stack.pop());
        System.out.println(stack);
        // 显示第一个元素但是不出栈
        System.out.println(stack.peek());
        System.out.println(stack);
    }
}
