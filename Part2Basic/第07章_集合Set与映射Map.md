# 第07章 集合Set与映射Map

## 7.1~7.4 集合Set与映射Map

### 二分搜索书BST和链表LinkedList都是动态结构，都可以用于构造集合Set
+ [基于BST的Set](src/main/java/Chapter07SetAndMap/Section1SetBasicAndBSTSet/BSTSet.java)
+ [基于LinkedList的Map](src/main/java/Chapter07SetAndMap/Section2LinkedListSet/LinkedListSet.java)

![BST和LinkedList都属于动态数据结构](https://img.mukewang.com/szimg/5e0b2b370001bb3b19201080.jpg)

### BSTSet和LinkedList的时间复杂度比较
> O(h)即O(logn)
![BSTSet和LinkedList的时间复杂度比较](https://img.mukewang.com/szimg/5e0b2e4b0001dc9e19201080.jpg)
+ BSTSet存储节点是有序集合，类似TreeSet
+ LinkedListSet存储节点是无序集合，类似HashSet

### LeetCode上的集合相关问题
+ [804.唯一摩尔斯密码词](https://leetcode-cn.com/problems/unique-morse-code-words/)

```java
class Solution {
    public int uniqueMorseRepresentations(String[] words) {
        String[] codecs = {".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."};
        // 不同的翻译
        Set<String> diffTrans = new TreeSet<>();
        for(String word : words){
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < word.length(); i++){
                sb.append(codecs[word.charAt(i) - 'a']);
            }
            diffTrans.add(sb.toString());
        }
        return diffTrans.size();
    }
}
```