/***********************************************************
 * @Description : 红黑树测试
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/5 15:53
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter13ReadBlackTree.Section5to7AddNode;

import Chapter07SetAndMap.Section1SetBasicAndBSTSet.FileOperation;

import java.util.ArrayList;
import java.util.Collections;

public class Main {
    public static void main(String[] args) {
        System.out.println("Pride and Prejudice");

        ArrayList<String> words = new ArrayList<>();
        String filename = "src/main/java/Chapter07SetAndMap/Section1SetBasicAndBSTSet/pride-and-prejudice.txt";
        if (FileOperation.readFile(filename, words)) {
            // 排序后看看性能，这样最能测AVL的实现好不好，排序后加入节点后整个BST会蜕化成一条链表，新加入的节点都要遍历整个BST才能找到自己的位置
            Collections.sort(words);
            System.out.println("Total words: " + words.size());

            BSTKV_RBTree<String, Integer> rbTree = new BSTKV_RBTree<>();
            for (String word : words) {
                if (rbTree.contains(word)) {
                    // 之前存在地话就词频+1
                    rbTree.set(word, rbTree.get(word) + 1);
                } else {
                    // 不存在就插入进去，词频初始化为1
                    rbTree.add(word, 1);
                }
            }
            System.out.println("Total different words: " + rbTree.getSize());
            // 出现pride和prejudice单词的次数
            System.out.println("Frequency of PRIDE : " + rbTree.get("pride"));
            System.out.println("Frequency of PREJUDICE : " + rbTree.get("prejudice"));
        }
    }
}
/**
 * Pride and Prejudice
 * Total words: 125901
 * Total different words: 6530
 * Frequency of PRIDE : 53
 * Frequency of PREJUDICE : 11
 */