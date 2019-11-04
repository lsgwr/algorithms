/***************************************************************
 * @Description : 有序数组中的二分查找。键值数组默认是升序。P239~P245
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/1/6 下午9:40
 * @email       : liangshanguang2@gmail.com
 **************************************************************/
package com.huawei.l00379880.algs4.chapter3search;

import com.huawei.l00379880.algs4.chapter1javabasic.P95LinkedListQueue;

import java.util.NoSuchElementException;

public class P239BinarySearchST<Key extends Comparable<Key>, Value> {
    /**
     * 如果外部不初始化数组容量，那么出默认给一个
     */
    private static final int INIT_CAPACITY = 2;

    /**
     * 键数组
     */
    private Key[] keys;
    /**
     * 值数组
     */
    private Value[] values;
    /**
     * 键值对的实时数目
     */
    private int n = 0;

    public P239BinarySearchST() {
        this(INIT_CAPACITY);
    }

    public P239BinarySearchST(int capacity) {
        // 不能直接新建泛型数组，所以只能先声明确定的然后强制转换了
        keys = (Key[]) new Comparable[capacity];
        values = (Value[]) new Object[capacity];
    }

    /**
     * 数组大小调整
     *
     * @param capacity 数组新的容量，可大可小
     */
    private void resize(int capacity) {
        assert capacity >= 0;
        Key[] tempK = (Key[]) new Comparable[capacity];
        Value[] tempV = (Value[]) new Object[capacity];
        for (int i = 0; i < n; i++) {
            tempK[i] = keys[i];
            tempV[i] = values[i];
        }
        keys = tempK;
        values = tempV;
    }

    /**
     * 当前键值对数量
     */
    public int size() {
        return n;
    }

    /**
     * 描述: 返回low到high之间的键值对数目(包含low和high,即双闭区间)
     */
    public int size(Key low, Key high) {
        if (low == null || high == null) {
            throw new IllegalArgumentException("传入的键不能为null");
        }
        // low>high,不正常，直接返回0
        if (low.compareTo(high) > 0) {
            return 0;
        }
        // 如果高的key在键值表中已有的话会少算一次。
        // rank(low)不受影响，low在不在键值表中都没事地，rank(low)会体现出来地
        // 但是rank(high) - rank(low)无论怎样，默认都是没有rank(high)地
        // 当contains(high)为false的时候，会少算一个。自己可以画图看下
        if (contains(high)) {
            return rank(high) - rank(low) + 1;
        } else {
            return rank(high) - rank(low);
        }
    }

    /**
     * 返回全部的键数目
     */
    public Iterable<Key> keys() {
        return keys(min(), max());
    }

    /**
     * 描述: 返回low到hight之前的键数组
     */
    public Iterable<Key> keys(Key low, Key high) {
        if (low == null || high == null) {
            throw new IllegalArgumentException("传入的键不能为null");
        }
        P95LinkedListQueue<Key> queue = new P95LinkedListQueue<>();
        if (low.compareTo(high) > 0) {
            return queue;
        }
        // low不会因为contains就少算地，rank(low)会体现出来地
        for (int i = rank(low); i < rank(high); i++) {
            queue.enqueue(keys[i]);
        }
        if (contains(high)) {
            queue.enqueue(keys[rank(high)]);
        }
        return queue;
    }

    /**
     * 是不是已经没元素了
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * 是否包含某个特定键
     */
    public boolean contains(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("传入的键不能为null");
        }
        return get(key) != null;
    }


    /**
     * 获取特定键对应的值
     */
    public Value get(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("传入的键不能为null");
        }
        if (isEmpty()) {
            return null;
        }
        // 正好利用rank找到合适key的下标，因为下标从0开始，所以也是小鱼给定键的数目
        int i = rank(key);
        if (i < n && keys[i].compareTo(key) == 0) {
            return values[i];
        }
        return null;
    }

    /**
     * 描述: 返回小于给定键的键的数量
     * 日期: 2018/1/6 下午10:01
     * 作者: 梁山广
     */
    public int rank(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("传入的键不能为null");
        }
        // 二分查找
        int low = 0;
        int high = n - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            // 比较结果
            int cmp = key.compareTo(keys[mid]);
            if (cmp < 0) {
                // 在前面的一半，所以high往小处取
                high = mid - 1;
            } else if (cmp > 0) {
                // 在后面的一半，所以low往高处取
                low = mid + 1;
            } else {
                // 找到了想找的键，返回其下表，即在数目中小于给定键的数量
                return mid;
            }
        }
        // 如果找不到相等地，返回最接近的low
        return low;
    }

    /**
     * 描述: 更新指定的键值对
     * 日期: 2018/1/6 下午10:22
     * 作者: 梁山广
     */
    public void put(Key key, Value value) {
        if (key == null) {
            throw new IllegalArgumentException("传入的键不能为null");
        }
        if (value == null) {
            throw new IllegalArgumentException("传入的值不能为null");
        }

        // key如果有的话，那么就是现在的位置；如果没有，那么就是待插入的位置。注意数组下标从0开始
        int i = rank(key);

        // i对应的键等于要插入的键的话，说明键值表中已经有这个键了，更新就好了
        if (i < n && keys[i].compareTo(key) == 0) {
            values[i] = value;
            // 更新完毕，记得返回
            return;
        }
        // 数组长了记得要扩容。小的数组还好，太大的数组扩容很影响性能。所以有序数组组成的键值表不怎么样
        if (n == keys.length) {
            resize(2 * keys.length);
        }
        // 如果上面没找到的话，(i对应的键keys[i]小于要插入的键)，那么说明原来不存在待插入的键，那么现在就新插入一个，而且要调整以让键值表保持有序
        // 因为要在紧挨着i的后面插入，所以要把i后面的键值对统一往后移动一位(键值有序默认是升序)
        for (int j = n; j > i; j--) {
            keys[j] = keys[j - 1];
            values[j] = values[j - 1];
        }
        keys[i] = key;
        values[i] = value;
        n++;
        // 检查键值表是否有序了
        assert check();
    }

    /**
     * 描述: 删除指定key对应的键值对
     * 日期: 2018/1/7 下午2:42
     * 作者: 梁山广
     */
    public void delete(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("传入的key不能为null");
        }
        if (isEmpty()) {
            return;
        }

        int i = rank(key);

        // 如果key不在当前的查找表中,那么直接返回
        if (i == n || keys[i].compareTo(key) != 0) {
            return;
        }

        // 从删除位置开始，后面的所有元素都往前移动一位
        for (int j = i; j < n - 1; j++) {
            keys[j] = keys[j + 1];
            values[j] = values[j + 1];
        }
        n--;
        // 把最后一个元素置为null,方便JVM进行回收
        keys[n] = null;
        values[n] = null;

        // 删除元素后如果数组太大，记得缩容.保证数组有一半以上的利用率
        if (n > 0 && n == keys.length / 4) {
            resize(keys.length / 2);
        }

        //  进行有序性检查
        assert check();
    }

    /**
     * 描述: 获取最小的键值（默认都是升序）
     * 日期: 2018/1/7 下午2:52
     * 作者: 梁山广
     */
    public Key min() {
        if (isEmpty()) {
            throw new NoSuchElementException("键值表已经为空！");
        }
        return keys[0];
    }

    /**
     * 描述: 获取最小的键值（键值数组是升序）
     * 日期: 2018/1/7 下午2:57
     * 作者: 梁山广
     */
    public Key max() {
        if (isEmpty()) {
            throw new NoSuchElementException("键值表已经为空！");
        }
        return keys[n - 1];
    }

    /**
     * 描述: 删除最小键对应的键值对
     * 日期: 2018/1/7 下午2:58
     * 作者: 梁山广
     */
    public void deleteMin() {
        if (isEmpty()) {
            throw new NoSuchElementException("键值表已经为空！");
        }
        delete(min());
    }

    /**
     * 描述: 删除最大键对应的键值对
     * 日期: 2018/1/7 下午2:58
     * 作者: 梁山广
     */
    public void deleteMax() {
        if (isEmpty()) {
            throw new NoSuchElementException("键值表已经为空！");
        }
        delete(max());
    }

    /**
     * 描述: 返回符号表中小于等于key的最大key
     * Returns the largest key in this symbol table less than or equal to  key.
     */
    public Key floor(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("传入的键不能为null");
        }
        int i = rank(key);
        if (i < n && key.compareTo(keys[i]) == 0) {
            return keys[i];
        }
        if (i == 0) {
            return null;
        } else {
            return keys[n - 1];
        }
    }

    /**
     * 描述: 返回符号表中大于key的最小键key
     */
    public Key ceiling(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("传入的键不能为null");
        }
        int i = rank(key);
        // 返回n一定是可以地，不需要像floor还分n和n-1两种情况
        if (i == n) {
            return null;
        } else {
            return keys[i];
        }
    }

    /**
     * 描述: 选择第k小的key
     * 日期: 2018/1/7 下午2:27
     * 作者: 梁山广
     */
    private Key select(int k) {
        if (k < 0 || k >= size()) {
            throw new IllegalArgumentException("传入的k超出合适范围了！");
        }
        return keys[k];
    }

    /**
     * 描述: 校验键值表的函数
     */
    private boolean check() {
        return isSorted() && rankCheck();
    }

    /**
     * 描述: 检测键值表是否有序
     * 日期: 2018/1/7 下午2:19
     * 作者: 梁山广
     */
    private boolean isSorted() {
        for (int i = 1; i < size(); i++) {
            // 因为是升序，只要有一个地方后面的元素小于前面的元素，那么就可以推翻有序的既定结论
            if (keys[i].compareTo(keys[i - 1]) < 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 描述: 检查rank函数返回的排名对不对(即小于当前key的有多少个元素)，即 rank(select(i)) = i
     * 日期: 2018/1/7 下午2:19
     * 作者: 梁山广
     */
    private boolean rankCheck() {
        for (int i = 0; i < size(); i++) {
            // 看看利用select校验rank函数返回的排名对不对
            if (i != rank(select(i))) {
                return false;
            }
        }
        for (int i = 0; i < size(); i++) {
            // 校验制定排名位置的key值经过rank函数和select函数后是不是正确
            if (keys[i].compareTo(select(rank(keys[i]))) != 0) {
                return false;
            }
        }
        return true;
    }


}
