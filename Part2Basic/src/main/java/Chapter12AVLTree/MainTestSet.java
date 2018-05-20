/***********************************************************
 * @Description : 
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/5/20 16:09
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter12AVLTree;

import Chapter07SetAndMap.Section1SetBasicAndBSTSet.BSTSet;
import Chapter07SetAndMap.Section1SetBasicAndBSTSet.FileOperation;
import Chapter07SetAndMap.Section1SetBasicAndBSTSet.Set;
import Chapter07SetAndMap.Section2LinkedListSet.LinkedListSet;

import java.util.ArrayList;

public class MainTestSet {
    private static double testSet(Set<String> set, String filename) {

        long startTime = System.nanoTime();
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
        String filename = "/Users/liangshanguang/Program/Algorithm/liuyubobobo-algorithms/Part2Basic/src/main/java/Chapter07SetAndMap/Section1SetBasicAndBSTSet/pride-and-prejudice.txt";
        BSTSet<String> bstSet = new BSTSet<>();
        double bstTime = testSet(bstSet, filename);
        System.out.println("BST Set ： " + bstTime + " s");
        System.out.println();

        LinkedListSet<String> linkedListSet = new LinkedListSet<>();
        double linkedListTime = testSet(linkedListSet, filename);
        System.out.println("LinkedList Set : " + linkedListTime + " s");
        System.out.println();

        AVLTreeSet<String> avlTreeSet = new AVLTreeSet<>();
        double avlTreeTime = testSet(avlTreeSet, filename);
        System.out.println("AVLTree Set : " + avlTreeTime + " s");
        System.out.println();
    }
}
