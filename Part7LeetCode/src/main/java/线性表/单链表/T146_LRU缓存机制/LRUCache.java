/***********************************************************
 * @Description : 146.LRU缓存机制
 * https://leetcode-cn.com/problems/lru-cache/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/29 22:02
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package 线性表.单链表.T146_LRU缓存机制;

import java.util.HashMap;

/**
 * LRU Cache
 * 时间复杂度O(logn)， 空间复杂度O(n)
 * <p>
 * 为了使查找、 插入和删除都有较高的性能， 这题的关键是要使用一个双向链表和一个HashMap， 因为：
 * 1.HashMap保存每个节点的地址， 可以基本保证在 O(1) 时间内查找节点
 * 2.双向链表能后在 O(1) 时间内添加和删除节点， 单链表则不行
 * <p>
 * 具体实现细节：
 * <p>
 * 1.越靠近链表头部， 表示节点上次访问距离现在时间最短， 尾部的节点表示最近访问最少
 * 2.访问节点时， 如果节点存在， 把该节点交换到链表头部， 同时更新hash表中该节点的地址
 * 3.插入节点时， 如果cache的size达到了上限capacity， 则删除尾部节点， 同时要在hash表中删除对应的
 * 项； 新节点插入链表头部
 */
public class LRUCache {
    /**
     * doubly linked list
     */
    static class Node {
        int key;
        int value;
        Node prev;
        Node next;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private int capacity;
    private final HashMap<Integer, Node> map;
    private Node head;
    private Node end;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
    }

    public int get(int key) {
        if (map.containsKey(key)) {
            Node n = map.get(key);
            remove(n);
            setHead(n);
            return n.value;
        }
        return -1;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            Node old = map.get(key);
            old.value = value;
            remove(old);
            setHead(old);
        } else {
            Node created = new Node(key, value);
            if (map.size() >= capacity) {
                map.remove(end.key);
                remove(end);
                setHead(created);
            } else {
                setHead(created);
            }
            map.put(key, created);
        }
    }

    private void remove(Node n) {
        if (n.prev != null) {
            n.prev.next = n.next;
        } else {
            head = n.next;
        }
        if (n.next != null) {
            n.next.prev = n.prev;
        } else {
            end = n.prev;
        }
    }

    private void setHead(Node n) {
        n.next = head;
        n.prev = null;
        if (head != null) {
            head.prev = n;
        }
        head = n;
        if (end == null) {
            end = head;
        }
    }
}
