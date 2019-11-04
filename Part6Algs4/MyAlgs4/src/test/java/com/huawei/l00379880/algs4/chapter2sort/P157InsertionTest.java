package com.huawei.l00379880.algs4.chapter2sort;

import org.junit.Test;

/**
 * P157Insertion Tester.
 *
 * @author liang shan guang
 * @datetime 01/01/2018
 * @email liangshanguang2@gmail.com
 * @description
 */
public class P157InsertionTest {
    /**
     * Description:
     */
    @Test
    public void testMain() {
        Integer[] a = {3, 2, 6, 5, 7, 4, 9, 8, 0, 1};
        System.out.println("原数组:");
        P153CommenFuns.show(a);
        System.out.println("排序前3个:");
        P157Insertion.sort(a, 0, 2);
        P153CommenFuns.show(a);
        System.out.println("排序后3个:");
        P157Insertion.sort(a, a.length - 3, a.length - 1);
        P153CommenFuns.show(a);
        // 升序排列
        System.out.println("升序排列:");
        P157Insertion.sort(a);
        P153CommenFuns.show(a);
        // 降序排列
        System.out.println("降序排列:");
        P153CommenFuns.reverseArray(a);
        P153CommenFuns.show(a);
    }
}
