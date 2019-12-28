/***********************************************************
 * @Description : 基于Jdk自带的链表实现的栈
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2019/12/28 08:29
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter04LinkedList.Section6LinkedListStack;

import Chapter03StackAndQueues.Section2ArrayStack.Stack;

import java.util.LinkedList;

public class JdkLinkedListStack<Element> implements Stack<Element> {
    private LinkedList<Element> list;

    public JdkLinkedListStack() {
        this.list = new LinkedList<>();
    }

    @Override
    public int getSize() {
        return list.size();
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
        return list.removeFirst();
    }

    @Override
    public Element peek() {
        return list.getFirst();
    }

    @Override
    public String toString() {
        String sb = "Stack: top " + list;
        return sb;
    }

    public static void main(String[] args) {
        JdkLinkedListStack<Integer> stack = new JdkLinkedListStack<>();
        for (int i = 0; i < 5; i++) {
            stack.push(i);
            System.out.println(stack);
        }
        stack.pop();
        System.out.println(stack);
    }
}
