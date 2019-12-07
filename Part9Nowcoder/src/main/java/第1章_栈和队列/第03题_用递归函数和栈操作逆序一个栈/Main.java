/***********************************************************
 * @Description : 第03题_用递归函数和栈操作逆序一个栈
 * https://www.nowcoder.com/practice/1de82c89cc0e43e9aa6ee8243f4dbefd
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2019/12/7 19:59
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package 第1章_栈和队列.第03题_用递归函数和栈操作逆序一个栈;

import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static int getAndRemoveLastElement(Stack<Integer> stack) {
        int result = stack.pop();
        if (stack.isEmpty()) {
            return result;
        } else {
            int last = getAndRemoveLastElement(stack);
            stack.push(result);
            return last;
        }
    }

    public static void reverse(Stack<Integer> stack) {
        if (stack.isEmpty()) {
            return;
        }
        int i = getAndRemoveLastElement(stack);
        reverse(stack);
        stack.push(i);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Stack<Integer> stack = new Stack<>();
        int N = sc.nextInt();
        int i = 0;
        while (i < N && sc.hasNext()) {
            stack.push(sc.nextInt());
            i++;
        }
        reverse(stack);
        for (Integer val : stack) {
            System.out.print(val+" ");
        }
    }
}
