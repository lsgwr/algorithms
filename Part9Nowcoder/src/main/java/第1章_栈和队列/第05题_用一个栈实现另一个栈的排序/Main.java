/***********************************************************
 * @Description : 
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/28 23:22
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package 第1章_栈和队列.第05题_用一个栈实现另一个栈的排序;

import java.util.ArrayDeque;
import java.util.Scanner;

public class Main {
    public static void sortStackByTheOtherStack(ArrayDeque<Integer> stack) {
        ArrayDeque<Integer> helperStack = new ArrayDeque<>();
        while (!stack.isEmpty()) {
            int cur = stack.pop();
            while (!helperStack.isEmpty() && helperStack.peek() < cur) {
                stack.push(helperStack.pop());
            }
            helperStack.push(cur);
        }
        while (!helperStack.isEmpty()) {
            stack.push(helperStack.pop());
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 操作总数
        int N = sc.nextInt();
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < N; i++) {
            stack.push(sc.nextInt());
        }
        sortStackByTheOtherStack(stack);
        while (!stack.isEmpty()) {
            System.out.print(stack.pop() + " ");
        }
    }
}
