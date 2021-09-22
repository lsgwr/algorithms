/***********************************************************
 * @Description : 基于支持键值对的BSTKV实现地Map,效率要更高。
 * 第1节实现地BST只支持传入一个1个E，为了支持传入键值对，所以用其他的实现了
 * 比如玩转数据结构C++版里写的 Chapter06BST.Part1BasicChapter05BST.BST
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/5/17 23:46
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter07SetAndMap.Section7BSTMap;

import Chapter06BST.BSTKV;
import Chapter07SetAndMap.Section5MapBasic.Map;

public class BSTMap<Key extends Comparable<Key>, Value> implements Map<Key, Value> {

    private BSTKV<Key, Value> bst;

    public BSTMap() {
        bst = new BSTKV<>();
    }

    @Override
    public void set(Key key, Value value) {
        bst.add(key, value);
    }

    @Override
    public void delete(Key key) {
        bst.remove(key);
    }

    @Override
    public boolean contains(Key key) {
        return bst.contains(key);
    }

    @Override
    public Value get(Key key) {
        return bst.get(key);
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
