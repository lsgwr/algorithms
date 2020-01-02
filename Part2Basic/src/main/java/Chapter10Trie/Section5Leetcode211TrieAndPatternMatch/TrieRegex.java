/***********************************************************
 * @Description : Trie的增强实现，contains(word)方法支持单词和正则表达式
 * 字符串只包含字母 . 或 a-z 。 . 可以表示任何一个字母。
 * LeetCode上的题目可以见211号问题 添加与搜索单词 - 数据结构设计
 * https://leetcode-cn.com/problems/add-and-search-word-data-structure-design/
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2020/1/2 10:09
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter10Trie.Section5Leetcode211TrieAndPatternMatch;

import java.util.TreeMap;

public class TrieRegex {
    private class Node {
        /**
         * 当前节点所属的链路能不能形成一个单词
         */
        public boolean isWord;
        /**
         * 指向下一个节点的map指针，因为一个节点可能有多个子节点
         * (英文单词每个节点可能对应下面的26个)，所以是一对多的关系，故需要map来存储
         */
        public TreeMap<Character, Node> next;

        public Node(boolean isWord) {
            this.isWord = isWord;
            next = new TreeMap<>();
        }

        public Node() {
            this(false);
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

    public TrieRegex() {
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
    public void add(String word) {
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
    }

    /**
     * 查询单词是否在Trie树中
     *
     * @param word 要查询的单词，支持正则表达式
     * @return 是否包含指定单词
     */
    public boolean contains(String word) {
        return match(root, word, 0);
    }


    /**
     * 从node开始作为根节点检索word在Trie中是否存在
     *
     * @param node  本次递归开始检索的节点
     * @param word  单词或者正则
     * @param index 本次递归检索到了word的第index个字符
     * @return 是否在Trie中匹配到word对应的模式
     */
    private boolean match(Node node, String word, int index) {
        // 1.递归终止条件
        if (index == word.length()) {
            // word已经检索到最后一个字符，直接返回其在Trie中的状态即可
            return node.isWord;
        }

        // 2.递归逻辑
        char c = word.charAt(index);
        if (c != '.') {
            // 2.1不是正则匹配，用普通的单词匹配即可
            if (node.next.get(c) == null) {
                // 在当前递归的Trie树中找不到c字符(同时也没有下一个node了，我们的map实际是起到记录当前节点值和下一个节点的指针地作用)，则匹配失败(前面层的递归都匹配上了)
                return false;
            } else {
                return match(node.next.get(c), word, index + 1);
            }
        }else {
            // 2.2 c==. 需要遍历node的所有相邻节点，继续向下递归
            for (Character cNext : node.next.keySet()) {
                if (match(node.next.get(cNext), word, index+1)){
                    // 任何一个邻接点向下递归找到了匹配就可以返回true
                    return true;
                }
            }
            // 所有的邻接点往下递归都没找到匹配，才返回false
            return false;
        }
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

    public static void main(String[] args) {
        TrieRegex trie = new TrieRegex();
        trie.add("bad");
        trie.add("dad");
        trie.add("mad");
        trie.add("world");
        // 测试单词查找
        System.out.println(trie.contains("pad")); // false
        System.out.println(trie.contains("bad")); // true
        System.out.println(trie.contains(".ad")); // true
        System.out.println(trie.contains("b..")); // true
    }
}
