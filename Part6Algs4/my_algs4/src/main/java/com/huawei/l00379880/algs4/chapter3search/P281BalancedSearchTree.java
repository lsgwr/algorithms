/***********************************************************
 * @Description : 平衡查找树，又称2-3查找树或红黑树
 *
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/1/8 上午12:08
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package com.huawei.l00379880.algs4.chapter3search;

import com.huawei.l00379880.algs4.chapter1javabasic.P95LinkedListQueue;

import java.util.NoSuchElementException;

public class P281BalancedSearchTree<Key extends Comparable<Key>, Value> {
    /**
     * 是否是红链接：两个2-节点连接起来组成一个3-节点
     */
    private static final boolean RED = true;
    /**
     * 是否是黑链接：2-3树种的普通节点
     */
    private static final boolean BLACK = false;

    /**
     * 红黑树的根节点
     */
    private Node root;

    private class Node {
        private Key key;
        private Value value;
        private Node left, right;
        /**
         * 节点类型：红节点还是黑节点
         */
        private boolean color;
        private int size;

        public Node(Key key, Value value, boolean color, int size) {
            this.key = key;
            this.value = value;
            this.color = color;
            this.size = size;
        }
    }

    public P281BalancedSearchTree() {
    }

    /**
     * 描述: is node x red; false if x is null ?
     */
    private boolean isRED(Node x) {
        if (x == null) {
            return false;
        }
        return x.color == RED;
    }

    /**
     * 描述: 以x为根节点的子树的节点数；如果节点为Null，那就返回0
     * number of node in subtree rooted at x; 0 if x is null
     */
    private int size(Node x) {
        if (x == null) {
            return 0;
        }
        return x.size;
    }

    /**
     * 整个红黑树的节点数
     *
     * @return
     */
    public int size() {
        return size(root);
    }

    public boolean isEmpty() {
        return root == null;
    }

    /***************************************************************************
     *  Standard BinarySearchTree search. 标准的二叉树查找
     ***************************************************************************/
    public Value get(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("传入的key不能为null");
        }
        return get(root, key);
    }

    /**
     * 描述: 在以x为根节点的子树中找到key所对应的Value;没有这个key地话就返回null
     */
    private Value get(Node x, Key key) {
        while (x != null) {
            // 待寻找的键和当前节点的键的比较结果
            int cmp = key.compareTo(x.key);
            if (cmp < 0) {
                // 待寻找的键小于当前节点的键，往左找
                x = x.left;
            } else if (cmp > 0) {
                // 待寻找的键大于当前节点的键，往右找
                x = x.right;
            } else {
                // 待寻找的键等于当前节点的键，返回节点的value
                return x.value;
            }
        }
        return null;
    }

    /**
     * 描述: 当前的符号表(红黑树)是否包含指定的键
     */
    public boolean contains(Key key) {
        return get(key) != null;
    }

    /***************************************************************************
     *  Red-black tree insertion. 红黑树的插入和更新
     ***************************************************************************/
    public void put(Key key, Value value) {
        if (key == null) {
            throw new IllegalArgumentException("传入的键不能为null");
        }
        if (value == null) {
            throw new IllegalArgumentException("传入的值不能为null");
        }
        root = put(root, key, value);
        // 黑色表示这个节点和父节点之间是普通的2-3连接
        root.color = BLACK;
    }

    /**
     * 描述: 在以x为根节点的子树中插入指定的键值对
     */
    private Node put(Node x, Key key, Value value) {
        // 递归到最后还是没找到，那就返回一个新节点放在最底下，通过递归回去更新到合适的位置
        if (x == null) {
            return new Node(key, value, RED, 1);
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            x.left = put(x.left, key, value);
        } else if (cmp > 0) {
            x.right = put(x.right, key, value);
        } else {
            x.value = value;
        }

        // 修复红黑连接，使得红黑树重新平衡。见P280图3.3.22旁边的三条
        // 1.如果右子节点是红色而左子节点是黑色，进行左旋转
        if (isRED(x.right) && !isRED(x.left)) {
            x = rotateLeft(x);
        }
        // 2.如果左子节点是红色且它的左子节点也是红色的，进行右旋转
        if (isRED(x.left) && isRED(x.left.left)) {
            x = rotateRight(x);
        }
        // 3.如果左右子节点均为红色，进行颜色转换
        if (isRED(x.left) && isRED(x.right)) {
            flipColors(x);
        }
        return x;
    }

    /**
     * 描述: 删除最小元素
     */
    public void deleteMin() {
        if (isEmpty()) {
            throw new NoSuchElementException("二叉查找树已经空啦！");
        }

        // 如果两个子节点都是黑色地，那么根节点改为红色
        if (!isRED(root.left) && !isRED(root.right)) {
            root.color = RED;
        }
        root = deleteMin(root);
        if (!isEmpty()) {
            root.color = BLACK;
        }
    }

    private Node deleteMin(Node x) {
        if (x.left == null) {
            return null;
        }
        if (!isRED(x.left) && !isRED(x.left.left)) {
            x = moveRedLeft(x);
        }
        x.left = deleteMin(x.left);
        return balance(x);
    }

    /**
     * 描述: 删除最大元素
     */
    public void deleteMax() {
        if (isEmpty()) {
            throw new NoSuchElementException("二叉查找树已经空啦！");
        }

        // 如果两个子节点都是黑色地，那么根节点改为红色
        if (!isRED(root.left) && !isRED(root.right)) {
            root.color = RED;
        }
        root = deleteMax(root);
        if (!isEmpty()) {
            root.color = BLACK;
        }
    }

    private Node deleteMax(Node x) {
        if (isRED(x.left)) {
            x = rotateRight(x);
        }
        if (x.right == null) {
            return null;
        }
        if (!isRED(x.right) && !isRED(x.right.left)) {
            x = moveRedRight(x);
        }
        x.right = deleteMax(x.right);
        return balance(x);
    }

    /**
     * 描述: 删除指定键的节点
     */
    public void delete(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("传入的键不能为null");
        }
        // 不包含指定的key，直接返回
        if (!contains(key)) {
            return;
        }

        // 如果两个子节点都是黑色地，那么根节点改为红色
        if (!isRED(root.left) && !isRED(root.right)) {
            root.color = RED;
        }

        root = delete(root, key);
        if (!isEmpty()) {
            root.color = BLACK;
        }
    }

    /**
     * 删除以x作为根节点的子树找那个的键为key的节点
     */
    private Node delete(Node x, Key key) {
        if (key.compareTo(x.key) < 0) {
            if (!isRED(x.left) && !isRED(x.left.left)) {
                x = moveRedLeft(x);
            }
            x.left = delete(x.left, key);
        } else {
            if (isRED(x.left)) {
                x = rotateRight(x);
            }
            if (key.compareTo(x.key) == 0 && (x.right == null)) {
                return null;
            }
            if (!isRED(x.right) && !isRED(x.right.left)) {
                x = moveRedRight(x);
            }
            if (key.compareTo(x.key) == 0) {
                Node y = min(x.right);
                x.key = y.key;
                x.value = y.value;
                x.right = deleteMin(x.right);
            } else {
                x.right = delete(x.right, key);
            }
        }
        // 把以x作为根节点子树重新平衡
        return balance(x);
    }

    /**********************************************************************
     ***  辅助函数：左旋转、右旋转、颜色翻转、红节点右移、红节点左移、子树再平衡等
     **********************************************************************/

    /**
     * make a left-leaning link lean to the right
     */
    private Node rotateRight(Node h) {
        // assert (h != null) && isRed(h.left);
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = x.right.color;
        x.right.color = RED;
        x.size = h.size;
        h.size = size(h.left) + size(h.right) + 1;
        return x;
    }

    /**
     * make a right-leaning link lean to the left
     */
    private Node rotateLeft(Node h) {
        // assert (h != null) && isRed(h.right);
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = x.left.color;
        x.left.color = RED;
        x.size = h.size;
        h.size = size(h.left) + size(h.right) + 1;
        return x;
    }

    /**
     * flip the colors of a node and its two children
     */
    private void flipColors(Node h) {
        // h must have opposite color of its two children
        // assert (h != null) && (h.left != null) && (h.right != null);
        // assert (!isRed(h) &&  isRed(h.left) &&  isRed(h.right))
        //    || (isRed(h)  && !isRed(h.left) && !isRed(h.right));
        h.color = !h.color;
        h.left.color = !h.left.color;
        h.right.color = !h.right.color;
    }

    /**
     * Assuming that h is red and both h.left and h.left.left
     * are black, make h.left or one of its children red.
     */
    private Node moveRedLeft(Node h) {
        // assert (h != null);
        // assert isRed(h) && !isRed(h.left) && !isRed(h.left.left);

        flipColors(h);
        if (isRED(h.right.left)) {
            h.right = rotateRight(h.right);
            h = rotateLeft(h);
            flipColors(h);
        }
        return h;
    }

    /**
     * Assuming that h is red and both h.right and h.right.left
     * are black, make h.right or one of its children red.
     */
    private Node moveRedRight(Node h) {
        // assert (h != null);
        // assert isRed(h) && !isRed(h.right) && !isRed(h.right.left);
        flipColors(h);
        if (isRED(h.left.left)) {
            h = rotateRight(h);
            flipColors(h);
        }
        return h;
    }

    /**
     * restore red-black tree invariant
     */
    private Node balance(Node h) {
        // assert (h != null);

        if (isRED(h.right)) {
            h = rotateLeft(h);
        }
        if (isRED(h.left) && isRED(h.left.left)) {
            h = rotateRight(h);
        }
        if (isRED(h.left) && isRED(h.right)) {
            flipColors(h);
        }

        h.size = size(h.left) + size(h.right) + 1;
        return h;
    }

    /**
     * Returns the height of the BST (for debugging).
     *
     * @return the height of the BST (a 1-node tree has height 0)
     */
    public int height() {
        return height(root);
    }

    private int height(Node x) {
        if (x == null) {
            return -1;
        }
        return 1 + Math.max(height(x.left), height(x.right));
    }

    /***************************************************************************
     *  Ordered symbol table methods.
     ***************************************************************************/

    /**
     * Returns the smallest key in the symbol table.
     *
     * @return the smallest key in the symbol table
     * @throws NoSuchElementException if the symbol table is empty
     */
    public Key min() {
        if (isEmpty()) {
            throw new NoSuchElementException("called min() with empty symbol table");
        }
        return min(root).key;
    }

    /**
     * the smallest key in subtree rooted at x; null if no such key
     */
    private Node min(Node x) {
        // assert x != null;
        if (x.left == null) {
            return x;
        } else {
            return min(x.left);
        }
    }

    /**
     * Returns the largest key in the symbol table.
     *
     * @return the largest key in the symbol table
     * @throws NoSuchElementException if the symbol table is empty
     */
    public Key max() {
        if (isEmpty()) {
            throw new NoSuchElementException("called max() with empty symbol table");
        }
        return max(root).key;
    }

    /**
     * the largest key in the subtree rooted at x; null if no such key
     */
    private Node max(Node x) {
        // assert x != null;
        if (x.right == null) {
            return x;
        } else {
            return max(x.right);
        }
    }


    /**
     * Returns the largest key in the symbol table less than or equal to {@code key}.
     *
     * @param key the key
     * @return the largest key in the symbol table less than or equal to {@code key}
     * @throws NoSuchElementException   if there is no such key
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public Key floor(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to floor() is null");
        }
        if (isEmpty()) {
            throw new NoSuchElementException("called floor() with empty symbol table");
        }
        Node x = floor(root, key);
        if (x == null) {
            return null;
        } else {
            return x.key;
        }
    }

    // the largest key in the subtree rooted at x less than or equal to the given key
    private Node floor(Node x, Key key) {
        if (x == null) {
            return null;
        }
        int cmp = key.compareTo(x.key);
        if (cmp == 0) {
            return x;
        }
        if (cmp < 0) {
            return floor(x.left, key);
        }
        Node t = floor(x.right, key);
        if (t != null) {
            return t;
        } else {
            return x;
        }
    }

    /**
     * Returns the smallest key in the symbol table greater than or equal to {@code key}.
     *
     * @param key the key
     * @return the smallest key in the symbol table greater than or equal to {@code key}
     * @throws NoSuchElementException   if there is no such key
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public Key ceiling(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to ceiling() is null");
        }
        if (isEmpty()) {
            throw new NoSuchElementException("called ceiling() with empty symbol table");
        }
        Node x = ceiling(root, key);
        if (x == null) {
            return null;
        } else {
            return x.key;
        }
    }

    /**
     * the smallest key in the subtree rooted at x greater than or equal to the given key
     */
    private Node ceiling(Node x, Key key) {
        if (x == null) {
            return null;
        }
        int cmp = key.compareTo(x.key);
        if (cmp == 0) {
            return x;
        }
        if (cmp > 0) {
            return ceiling(x.right, key);
        }
        Node t = ceiling(x.left, key);
        if (t != null) {
            return t;
        } else {
            return x;
        }
    }

    /**
     * Return the kth smallest key in the symbol table.
     *
     * @param k the order statistic
     * @return the {@code k}th smallest key in the symbol table
     * @throws IllegalArgumentException unless {@code k} is between 0 and
     *                                  <em>n</em>–1
     */
    public Key select(int k) {
        if (k < 0 || k >= size()) {
            throw new IllegalArgumentException("called select() with invalid argument: " + k);
        }
        Node x = select(root, k);
        return x.key;
    }

    /**
     * the key of rank k in the subtree rooted at x
     */
    private Node select(Node x, int k) {
        // assert x != null;
        // assert k >= 0 && k < size(x);
        int t = size(x.left);
        if (t > k) {
            return select(x.left, k);
        } else if (t < k) {
            return select(x.right, k - t - 1);
        } else {
            return x;
        }
    }

    /**
     * Return the number of keys in the symbol table strictly less than {@code key}.
     *
     * @param key the key
     * @return the number of keys in the symbol table strictly less than {@code key}
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public int rank(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to rank() is null");
        }
        return rank(key, root);
    }

    /**
     * number of keys less than key in the subtree rooted at x
     */
    private int rank(Key key, Node x) {
        if (x == null) {
            return 0;
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            return rank(key, x.left);
        } else if (cmp > 0) {
            return 1 + size(x.left) + rank(key, x.right);
        } else {
            return size(x.left);
        }
    }

    /***************************************************************************
     *  Range count and range search.
     ***************************************************************************/

    /**
     * Returns all keys in the symbol table as an {@code Iterable}.
     * To iterate over all of the keys in the symbol table named {@code st},
     * use the foreach notation: {@code for (Key key : st.keys())}.
     *
     * @return all keys in the symbol table as an {@code Iterable}
     */
    public Iterable<Key> keys() {
        if (isEmpty()) {
            return new P95LinkedListQueue<>();
        }
        return keys(min(), max());
    }

    /**
     * Returns all keys in the symbol table in the given range,
     * as an {@code Iterable}.
     *
     * @param lo minimum endpoint
     * @param hi maximum endpoint
     * @return all keys in the sybol table between {@code lo}
     * (inclusive) and {@code hi} (inclusive) as an {@code Iterable}
     * @throws IllegalArgumentException if either {@code lo} or {@code hi}
     *                                  is {@code null}
     */
    public Iterable<Key> keys(Key lo, Key hi) {
        if (lo == null) {
            throw new IllegalArgumentException("first argument to keys() is null");
        }
        if (hi == null) {
            throw new IllegalArgumentException("second argument to keys() is null");
        }

        P95LinkedListQueue<Key> queue = new P95LinkedListQueue<>();
        // if (isEmpty() || lo.compareTo(hi) > 0) return queue;
        keys(root, queue, lo, hi);
        return queue;
    }

    /**
     * add the keys between lo and hi in the subtree rooted at x to the queue
     */
    private void keys(Node x, P95LinkedListQueue<Key> queue, Key lo, Key hi) {
        if (x == null) {
            return;
        }
        int cmplo = lo.compareTo(x.key);
        int cmphi = hi.compareTo(x.key);
        if (cmplo < 0) {
            keys(x.left, queue, lo, hi);
        }
        if (cmplo <= 0 && cmphi >= 0) {
            queue.enqueue(x.key);
        }
        if (cmphi > 0) {
            keys(x.right, queue, lo, hi);
        }
    }

    /**
     * Returns the number of keys in the symbol table in the given range.
     *
     * @param lo minimum endpoint
     * @param hi maximum endpoint
     * @return the number of keys in the sybol table between {@code lo}
     * (inclusive) and {@code hi} (inclusive)
     * @throws IllegalArgumentException if either {@code lo} or {@code hi}
     *                                  is {@code null}
     */
    public int size(Key lo, Key hi) {
        if (lo == null) {
            throw new IllegalArgumentException("first argument to size() is null");
        }
        if (hi == null) {
            throw new IllegalArgumentException("second argument to size() is null");
        }

        if (lo.compareTo(hi) > 0) {
            return 0;
        }
        if (contains(hi)) {
            return rank(hi) - rank(lo) + 1;
        } else {
            return rank(hi) - rank(lo);
        }
    }


    /***************************************************************************
     *  Check integrity of red-black tree data structure.
     ***************************************************************************/
    private boolean check() {
        if (!isBST()) {
            System.out.println("Not in symmetric order");
        }
        if (!isSizeConsistent()) {
            System.out.println("Subtree counts not consistent");
        }
        if (!isRankConsistent()) {
            System.out.println("Ranks not consistent");
        }
        if (!is23()) {
            System.out.println("Not a 2-3 tree");
        }
        if (!isBalanced()) {
            System.out.println("Not balanced");
        }
        return isBST() && isSizeConsistent() && isRankConsistent() && is23() && isBalanced();
    }

    /**
     * does this binary tree satisfy symmetric order?
     * Note: this test also ensures that data structure is a binary tree since order is strict
     */
    private boolean isBST() {
        return isBST(root, null, null);
    }

    /**
     * is the tree rooted at x a BST with all keys strictly between min and max
     * (if min or max is null, treat as empty constraint)
     * Credit: Bob Dondero's elegant solution
     */
    private boolean isBST(Node x, Key min, Key max) {
        if (x == null) {
            return true;
        }
        if (min != null && x.key.compareTo(min) <= 0) {
            return false;
        }
        if (max != null && x.key.compareTo(max) >= 0) {
            return false;
        }
        return isBST(x.left, min, x.key) && isBST(x.right, x.key, max);
    }

    /**
     * are the size fields correct?
     */
    private boolean isSizeConsistent() {
        return isSizeConsistent(root);
    }

    private boolean isSizeConsistent(Node x) {
        if (x == null) {
            return true;
        }
        if (x.size != size(x.left) + size(x.right) + 1) {
            return false;
        }
        return isSizeConsistent(x.left) && isSizeConsistent(x.right);
    }

    /**
     * check that ranks are consistent
     */
    private boolean isRankConsistent() {
        for (int i = 0; i < size(); i++) {
            if (i != rank(select(i))) {
                return false;
            }
        }
        for (Key key : keys()) {
            if (key.compareTo(select(rank(key))) != 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Does the tree have no red right links, and at most one (left)
     * red links in a row on any path?
     */
    private boolean is23() {
        return is23(root);
    }

    private boolean is23(Node x) {
        if (x == null) {
            return true;
        }
        if (isRED(x.right)) {
            return false;
        }
        if (x != root && isRED(x) && isRED(x.left)) {
            return false;
        }
        return is23(x.left) && is23(x.right);
    }

    /**
     * do all paths from root to leaf have same number of black edges?
     */
    private boolean isBalanced() {
        // number of black links on path from root to min
        int black = 0;
        Node x = root;
        while (x != null) {
            if (!isRED(x)) {
                black++;
            }
            x = x.left;
        }
        return isBalanced(root, black);
    }

    /**
     * does every path from the root to a leaf have the given number of black links?
     */
    private boolean isBalanced(Node x, int black) {
        if (x == null) {
            return black == 0;
        }
        if (!isRED(x)) {
            black--;
        }
        return isBalanced(x.left, black) && isBalanced(x.right, black);
    }

}
