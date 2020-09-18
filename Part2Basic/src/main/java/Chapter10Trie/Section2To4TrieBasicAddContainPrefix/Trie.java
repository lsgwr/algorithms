/***********************************************************
 * @Description : Trie的基本实现
 * 只针对英文单词，所以不用泛型类，只用字符串即可,Trie中的每个节点都是一个字符Character
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/5/19 18:12
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter10Trie.Section2To4TrieBasicAddContainPrefix;

import java.util.*;

public class Trie {
    class Node {
        boolean isWord; // 当前节点所属的到根节点的链路能不能形成一个单词
        TreeMap<Character, Node> next; // 当前节点的所有子节点，是一对多的关系，故需要map来存储;此外存Map可以实现快速根据键值选中符合条件的子节点，因此此处必须用Map

        Node() {
            next = new TreeMap<>();
        }
    }

    private Node root; // 整个Trie树的根节点
    private int size; // Trie树种有多少个单词

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
     */
    public void add(String word) {
        Node cur = root; // 开始从根节点开始
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (cur.next.get(c) == null) cur.next.put(c, new Node()); //  当在当前节点指向的孩子节点中不存在要插入的字符c的时候。为null表示不存在，把字符串作为新的Trie节点插入
            cur = cur.next.get(c); // cur节点往后移动一位，这里用map的作用就体现出来了，可以快速找到当前字符c处在哪个子节点上
        }
        if (cur.isWord) return; // 先判断这个单词是不是以前就存在
        cur.isWord = true; // 插入单词后，把这个单词插入后的末尾节点标记为是单词
        size++; // 单词数+1
    }

    /**
     * 查询单词word是否在Trie树中
     */
    public boolean contains(String word) {
        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (cur.next.get(c) == null) return false; // 当前节点的子节点是否包含字符c，不包含则肯定不包含单词word了，直接返回即可
            cur = cur.next.get(c); // 循环到下一个点
        }
        return cur.isWord; // 到达字符串的最后一个字符,即使有这个单词，但是isWord不为True也表明没有被标记过。不算包含这个单词
    }

    /**
     * 判断某个字符串prefix是否是Trie树种某个单词的前缀(即某个单词以这个字符串开始)
     */
    public boolean startsWith(String prefix) {
        Node cur = root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if (cur.next.get(c) == null) return false; // 当前节点的子节点是否包含字符c，不包含则肯定不包含前缀prefix了，直接返回即可
            cur = cur.next.get(c); // 循环到下一个点
        }
        return true; // 只要找到这个前缀，就可认为包含这个前缀的单词是存在地，不需要判断前缀到达的位置是否是单词
    }
}
