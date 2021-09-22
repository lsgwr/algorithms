/***********************************************************
 * @Description : 比较基于动态数组实现地栈和基于链表实现的栈的区别
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/5/16 00:20
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter04LinkedList.Section6LinkedListStack;

import Chapter03StackAndQueues.Section2ArrayStack.ArrayStack;
import Chapter03StackAndQueues.Section2ArrayStack.Stack;

import java.util.Random;

public class Main {
    /**
     * 测试使用stack运行opCount个push和pop操作所需要的时间，单位：秒
     */
    private static double testStack(Stack<Integer> stack, int opCount) {

        long startTime = System.nanoTime();

        Random random = new Random();
        for (int i = 0; i < opCount; i++) {
            stack.push(random.nextInt(Integer.MAX_VALUE));
        }
        for (int i = 0; i < opCount; i++) {
            stack.pop();
        }

        long endTime = System.nanoTime();

        return (endTime - startTime) / 1000000000.0;
    }

    public static void main(String[] args) {

        int opCount = 100000;

        ArrayStack<Integer> arrayStack = new ArrayStack<>();
        double time1 = testStack(arrayStack, opCount);
        System.out.println("基于数组实现的栈ArrayStack, time: " + time1 + " s");

        LinkedListStack<Integer> linkedListStack = new LinkedListStack<>();
        double time2 = testStack(linkedListStack, opCount);
        System.out.println("基于链表实现的栈LinkedListStack, time: " + time2 + " s");

        JdkLinkedListStack<Integer> jdkLinkedListStack = new JdkLinkedListStack<>();
        double time3 = testStack(jdkLinkedListStack, opCount);
        System.out.println("基于JDK自带的链表实现的栈JdkLinkedListStack, time: " + time3 + " s");

        // 其实这个时间比较很复杂，因为LinkedListStack中包含更多的new操作
    }
}
/**
 * 基于数组实现的栈ArrayStack, time: 0.0096867 s
 * 基于链表实现的栈LinkedListStack, time: 0.0083597 s
 * 基于JDK自带的链表实现的栈JdkLinkedListStack, time: 0.0082778 s
 */