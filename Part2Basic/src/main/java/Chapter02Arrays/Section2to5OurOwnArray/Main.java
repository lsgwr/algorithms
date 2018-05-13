/***********************************************************
 * @Description : 测试下Array
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/5/13 13:10
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter02Arrays.Section2to5OurOwnArray;

public class Main {
    public static void main(String[] args) {
        Array arr = new Array(20);
        for (int i = 0; i < 10; i++) {
            arr.addLast(i);
        }
        // 向下标为1的地方插入元素，原来的1及后面的元素统一后移一位
        arr.insert(1, 100);
        System.out.println(arr);
        // 向队首添加元素
        arr.addFirst(-1);
        System.out.println(arr);
        // 删除索引为2的元素
        arr.remove(2);
        System.out.println(arr);
        arr.removeFirst();
        System.out.println(arr);
    }

}
