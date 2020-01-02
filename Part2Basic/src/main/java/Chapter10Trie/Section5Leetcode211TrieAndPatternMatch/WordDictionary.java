/***********************************************************
 * @Description : 字典查询，LeetCode题目211
 * https://leetcode-cn.com/problems/add-and-search-word-data-structure-design/
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2020/1/2 10:41
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter10Trie.Section5Leetcode211TrieAndPatternMatch;

public class WordDictionary {
    private TrieRegex trieRegex;

    /** Initialize your data structure here. */
    public WordDictionary() {
        trieRegex = new TrieRegex();
    }

    /** Adds a word into the data structure. */
    public void addWord(String word) {
        trieRegex.add(word);
    }

    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        return trieRegex.contains(word);
    }

    public static void main(String[] args) {
        WordDictionary dictionary = new WordDictionary();
        dictionary.addWord("bad");
        dictionary.addWord("dad");
        dictionary.addWord("mad");
        dictionary.addWord("world");
        // 测试单词查找
        System.out.println(dictionary.search("pad")); // false
        System.out.println(dictionary.search("bad")); // true
        System.out.println(dictionary.search(".ad")); // true
        System.out.println(dictionary.search("b..")); // true
    }
}
