/***********************************************************
 * @Description : 
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/5/13 22:16
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter03StackAndQueues.Section5ArrayQueue;

import Chapter02Arrays.Section7DynamicArray.Array;

public class ArrayQueue<Element> implements Queue<Element> {

    private Array<Element> arr;

    public ArrayQueue(int capacity) {
        arr = new Array<>(capacity);
    }

    public ArrayQueue() {
        arr = new Array<>();
    }

    @Override
    public int getSize() {
        return arr.getSize();
    }

    @Override
    public boolean isEmpty() {
        return arr.isEmpty();
    }

    @Override
    public void enquque(Element element) {
        arr.addLast(element);
    }

    @Override
    public Element dequque() {
        return arr.removeFirst();
    }

    @Override
    public Element getFront() {
        return arr.getFirst();
    }

    @Override
    public String toString() {
        return "ArrayQueue: " + arr + "<----- tail of queue";
    }

    /**
     * 获取栈的容量
     */
    public int getCapacity() {
        return arr.getCapacity();
    }

    public static void main(String[] args) {
        ArrayQueue<Integer> queue = new ArrayQueue<>();
        for (int i = 0; i < 10; i++) {
            queue.enquque(i);
            System.out.println(queue);
            if (i%3==2){
                queue.dequque();
                System.out.println(queue);
            }
        }
    }
}
