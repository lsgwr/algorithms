/***********************************************************
 * @Description : 
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/5/20 16:03
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter12AVLTree;

import Chapter07SetAndMap.Section1SetBasicAndBSTSet.Set;

public class AVLTreeSet<Element extends Comparable<Element>> implements Set<Element> {

    /**
     * 键值一样即可
     */
    private AVLTree<Element, Element> avlTree;

    public AVLTreeSet() {
        avlTree = new AVLTree<>();
    }

    @Override
    public void add(Element element) {
        avlTree.insert(element, element);
    }

    @Override
    public void delete(Element element) {
        avlTree.deleteNode(element);
    }

    @Override
    public boolean contain(Element element) {
        return avlTree.contain(element);
    }

    @Override
    public int getSize() {
        return avlTree.size();
    }

    @Override
    public boolean isEmpty() {
        return avlTree.isEmpty();
    }
}
