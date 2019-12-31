/***********************************************************
 * @Description : 基于第六章的BST实现的集合(BSTSet)
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/5/16 23:19
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter07SetAndMap.Section1SetBasicAndBSTSet;


import Chapter06BST.BST;

public class BSTSet<Element extends Comparable<Element>> implements Set<Element> {

    private BST<Element> bst;

    public BSTSet() {
        bst = new BST<>();
    }

    @Override
    public void add(Element element) {
        bst.add(element);
    }

    @Override
    public void delete(Element element) {
        bst.remove(element);
    }

    @Override
    public boolean contain(Element element) {
        // 这里的element实际指地是key哈
        return bst.contains(element);
    }

    @Override
    public int getSize() {
        return bst.getSize();
    }

    @Override
    public boolean isEmpty() {
        return bst.isEmpty();
    }
}
