/***********************************************************
 * @Description : 
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/5/17 08:11
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter07SetAndMap.Section5MapBasic;

public interface Map<Key, Value> {
    /**
     * 添加/更新键值对
     */
    void set(Key key, Value value);

    /**
     * 删除指定键的键值对
     */
    void delete(Key key);

    /**
     * 是否包含某指定键的键值对
     */
    boolean contains(Key key);

    /**
     * 根据键获取键值对
     */
    Value get(Key key);


    /**
     * 获取Map的大小
     */
    int getSize();

    /**
     * 判断Map是否为空
     */
    boolean isEmpty();
}
