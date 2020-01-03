/***********************************************************
 * @Description : AVLTree和基于键值对的BST的性能比较
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/5/20 14:36
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter12AVLTree;

import Chapter06BST.BSTKV;
import Chapter07SetAndMap.Section1SetBasicAndBSTSet.FileOperation;

import java.util.ArrayList;
import java.util.Collections;

public class MainPerformance {
    public static void main(String[] args) {
        System.out.println("Pride and Prejudice");

        ArrayList<String> words = new ArrayList<>();
        String filename = "src/main/java/Chapter07SetAndMap/Section1SetBasicAndBSTSet/pride-and-prejudice.txt";
        if (FileOperation.readFile(filename, words)) {
            System.out.println("Total words: " + words.size());
            // 排序后，BST算法的性能会急剧下降，因为已经蜕化成了链表，性能是n^2了。但AVL的性能丝毫不受影响
            Collections.sort(words);
            // 1.测试AVLTree的性能
            long startTime = System.nanoTime();
            AVLTree<String, Integer> map = new AVLTree<>();
            for (String word : words) {
                if (map.contain(word)) {
                    // 之前存在地话就词频+1
                    map.insert(word, map.get(word) + 1);
                } else {
                    // 不存在就插入进去，词频初始化为1
                    map.insert(word, 1);
                }
            }
            for (String word : words) {
                map.contain(word);
            }
            long endTime = System.nanoTime();
            double time = (endTime - startTime) / 1000000000.0;
            System.out.println("AVLTree : " + time + "s");

            // 2.测试BST的性能
            startTime = System.nanoTime();
            BSTKV<String, Integer> bst = new BSTKV<>();
            for (String word : words) {
                if (bst.contains(word)) {
                    // 之前存在地话就词频+1
                    bst.add(word, bst.get(word) + 1);
                } else {
                    // 不存在就插入进去，词频初始化为1
                    bst.add(word, 1);
                }
            }
            for (String word : words) {
                bst.contains(word);
            }
            endTime = System.nanoTime();
            time = (endTime - startTime) / 1000000000.0;
            System.out.println("BST : " + time + "s");
        }
    }
}
