/***********************************************************
 * @Description : 
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/5/13 21:01
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter03StackAndQueues.Section2ArrayStack;

public class Main {
    public static void main(String[] args) {
        ArrayStack<Integer> stack = new ArrayStack<>();
        for (int i = 0; i < 5; i++) {
            stack.push(i);
            System.out.println(stack);
        }
    }
}
