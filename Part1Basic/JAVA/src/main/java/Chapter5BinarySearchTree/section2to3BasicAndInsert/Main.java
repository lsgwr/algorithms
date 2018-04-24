/***********************************************************
 * @Description : BST测试类
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/4/25 01:26
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter5BinarySearchTree.section2to3BasicAndInsert;

import java.util.Vector;

public class Main {
    /**
     * 测试二分搜索树和顺序查找表之间的性能差距
     * 二分搜索树的性能远远优于顺序查找表
     */
    public static void main(String[] args) {
        // 使用圣经作为我们的测试用例
        String filename = "/Users/liangshanguang/Program/Algorithm/Play-with-Algorithms/05-Binary-Search-Tree/Course Code (C++)/04-Binary-Search-Tree-Search/bible.txt";
        Vector<String> words = new Vector<>();
        if (FileOperations.readFile(filename, words)) {
            System.out.println("There are totally " + words.size() + " words in " + filename);
            System.out.println();

            // 测试 BST
            long startTime = System.currentTimeMillis();

            // 统计圣经中所有词的词频
            // 注: 这个词频统计法相对简陋, 没有考虑很多文本处理中的特殊问题
            // 在这里只做性能测试用
            BST<String, Integer> bst = new BST<>();
            for (String word : words) {
                Integer res = bst.search(word);
                if (res == null) {
                    bst.insert(word, 1);
                } else {
                    bst.insert(word, res + 1);
                }
            }

            // 输出圣经中god一词出现的频率
            if (bst.contain("god")) {
                System.out.println("'god' : " + bst.search("god"));
            } else {
                System.out.println("No word 'god' in " + filename);
            }

            long endTime = System.currentTimeMillis();
            System.out.println("BST , time: " + (endTime - startTime) + "ms.");

            System.out.println();
        }
    }
}
