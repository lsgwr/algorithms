/***********************************************************
 * @Description : LeetCode题号为677，当Trie中单词加入权重后，计算指定单词的权重值
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/5/19 22:16
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter10Trie.Section6Leetcode677MapSum;

import java.util.TreeMap;

public class MapSum {
    private class Node {
        /**
         * 每个单词对应的权重。为0表示不为单词，非0表示为单词
         */
        public int value;
        /**
         * 指向下一个节点的map指针，因为一个节点可能有多个子节点
         * (英文单词每个节点可能对应下面的26个)，所以是一对多的关系，故需要map来存储
         */
        public TreeMap<Character, Node> next;

        public Node(int value) {
            this.value = value;
            next = new TreeMap<>();
        }

        public Node() {
            // 默认各个节点的权重非0，表示不是单词节点
            this(0);
        }
    }

    private Node root;

    public MapSum() {
        root = new Node();
    }

    public void insert(String word, int val) {
        // 开始从根节点开始
        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            //  当在当前节点指向的孩子节点中不存在要插入的字符c的时候。为null表示不存在，才执行插入动作
            if (cur.next.get(c) == null) {
                cur.next.put(c, new Node());
            }
            // 当在当前节点指向的孩子节点中存在要插入的字符c的时候,直接跳过
            cur = cur.next.get(c);
        }
        // 单词结尾节点的权重设置为val
        cur.value = val;
    }

    public int sum(String prefix) {
        Node cur = root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            // 只要在第二层找不到word第一个字符(第二层是所有单词的起点)，prefix肯定在Trie中找不到了
            if (cur.next.get(c) == null) {
                return 0;
            }
            cur = cur.next.get(c);
        }
        return sum(cur);
    }

    /**
     * 遍历Node及所有的子树，把权重值加起来。即把所有同前缀的单词的权重加起来
     *
     * @param node 开始的根节点
     * @return
     */
    private int sum(Node node) {
        // 前缀对应的Value先记下
        int result = node.value;
        for (Character c : node.next.keySet()) {
            // 递归找到所有的子树的value
            result += sum(node.next.get(c));
        }
        return result;
    }
}
