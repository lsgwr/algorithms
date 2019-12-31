/***********************************************************
 * @Description : BSTSet和LinkedListSet两种集合的性能比较
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/5/17 08:00
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter07SetAndMap.Section3TimeComplexityOfSet;

import Chapter07SetAndMap.Section1SetBasicAndBSTSet.BSTSet;
import Chapter07SetAndMap.Section1SetBasicAndBSTSet.FileOperation;
import Chapter07SetAndMap.Section1SetBasicAndBSTSet.Set;
import Chapter07SetAndMap.Section2LinkedListSet.LinkedListSet;

import java.util.ArrayList;

public class Main {
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

        BSTSet<String> bstSet = new BSTSet<>();
        double time1 = testSet(bstSet, filename);
        System.out.println("AVLTree Set: " + time1 + " s");

        System.out.println();

        LinkedListSet<String> linkedListSet = new LinkedListSet<>();
        double time2 = testSet(linkedListSet, filename);
        System.out.println("Linked List Set: " + time2 + " s");

    }

}
/**
 * src/main/java/Chapter07SetAndMap/Section1SetBasicAndBSTSet/pride-and-prejudice.txt
 * Total words: 125901
 * Total different words: 6530
 * AVLTree Set: 0.085380201 s
 *
 * src/main/java/Chapter07SetAndMap/Section1SetBasicAndBSTSet/pride-and-prejudice.txt
 * Total words: 125901
 * Total different words: 6530
 * Linked List Set: 2.0119759 s
 */
