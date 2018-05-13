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
     */
    int getSize();

    /**
     * 判断Queue是否为空
     */
    boolean isEmpty();

    /**
     * 从队尾入队
     */
    void enquque(Element element);

    /**
     * 从队首出队
     */
    Element dequque();

    /**
     * 获取队头元素但是不弹出
     */
    Element getFront();
}
