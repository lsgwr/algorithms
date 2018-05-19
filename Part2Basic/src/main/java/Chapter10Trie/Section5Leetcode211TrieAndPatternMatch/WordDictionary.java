/***********************************************************
 * @Description : 字典查询，LeetCode题目211
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/5/19 19:32
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter10Trie.Section5Leetcode211TrieAndPatternMatch;

import java.util.TreeMap;

public class WordDictionary {
    private class Node {

        public boolean isWord;
        public TreeMap<Character, Node> next;

        public Node(boolean isWord) {
            this.isWord = isWord;
            next = new TreeMap<>();
        }

        public Node() {
            this(false);
        }
    }

    private Node root;

    /**
     * Initialize your data structure here.
     */
    public WordDictionary() {
        root = new Node();
    }

    /**
     * Adds a word into the data structure.
     */
    public void addWord(String word) {

        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (cur.next.get(c) == null) {
                cur.next.put(c, new Node());
            }
            cur = cur.next.get(c);
        }
        cur.isWord = true;
    }

    /**
     * Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter.
     */
    public boolean search(String word) {
        return match(root, word, 0);
    }

    private boolean match(Node node, String word, int index) {
        // 递归到底的情况
        if (index == word.length()) {
            return node.isWord;
        }

        char c = word.charAt(index);

        if (c != '.') {
            if (node.next.get(c) == null) {
                // 第二层没有c对应的节点，肯定就不用找了
                return false;
            }
            // 一个个接着往下匹配字符
            return match(node.next.get(c), word, index + 1);
        } else {
            // c是'.'表示需要查找所有的下一层连接点
            for (char nextChar : node.next.keySet()) {
                if (match(node.next.get(nextChar), word, index + 1)) {
                    return true;
                }
            }
            return false;
        }
    }
}
