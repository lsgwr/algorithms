/***********************************************************
 * @Description : 依赖于第2章的动态数组实现自己的队列
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/5/13 20:11
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter03StackAndQueues.Section2ArrayStack;

import Chapter02Arrays.Section7DynamicArray.Array;

public class ArrayStack<Element> implements Stack<Element> {

    Array<Element> arr;

    public ArrayStack(int capacity) {
        arr = new Array<>(capacity);
    }

    public ArrayStack() {
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
    public void push(Element element) {
        arr.addLast(element);
    }

    @Override
    public Element pop() {
        return arr.removeLast();
    }

    @Override
    public Element peek() {
        return arr.getLast();
    }

    @Override
    public String toString() {
        return "ArrayStack: " + arr + "<----top of stack";
    }

    /**
     * 获取栈的容量
     */
    public int getCapacity() {
        return arr.getCapacity();
    }
}
