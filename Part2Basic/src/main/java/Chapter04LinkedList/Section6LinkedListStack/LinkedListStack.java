/***********************************************************
 * @Description : 基于链表实现的栈
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/5/15 08:18
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter04LinkedList.Section6LinkedListStack;

import Chapter03StackAndQueues.Section2ArrayStack.Stack;
import Chapter04LinkedList.Section5Delete.LinkedList;

public class LinkedListStack<Element> implements Stack<Element> {
    private LinkedList list;
    @Override
    public int getSize() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void push(Element element) {

    }

    @Override
    public Element pop() {
        return null;
    }

    @Override
    public Element peek() {
        return null;
    }
}
