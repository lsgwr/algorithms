/***********************************************************
 * @Description :  括号匹配问题，用地自己实现地ArrayStack
 *                 Given a string containing just the characters
 *                '(',')','{','}','['and']', determine if the input string is valid.
 *                The brackets must close in the correct order,"()"and"()[]{}"are
 *                all valid but"(]"and"([)]"are not.
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/5/13 21:33
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter03StackAndQueues.Section4MyLeetcodeValidParentheses;


import java.util.Arrays;

public class Solution {

    private class Array<Element> {
        /**
         * 存储数据的数组
         */
        private Element[] data;
        /**
         * 数组的容量(最多能容纳多少个元素)
         */
        private int capacity;
        /**
         * 数组目前有多少个元素
         */
        private int size;

        /**
         * 根据传入的容量量初始化
         *
         * @param capacity data的容量
         */
        public Array(int capacity) {
            this.capacity = capacity;
            // 泛型数组不能直接声明
            data = (Element[]) new Object[capacity];
            size = 0;
        }

        /**
         * 默认构造函数
         */
        public Array() {
            this(10);
        }

        /**
         * 获取数组中当前的元素数量
         */
        public int getSize() {
            return size;
        }

        /**
         * 获取数组中的数据容量(最多可以容纳多少元素)
         */
        public int getCapacity() {
            return capacity;
        }

        /**
         * 判断数组中数据是否为0，直接判断数组的实时大小size即可
         */
        public boolean isEmpty() {
            return size == 0;
        }

        /**
         * 向data中的index位置插入一个元素e
         *
         * @param index   data中下标为index的元素
         * @param element 要插入的元素
         */
        public void insert(int index, Element element) {

            if (index < 0 || index > size) {
                throw new IllegalArgumentException("插入元素失败！index的范围必须在[0, size)");
            }
            // 数组满了要自动扩容
            if (size == data.length) {
                resize(2 * data.length);
            }
            // index后面的所有元素后移
            for (int i = size - 1; i >= index; i--) {
                data[i + 1] = data[i];
            }
            data[index] = element;
            size++;
        }

        /**
         * 数组大小进行调整，根据数组元素数目决定数组是扩容还是缩容
         *
         * @param newCapacity 新的数组的大小
         */
        private void resize(int newCapacity) {
            // 新的容量赋值
            this.capacity = newCapacity;
            Element[] newData = (Element[]) new Object[newCapacity];
            for (int i = 0; i < size; i++) {
                newData[i] = data[i];
            }
            // 释放原来的data,指向新的Data
            data = newData;
        }

        /**
         * 向所有元素最后加一个元素
         */
        public void addLast(Element element) {
            insert(size, element);
        }

        /**
         * 向所有元素开始位置加一个元素
         */
        public void addFirst(Element element) {
            insert(0, element);
        }

        /**
         * 获取指定位置的元素
         */
        public Element get(int index) {
            if (index < 0 || index > size) {
                throw new IllegalArgumentException("index的范围必须在[0, size)");
            }
            return data[index];
        }

        /**
         * 获取最后的元素
         */
        public Element getLast() {
            return get(size - 1);
        }

        /**
         * 获取第一个元素
         */
        public Element getFirst() {
            return get(0);
        }

        /**
         * 更新指定位置的元素
         *
         * @param index   要更新的位置
         * @param element 更新后的元素
         */
        public void set(int index, Element element) {
            if (index < 0 || index > size) {
                throw new IllegalArgumentException("index的范围必须在[0, size)");
            }
            data[index] = element;
        }

        /**
         * 是否包含指定元素
         *
         * @param element 要查找的元素
         * @return 是否包含的标志位
         */
        public boolean contain(Element element) {
            for (int i = 0; i < size; i++) {
                // 非基本数据类型不能直接用==比较
                if (data[i].equals(element)) {
                    return true;
                }
            }
            return false;
        }

        /**
         * 查找指定元素
         *
         * @param element 要查找的元素
         * @return 找到的元素在data中的位置
         */
        public int find(Element element) {
            for (int i = 0; i < size; i++) {
                if (data[i].equals(element)) {
                    return i;
                }
            }
            // 没找到就返回-1
            return -1;
        }

        /**
         * 从数组中删除index位置的元素，并返回删除的元素
         *
         * @param index 待删除的位置
         * @return 删除的元素的值
         */
        public Element remove(int index) {
            if (index < 0 || index > size) {
                throw new IllegalArgumentException("index的范围必须在[0, size)");
            }
            Element ret = data[index];
            // index位置开始，每个元素往前挪一位
            for (int i = index + 1; i < size; i++) {
                data[i - 1] = data[i];
            }
            size--;
            // 把原来的最后面的元素释放掉
            data[size] = null;
            // 当数组中元素数小于容量的1/4时，自动缩容为原来的一半.之所以选1/4是为了防止频繁扩容和缩容引起性能下降
            if (size == capacity / 4 && data.length / 2 != 0) {
                resize(capacity / 2);
            }
            return ret;
        }

        /**
         * 删除数组头的元素
         *
         * @return 删除的元素
         */
        public Element removeFirst() {
            return remove(0);
        }

        /**
         * 删除数组尾的元素
         *
         * @return 删除的元素
         */
        public Element removeLast() {
            return remove(size - 1);
        }

        /**
         * 删除指定值的元素，不适用于存在重复元素的情况。自己改下很容易地
         *
         * @param element 元素的值
         */
        public void removeElement(Element element) {
            int index = find(element);
            // 找到地话
            if (index != -1) {
                remove(index);
            }
        }

        @Override
        public String toString() {
            // 输出的时候要把不是NULL的元素提取出来
            Element[] dataWithOutNull = (Element[]) new Object[size];
            System.arraycopy(data, 0, dataWithOutNull, 0, size);
            return "capacity=" + capacity +
                    ", size=" + size +
                    ", data=" + Arrays.toString(dataWithOutNull);
        }
    }

    private interface Stack<Element> {
        /**
         * 获取Stack的元素数
         */
        int getSize();

        /**
         * 判断Stack是否为空
         */
        boolean isEmpty();

        /**
         * 像Stack中添加元素
         */
        void push(Element element);

        /**
         * 弹出栈顶元素
         */
        Element pop();

        /**
         * 获取栈顶元素但是不弹出
         */
        Element peek();
    }

    private class ArrayStack<Element> implements Stack<Element> {

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

    public boolean isValid(String s) {
        ArrayStack<Character> stack = new ArrayStack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            // 遇到左括号就入栈
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) {
                    // 栈为空肯定是不匹配了
                    return false;
                }
                char topChar = stack.pop();
                // 判断三种括号类型
                if (c == ')' && topChar != '(') {
                    return false;
                }
                if (c == ']' && topChar != '[') {
                    return false;
                }
                if (c == '}' && topChar != '{') {
                    return false;
                }
            }
        }
        // 匹配到最后栈还是不为空，及时前面都匹配成功了，结果还是匹配失败。
        // 为空地话才表明前面都匹配成功了，而且没有剩余括号要匹配了
        return stack.isEmpty();
    }
}
