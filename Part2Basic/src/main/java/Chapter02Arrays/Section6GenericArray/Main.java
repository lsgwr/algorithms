/***********************************************************
 * @Description : 测试下Array
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/5/13 13:10
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter02Arrays.Section6GenericArray;


public class Main {
    public static void main(String[] args) {

        System.out.println("*****************整型数组测试***************************");
        Array<Integer> arr = new Array<>(20);
        for (int i = 0; i < 10; i++) {
            arr.addLast(i);
        }
        System.out.println("向下标为1的地方插入元素100，原来的1及后面的元素统一后移一位");
        arr.insert(1, 100);
        System.out.println(arr);
        System.out.println("向队首添加元素-1");
        arr.addFirst(-1);
        System.out.println(arr);
        System.out.println("删除索引为2的元素");
        arr.remove(2);
        System.out.println(arr);
        System.out.println("删除数组头的元素");
        arr.removeFirst();
        System.out.println(arr);


        System.out.println("**********************自定义数组测试***********************");
        Array<Student> studentArray = new Array<>();
        studentArray.addLast(new Student("lsg", 26));
        studentArray.addLast(new Student("wn", 25));
        studentArray.addLast(new Student("hjl", 24));
        System.out.println(studentArray);
    }

}
