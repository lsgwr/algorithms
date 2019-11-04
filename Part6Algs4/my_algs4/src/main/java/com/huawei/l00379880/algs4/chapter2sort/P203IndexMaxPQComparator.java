package com.huawei.l00379880.algs4.chapter2sort;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

/***********************************************************
 * @Description : 带索引的最大堆,pq、qp的详细讲解见
 *              https://www.cnblogs.com/nullzx/p/6624731.html
 *              在这个过程中keys数组是不会进行swim和sink地因为pq和
 *              qp完全能记录堆有序的关系.只有在增加和删除元素的时候
 *              才会修改keys,这也是为什么在MaxPQ中元素数组Key[]明明
 *              为pq,而当前类中的Key[]称为keys、记录索引关系的整型数组
 *              int[]被命名为pq的原因,名字为pq地一定是最终进行优先队列
 *              各种操作的对象
 *
 *
 * @核心算法 通过比较keys中元素的大小关系, 把pq排成堆有序, 堆中的元素为"第x大"的x
 * @author      : 梁山广
 * @date        : 2018/1/3 21:07
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
public class P203IndexMaxPQComparator<Key> implements Iterable<Integer> {
    /**
     * 优先队列的最大元素数目
     */
    private int maxN;
    /**
     * 队列实时元素数
     */
    private int n;
    /**
     * binary heap using 1-based indexing 下标是从1开始
     * pq[i]代表在keys中第i大的元素在keys中的下标是pq[i]
     */
    private int[] pq;
    /**
     * 下标是从1开始
     * inverse of pq - qp[pq[i]] = pq[qp[i]] = i
     * qp[i]代表在keys中第i个元素在keys中是第pq[i]大
     */
    private int[] qp;
    /**
     * keys[i] = priority of i
     */
    private Key[] keys;

    /**
     * 自定义比较器
     */
    private Comparator<Key> comparator;

    public P203IndexMaxPQComparator(Comparator<Key> comparator, int maxN) {
        if (maxN < 0) {
            throw new IllegalArgumentException("优先队列长度不能小于0");
        }
        this.maxN = maxN;
        this.n = 0;
        this.comparator = comparator;
        this.keys = (Key[]) new Object[maxN + 1];
        this.pq = new int[maxN + 1];
        this.qp = new int[maxN + 1];
        // keys[i]在优先队列中的排次初始化为-1,后面排完了都会不是-1了
        for (int i = 0; i <= maxN; i++) {
            qp[i] = -1;
        }
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    /**
     * Is i an index on this priority queue?
     *
     * @param i an index
     * @return true if i is an index on this priority queue;
     * false otherwise
     * @throws IllegalArgumentException unless 0 <= i < maxN}
     */
    public boolean contains(int i) {
        if (i < 0 || i >= maxN) {
            throw new IllegalArgumentException("参数越界!");
        }
        return qp[i] != -1;
    }

    /**
     * Associate key with index i.
     *
     * @param i   an index
     * @param key the key to associate with index i
     * @throws IllegalArgumentException unless 0 <= i < maxN
     * @throws IllegalArgumentException if there already is an item
     *                                  associated with index i
     */
    public void insert(int i, Key key) {
        if (i < 0 || i >= maxN) {
            throw new IllegalArgumentException("参数越界!");
        }
        if (contains(i)) {
            throw new IllegalArgumentException("index is already in the priority");
        }
        n++;
        qp[i] = n;
        pq[n] = i;
        keys[i] = key;
        swim(n);
    }

    /**
     * 返回最大元素在keys中的下标
     */
    public int maxIndex() {
        if (n == 0) {
            throw new NoSuchElementException("优先队列已空!");
        }
        return pq[1];
    }

    /**
     * 返回最大元素
     */
    public Key maxKey() {
        if (n == 0) {
            throw new NoSuchElementException("优先队列已空");
        }
        return keys[pq[1]];
    }


    /**
     * Removes a maximum key and returns its associated index.
     *
     * @return an index associated with a maximum key
     * @throws NoSuchElementException if this priority queue is empty
     */
    public int delMax() {
        if (n == 0) {
            throw new NoSuchElementException("优先队列已空");
        }
        // pq[1]表示第一大的元素在keys中的下标
        int max = pq[1];
        // 在keys中的元素删除之前,先把pq和qp中的对应元素进行修改
        exchange(1, n--);
        // 修改keys中的元素
        sink(1);
        // 把数组最大元素置位null,相当于释放掉了.
        // keys中第下标为max的在keys中是第-1大,明显给初始化掉了.
        // 也是因为qp在一开始初始化的时候就初始化地-1
        qp[max] = -1;
        keys[max] = null;

        // 确保最大元素的下标在前面exchange(1,n--)中被移到了合适位置
        assert pq[n + 1] == max;
        // 把交换过去的最大元素在keys中的下标
        pq[n + 1] = -1;
        // 返回最大元素在数组中的下标
        return max;
    }

    /**
     * 返回keys数组中下标为i的元素值
     */
    public Key keyOf(int i) {
        if (i < 0 || i >= maxN) {
            throw new IllegalArgumentException("参数越界!");
        }
        if (!contains(i)) {
            // 可能已经被delMax了
            throw new IllegalArgumentException("不存在该下标对应的元素");
        } else {
            return keys[i];
        }

    }

    /**
     * 把keys[i]的值设置为key.新的key可以比原来的大也可以比原来的要小
     */
    public void changeKey(int i, Key key) {
        if (i < 0 || i >= maxN) {
            throw new IllegalArgumentException("参数越界!");
        }
        if (!contains(i)) {
            // 可能已经被delMax了
            throw new IllegalArgumentException("不存在该下标对应的元素");
        }
        keys[i] = key;
        swim(qp[i]);
        sink(qp[i]);
    }

    /**
     * 把指定位置的元素替换为比原来大的值
     */
    public void increaseKey(int i, Key key) {
        if (i < 0 || i >= maxN) {
            throw new IllegalArgumentException("参数越界!");
        }
        if (!contains(i)) {
            // 可能已经被delMax了
            throw new IllegalArgumentException("不存在该下标对应的元素");
        }
        // 如果新来的key不比老的key要大的话(注意也可能相等哦!这个也是不合理的情况)
        // 换成P153CommenFuns.less(key, keys[i])是不行地,因为没有检查相等的情况
        if (!P153CommenFuns.more(comparator, key, keys[i])) {
            throw new IllegalArgumentException("新的Key必须要比老的Key大!");
        }
        keys[i] = key;
        //来更大的元素就该上浮
        swim(qp[i]);
    }

    /**
     * 把指定位置的元素替换为比原来小的值
     */
    public void decreaseKey(int i, Key key) {
        if (i < 0 || i >= maxN) {
            throw new IllegalArgumentException("参数越界!");
        }
        if (!contains(i)) {
            // 可能已经被delMax了
            throw new IllegalArgumentException("不存在该下标对应的元素");
        }
        // 如果新来的key不比老的key要小的话(注意也可能相等哦!这个也是不合理的情况)
        // 换成P153CommenFuns.more(key, keys[i])是不行地,因为没有检查相等的情况
        if (!P153CommenFuns.less(comparator, key, keys[i])) {
            throw new IllegalArgumentException("新的Key必须要比老的Key大!");
        }
        keys[i] = key;
        //来更小的元素就该下沉
        sink(qp[i]);
    }

    /**
     * 删除keys中下标为i的元素
     */
    public void delete(int i) {
        if (i < 0 || i >= maxN) {
            throw new IllegalArgumentException("参数越界!");
        }
        if (!contains(i)) {
            // 可能已经被delMax了
            throw new IllegalArgumentException("不存在该下标对应的元素");
        }
        // 下标为i的元素在keys中是第qp[i]大,即第index大
        int index = qp[i];
        exchange(index, n--);
        swim(index);
        sink(index);
        keys[i] = null;
        qp[i] = -1;
    }

    /**
     * 交换pp和qp中i和j位置
     */
    private void exchange(int i, int j) {
        int temp = pq[i];
        pq[i] = pq[j];
        pq[j] = temp;
        // pq更新了,那么根据qp[pq[i]] = pq[qp[i]] = i可以对qp进行更新
        qp[pq[i]] = i;
        qp[pq[j]] = j;
    }

    /**
     * 如果当前节点大于父节点那么就上浮(针对pq和qp来进行地)
     */
    private void swim(int k) {
        while (k > 1 && P153CommenFuns.more(comparator, keys[pq[k]], keys[pq[k / 2]])) {
            exchange(k, k / 2);
            k = k / 2;
        }
    }

    private void sink(int k) {
        while (2 * k <= n) {
            int j = 2 * k;
            if (j < n && P153CommenFuns.less(comparator, keys[pq[j]], keys[pq[j + 1]])) {
                j++;
            }
            // 当父节点大于左右节点较大者时,堆有序了,退出while循环
            if (P153CommenFuns.more(comparator, keys[pq[k]], keys[pq[j]])) {
                break;
            }
            exchange(k, j);
            // 没完地话就继续下一轮
            k = j;
        }
    }


    @Override
    public Iterator<Integer> iterator() {
        return new HeapIterator();
    }

    private class HeapIterator implements Iterator<Integer> {
        /**
         * 元素数组的拷贝
         */
        private P203IndexMaxPQComparator<Key> keysCopy;

        public HeapIterator() {
            this.keysCopy = new P203IndexMaxPQComparator<Key>(comparator, pq.length - 1);
            // 复制所有keys中的元素到keysCopy
            for (int i = 1; i <= n; i++) {
                // pq[i] = keys中第i大元素的下标
                // keys[pq[i]] = keys中第i大的元素
                keysCopy.insert(pq[i], keys[pq[i]]);
            }
        }

        @Override
        public boolean hasNext() {
            return !keysCopy.isEmpty();
        }

        /**
         * 遍历的时候依次弹出当前最大值在keys中的下标
         */
        @Override
        public Integer next() {
            if (!hasNext()) {
                throw new NoSuchElementException("优先队列没有元素啦!");
            }
            return keysCopy.delMax();
        }
    }
}
