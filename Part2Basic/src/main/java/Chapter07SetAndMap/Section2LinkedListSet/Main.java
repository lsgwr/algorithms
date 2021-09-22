/***********************************************************
 * @Description : 利用基于链表的集合来统计英文原版书中的不同单词数。
 *                速度明显慢于上一节BSTSet
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/5/17 07:54
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter07SetAndMap.Section2LinkedListSet;

import Chapter07SetAndMap.Section1SetBasicAndBSTSet.FileOperation;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        System.out.println("Pride and Prejudice");

        ArrayList<String> words1 = new ArrayList<>();
        String filename = "src/main/java/Chapter07SetAndMap/Section1SetBasicAndBSTSet/pride-and-prejudice.txt";
        if (FileOperation.readFile(filename, words1)) {
            System.out.println("Total words: " + words1.size());

            LinkedListSet<String> set1 = new LinkedListSet<>();
            for (String word : words1) {
                set1.add(word);
            }
            System.out.println("Total different words: " + set1.getSize());
        }

        System.out.println();


        System.out.println("A Tale of Two Cities");

        ArrayList<String> words2 = new ArrayList<>();
        filename = "src/main/java/Chapter07SetAndMap/Section1SetBasicAndBSTSet/a-tale-of-two-cities.txt";
        if (FileOperation.readFile(filename, words2)) {
            System.out.println("Total words: " + words2.size());

            LinkedListSet<String> set2 = new LinkedListSet<>();
            for (String word : words2) {
                set2.add(word);
            }
            System.out.println("Total different words: " + set2.getSize());
        }
    }
}
/**
 * Pride and Prejudice
 * Total words: 125901
 * Total different words: 6530
 *
 * A Tale of Two Cities
 * Total words: 141489
 * Total different words: 9944
 **/