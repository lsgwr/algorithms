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
        arr.insert(1, 100);
        arr.set(1, 666);
        System.out.println(arr);
    }

}
