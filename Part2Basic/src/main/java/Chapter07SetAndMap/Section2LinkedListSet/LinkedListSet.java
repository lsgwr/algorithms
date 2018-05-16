/***********************************************************
 * @Description : 
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/5/17 00:30
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter07SetAndMap.Section2LinkedListSet;

import Chapter04LinkedList.Section5Delete.LinkedList;
import Chapter07SetAndMap.Section1SetBasicAndBSTSet.Set;

public class LinkedListSet<Element> implements Set<Element> {

    private LinkedList<Element> list;

    @Override
    public void add(Element element) {

    }

    @Override
    public void delete(Element element) {

    }

    @Override
    public boolean contain(Element element) {
        return false;
    }

    @Override
    public int getSize() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
