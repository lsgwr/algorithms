/***********************************************************
 * @Description : 基于第六章的BST实现的集合(BSTSet)
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/5/16 23:19
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter07SetAndMap.Section1SetBasicAndBSTSet;


import Chapter06BST.BST;

public class BSTSet<E extends Comparable<E>> implements Set<E> {

    private BST<E> bst;

    public BSTSet() {
        bst = new BST<>();
    }

    @Override
    public void add(E e) {
        bst.add(e);
    }

    @Override
    public void delete(E e) {
        bst.remove(e);
    }

    @Override
    public boolean contain(E e) {
        // 这里的e实际指地是key哈
        return bst.contains(e);
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
