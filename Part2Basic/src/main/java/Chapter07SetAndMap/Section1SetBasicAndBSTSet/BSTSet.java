/***********************************************************
 * @Description : 基于第六章的BST实现的集合(BSTSet)
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/5/16 23:19
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter07SetAndMap.Section1SetBasicAndBSTSet;

import Chapter06BST.BST;

public class BSTSet<Element extends Comparable<Element>> implements Set<Element> {

    private BST<Element, Element> bst;

    public BSTSet() {
        bst = new BST<>();
    }

    @Override
    public void add(Element element) {
        bst.insert(element, element);
    }

    @Override
    public void delete(Element element) {
        bst.deleteNode(element);
    }

    @Override
    public boolean contain(Element element) {
        // 这里的element实际指地是key哈
        return bst.contain(element);
    }

    @Override
    public int getSize() {
        return bst.size();
    }

    @Override
    public boolean isEmpty() {
        return bst.isEmpty();
    }
}
