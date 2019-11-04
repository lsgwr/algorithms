/***********************************************************
 * @Description : A symbol table implemented with a separate-chaining hash table
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/2/12 上午12:01
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package com.huawei.l00379880.algs4.chapter3search;

import com.huawei.l00379880.algs4.chapter1javabasic.P95LinkedListQueue;

/**
 * The {@code SeparateChainingHashST} class represents a symbol table of generic
 * key-value pairs.
 * It supports the usual <em>put</em>, <em>get</em>, <em>contains</em>,
 * <em>delete</em>, <em>size</em>, and <em>is-empty</em> methods.
 * It also provides a <em>keys</em> method for iterating over all of the keys.
 * A symbol table implements the <em>associative array</em> abstraction:
 * when associating a value with a key that is already in the symbol table,
 * the convention is to replace the old value with the new value.
 * Unlike {@link java.util.Map}, this class uses the convention that
 * values cannot be {@code null}—setting the
 * value associated with a key to {@code null} is equivalent to deleting the key
 * from the symbol table.
 * <p>
 * This implementation uses a separate chaining hash table. It requires that
 * the key type overrides the {@code equals()} and {@code hashCode()} methods.
 * The expected time per <em>put</em>, <em>contains</em>, or <em>remove</em>
 * operation is constant, subject to the uniform hashing assumption.
 * The <em>size</em>, and <em>is-empty</em> operations take constant time.
 * Construction takes constant time.
 * <p>
 * For additional documentation, see <a href="http://algs4.cs.princeton.edu/34hash">Section 3.4</a> of
 * <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 * For other implementations, see {@link P230ST}, {@link P239BinarySearchST},
 * {@link P236SequentialSearchST}, {@link P252BinarySearchTree}, {@link P281BalancedSearchTree}, and
 * {@link P301LinearProbingHashST},
 */
public class P297SeparateChainingHashST<Key, Value> {
    private static final int INIT_CAPACITY = 4;

    /**
     * number of key-value pairs
     */
    private int n;
    /**
     * hash table size
     */
    private int m;
    /**
     * array of linked-list symbol tables
     */
    private P236SequentialSearchST<Key, Value>[] st;


    /**
     * Initializes an empty symbol table.
     */
    public P297SeparateChainingHashST() {
        this(INIT_CAPACITY);
    }

    /**
     * Initializes an empty symbol table with {@code m} chains.
     *
     * @param m the initial number of chains
     */
    public P297SeparateChainingHashST(int m) {
        this.m = m;
        // 不能直接声明泛型数组，只能先声明后强制转换
        st = (P236SequentialSearchST<Key, Value>[]) new P236SequentialSearchST[m];
        for (int i = 0; i < m; i++) {
            st[i] = new P236SequentialSearchST<>();
        }
    }

    /**
     * resize the hash table to have the given number of chains,
     * rehashing all of the keys
     */
    private void resize(int chains) {
        P297SeparateChainingHashST<Key, Value> temp = new P297SeparateChainingHashST<>(chains);
        for (int i = 0; i < m; i++) {
            for (Key key : st[i].keys()) {
                temp.put(key, st[i].get(key));
            }
        }
        this.m = temp.m;
        this.n = temp.n;
        this.st = temp.st;
    }

    /**
     * hash value between 0 and m-1
     */
    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % m;
    }

    /**
     * Returns the number of key-value pairs in this symbol table.
     *
     * @return the number of key-value pairs in this symbol table
     */
    public int size() {
        return n;
    }

    /**
     * Returns true if this symbol table is empty.
     *
     * @return {@code true} if this symbol table is empty;
     * {@code false} otherwise
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Returns true if this symbol table contains the specified key.
     *
     * @param key the key
     * @return {@code true} if this symbol table contains {@code key};
     * {@code false} otherwise
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public boolean contains(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to contains() is null");
        }
        return get(key) != null;
    }

    /**
     * Returns the value associated with the specified key in this symbol table.
     *
     * @param key the key
     * @return the value associated with {@code key} in the symbol table;
     * {@code null} if no such value
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public Value get(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to get() is null");
        }
        int i = hash(key);
        return st[i].get(key);
    }

    /**
     * Inserts the specified key-value pair into the symbol table, overwriting the old
     * value with the new value if the symbol table already contains the specified key.
     * Deletes the specified key (and its associated value) from this symbol table
     * if the specified value is {@code null}.
     *
     * @param key the key
     * @param val the value
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public void put(Key key, Value val) {
        if (key == null) {
            throw new IllegalArgumentException("first argument to put() is null");
        }
        if (val == null) {
            delete(key);
            return;
        }

        // double table size if average length of list >= 10
        if (n >= 10 * m) {
            resize(2 * m);
        }

        int i = hash(key);
        if (!st[i].contains(key)) {
            n++;
        }
        st[i].put(key, val);
    }

    /**
     * Removes the specified key and its associated value from this symbol table
     * (if the key is in this symbol table).
     *
     * @param key the key
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public void delete(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to delete() is null");
        }

        int i = hash(key);
        if (st[i].contains(key)) {
            n--;
        }
        st[i].delete(key);

        // halve table size if average length of list <= 2
        if (m > INIT_CAPACITY && n <= 2 * m) {
            resize(m / 2);
        }
    }

    /**
     * return keys in symbol table as an Iterable
     */
    public Iterable<Key> keys() {
        P95LinkedListQueue<Key> queue = new P95LinkedListQueue<>();
        for (int i = 0; i < m; i++) {
            for (Key key : st[i].keys()) {
                queue.enqueue(key);
            }
        }
        return queue;
    }


    /**
     * Unit tests the {@code SeparateChainingHashST} data type.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        P297SeparateChainingHashST<String, Integer> st = new P297SeparateChainingHashST<>();
        st.put("China", 1);
        st.put("India", 3);
        st.put("England", 34);
        st.put("Austrilia", 0);
        st.put("America", 5);
        st.put("Canada", 67);
        st.put("France", 45);
        // print keys
        for (String s : st.keys()) {
            System.out.println(s + " " + st.get(s));
        }

    }

}


