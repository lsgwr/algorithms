/***********************************************************
 * @Description :
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/29 11:03
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package 第1章_栈和队列.第08题_单调栈结构;

import java.util.ArrayDeque;
import java.util.Scanner;

/**
 * 找到每个i位置左边和右边离i位置最近且值比arr[i]小的位置
 */
public class Main {
    public static int[][] getNearLessNoRepeat(int[] arr) {
        int[][] res = new int[arr.length][2];
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
                // 新来的元素arr[i]破坏了stack的递减结构,就弹出栈顶的元素，
                int popIndex = stack.pop();
                // 被弹出元素的下方，就是popIndex位置左侧离popIndex位置最近，而且比arr[i]要小的位置
                int leftLessIndex = stack.isEmpty() ? -1 : stack.peek();
                res[popIndex][0] = leftLessIndex;
                // arr[i]就是arr[popIndex]右侧离arr[popIndex]最近且值比arr[popIndex]小的位置
                res[popIndex][1] = i;
            }
            stack.push(i);
        }
        // 遍历阶段结束，清算栈中剩下的位置
        while (!stack.isEmpty()) {
            int popIndex = stack.pop();
            //popIndex下方还是可能有元素的
            int leftLessIndex = stack.isEmpty() ? -1 : stack.peek();
            res[popIndex][0] = leftLessIndex;
            // popIndex上方肯定没有i了
            res[popIndex][1] = -1;
        }
        return res;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
        }
        int[][] res = getNearLessNoRepeat(arr);
        for (int i = 0; i < N; i++) {
            System.out.print(res[i][0] + " ");
            System.out.println(res[i][1]);
        }
    }
}
