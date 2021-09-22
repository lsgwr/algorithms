/***********************************************************
 * @Description : LeetCode题号为677，当Trie中单词加入权重后，计算指定单词的权重值
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/5/19 22:16
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter10Trie.Section6Leetcode677MapSum;


public class MapSum {
    private TrieWeighted trieWeighted;

    public MapSum() {
        trieWeighted = new TrieWeighted();
    }

    public void insert(String key, int val) {
        trieWeighted.add(key, val);
    }

    public int sum(String prefix) {
        return trieWeighted.getWeight(prefix);
    }

    public static void main(String[] args) {
        MapSum mapSum = new MapSum();
        mapSum.insert("apple", 3);
        System.out.println(mapSum.sum("ap"));
        mapSum.insert("app", 2);
        System.out.println(mapSum.sum("ap"));
    }
}
/**
 * 3
 * 5
 */