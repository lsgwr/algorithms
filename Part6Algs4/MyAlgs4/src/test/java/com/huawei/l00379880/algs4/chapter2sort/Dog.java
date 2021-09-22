package com.huawei.l00379880.algs4.chapter2sort;

/***********************************************************
 * @Description : 单纯的dog类
 * @author      : 梁山广
 * @date        : 2018/1/1 18:14
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
public class Dog {
    private int age;
    private String name;

    public Dog(int age, String name) {
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

    @Override
    public String toString() {
        return "Dog{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}
