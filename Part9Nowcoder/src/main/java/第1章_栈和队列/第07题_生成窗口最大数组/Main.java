/***********************************************************
 * @Description : 
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/29 10:19
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package 第1章_栈和队列.第07题_生成窗口最大数组;

import java.util.ArrayDeque;
import java.util.Scanner;

public class Main {
    public static int[] getMaxWindow(int[] arr, int w) {
        if (arr == null || w < 1 || arr.length < w) {
            return null;
        }
        ArrayDeque<Integer> qmax = new ArrayDeque<>();
        int[] res = new int[arr.length - w + 1];
        int index = 0;
        for (int i = 0; i < arr.length; i++) {
            while (!qmax.isEmpty() && arr[qmax.peekLast()] <= arr[i]) {
                qmax.pollLast();
            }
            qmax.addLast(i);
            // 窗口头元素过期
            if (qmax.peekFirst() == i - w) {
                qmax.pollFirst();
            }
            // 一旦窗口宽度达到就弹出当前窗口内的最大值
            if (i >= w - 1) {
                res[index++] = arr[qmax.peekFirst()];
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int width = sc.nextInt();
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
        }
        int[] res = getMaxWindow(arr, width);
        for (int i : res) {
            System.out.print(i + " ");
        }
    }
}
