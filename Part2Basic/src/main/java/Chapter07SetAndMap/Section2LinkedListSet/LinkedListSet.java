/***********************************************************
 * @Description : 基于链表的集合
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/5/17 00:30
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter07SetAndMap.Section2LinkedListSet;

import Chapter04LinkedList.Section5Delete.LinkedList;
import Chapter07SetAndMap.Section1SetBasicAndBSTSet.Set;

public class LinkedListSet<E> implements Set<E> {

    private LinkedList<E> list;

    public LinkedListSet() {
        list = new LinkedList<>();
    }

    @Override
    public void add(E e) {
        if (!list.contain(e)) {
            list.addFirst(e);
        }
    }

    @Override
    public void delete(E e) {
        list.deleteElement(e);
    }

    @Override
    public boolean contain(E e) {
        return list.contain(e);
    }

    @Override
    public int getSize() {
        return list.getSize();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }
}
