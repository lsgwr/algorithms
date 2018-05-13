/***********************************************************
 * @Description : 自己封装的数组类
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/5/13 12:23
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter02Arrays.Section2to5OurOwnArray;

import java.util.Arrays;

public class Array {
    /**
     * 存储数据的数组
     */
    private int[] data;
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
        data = new int[capacity];
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
     * 做一些预检查，比如范围、临界啊等等
     */
    public void preCheck() {
        if (size == data.length) {
            throw new IllegalArgumentException("容量已满！不能再添加新元素！");
        }
    }


    /**
     * 向data中的index位置插入一个元素e
     *
     * @param index   data中下标为index的元素
     * @param element 要插入的元素
     */
    public void insert(int index, int element) {
        preCheck();
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("插入元素失败！index的范围必须在[0, size)");
        }
        // index后面的所有元素后移
        for (int i = size - 1; i >= index; i--) {
            data[i + 1] = data[i];
        }
        data[index] = element;
        size++;
    }

    /**
     * 向所有元素最后加一个元素
     */
    public void addLast(int element) {
        insert(size, element);
    }

    /**
     * 向所有元素开始位置加一个元素
     */
    public void addFirst(int element) {
        insert(0, element);
    }

    /**
     * 获取指定位置的元素
     */
    public int get(int index) {
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
    public void set(int index, int element) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("index的范围必须在[0, size)");
        }
        data[index] = element;
    }

    @Override
    public String toString() {
        return "Array:" +
                " capacity=" + capacity +
                ", size=" + size +
                ", data=" + Arrays.toString(data);
    }
}
