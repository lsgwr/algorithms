/***********************************************************
 * @Description : Java自带的哈希函数hashCode
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/5 19:10
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter14HashTable.Section3HashCodeInJDK;

import java.util.HashMap;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) {
        Integer a = 42;
        System.out.println(a.hashCode());
        Integer b = -42;
        System.out.println(b.hashCode());
        Double c = 3.1415926;
        System.out.println(c.hashCode());
        String d = "imooc";
        System.out.println(d.hashCode());

        // Student要自定义hashCode才能当入参相同时两个对象的hashCode相等，否则jdk会自己根据对象地址计算hashCode，一般不能用于判断对象相等
        // 要判断自定义对象相等，除了要重写hashCode()方法，还要重写equals()方法
        Student student = new Student(3, 2, "SHANGUANG", "LIANG");
        System.out.println(student.hashCode());

        HashSet<Student> set = new HashSet<>();
        set.add(student);

        HashMap<Student, Integer> scores = new HashMap<>();
        scores.put(student, 100);
    }
}
