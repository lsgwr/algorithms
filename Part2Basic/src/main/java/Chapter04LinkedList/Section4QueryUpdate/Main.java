/***********************************************************
 * @Description : 测试LinkedList
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/5/15 07:56
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter04LinkedList.Section4QueryUpdate;

public class Main {
    public static void main(String[] args) {
        LinkedList<Integer> linkedList=new LinkedList<>();
        for (int i = 0; i < 5; i++) {
            linkedList.addFirst(i);
            System.out.println(linkedList);
        }
        linkedList.insert(2,666);
        System.out.println(linkedList);
    }
}
