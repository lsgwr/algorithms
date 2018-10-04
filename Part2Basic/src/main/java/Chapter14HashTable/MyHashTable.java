/***********************************************************
 * @Description : 自己的哈希表实现
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/10/4 10:58
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter14HashTable;

import java.util.TreeMap;

public class MyHashTable<K extends Comparable<K>, V> {

    private final int[] capacity
            = {53, 97, 193, 389, 769, 1543, 3079, 6151, 12289, 24593,
            49157, 98317, 196613, 393241, 786433, 1572869, 3145739, 6291469,
            12582917, 25165843, 50331653, 100663319, 201326611, 402653189, 805306457, 1610612741};

    private static final int UPPER_TOL = 10;
    private static final int LOWER_TOL = 2;
    private int capacityIndex = 0;

    private TreeMap[] hashtable;
    private int size;
    private int M;

    public MyHashTable() {
        this.M = capacity[capacityIndex];
        size = 0;
        hashtable = new TreeMap[M];
        for (int i = 0; i < M; i++) {
            hashtable[i] = new TreeMap<>();
        }
    }

    private int hash(K key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    public int getSize() {
        return size;
    }

    public void add(K key, V value) {
        TreeMap map = hashtable[hash(key)];
        if (map.containsKey(key)) {
            map.put(key, value);
        } else {
            map.put(key, value);
            size++;

            if (size >= UPPER_TOL * M && capacityIndex + 1 < capacity.length) {
                capacityIndex++;
                resize(capacity[capacityIndex]);
            }
        }
    }

    public V remove(K key) {
        V ret = null;
        TreeMap map = hashtable[hash(key)];
        if (map.containsKey(key)) {
            ret = (V) map.remove(key);
            size--;

            if (size < LOWER_TOL * M && capacityIndex - 1 >= 0) {
                capacityIndex--;
                resize(capacity[capacityIndex]);
            }
        }
        return ret;
    }

    public void set(K key, V value) {
        TreeMap map = hashtable[hash(key)];
        if (!map.containsKey(key)) {
            throw new IllegalArgumentException(key + " doesn't exist!");
        }

        map.put(key, value);
    }

    public boolean contains(K key) {
        return hashtable[hash(key)].containsKey(key);
    }

    public V get(K key) {
        return (V) hashtable[hash(key)].get(key);
    }

    private void resize(int newM) {
        TreeMap[] newHashTable = new TreeMap[newM];
        for (int i = 0; i < newM; i++) {
            newHashTable[i] = new TreeMap<>();
        }

        int oldM = M;
        this.M = newM;
        for (int i = 0; i < oldM; i++) {
            TreeMap map = hashtable[i];
            for (Object key : map.keySet()) {
                newHashTable[hash((K) key)].put(key, map.get(key));
            }
        }

        this.hashtable = newHashTable;
    }
}
