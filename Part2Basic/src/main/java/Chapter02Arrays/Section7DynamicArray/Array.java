/***********************************************************
 * @Description : 自己封装的数组类,实现了泛型和动态数组大小
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/5/13 17:49
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter02Arrays.Section7DynamicArray;

import java.util.Arrays;

public class Array<Element> {
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
        // 当数组中元素数小于容量的一半时，自动缩容为原来的一半
        if (size == capacity / 2) {
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
        return "Array:" +
                " capacity=" + capacity +
                ", size=" + size +
                ", data=" + Arrays.toString(data);
    }
}
