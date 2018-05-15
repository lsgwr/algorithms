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
    private LinkedList<Element> list;

    public LinkedListStack() {
        this.list = new LinkedList<>();
    }

    @Override
    public int getSize() {
        return list.getSize();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public void push(Element element) {
        list.addFirst(element);
    }

    @Override
    public Element pop() {
        return list.deleteFirst();
    }

    @Override
    public Element peek() {
        return list.getFirst();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Stack: top ");
        sb.append(list);
        return sb.toString();
    }

    public static void main(String[] args) {
        LinkedListStack<Integer> stack = new LinkedListStack<>();
        for (int i = 0; i < 5; i++) {
            stack.push(i);
            System.out.println(stack);
        }
        stack.pop();
        System.out.println(stack);
    }
}
