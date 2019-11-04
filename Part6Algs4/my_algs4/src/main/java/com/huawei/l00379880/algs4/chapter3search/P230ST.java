package com.huawei.l00379880.algs4.chapter3search;

import java.util.NoSuchElementException;
import java.util.TreeMap;

/***********************************************************
 * @Description : 泛型有序符号表 ST(Symbol Table),键必须是Comparable对象
 *                默认都改是升序排列
 * @author      : 梁山广
 * @date        : 2018/1/5 15:20
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
public class P230ST<Key extends Comparable<Key>, Value> {
    private TreeMap<Key, Value> st;


    public P230ST() {
        st = new TreeMap<>();
    }

    /**
     * 获取键key对应的值,不存在的话就返回Null
     */
    public Value get(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("传入的键key不能为null");
        }
        return st.get(key);
    }

    /**
     * 将键值对存入表中(若value为空)
     */

    public void put(Key key, Value value) {
        if (key == null || value == null) {
            // 书中实现是如果value为空那么就把这个键值对从符号表中删除
            throw new IllegalArgumentException("键key或值value不能为null");
        }
        st.put(key, value);
    }

    /**
     * 从表中删除key对应的键值对
     */
    public void delete(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("传入的键key不能为null");
        }
        st.remove(key);
    }

    /**
     * 键是否已经存在
     */
    public boolean contains(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("传入的键key不能为null");
        }
        return st.containsKey(key);
    }

    /**
     * 符号表长度
     */
    public int size() {
        return st.size();
    }

    /**
     * 符号表是否为空
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * 表中所有键的集合,已排序
     */
    public Iterable<Key> keys() {
        return st.keySet();
    }

    /**
     * 最小的键
     */
    public Key min() {
        return st.firstKey();
    }


    /**
     * 最大的键
     */
    public Key max() {
        return st.lastKey();
    }


    /**
     * 小于等于key的最大键
     */
    public Key floor(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("传入的键key不能为null");
        }
        Key k = st.floorKey(key);
        if (k == null) {
            throw new NoSuchElementException("找不到小于等于key的键!");
        }
        return k;
    }

    /**
     * 大于等于key的最小键
     */
    public Key ceiling(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("传入的键key不能为null");
        }
        Key k = st.ceilingKey(key);
        if (k == null) {
            throw new NoSuchElementException("找不到小于等于key的键!");
        }
        return k;
    }
}
