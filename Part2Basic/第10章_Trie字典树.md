# 第10章 Trie字典树
> 也称前缀树prefix tree
## 10.1 什么是Trie字典树
+ 也称字典树Digital Tree；前缀树Prefix Tree
+ Trie是一个多叉树，通常只用来处理字符串
+ 前面几章我们一直在用的都是二叉树

### Trie与字典在字符串查找中的性能比较
> trie添加和查询字符串只与字符串的长度有关，与有多少个字符串无关
![Trie与字典在字符串查找中的性能比较](images/第10章_Trie字典树/Trie与字典在字符串查找中的性能比较.png)

### Trie的结构
> 注意根节点不存储任何字符
![Trie的表示](images/第10章_Trie字典树/Trie的表示.png)

## 10.2~10.4 Trie的基础结构、添加单词、单词查询、前缀查询
### 基础结构
> 基础节点表示和构造方法，`TreeMap<Character, Node> next`的同时记录的顶点值和边之间的关系，好好理解下~~
```java
public class Trie {
    private class Node {
        /**
         * 当前节点所属的到根节点的链路能不能形成一个单词
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
}
```
### 添加单词：遍历单词的每个字符，刷新Trie树
```java
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
        //  当在当前节点指向的孩子节点中不存在要插入的字符c的时候。为null表示不存在，把字符串作为新的Trie节点插入
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
```

### 单词查询：查询单词是否在Trie树种存在
```java
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
    // 到达字符串的最后一个字符,即使有这个单词，但是isWord不为True也表明没有被标记过。不算包含这个单词
    return cur.isWord;
}
```

### 前缀查询：判断某个字符串是否是Trie树种某个单词的前缀
```java
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
```

### 测试代码
```java
public static void main(String[] args) {
    Trie trie = new Trie();
    trie.add("cat");
    trie.add("category");
    trie.add("hello");
    trie.add("world");
    System.out.println(trie.contains("cat")); // true
    System.out.println(trie.contains("wor")); // false
    System.out.println(trie.startsWith("wor")); // true
}
```

### LeetCode上相关问题：[LeetCode208号问题](https://leetcode-cn.com/problems/implement-trie-prefix-tree/)

## 10.5 Trie支持正则表达式
> [LeetCode211号问题](https://leetcode-cn.com/problems/add-and-search-word-data-structure-design/)

核心逻辑如下：
```java
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
```

+ [代码实现：支持正则的增强Trie](src/main/java/Chapter10Trie/Section5Leetcode211TrieAndPatternMatch/TrieRegex.java)
+ [代码实现：用增强的TrieRegex来解决211号问题](src/main/java/Chapter10Trie/Section5Leetcode211TrieAndPatternMatch/WordDictionary.java)

## 10.6 带权重的Trie树
> 每个Node节点加一个weight属性，每次加入节点都要更新权重，每个单词都有自己的权重，多个前缀相同单词的相同前缀要把多个单词的权重加起来
+ [代码实现](src/main/java/Chapter10Trie/Section6Leetcode677MapSum/TrieWeighted.java)
+ [实际问题：leetcode677](src/main/java/Chapter10Trie/Section6Leetcode677MapSum/MapSum.java)