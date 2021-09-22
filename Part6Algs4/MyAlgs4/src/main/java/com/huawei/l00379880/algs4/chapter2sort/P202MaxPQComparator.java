package com.huawei.l00379880.algs4.chapter2sort;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

/*********************************************************************
 * @Description : P198~P203 基于堆的最大优先队列,!!!仅适用于自定义Comparator!!!
 *                (通过上浮和下沉形成完全二叉堆)
 *                Key 必须是自定义比较规则地.
 *                局限:没有实现多个Comparator,Key只能是实现Comparable
 *                     比较规则没能实现灵活切换,不过对于算法来说,足够了.
 *                     如果想实现自己传入多个Comparator,可以把less函
 *                     数改造地兼容Comparator和Comparable,Comparator
 *                     可以通过构造函数传入.具体见官方实现.我的尽量简单
 *
 * @author      : 梁山广
 * @date        : 2018/1/3 16:39
 * @email       : liangshanguang2@gmail.com
 ******************************************************************/
public class P202MaxPQComparator<Key> implements Iterable<Key> {
    /**
     * 优先队列数组,Priority Queue.利用这个数组构造完全二叉堆
     */
    private Key[] pq;
    /**
     * 优先队列的实时元素数目,
     */
    private int n;

    /**
     * 自定义比较器
     */
    private Comparator<Key> comparator;

    /**
     * 初始化指定大小的优先队列数组
     *
     * @param initCapacity 优先队列的初始大小
     */
    public P202MaxPQComparator(Comparator<Key> comparator, int initCapacity) {
        // 不能直接创建泛型数组.
        this.pq = (Key[]) new Object[initCapacity + 1];
        this.n = 0;
        this.comparator = comparator;
    }

    public P202MaxPQComparator(Comparator<Key> comparator) {
        // 啥都不传就初始化为1的优先队列数组
        this(comparator, 1);
    }


    public P202MaxPQComparator(Comparator comparator, Key[] keys) {
        this.comparator = comparator;
        n = keys.length;
        // 之所以+1是因为第一个元素不用,即pq[0]使用空着就行
        pq = (Key[]) new Object[keys.length + 1];
        for (int i = 0; i < n; i++) {
            pq[i + 1] = keys[i];
        }
        // 中间元素相当于堆定元素.从堆顶开始向下,通过不断下沉,改造成完全二叉堆
        for (int k = n / 2; k >= 1; k--) {
            sink(k);
        }
    }

    /**
     * 判断队列是否为空
     */
    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    /**
     * 获取优先队列中的最大元素
     */
    public Key max() {
        if (isEmpty()) {
            throw new NoSuchElementException("优先队列已经为空!更别说取最大值了!");
        }
        return pq[1];
    }

    /**
     * 插入元素:插入到最底端,然后通过上浮重新平衡二叉堆
     *
     * @param key 传入的元素
     */
    public void insert(Key key) {
        // 先检查队列剩余空间还够不,不够就翻倍扩容
        if (n == pq.length - 1) {
            resize(2 * pq.length);
        }
        // 把元素插入到二叉树的最底部,然后通过上浮重新平衡二叉树
        pq[++n] = key;
        swim(n);
        // 确保当前已经是最大堆了
        assert isMaxHeap();
    }

    public Key delMax() {
        if (isEmpty()) {
            throw new NoSuchElementException("优先队列已经为空!更别说取最大值了!");
        }
        // 排好地二叉堆,最大元素一定是最上面的元素
        Key max = pq[1];
        // 把最上面的顶点和最下面的节点交换
        P153CommenFuns.exchange(pq, 1, n--);
        // 把交换上来的元素沉到合适位置
        sink(1);
        // 把交换下来的最大元素所在的位置的元素释放掉,帮助JVM回收.
        // 这里的n+1和上面的n--实际是等大的值,之所以这么是为了及时更新pq中的有效元素数n
        pq[n + 1] = null;
        // 删除元素后看看pq的利用率太小,就自动缩容
        if ((n > 0) && (n == (pq.length - 1) / 4)) {
            resize(pq.length / 2);
        }
        assert isMaxHeap();
        return max;
    }

    /**
     * 调整数组大小.即把当前数据复制到一个更大的数组中
     */
    private void resize(int capacity) {
        // 新传进来的容量一定要比数组的当前容量大
        assert capacity > n;
        Key[] temp = (Key[]) new Object[capacity];
        for (int i = 1; i <= n; i++) {
            // 注意第一个元素不用
            temp[i] = pq[i];
        }
        pq = temp;
    }

    /**
     * 完全二叉树定义:若设二叉树的深度为h，除第 h 层外，其它各层 (1～h-1) 的结点数都
     * 达到最大个数，第 h 层所有的结点都连续集中在最左边，这就是完全二叉树。
     * 堆有序:当一颗完全二叉树的每个节点都大于(小于)等于他的两个子节点时,被称为堆有序
     * 二叉堆:一组能够用堆有序的完全二叉树排序的元素
     * 最大堆:每个节点都大于等于他的两个子节点
     */
    private void sink(int k) {
        while (2 * k <= n) {
            int j = 2 * k;
            // 如果2k处的元素相遇2k+1处的元素,那么j++=2k+1,
            // 下面的k处的元素2k、2k+1中的元素较大者进行交换.
            // 之所以可能pq[2k]<pq[zk+1],是因为最大堆的
            // 要求只是顶点大于左右节点即可.左右节点的大小关系
            // 随便.自己一直与之混淆的是二叉查找树:其顶点大于
            // 左节点而小于右节点.
            if (j < n && P153CommenFuns.less(comparator, pq[j], pq[j + 1])) {
                // 下面的k处的元素2k、2k+1中的元素较大者进行交换
                j++;
            }
            // 如果pq[k]满足大于左右两个节点,那么下沉完成,退出while循环
            if (P153CommenFuns.more(comparator, pq[k], pq[j])) {
                break;
            }
            P153CommenFuns.exchange(pq, k, j);
            k = j;
        }
    }

    /**
     * 元素上浮
     */
    private void swim(int k) {
        while (k > 1 && P153CommenFuns.more(comparator, pq[k], pq[k / 2])) {
            // 如果当前位置的元素大于其父节点元素,那么就把该元素上浮
            P153CommenFuns.exchange(pq, k, k / 2);
            k = k / 2;
        }
    }

    /**
     * is pq[1..N] a max heap?
     */
    private boolean isMaxHeap() {
        return isMaxHeap(1);
    }

    /**
     * 判断k节点往下的树是否已经是最大堆
     * is subtree of pq[1..n] rooted at k a max heap?
     */
    private boolean isMaxHeap(int k) {
        // 输入不合法,返回是最大堆
        if (k > n) {
            return true;
        }
        int left = 2 * k;
        int right = 2 * k + 1;
        // 任何一个地方的顶点如果小于左节点或者右节点,那么一定不是最大堆
        if (left <= n && P153CommenFuns.less(comparator, pq[k], pq[left])) {
            return false;
        }
        if (right <= n && P153CommenFuns.less(comparator, pq[k], pq[right])) {
            return false;
        }
        // 当前子树符合二叉堆特点,那么就接着往下走
        return isMaxHeap(left) && isMaxHeap(right);
    }

    @Override
    public Iterator<Key> iterator() {
        return new HeapIterator();
    }

    private class HeapIterator implements Iterator<Key> {

        /**
         * create a new pq,为了在遍历的时候不影响原最大堆
         */
        private P202MaxPQComparator<Key> pqCopy;

        /**
         * 把所有元素复制到pqCopy数组中
         */
        public HeapIterator() {
            pqCopy = new P202MaxPQComparator<>(comparator, size());
            for (int i = 1; i <= n; i++) {
                pqCopy.insert(pq[i]);
            }
        }

        @Override
        public boolean hasNext() {
            return !pqCopy.isEmpty();
        }

        @Override
        public Key next() {
            if (!hasNext()) {
                throw new NoSuchElementException("最大堆已经空了!不能继续遍历了!");
            }
            return pqCopy.delMax();
        }

        @Override
        public void remove() {
            throw new NoSuchElementException("不支持删除元素操作!");
        }
    }
}
