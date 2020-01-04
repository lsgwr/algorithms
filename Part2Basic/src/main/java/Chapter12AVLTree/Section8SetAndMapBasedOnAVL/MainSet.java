/***********************************************************
 * @Description : 分别测试基于LinkedList、BST和AVL的Set实现
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/4 19:42
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter12AVLTree.Section8SetAndMapBasedOnAVL;

import Chapter07SetAndMap.Section1SetBasicAndBSTSet.BSTSet;
import Chapter07SetAndMap.Section1SetBasicAndBSTSet.FileOperation;
import Chapter07SetAndMap.Section1SetBasicAndBSTSet.Set;
import Chapter07SetAndMap.Section2LinkedListSet.LinkedListSet;

import java.util.ArrayList;

public class MainSet {
    private static double testSet(Set<String> set, String filename) {

        long startTime = System.nanoTime();

        System.out.println(filename);
        ArrayList<String> words = new ArrayList<>();
        if (FileOperation.readFile(filename, words)) {
            System.out.println("Total words: " + words.size());
            for (String word : words) {
                set.add(word);
            }
            System.out.println("Total different words: " + set.getSize());
        }
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1000000000.0;
    }

    public static void main(String[] args) {

        String filename = "src/main/java/Chapter07SetAndMap/Section1SetBasicAndBSTSet/pride-and-prejudice.txt";

        LinkedListSet<String> linkedListSet = new LinkedListSet<>();
        double time1 = testSet(linkedListSet, filename);
        System.out.println("LinkedList Set: " + time1 + "s");

        System.out.println();

        BSTSet<String> bstSet = new BSTSet<>();
        double time2 = testSet(bstSet, filename);
        System.out.println("BST Set: " + time2 + "s");

        System.out.println();

        AVLSet<String> avlSet = new AVLSet<>();
        double time3 = testSet(avlSet, filename);
        System.out.println("AVL Set: " + time3 + "s");
    }
}
/**
 * src/main/java/Chapter07SetAndMap/Section1SetBasicAndBSTSet/pride-and-prejudice.txt
 * Total words: 125901
 * Total different words: 6530
 * LinkedList Set: 2.2095731s
 *
 * src/main/java/Chapter07SetAndMap/Section1SetBasicAndBSTSet/pride-and-prejudice.txt
 * Total words: 125901
 * Total different words: 6530
 * BST Set: 0.0454047s
 *
 * src/main/java/Chapter07SetAndMap/Section1SetBasicAndBSTSet/pride-and-prejudice.txt
 * Total words: 125901
 * Total different words: 6530
 * AVL Set: 0.0378066s
 */
