# 02_Huffman树

## 1.[AcWing 148.果子合并](https://www.acwing.com/problem/content/150/)
### 联想：与`石子合并`题目进行比较
看起来和[石子合并](https://www.acwing.com/problem/content/284/)差不多，其实不同如下：
+ `石子合并`只能相邻两个合并
+ `果子合并`可以任意两个合并

> 因此前者只能用区间DP，后者也可以用DP，但是后者用DP肯定会超时，只能过`1/10`个用例，因此后者只能用贪心

### 算法解析
> (贪心,哈夫曼树,堆,优先队列) $O(nlogn)$

看成是一棵树，最后合并成一个节点。

完全二叉树，所有的叶子节点就是要合并的点。

内部点就是合并过来的点。

每个叶子节点会被算几次，看看这个叶子节点有几个父节点就会被算几次。

这道题的本质，`一共有多个完全二叉树，看看哪个完全二叉树的叶子节点的权值总和最小，把它输出来。`

这是经典哈夫曼树的模型，每次合并重量最小的两堆果子即可。直接用Java自带的优先队列即可

本题结合Huffman树的讲解为[贪心——Huffman树](https://www.jianshu.com/p/0d1cbb1f394b)

### 时间复杂度
> 使用小根堆维护所有果子，每次弹出堆顶的两堆果子，并将其合并，合并之后将两堆重量之和再次插入小根堆中。

每次操作会将果子的堆数减一，一共操作$n−1$次即可将所有果子合并成1堆。每次操作涉及到2次堆的删除操作和1次堆的插入操作，计算量是$O(logn)$。因此总时间复杂度是 $O(nlogn)$

### 代码实现
```java
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Queue<Integer> pq = new PriorityQueue<>();
        while(n-- > 0) {
            pq.add(sc.nextInt());
        }
        int res = 0;
        // 每次弹出堆中的两个最小值，合并后再压入回去
        while(pq.size() > 1){
            int m = pq.remove() + pq.remove(); // 弹出两个最小值进行合并
            pq.add(m); // 新结果加入
            res += m; // 代价累计
        }
        System.out.println(res);
    }
}
```

## 2.[LeetCode 527.单词缩写](https://leetcode-cn.com/problems/word-abbreviation)
```txt
给定一个由n个不重复非空字符串组成的数组，你需要按照以下规则为每个单词生成最小的缩写。

初始缩写由起始字母+省略字母的数量+结尾字母组成。
若存在冲突，亦即多于一个单词有同样的缩写，则使用更长的前缀代替首字母，直到从单词到缩写的映射唯一。换而言之，最终的缩写必须只能映射到一个单词。
若缩写并不比原单词更短，则保留原样。
示例:

输入: ["like", "god", "internal", "me", "internet", "interval", "intension", "face", "intrusion"]
输出: ["l2e","god","internal","me","i6t","interval","inte4n","f2e","intr4n"]
 

注意:

n和每个单词的长度均不超过 400。
每个单词的长度大于 1。
单词只由英文小写字母组成。
返回的答案需要和原数组保持同一顺序。
```

```java
// 首先给每个单词选择最短的缩写。然后我们对于所有重复的单词，我们增加这些重复项的长度。
// 参考：https://leetcode-cn.com/problems/word-abbreviation/solution/dan-ci-suo-xie-by-leetcode/
class Solution {
    private int[] prefixs;
    private Map<Integer, String> wordsCur;

    // 单词word需保留前prefix个字符以及最后一个字符中间的字符用end - prefix - 1来代替
    private String abbrev(String word, int prefix) {
        // prefix已经到了最后一个字符了，word已经不用变了
        if (prefix >= (word.length() - 1)) {
            return word;
        }
        return word.substring(0, prefix) + (word.length() - 1 - prefix) + word.substring(word.length() - 1);
    }

    // 查找重复单词，存在地话返回重复的单词的下标
    private Set<String> getRepeated() {
        Set<String> result = new HashSet<>();
        Map<String, Integer> wordCnt = new HashMap<>();
        for (int index : wordsCur.keySet()) {
            String word = wordsCur.get(index);
            if (wordCnt.get(word) == null) wordCnt.put(word, 1);
            else result.add(word); // 把重复出现的单词加入到结果中去
        }
        return result;
    }

    // 首先给每个单词选择最短的缩写。然后我们对于所有重复的单词，我们增加这些重复项的长度。
    public List<String> wordsAbbreviation(List<String> words) {
        wordsCur = new HashMap<>(); // 记录当前的单词
        prefixs = new int[words.size()];
        Arrays.fill(prefixs, 1); // 初始认为每个单词的前缀都只保留一个字符
        for (int i = 0; i < words.size(); i++) {
            wordsCur.put(i, abbrev(words.get(i), prefixs[i]));
        }
        // 查找重复单词
        Set<String> repeatedWords = getRepeated();
        while (repeatedWords.size() != 0) {
            for (int i = 0; i < words.size(); i++) {
                if (repeatedWords.contains(wordsCur.get(i))) {
                    prefixs[i]++; // 找到重复的单词就尝试把单词前缀长度+1
                    wordsCur.put(i, abbrev(words.get(i), prefixs[i])); // 更新单词缩写
                }
            }
            repeatedWords = getRepeated();
        }

        List<String> result = new ArrayList<>();
        for (Integer index : wordsCur.keySet()) {
            if (wordsCur.get(index).length() < words.get(index).length()) result.add(wordsCur.get(index));
            else result.add(words.get(index)); // 缩写后的单词长度比原来地还长，就把原来的单词加进去
        }
        return result;
    }
}
```

