/***********************************************************
 * @Description : 测试基于BST和LinkedList的Map实现，可以看到基于BST的实现明显快很多
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2019/12/31 19:55
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter07SetAndMap.Section8TestMap;

import Chapter07SetAndMap.Section1SetBasicAndBSTSet.FileOperation;
import Chapter07SetAndMap.Section5MapBasic.Map;
import Chapter07SetAndMap.Section6LinkedListMap.LinkedListMap;
import Chapter07SetAndMap.Section7BSTMap.BSTMap;

import java.util.ArrayList;

public class Main {
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

        BSTMap<String, Integer> bstMap = new BSTMap<>();
        double time1 = testMap(bstMap, filename);
        System.out.println("BST Map: " + time1 + " s");

        System.out.println();

        LinkedListMap<String, Integer> linkedListMap = new LinkedListMap<>();
        double time2 = testMap(linkedListMap, filename);
        System.out.println("Linked List Map: " + time2 + " s");

    }
}
/**
 * src/main/java/Chapter07SetAndMap/Section1SetBasicAndBSTSet/pride-and-prejudice.txt
 * Total words: 125901
 * Total different words: 6530
 * Frequency of PRIDE: 53
 * Frequency of PREJUDICE: 11
 * BST Map: 0.128220999 s
 *
 * src/main/java/Chapter07SetAndMap/Section1SetBasicAndBSTSet/pride-and-prejudice.txt
 * Total words: 125901
 * Total different words: 6530
 * Frequency of PRIDE: 53
 * Frequency of PREJUDICE: 11
 * Linked List Map: 9.7055565 s
 */