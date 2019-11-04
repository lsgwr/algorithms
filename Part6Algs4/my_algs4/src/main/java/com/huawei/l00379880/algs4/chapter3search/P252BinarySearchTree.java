/*****************************************************************
 * @Description : 二叉查找树，P250~P263，以键作为每个树杈节点的标明值，
 *              特征(官方称为BST.java)：
 *                1.每个节点都含有一个Comparable的键(以及相关联的值)
 *                2.每个节点的键都大于左子树的任意节点而小于右子树的任
 *                  意节点的键
 *
 *
 *                加入二叉查找树只需要对单个数据类型进行查找而不是键值对，
 *                那么只需要把key设置为那个数据类型,value随便设置一个就行了
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/1/7 下午4:01
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package com.huawei.l00379880.algs4.chapter3search;


import com.huawei.l00379880.algs4.chapter1javabasic.P95LinkedListQueue;

import java.util.NoSuchElementException;

public class P252BinarySearchTree<Key extends Comparable<Key>, Value> {

    /**
     * 二叉查找树的根节点
     */
    private Node root;

    private class Node {
        private Key key;
        private Value value;
        /**
         * 左子节点和右子节点(也可以说左子树和右子树)
         */
        private Node left, right;
        /**
         * 子树的节点总数
         */
        private int size;

        public Node(Key key, Value value, int size) {
            this.key = key;
            this.value = value;
            this.size = size;
        }
    }

    public P252BinarySearchTree() {
    }

    public int size() {
        return size(root);
    }

    /**
     * 返回以x作为根节点的二分查找树下的键值对数目
     */
    private int size(Node x) {
        if (x == null) {
            return 0;
        } else {
            return x.size;
        }
    }

    /**
     * 描述:返回指定key范围内的key的数目
     * Returns the number of keys in the symbol table in the given range.
     */
    public int size(Key low, Key high) {
        if (low == null) {
            throw new IllegalArgumentException("第一个参数不能为null");
        }
        if (low == null) {
            throw new IllegalArgumentException("第二个参数不能为null");
        }
        // 入参不合理
        if (low.compareTo(high) > 0) {
            return 0;
        }
        if (contains(high)) {
            // 包含上界
            return rank(high) - rank(low) + 1;
        } else {
            return rank(high) - rank(low);
        }
    }

    /**
     * 描述: 判断键值表是否为空
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * 描述: 查看在当前的二分查找树中是否含有指定键的节点
     */
    public boolean contains(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("传入的key不能为null");
        }
        return get(key) != null;
    }

    /**
     * 获取键key在二分查找树中对应的值value
     */
    public Value get(Key key) {
        return get(root, key);
    }

    /**
     * 以x作为二分查找树，查找是否存在键为key的树节点
     *
     * @param x   二分查找树的根节点
     * @param key 要查找的键
     * @return 要查找的键对应的值
     */
    private Value get(Node x, Key key) {
        if (key == null) {
            throw new IllegalArgumentException("传入的key不能为null");
        }
        // 为null不太可能是传入的null,更可能是递归到了子树的最后一层，下面再没节点了，所以没找到就是没找到了

        if (x == null) {
            return null;
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            // 给定的key小于当前节点的key,那么在左子树中接着查
            return get(x.left, key);
        } else if (cmp > 0) {
            // 给定的key大于当前节点的key,那么在左子树中接着查
            return get(x.right, key);
        } else {
            // 找到了与key相等的节点
            return x.value;
        }
    }

    /**
     * 描述: 更新制定key对应的节点
     */
    public void put(Key key, Value value) {
        if (key == null) {
            throw new IllegalArgumentException("传入的键不能为null！");
        }
        if (value == null) {
            throw new IllegalArgumentException("传入的值不能为null");
        }
        root = put(root, key, value);
        assert check();
    }

    private Node put(Node x, Key key, Value value) {
        // 如果递归到头还没发现与制定key相等的节点，创建新的节点并返回
        if (x == null) {
            return new Node(key, value, 1);
        }
        // 制定的key与当前递归到的Key的键进行比较
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            x.left = put(x.left, key, value);
        } else if (cmp > 0) {
            x.right = put(x.right, key, value);
        } else {
            // 找到合适的点了，那么就进行更新
            x.value = value;
        }
        // 这个size是从一开始插入就设置好地，所以不需要递归
        x.size = 1 + size(x.left) + size(x.right);
        return x;
    }

    public void deleteMin() {
        if (isEmpty()) {
            throw new NoSuchElementException("键值表已经空啦");
        }
        root = deleteMin(root);
        assert check();
    }

    /**
     * 描述: 队规查找最小节点，找到后再递归回到根节点，一路顺便更新所有左节点的size。
     * 可以参考P260的图3.2.12
     */
    private Node deleteMin(Node x) {
        if (x.left == null) {
            return x.right;
        }
        // 只会把最小的节点删除，然后用右节点覆盖最后一级的根节点。然后再循环到整个二叉树根节点左侧的左侧节点，更新所有相关节点的size，一直到根节点。
        x.left = deleteMin(x.left);
        x.size = size(x.left) + size(x.right) + 1;
        return x;
    }

    /**
     * 描述: 删除删除最大key对应的键值对
     */
    private void deleteMax() {
        if (isEmpty()) {
            throw new NoSuchElementException("键值表已经空啦");
        }
        root = deleteMax(root);
        assert check();
    }

    /**
     * 描述: 队规查找最大节点，找到后再递归回到根节点，一路顺便更新所有右节点的size。
     */
    private Node deleteMax(Node x) {
        if (x.right == null) {
            return x.left;
        }
        // 只会把最大的节点删除，然后用左节点覆盖最后一级的根节点。然后再循环到整个二叉树根节点右侧的右节点，更新所有相关节点的size，一直到根节点。
        x.right = deleteMax(x.right);
        x.size = size(x.left) + size(x.right) + 1;
        return x;
    }

    public void delete(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("传入的key不能为null");
        }
        root = delete(root, key);
    }

    /**
     * 描述: 递归查找要删除的键.
     * 可以参考P260页的图3.2.13
     */
    private Node delete(Node x, Key key) {
        if (x == null) {
            return null;
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            x.left = delete(x.left, key);
        } else if (cmp > 0) {
            x.right = delete(x.right, key);
        } else {
            // 当待删除节点的左子树或者右子树为空的时候，直接把不空的子树挂到删除节点的付节点上，
            // 待删除节点自己就被JVM回收了，然后一路递归回去更新节点的size即可
            if (x.right == null) {
                return x.left;
            }
            if (x.left == null) {
                return x.right;
            }
            // 如果待删除节点的左右子树都不为空，那么新建一个执行待删除节点的指针(见P260 图3.2.13)
            Node t = x;
            // 找到待删除节点右子树中的最小节点。将原来指向待删除节点的指针x指向右子树的最小节点
            x = min(t.right);
            // 下面的两步就是用右侧最小的节点直接替换掉待删除节点来连接待删除节点的左右节点，
            // 再通过递归将待删除节点孤立出去从而被JVM自动回收
            x.right = deleteMin(t.right);
            x.left = t.left;
        }
        // 递归更新待删除节点到根节点的所有节点的size
        x.size = size(x.left) + size(x.right) + 1;
        return x;
    }

    /**
     * 描述: 返回整个二叉查找树的最小节点的key
     */
    public Key min() {
        if (isEmpty()) {
            throw new NoSuchElementException("键值表已经为空啦！！");
        }
        return min(root).key;
    }

    /**
     * 描述: 找到从某个节点开始作为根节点的子二叉查找树中的最小key节点
     */
    private Node min(Node x) {
        if (x.left == null) {
            return x;
        } else {
            return min(x.left);
        }
    }

    /**
     * 描述: 返回整个二叉查找树的最大节点的key
     */
    public Key max() {
        if (isEmpty()) {
            throw new NoSuchElementException("键值表已经为空啦！！");
        }
        return max(root).key;
    }

    /**
     * 描述: 找到从某个节点开始作为根节点的子二叉查找树中的最大key节点
     */
    private Node max(Node x) {
        if (x.right == null) {
            return x;
        } else {
            return max(x.right);
        }
    }

    /**
     * 描述: 返回符号表中小于等于给定key的最大key
     * Returns the largest key in the symbol table less than or equal to  key.
     */
    public Key floor(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("传入的key不能为null");
        }
        if (isEmpty()) {
            throw new NoSuchElementException("键值表已经为空啦！");
        }
        Node x = floor(root, key);
        if (x == null) {
            return null;
        } else {
            return x.key;
        }
    }

    private Node floor(Node x, Key key) {
        // 如果递归找到最后也没找到小于key的节点，那么就返回null
        if (x == null) {
            return null;
        }
        int cmp = key.compareTo(x.key);
        // 如果相等，这个点就是<=key的最大节点
        if (cmp == 0) {
            return x;
        }
        // 如果当前的节点大于给定节点,那么继续向左边(更小的方向找)
        if (cmp < 0) {
            return floor(x.left, key);
        }
        // 如果当前的节点小于给定节点，那么向右边走(更大的方向找，看看是不是仍然小于key,这样才能更逼近key)
        Node t = floor(x.right, key);
        if (t != null) {
            return t;
        } else {
            // 再往右就空了，那么直接返回当前的小于key的节点
            return x;
        }
    }

    /**
     * 描述: 返回符号表中大于等于给定key的最小key
     * Returns the smallest key in the symbol table greater than or equal to  key.
     */
    public Key ceiling(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("传入的key不能为null");
        }
        if (isEmpty()) {
            throw new NoSuchElementException("键值表已经为空啦！");
        }
        Node x = ceiling(root, key);
        if (x == null) {
            return null;
        } else {
            return x.key;
        }
    }

    private Node ceiling(Node x, Key key) {
        // 如果递归找到最后也没找到大于key的节点，那么就返回null
        if (x == null) {
            return null;
        }
        int cmp = key.compareTo(x.key);
        // 如果相等，这个点就是>=key的最小节点
        if (cmp == 0) {
            return x;
        }
        // 如果当前的节点大于给定节点,那么继续向左边(更小的方向找，也许还有更小地但是大于key的节点呢)
        if (cmp < 0) {
            Node t = ceiling(x.left, key);
            if (t != null) {
                return t;
            } else {
                // 再往左就空了，那么直接返回当前的大于key的节点
                return x;
            }
        }
        // 如果cmp<0,那么当前节点小于给定节点，那么往右侧找更大的
        return ceiling(x.right, key);
    }

    /**
     * 描述: 返回符号表中第k小的key
     * Return the kth smallest key in the symbol table.
     */
    public Key select(int k) {
        if (k < 0 || k > size()) {
            throw new IllegalArgumentException("k的范围超出了合适范围！");
        }
        Node x = select(root, k);
        return x.key;
    }

    /**
     * 描述: Return key of rank k.
     */
    private Node select(Node x, int k) {
        if (x == null) {
            return null;
        }
        int t = size(x.left);
        if (t > k) {
            // 剩下的节点还是多于要找的k个
            // 继续向左，不断再刨除
            return select(x.left, k);
        } else if (t < k) {
            // 左子树还不够，那么再从右边选几个补充
            return select(x.right, k - t - 1);
        } else {
            // 一直递归到数目正常了，返回递归到的点
            return x;
        }
    }


    /**
     * 描述: 返回符号表中严格小于制定key的节点数目(注意不包含等于)
     * Return the number of keys in the symbol table strictly less than key.
     */
    public int rank(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("输入的key不能为null");
        }
        return rank(root, key);
    }

    /**
     * 描述: Number of keys in the subtree less than key.(注意不包含等于)
     */
    private int rank(Node x, Key key) {
        if (x == null) {
            return 0;
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            // 如果当前key大于给定key，那么向左边小的方向找
            return rank(x.left, key);
        } else if (cmp > 0) {
            // 如果当前key小于给定key,那么左子树一定也全都小于key，右子树可能还有大于当前key但是小于指定key地
            return 1 + size(x.left) + rank(x.right, key);
        } else {
            // 如果当前key等于给定key,那么左子树肯定全小于keu，右子树肯定全大于key.返回左子树长度即可。等于key的节点不用返回了
            return size(x.left);
        }
    }

    public Iterable<Key> keys() {
        return keys(min(), max());
    }

    private Iterable<Key> keys(Key low, Key high) {
        if (low == null) {
            throw new IllegalArgumentException("第一个参数不能为null");
        }
        if (low == null) {
            throw new IllegalArgumentException("第二个参数不能为null");
        }
        P95LinkedListQueue<Key> queue = new P95LinkedListQueue<>();
        keys(root, queue, low, high);
        return queue;
    }

    private void keys(Node x, P95LinkedListQueue queue, Key low, Key high) {
        if (x == null) {
            return;
        }
        int cmpLow = low.compareTo(x.key);
        int cmpHigh = high.compareTo(x.key);
        // 1.如果当前的点的键大于low,继续向左找更小的键但是大于key地
        if (cmpLow < 0) {
            keys(x.left, queue, low, high);
        }
        // 2.通过1和3确定了在low~high范围的节点，下面就该依次加入到队列里了
        if (cmpLow <= 0 && cmpHigh >= 0) {
            queue.enqueue(x.key);
        }
        // 3.如果当前的当前的键小于high,那么久继续向右找更大的键但是小于key地
        if (cmpHigh > 0) {
            keys(x.right, queue, low, high);
        }
    }

    /**
     * 描述: 返回二叉查找树的高度（几层）。一个节点的话是0层
     * Returns the height of the BinarySearchTree
     */
    public int height() {
        return height(root);
    }

    /**
     * 描述: 递归返回以x为根节点的树的高度
     */
    private int height(Node x) {
        if (x == null) {
            return -1;
        }
        // 一个节点的时候为1+max(-1,-1)=1+(-1)=0,所以一个节点的时候是0层。
        // 三个节点的时候为1+max((1+(-1)),(1+(-1)))=1+0=1.依次验证即可
        return 1 + Math.max(height(x.left), height(x.right));
    }

    /**
     * 描述: 对二叉树进行广度优先遍历 ,即一层一层地访问下去。
     * Returns the keys in the BST in level order
     * 返回值：the keys in the BST in level order traversal
     */
    public Iterable<Key> levelOrder() {
        // 键值队列
        P95LinkedListQueue<Key> keys = new P95LinkedListQueue<>();
        // 节点队列
        P95LinkedListQueue<Node> queue = new P95LinkedListQueue<>();
        // 把整个树放进队列
        queue.enqueue(root);
        while (!queue.isEmpty()) {
            Node x = queue.dequeue();
            if (x == null) {
                continue;
            }
            // 这里非常巧，利用queue不全的仅仅出出，完美地把每一层上的key给弹了出来。
            // 最终queue是什么样式无所谓地
            keys.enqueue(x.key);
            queue.enqueue(x.left);
            queue.enqueue(x.right);
        }
        return keys;
    }

    /**
     * 描述: 检查二叉查找树的数据结构是不是符合定义，分多个方面
     */
    private boolean check() {
        if (!isBinarySearchTree()) {
            System.out.println("二叉查找树不符合基本规定：根节点大于左边小于右边");
        }
        if (!isSizeConsistent()) {
            System.out.println("相邻节点的size不是连续地！");
        }
        if (!isRankConsistent()) {
            System.out.println("相邻节点的排名是不连续地！");
        }
        return isBinarySearchTree() && isSizeConsistent() && isRankConsistent();
    }

    /**
     * does this binary tree satisfy symmetric order?
     * Note: this test also ensures that data structure is a binary tree since order is strict
     */
    private boolean isBinarySearchTree() {
        return isBinarySearchTree(root, null, null);
    }

    /**
     * is the tree rooted at x a BST with all keys strictly between min and max
     * (if min or max is null, treat as empty constraint)
     * Credit: Bob Dondero's elegant solution
     */
    private boolean isBinarySearchTree(Node x, Key min, Key max) {
        if (x == null) {
            return true;
        }
        if (min != null && x.key.compareTo(min) <= 0) {
            return false;
        }
        if (max != null && x.key.compareTo(max) >= 0) {
            return false;
        }
        return isBinarySearchTree(x.left, min, x.key) && isBinarySearchTree(x.right, x.key, max);
    }

    // are the size fields correct?
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
     * 描述: check that ranks are consistent
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

}
