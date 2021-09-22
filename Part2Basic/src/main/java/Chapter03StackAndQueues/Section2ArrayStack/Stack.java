/***********************************************************
 * @Description : 自己定义的栈接口
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/5/13 20:08
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter03StackAndQueues.Section2ArrayStack;

public interface Stack<Element> {
    /**
     * 获取Stack的元素数
     *
     * @return 栈的总元素数
     */
    int getSize();

    /**
     * 判断Stack是否为空
     *
     * @return 栈为空返回true, 否则返回false
     */
    boolean isEmpty();

    /**
     * 像Stack中添加元素
     *
     * @param element 要添加的元素
     */
    void push(Element element);

    /**
     * 弹出栈顶元素
     *
     * @return 栈顶元素
     */
    Element pop();

    /**
     * 获取栈顶元素但是不弹出
     *
     * @return 栈顶元素
     */
    Element peek();
}
