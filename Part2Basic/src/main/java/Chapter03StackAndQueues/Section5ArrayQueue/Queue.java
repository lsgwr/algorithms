/***********************************************************
 * @Description : Queue的接口
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/5/13 22:12
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter03StackAndQueues.Section5ArrayQueue;

public interface Queue<Element> {
    /**
     * 获取Queue的元素数
     *
     * @return 队列的元素总数
     */
    int getSize();

    /**
     * 判断Queue是否为空
     *
     * @return 为空返回true，非空返回false
     */
    boolean isEmpty();

    /**
     * 从队尾入队
     *
     * @param element 要入队的元素
     */
    void enqueue(Element element);

    /**
     * 从队首出队
     *
     * @return 出队的元素
     */
    Element dequeue();

    /**
     * 获取队头元素但是不弹出
     *
     * @return 队列头元素
     */
    Element getFront();
}
