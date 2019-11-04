/***********************************************************
 * @Description : 低位优先的字符串排序
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/2/25 下午11:14
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package com.huawei.l00379880.algs4.chapter5string;

public class P459LSD {
    private static final int BITS_PER_BYTE = 8;

    public P459LSD() {
    }

    /**
     * Rearranges the array of W-character strings in ascending order
     * 通过前w个字符将a[]排序
     *
     * @param arr the array to be sorted
     * @param w   the number of characters per string
     */
    public static void sort(String[] arr, int w) {
        int N = arr.length;
        // extend ASCII alphabet size
        int R = 256;
        String[] aux = new String[N];

        for (int d = w - 1; d >= 0; d--) {
            // 根据第d个字符按照键索引法计数法排序

            // 1.计算出现频率
            int[] count = new int[R + 1];
            for (int i = 0; i < N; i++) {
                count[arr[i].charAt(d) + 1]++;
            }
            // 2.将频率转换为索引
            for (int r = 0; r < R; r++) {
                count[r + 1] += count[r];
            }
            // 3.将元素分类
            for (int i = 0; i < N; i++) {
                aux[count[arr[i].charAt(d)]++] = arr[i];
            }
            // 4.回写
            for (int i = 0; i < N; i++) {
                arr[i] = aux[i];
            }
        }
    }

    /**
     * 把整形数组按照升序排列
     * 速度比JDK自带地Arrays.sort()方法快2~3倍
     *
     * @param arr 待排序数组
     */
    public static void sort(int[] arr) {
        // int 类型是32位
        final int BITS = 32;
        // 每位是在0~255之间
        final int R = 1 << BITS_PER_BYTE;
        // 0xFF
        final int MASK = R - 1;
        // 每个整型是4 bytes
        final int w = BITS / BITS_PER_BYTE;
        int N = arr.length;
        int[] aux = new int[N];
        for (int d = 0; d < w; d++) {
            // 1.计算出现频率
            int[] count = new int[R + 1];
            for (int i = 0; i < N; i++) {
                int c = (arr[i] >> BITS_PER_BYTE * d) & MASK;
                count[c + 1]++;
            }
            // 2.频率转换为索引
            for (int r = 0; r < R; r++) {
                count[r + 1] += count[r];
            }

            // 3.for most significant byte, 0x80-0xFF comes before 0x00-0x7F
            if (d == w - 1) {
                int shift1 = count[R] - count[R / 2];
                int shift2 = count[R / 2];
                for (int r = 0; r < R / 2; r++) {
                    count[r] += shift1;
                }
                for (int r = R / 2; r < R; r++) {
                    count[r] -= shift2;
                }
            }
            // 4.移动数据
            for (int i = 0; i < N; i++) {
                int c = (arr[i] >> BITS_PER_BYTE * d) & MASK;
                aux[count[c]++] = arr[i];
            }
            // 5.数据回填
            for (int i = 0; i < N; i++) {
                arr[i] = aux[i];
            }
        }
    }
}
