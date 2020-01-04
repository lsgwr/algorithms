/***********************************************************
 * @Description : 基于AVL的Map
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/4 19:11
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter12AVLTree.Section8SetAndMapBasedOnAVL;

import Chapter07SetAndMap.Section5MapBasic.Map;
import Chapter12AVLTree.Section7DeleteNode.BSTKV_AVL;

public class AVLMap<K extends Comparable<K>, V> implements Map<K, V> {
    /**
     * 基于上一节实现地最完整高效的AVL
     */
    private BSTKV_AVL<K, V> avl;

    public AVLMap() {
        this.avl = new BSTKV_AVL<>();;
    }

    @Override
    public void set(K k, V v) {
        avl.set(k, v);
    }

    @Override
    public void delete(K k) {
        avl.remove(k);
    }

    @Override
    public boolean contains(K k) {
        return avl.contains(k);
    }

    @Override
    public V get(K k) {
        return avl.get(k);
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
