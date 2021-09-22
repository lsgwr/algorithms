/***********************************************************
 * @Description : Trie的增强实现，支持节点带权值，每次加入节点都要更新权重
 * 每个单词都有自己的权重，多个前缀相同单词的相同前缀要把多个单词的权重加起来
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2020/1/2 11:16
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter10Trie.Section6Leetcode677MapSum;

import java.util.TreeMap;

public class TrieWeighted {
    private class Node {
        /**
         * 当前节点所属的链路能不能形成一个单词
         */
        public boolean isWord;
        /**
         * 当前节点的权重，isWord为true，weight大于0；isWord为false，weight等于0
         */
        public int weight;
        /**
         * 指向下一个节点的map指针，因为一个节点可能有多个子节点
         * (英文单词每个节点可能对应下面的26个)，所以是一对多的关系，故需要map来存储
         */
        public TreeMap<Character, Node> next;

        public Node(boolean isWord, int weight) {
            this.isWord = isWord;
            this.weight = weight;
            next = new TreeMap<>();
        }

        public Node() {
            // 默认节点不是单词、权重为0
            this(false, 0);
        }
    }

    /**
     * 整个Trie树的根节点
     */
    private Node root;
    /**
     * Trie树种有多少个单词
     */
    private int size;

    public TrieWeighted() {
        root = new Node();
        size = 0;
    }

    /**
     * Trie树中有多少个单词
     */
    public int getSize() {
        return size;
    }

    /**
     * 向Trie中添加一个新的单词word，并更新trie中各个节点的权重
     *
     * @param word   要添加的单词
     * @param weight 单词word对应的权重
     */
    public void add(String word, int weight) {
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
        // 先判断这个单词是不是以前就存在
        if (!cur.isWord) {
            // 插入单词后，把这个单词插入后的末尾节点标记为是单词
            cur.isWord = true;
            size++;
        }
        // 不论单词是否访问过，都要设置下weight，因为可能会更新weight值
        cur.weight = weight;
    }

    /**
     * 查询单词是否在Trie树中
     *
     * @param word 要查询的单词
     * @return 是否包含指定单词
     */
    public boolean contains(String word) {
        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            // 只要在第二层找不到word第一个字符(第二层是所有单词的起点)
            if (cur.next.get(c) == null) {
                return false;
            }
            cur = cur.next.get(c);
        }
        // 到达字符串的最后一个字符,及时有这个单词，但是isWord不为True也表明没有被标记过。不算包含这个单词
        return cur.isWord;
    }

    /**
     * 判断某个字符串是否是单词的前缀(即某个字符串以这个字符串开始)
     *
     * @param prefix 字符串
     * @return 是否是前缀，只要能找到prefix的链路即可，不需要判断链路结尾是否是单词
     */
    public boolean startsWith(String prefix) {
        Node cur = root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            // 只要在第二层找不到word第一个字符(第二层是所有单词的起点)
            if (cur.next.get(c) == null) {
                return false;
            }
            cur = cur.next.get(c);
        }
        // 只要找到这个链路，就可认为这个前缀是存在地
        return true;
    }

    /**
     * 计算指定前缀的权重值，其为所有包含这个前缀的单词的权重之和
     *
     * @param prefix 要获取权重的前缀
     * @return 所有包含这个前缀的单词的权重之和
     */
    public int getWeight(String prefix) {
        Node cur = root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if (cur.next.get(c) == null) {
                // 前缀中的某个字符在Trie中不存在，即这个前缀都在Trie中不存在，所以可以直接返回0
                return 0;
            }
            cur = cur.next.get(c);
        }
        // 上面的for循环完毕，已经到达了前缀prefix的最后一个字符在Trie中的位置
        // 这个位置往下所有的子树是单词的权重都要加起来，所以需要遍历cur往下所有的子树，最好递归实现
        return sumWeight(cur);
    }

    /**
     * 遍历node往下所有的子树,是单词的权重都要加起来
     *
     * @param node 要遍历的子树的根节点
     * @return 所有node的子树中是单词的节点的权重和
     */
    private int sumWeight(Node node) {
        // 1.递归终止条件
        if (node.next.size() == 0) {
            // 递归到叶子节点了，叶子节点肯定是某个单词的最后一个字符！！
            return node.weight;
        }

        // 2.递归具体逻辑
        int result = node.weight;
        for (Character c : node.next.keySet()) {
            // 递归找到所有子树并把子树中是单词的权重加到result上
            result += sumWeight(node.next.get(c));
        }
        return result;
    }

    public static void main(String[] args) {
        TrieWeighted trieWeighted = new TrieWeighted();
        trieWeighted.add("apple", 3);
        System.out.println(trieWeighted.getWeight("ap"));
        trieWeighted.add("app", 2);
        System.out.println(trieWeighted.getWeight("ap"));
    }
}
/**
 * 3
 * 5
 */