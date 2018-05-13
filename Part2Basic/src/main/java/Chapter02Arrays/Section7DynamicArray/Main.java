/***********************************************************
 * @Description : 测试下Array
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/5/13 13:10
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter02Arrays.Section7DynamicArray;


public class Main {
    public static void main(String[] args) {

        System.out.println("*****************整型数组测试***************************");
        Array<Integer> arr = new Array<>();
        for (int i = 0; i < 10; i++) {
            arr.addLast(i);
        }
        System.out.println(arr);
        System.out.println("向下标为1的地方插入元素100，超出数组容量会自动扩容");
        arr.insert(1, 100);
        System.out.println(arr);
        System.out.println("连续减少2个元素，元素数小于容量一半的时候，不在缩容，得等到1/4的时候，可以防止反复扩容缩容引起性能震荡");
        arr.removeFirst();
        arr.removeFirst();
        System.out.println(arr);
        System.out.println("再弹出四个，容量到1/4，才会自动缩容");
        arr.removeFirst();
        arr.removeFirst();
        arr.removeFirst();
        arr.removeFirst();
        System.out.println(arr);
    }

}
