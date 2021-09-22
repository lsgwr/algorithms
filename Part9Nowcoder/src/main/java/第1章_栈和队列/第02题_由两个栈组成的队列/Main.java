/***********************************************************
 * @Description : 用两个栈实现队列，支持队列的基本操作
 * https://www.nowcoder.com/practice/6bc058b32ee54a5fa18c62f29bae9863
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2019/12/7 16:39
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package 第1章_栈和队列.第02题_由两个栈组成的队列;

import java.util.Scanner;
import java.util.Stack;

class TwoStackQueue {
    public Stack<Integer> stackPush;
    public Stack<Integer> stackPop;

    public TwoStackQueue() {
        this.stackPush = new Stack<>();
        this.stackPop = new Stack<>();
    }

    /**
     * push栈向pop栈倒入数据
     */
    private void pushToPop() {
        if (stackPop.empty()) {
            while (!stackPush.empty()) {
                stackPop.push(stackPush.pop());
            }
        }
    }

    /**
     * 向队列添加元素
     */
    public void add(int pushInt) {
        stackPush.push(pushInt);
        pushToPop();
    }

    /**
     * 弹出队列头部元素
     */
    public int poll() {
        if (stackPop.empty() && stackPush.empty()) {
            throw new RuntimeException("Queue is empty");
        }
        pushToPop();
        return stackPop.pop();
    }

    /**
     * 查询队列头部元素是多少
     */
    public int peek() {
        if (stackPop.empty() && stackPush.empty()) {
            throw new RuntimeException("Queue is empty");
        }
        pushToPop();
        return stackPop.peek();
    }
}

/**
 * @author liangshanguang
 * @date 2019-12-07
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 对队列进行的操作总数
        int N = Integer.parseInt(sc.nextLine());
        TwoStackQueue queue = new TwoStackQueue();
        int i = 0;
        while (i < N && sc.hasNext()) {
            String line = sc.nextLine();
            if (line.contains("add")) {
                queue.add(Integer.parseInt(line.split(" ")[1]));
            }
            if (line.contains("poll")) {
                queue.poll();
            }
            if (line.contains("peek")) {
                System.out.println(queue.peek());
            }
            i++;
        }
    }
}
