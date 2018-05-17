/***********************************************************
 * @Description : 基于链表实现地Map
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/5/17 22:24
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter07SetAndMap.Section6LinkedListMap;

import Chapter07SetAndMap.Section5MapBasic.Map;

public class LinkedListMap<Key, Value> implements Map<Key, Value> {

    /**
     * 链表伪头节点,方便些代码而且好理解
     */
    private Node dummyHead;
    /**
     * 链表的头节点
     */
    private int size;

    /**
     * 每个节点的封装
     */
    private class Node {
        Key key;
        /**
         * 节点元素值
         */
        Value value;
        /**
         * 指向的下一个节点
         */
        Node next;

        public Node(Key key, Value value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public Node(Key key,Value value) {
            this(key, value, null);
        }

        public Node() {
            this(null, null, null);
        }

        @Override
        public String toString() {
            return key.toString() + " : " + value.toString();
        }
    }

    public LinkedListMap() {
        dummyHead = new Node();
        size = 0;
    }

    /**
     * 辅助函数，获取指定键的节点
     *
     * @param key Node中的key
     * @return 返回找到的Node节点
     */
    private Node getNode(Key key) {
        Node cur = dummyHead.next;
        while (cur != null) {
            if (cur.key.equals(key)) {
                return cur;
            }
            cur = cur.next;
        }
        return null;
    }

    @Override
    public void add(Key key, Value value) {
        // 先查询是否能找到这个节点
        Node node = getNode(key);
        if (node == null) {
            // 如果之前没有这个key对应的Node,就加入新节点.好好体会下，下面1步相当于3步，参考第4章的LinkedList实现
            dummyHead.next = new Node(key, value, dummyHead.next);
            size++;
        } else {
            // 如果之前存在的话就更新
            node.value = value;
        }
    }

    @Override
    public Value delete(Key key) {
        Node prev = dummyHead;
        while (prev.next != null) {
            if (prev.next.key.equals(key)) {
                break;
            }
            prev = prev.next;
        }

        if (prev.next != null) {
            Node delNode = prev.next;
            prev.next = delNode.next;
            delNode.next = null;
            size--;
            return delNode.value;
        }
        return null;
    }

    @Override
    public boolean contains(Key key) {
        return getNode(key) != null;
    }

    @Override
    public Value get(Key key) {
        Node node = getNode(key);
        return node == null ? null : node.value;
    }

    @Override
    public void update(Key key, Value value) {
        // 先查询是否能找到这个节点
        Node node = getNode(key);
        if (node == null) {
            throw new IllegalArgumentException(key + " does't exist");
        }
        // 找到这个节点了就更新
        node.value = value;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}
