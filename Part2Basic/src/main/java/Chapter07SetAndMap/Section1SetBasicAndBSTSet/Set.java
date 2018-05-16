/***********************************************************
 * @Description : 
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/5/16 23:16
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter07SetAndMap.Section1SetBasicAndBSTSet;

public interface Set<Element> {
    /**
     * 添加元素
     */
    void add(Element element);

    /**
     * 删除元素
     */
    void delete(Element element);

    /**
     * 判断是否包含某元素
     */
    boolean contain(Element element);

    /**
     * 获取集合的元素数量
     */
    int getSize();

    /**
     * 判断集合是否为空
     */
    boolean isEmpty();
}
