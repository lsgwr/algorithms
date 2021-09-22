/***********************************************************
 * @Description : 127.单词接龙
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/21 0:15
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter06StackAndQueue.LeetCode127;

import java.util.*;

class Solution {
    private class Node {
        /**
         * 节点对应的单词
         */
        String word;
        /**
         * 当前单词到起点单词的距离
         */
        int distance;

        public Node(String word, int distance) {
            this.word = word;
            this.distance = distance;
        }
    }

    /**
     * 判断两个单词是否只有一个单词不同
     * 两个单词的长度是相等地
     */
    private boolean oneCharDiff(String wordA, String wordB) {
        // 不同字符的个数
        int diffCnt = 0;
        for (int i = 0; i < wordA.length(); i++) {
            if (wordA.charAt(i) != wordB.charAt(i)) {
                diffCnt++;
            }
        }
        return diffCnt == 1;
    }

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        // 访问数组
        Map<String, Boolean> visited = new HashMap<>();
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(new Node(beginWord, 1));
        visited.put(beginWord, true);
        while (!queue.isEmpty()) {
            Node node = queue.remove();
            for (String word : wordList) {
                if (oneCharDiff(word, node.word) && visited.get(word) == null) {
                    // 一旦找到目标单词endWord，直接返回
                    if (word.equals(endWord)) {
                        return node.distance + 1;
                    }
                    // 和父节点相比只有一个节点不同而且还没被访问
                    queue.add(new Node(word, node.distance + 1));
                    visited.put(word, true);
                }
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        String beginWord = "hit";
        String endWord = "cog";
        String[] wordList = {"hot", "dot", "dog", "lot", "log"};
        List<String> list = new ArrayList<>(Arrays.asList(wordList));
        System.out.println(new Solution().ladderLength(beginWord, endWord, list));
    }
}
