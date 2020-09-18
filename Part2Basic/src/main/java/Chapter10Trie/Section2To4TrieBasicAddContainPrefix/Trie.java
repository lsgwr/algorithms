/***********************************************************
 * @Description : Trie的基本实现
 * 只针对英文单词，所以不用泛型类，只用字符串即可,Trie中的每个节点都是一个字符Character
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/5/19 18:12
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter10Trie.Section2To4TrieBasicAddContainPrefix;

import java.util.TreeMap;

public class Trie {
    class Node {
        /**
         * 当前节点所属的到根节点的链路能不能形成一个单词
         */
        boolean isWord;
        /**
         * 指向下一个节点的map指针，因为一个节点可能有多个子节点
         * (英文单词每个节点可能对应下面的26个)，所以是一对多的关系，故需要map来存储
         * 此外存Map可以实现快速根据键值选中符合条件的子节点，因此此处必须用Map
         */
        TreeMap<Character, Node> next;

        Node() {
            next = new TreeMap<>();
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

    public Trie() {
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
     * 向Trie中添加一个新的单词word
     *
     * @param word 要添加的单词
     */
    public void insert(String word) {
        // 开始从根节点开始
        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            //  当在当前节点指向的孩子节点中不存在要插入的字符c的时候。为null表示不存在，把字符串作为新的Trie节点插入
            if (cur.next.get(c) == null) {
                cur.next.put(c, new Node());
            }
            // cur节点往后移动一位，这里用map的作用就体现出来了，可以快速找到当前字符c处在哪个子节点上
            cur = cur.next.get(c);
        }
        // 先判断这个单词是不是以前就存在
        if (!cur.isWord) {
            // 插入单词后，把这个单词插入后的末尾节点标记为是单词
            cur.isWord = true;
            size++;
        }
    }

    /**
     * 查询单词是否在Trie树中
     *
     * @param word 要查询的单词
     * @return 是否包含指定单词
     */
    public boolean search(String word) {
        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            // 当前节点的子节点是否包含字符c，不包含则肯定不包含单词word了，直接返回即可
            if (cur.next.get(c) == null) return false;
            cur = cur.next.get(c); // 循环到下一个点
        }
        // 到达字符串的最后一个字符,即使有这个单词，但是isWord不为True也表明没有被标记过。不算包含这个单词
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
            // 当前节点的子节点是否包含字符c，不包含则肯定不包含前缀prefix了，直接返回即可
            if (cur.next.get(c) == null) return false;
            cur = cur.next.get(c); // 循环到下一个点
        }
        // 只要找到这个前缀，就可认为包含这个前缀的单词是存在地，不需要判断前缀到达的位置是否是单词
        return true;
    }
}
