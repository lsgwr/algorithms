/***********************************************************
 * @Description : 
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/5/20 15:50
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter12AVLTree;

import Chapter07SetAndMap.Section1SetBasicAndBSTSet.FileOperation;
import Chapter07SetAndMap.Section5MapBasic.Map;

import java.util.ArrayList;
import java.util.Collections;

public class AVLTreeMap<Key extends Comparable<Key>, Value> implements Map<Key, Value> {

    private AVLTree<Key, Value> avlTree;

    public AVLTreeMap() {
        avlTree = new AVLTree<>();
    }

    @Override
    public void set(Key key, Value value) {
        avlTree.insert(key, value);
    }

    @Override
    public void delete(Key key) {
        avlTree.deleteNode(key);
    }

    @Override
    public boolean contains(Key key) {
        return avlTree.contain(key);
    }

    @Override
    public Value get(Key key) {
        return avlTree.get(key);
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
