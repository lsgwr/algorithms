/***********************************************************
 * @Description : 循环队列的实现
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/5/13 23:24
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter03StackAndQueues.Section6LoopQueue;

import Chapter03StackAndQueues.Section5ArrayQueue.Queue;

import java.util.Arrays;

public class LoopQueue<Element> implements Queue<Element> {
    /**
     * 存储循环队列数据的数组
     */
    private Element[] data;
    /**
     * 队首元素的位置
     */
    private int front;
    /**
     * 队尾元素的下一个位置
     */
    private int tail;
    /**
     * 循环队列中的元素数
     */
    private int size;
    /**
     * 循环队列的容量，记得要有一个要闲置从而可以区分判空和判满的条件，条件如下：
     * 为了使得tail == front时队列为空(c为队列长度)，循环队列一定要有一个空间是不用地。
     * (tail + 1)%c == front时队列满
     */
    private int capacity;

    public LoopQueue(int capacity) {
        this.capacity = capacity;
        // 声明泛型数组, 有一个空间是要浪费地,所以要多声明一个空间
        data = (Element[]) new Object[capacity + 1];
        front = 0;
        tail = 0;
        size = 0;
    }

    public LoopQueue() {
        this(10);
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return front == tail;
    }

    @Override
    public void enqueue(Element element) {
        // 判断队列是否已满，这个判断条件好好体会，也可以见老师的视频教程
        if ((tail + 1) % data.length == front) {
            // 循环队列满了就自动扩容
            resize(getCapacity() * 2);
        }
        data[tail] = element;
        tail = (tail + 1) % data.length;
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
        // 有一个空间是注定要浪费地
        Element[] newData = (Element[]) new Object[newCapacity + 1];
        for (int i = 0; i < size; i++) {
            // 注意循环队列坐标的特殊性
            newData[i] = data[(i + front) % data.length];
        }
        // 释放原来的data,指向新的Data
        data = newData;
        front = 0;
        tail = size;
    }

    @Override
    public Element dequeue() {
        if (isEmpty()) {
            throw new IllegalArgumentException("队列不能为空！");
        }
        Element ret = data[front];
        data[front] = null;
        // 循环队列的特殊导致地
        front = (front + 1) % data.length;
        size--;
        // 当数组中元素数小于容量的1/4时，自动缩容为原来的一半.之所以选1/4是为了防止频繁扩容和缩容引起性能下降
        if (size == getCapacity() / 4 && getCapacity() / 2 != 0) {
            resize(getCapacity() / 2);
        }
        return ret;
    }

    @Override
    public Element getFront() {
        if (isEmpty()) {
            throw new IllegalArgumentException("队列不能为空！");
        }
        return data[front];
    }

    @Override
    public String toString() {

        StringBuilder res = new StringBuilder();
        res.append(String.format("Queue: size = %d , capacity = %d\n", size, getCapacity()));
        res.append("front [");
        for (int i = front; i != tail; i = (i + 1) % data.length) {
            res.append(data[i]);
            // 判断当前元素不是队列的最后一个元素
            if ((i + 1) % data.length != tail) {
                res.append(", ");
            }
        }
        res.append("] tail");
        return res.toString();
    }


    /**
     * 获取循环队列的容量
     */
    public int getCapacity() {
        return capacity;
    }

    public static void main(String[] args) {
        LoopQueue<Integer> queue = new LoopQueue<>();
        for (int i = 0; i < 10; i++) {
            queue.enqueue(i);
            System.out.println(queue);
            if (i % 3 == 2) {
                queue.dequeue();
                System.out.println(queue);
            }
        }
    }

}
