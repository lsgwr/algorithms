package com.huawei.l00379880.algs4.chapter2sort; 

import org.junit.Test;

import java.util.Arrays;

/** 
* P187QuickBetter Tester. 
* 
* @author       liang shan guang 
* @datetime     01/02/2018
* @email        liangshanguang2@gmail.com
* @description  
*/ 
public class P187QuickBetterTest { 
    /** 
    * Description:
    */ 
    @Test
    public void testMain(){
        Integer[] a = {3, 2, 6, 5, 7, 4, 9, 8, 0, 1};
        System.out.println("原数组:");
        System.out.println(Arrays.toString(a));
        System.out.println("排前5个:");
        System.out.println("排后结果:");
        P187QuickBetter.sort(a, 0, 4);
        System.out.println(Arrays.toString(a));
        System.out.println("排后5个:");
        P187QuickBetter.sort(a, a.length - 5, a.length - 1);
        System.out.println("排后结果:");
        System.out.println(Arrays.toString(a));
        System.out.println("升序:");
        P187QuickBetter.sort(a);
        System.out.println(Arrays.toString(a));
        System.out.println("降序:");
        P153CommenFuns.reverseArray(a);
        System.out.println(Arrays.toString(a));
    } 
} 
