/***********************************************************
 * @Description : 测试基于AVL的集合Set
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/4 19:34
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter12AVLTree.Section8SetAndMapBasedOnAVL;

import Chapter07SetAndMap.Section1SetBasicAndBSTSet.Set;
import Chapter12AVLTree.Section7DeleteNode.BSTKV_AVL;

public class AVLSet<E extends Comparable<E>> implements Set<E> {
    /**
     * 我们让键值相等当做集合来用
     */
    private BSTKV_AVL<E, E> avl;

    public AVLSet() {
        this.avl = new BSTKV_AVL<>();
    }

    @Override
    public void add(E e) {
        // 认为键值相等的avl就是set
        // 这里的set我们已经改造了，存在e就会更新，不存在就会自动add的
        avl.set(e, e);
    }

    @Override
    public void delete(E e) {
        avl.remove(e);
    }

    @Override
    public boolean contain(E e) {
        return avl.contains(e);
    }

    @Override
    public int getSize() {
        return avl.getSize();
    }

    @Override
    public boolean isEmpty() {
        return avl.isEmpty();
    }
}
