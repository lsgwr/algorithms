package com.huawei.l00379880.algs4.chapter2sort;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * P206Heap Tester.
 *
 * @author liang shan guang
 * @datetime 01/04/2018
 * @email liangshanguang2@gmail.com
 * @description
 */
public class P206HeapTest {
    /**
     * Description:
     */
    @Test
    public void testMain() {
        String[] a = {"a", "c", "d", "b"};
        P206Heap.sort(a);
        System.out.println(Arrays.toString(a));
    }


    @Test
    public void testComparable() {
        List<DogComparble> list = new ArrayList<>();
        list.add(new DogComparble(10, "DogC"));
        list.add(new DogComparble(5, "DogB"));
        list.add(new DogComparble(7, "DogD"));
        list.add(new DogComparble(6, "DogA"));
        DogComparble[] a = new DogComparble[list.size()];
        // 把list转换到数组中
        list.toArray(a);
        // 按照age还是按照name不能灵活指定,只能通过修改DogComparable里的compareTo()方法来实现
        System.out.println("*****************排序前****************");
        System.out.println(Arrays.toString(a));
        System.out.println("*****************排序后****************");
        P206Heap.sort(a);
        System.out.println(Arrays.toString(a));
    }

    @Test
    public void testComparator() {
        List<Dog> list = new ArrayList<>();
        list.add(new Dog(10, "DogC"));
        list.add(new Dog(5, "DogB"));
        list.add(new Dog(7, "DogD"));
        list.add(new Dog(6, "DogA"));
        Dog[] a = new Dog[list.size()];
        // 把list转换到数组中
        list.toArray(a);
        System.out.println("*****************排序前****************");
        System.out.println(Arrays.toString(a));
        System.out.println("*****************按照年龄排序后****************");
        P206Heap.sort(a, new DogComparatorByAge());
        System.out.println(Arrays.toString(a));
        System.out.println("*****************按照名称排序后****************");
        P206Heap.sort(a, new DogComparatorByName());
        System.out.println(Arrays.toString(a));
    }
}
