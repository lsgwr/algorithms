package com.huawei.l00379880.algs4.chapter2sort;

/***********************************************************
 * @Description : 自定义数据类型,实现Comparable方法,但是相对于
 *                comparator比较受限,因为实现Comparable后只能写一个
 *                比较方法compareTo;但是可以自定义多个Comparator从而
 *                灵活选择自定义数据类型的排序方式
 * @author      : 梁山广
 * @date        : 2018/1/1 17:49
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
public class DogComparble implements Comparable<DogComparble> {
    private int age;
    private String name;

    public DogComparble(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param that - 要比较的对象。
     * @return 负整数、零或正整数，根据此对象是小于、等于还是大于指定对象。
     */
    @Override
    public int compareTo(DogComparble that) {
        // int类型没有compareTo方法,Integer才有
        return this.age - that.age;
    }


//    /**
//     * @param that - 要比较的对象。
//     * @return 负整数、零或正整数，根据此对象是小于、等于还是大于指定对象。
//     */
//    @Override
//    public int compareTo(DogComparble that) {
//        // String类型本来就是封装类型,所以可以用compareTo方法
//        return this.name.compareTo(that.name);
//    }

    @Override
    public String toString() {
        return "DogComparble{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}
