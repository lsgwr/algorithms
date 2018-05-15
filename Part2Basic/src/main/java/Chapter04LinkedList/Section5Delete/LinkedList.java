/***********************************************************
 * @Description : 实现链表的查询和更新
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/5/14 07:56
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter04LinkedList.Section5Delete;

public class LinkedList<Element> {
    /**
     * 链表伪头节点
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
        /**
         * 节点元素值
         */
        Element element;
        /**
         * 指向的下一个节点
         */
        Node next;

        public Node(Element element, Node next) {
            this.element = element;
            this.next = next;
        }

        public Node(Element element) {
            this(element, null);
        }

        public Node() {
            this(null, null);
        }

        @Override
        public String toString() {
            return element.toString();
        }
    }

    public LinkedList() {
        dummyHead = new Node(null, null);
        size = 0;
    }

    /**
     * 获取链表节点数目
     */
    public int getSize() {
        return size;
    }

    /**
     * 判断链表是否已经为空
     */
    public boolean isEmpty() {
        return size == 0;
    }


    /**
     * 在链表的index(从0开始)位置添加新的元素element
     * 在链表中这种操作很少用，仅仅练习下就行
     */
    public void insert(int index, Element element) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("插入失败！index范围违法！");
        }
        Node prev = dummyHead;
        for (int i = 0; i < index; i++) {
            // 链表不停向后移动，直到移动到要插入位置的前一个位置
            prev = prev.next;
        }
        // 下面3行等效于prev.next = new Node(element, prev.next);
        Node node = new Node(element);
        node.next = prev.next;
        prev.next = node;

        size++;
    }

    /**
     * 链表头添加元素
     */
    public void addFirst(Element element) {
        insert(0, element);
    }

    /**
     * 向链表尾加入元素
     *
     * @param element 要加入地元素
     */
    public void addLast(Element element) {
        insert(size, element);
    }


    /**
     * 删除链表的第index个位置的元素(从0开始计)为element
     *
     * @param index 待删除元素的位置
     * @return
     */
    public Element delete(int index) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("插入失败！index范围违法！");
        }
        Node prev = dummyHead;
        for (int i = 0; i < index; i++) {
            // 链表不停向后移动，直到移动到要插入位置的前一个位置
            prev = prev.next;
        }
        Node retNode = prev.next;
        prev.next = retNode.next;
        retNode.next = null;
        size--;
        return retNode.element;
    }

    /**
     * 链表头删除元素
     */
    public Element deleteFirst() {
        return delete(0);
    }

    /**
     * 向链表尾删除元素
     */
    public Element deleteLast() {
        return delete(size - 1);
    }

    /**
     * 更新链表的第index个位置的元素(从0开始计)为element
     * 在链表中不是一个常用的操作，练习用
     *
     * @param index   要查询的元素的索引
     * @param element 更新后的元素
     */
    public void update(int index, Element element) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("更新失败！index范围违法！");
        }
        // 和insert操作中的for循环加以区分二者差一个元素
        Node cur = dummyHead.next;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        cur.element = element;
    }

    /**
     * 获得链表的第index个位置的元素(从0开始计)
     * 在链表中不是一个常用的操作，练习用
     *
     * @param index 要查询的元素的索引
     * @return 要查询的元素值
     */
    public Element get(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("插入失败！index范围违法！");
        }
        // 和insert操作中的for循环加以区分二者差一个元素
        Node cur = dummyHead.next;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        return cur.element;
    }

    /**
     * 获取链表的第一个元素
     */
    public Element getFirst() {
        return get(0);
    }

    /**
     * 获取链表的最后一个元素
     */
    public Element getLast() {
        return get(size - 1);
    }


    /**
     * 查询链表中是否有元素element
     * 需要遍历，找到就返回true,否则返回false
     */
    public boolean contain(Element element) {
        Node cur = dummyHead.next;
        while (cur != null) {
            if (cur.element.equals(element)) {
                return true;
            }
            cur = cur.next;
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node cur = dummyHead.next;
        while (cur != null) {
            sb.append(cur + "->");
            cur = cur.next;
        }
        sb.append("NULL");
        return sb.toString();
    }
}
