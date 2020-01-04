/***********************************************************
 * @Description : 分别测试基于LinkedList、BST和AVL的Map实现
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/4 19:16
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter12AVLTree.Section8SetAndMapBasedOnAVL;

import Chapter07SetAndMap.Section1SetBasicAndBSTSet.FileOperation;
import Chapter07SetAndMap.Section5MapBasic.Map;
import Chapter07SetAndMap.Section6LinkedListMap.LinkedListMap;
import Chapter07SetAndMap.Section7BSTMap.BSTMap;

import java.util.ArrayList;

public class MainMap {
    private static double testMap(Map<String, Integer> map, String filename) {

        long startTime = System.nanoTime();

        System.out.println(filename);
        ArrayList<String> words = new ArrayList<>();
        if (FileOperation.readFile(filename, words)) {
            System.out.println("Total words: " + words.size());

            for (String word : words) {
                if (map.contains(word)) {
                    map.set(word, map.get(word) + 1);
                } else {
                    map.set(word, 1);
                }
            }

            System.out.println("Total different words: " + map.getSize());
            System.out.println("Frequency of PRIDE: " + map.get("pride"));
            System.out.println("Frequency of PREJUDICE: " + map.get("prejudice"));
        }

        long endTime = System.nanoTime();

        return (endTime - startTime) / 1000000000.0;
    }

    public static void main(String[] args) {

        String filename = "src/main/java/Chapter07SetAndMap/Section1SetBasicAndBSTSet/pride-and-prejudice.txt";

        LinkedListMap<String, Integer> linkedListMap = new LinkedListMap<>();
        double time1 = testMap(linkedListMap, filename);
        System.out.println("LinkedList Map: " + time1 + " s");

        System.out.println();

        BSTMap<String, Integer> bstMap = new BSTMap<>();
        double time2 = testMap(bstMap, filename);
        System.out.println("BST Map: " + time2 + " s");

        System.out.println();

        AVLMap<String, Integer> avlMap = new AVLMap<>();
        double time3 = testMap(avlMap, filename);
        System.out.println("AVL Map: " + time3 + " s");
    }
}
/**
 * src/main/java/Chapter07SetAndMap/Section1SetBasicAndBSTSet/pride-and-prejudice.txt
 * Total words: 125901
 * Total different words: 6530
 * Frequency of PRIDE: 53
 * Frequency of PREJUDICE: 11
 * LinkedList Map: 10.0410104 s
 *
 * src/main/java/Chapter07SetAndMap/Section1SetBasicAndBSTSet/pride-and-prejudice.txt
 * Total words: 125901
 * Total different words: 6530
 * Frequency of PRIDE: 53
 * Frequency of PREJUDICE: 11
 * BST Map: 0.0749493 s
 *
 * src/main/java/Chapter07SetAndMap/Section1SetBasicAndBSTSet/pride-and-prejudice.txt
 * Total words: 125901
 * Total different words: 6530
 * Frequency of PRIDE: 53
 * Frequency of PREJUDICE: 11
 * AVL Map: 0.0585773 s
 */
