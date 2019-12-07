/***********************************************************
 * @Description : 设计一个有getMin功能的栈
 * https://www.nowcoder.com/practice/05e57ce2cd8e4a1eae8c3b0a7e9886be
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2019/12/7 15:14
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package 第1章_栈和队列.设计一个有getMin功能的栈;

import java.util.Scanner;
import java.util.Stack;

/**
 * 解法1
 */
class MyStack1 {
    private Stack<Integer> stackData;
    private Stack<Integer> stackMin;

    public MyStack1() {
        this.stackData = new Stack<>();
        this.stackMin = new Stack<>();
    }

    public void push(int newNum) {
        if (this.stackMin.isEmpty() || newNum <= this.getMin()) {
            // 最小栈为空或者新元素比最小栈栈顶元素还小，就压入元素到最小栈
            this.stackMin.push(newNum);
        }
        this.stackData.push(newNum);
    }

    public int pop() {
        if (this.stackData.isEmpty()) {
            throw new RuntimeException("Your stack is empty!");
        }
        int value = this.stackData.pop();
        if (value == this.getMin()) {
            this.stackMin.pop();
        }
        return value;
    }

    public int getMin() {
        if (this.stackMin.isEmpty()) {
            throw new RuntimeException("Your stack is empty!");
        }
        return this.stackMin.peek();
    }
}

/**
 * 解法2
 */
class MyStack2 {
    private Stack<Integer> stackData;
    private Stack<Integer> stackMin;

    public MyStack2() {
        this.stackData = new Stack<>();
        this.stackMin = new Stack<>();
    }

    public void push(int newNum) {
        if (this.stackMin.isEmpty() || newNum <= this.getMin()) {
            // 最小栈为空或者新元素比最小栈栈顶元素还小，就压入元素到最小栈
            this.stackMin.push(newNum);
        } else {
            // 新元素比最小栈的栈顶元素还小，就把最小栈的栈顶元素再压入一次
            this.stackMin.push(this.getMin());
        }
        this.stackData.push(newNum);
    }

    public int pop() {
        if (this.stackData.isEmpty()) {
            throw new RuntimeException("Your stack is empty!");
        }
        this.stackMin.pop();
        return this.stackData.pop();
    }

    public int getMin() {
        if (this.stackMin.isEmpty()) {
            throw new RuntimeException("Your stack is empty!");
        }
        return this.stackMin.peek();
    }
}

/**
 * @author liangshanguang
 * @date 2019-12-07
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 对栈进行的操作总数
        int N = Integer.parseInt(sc.nextLine());
        // MyStack1 stack = new MyStack1();
        MyStack2 stack = new MyStack2();
        int i = 0;
        while (i < N && sc.hasNext()) {
            String line = sc.nextLine();
            if (line.contains("push")) {
                stack.push(Integer.parseInt(line.split(" ")[1]));
            }
            if (line.contains("pop")) {
                stack.pop();
            }
            if (line.contains("getMin")) {
                System.out.println(stack.getMin());
            }
            i++;
        }
    }
}
