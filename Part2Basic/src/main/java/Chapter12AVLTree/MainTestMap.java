/***********************************************************
 * @Description : 
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/5/20 15:55
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter12AVLTree;

import Chapter07SetAndMap.Section1SetBasicAndBSTSet.FileOperation;
import Chapter07SetAndMap.Section5MapBasic.Map;
import Chapter07SetAndMap.Section6LinkedListMap.LinkedListMap;
import Chapter07SetAndMap.Section7BSTMap.BSTMap;

import java.util.ArrayList;

public class MainTestMap {
    private static double testMap(Map<String, Integer> map, String filename) {

        long startTime = System.nanoTime();
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
        String filename = "/Users/liangshanguang/Program/Algorithm/liuyubobobo-algorithms/Part2Basic/src/main/java/Chapter07SetAndMap/Section1SetBasicAndBSTSet/pride-and-prejudice.txt";
        BSTMap<String, Integer> bstMap = new BSTMap<>();
        double bstTime = testMap(bstMap, filename);
        System.out.println("BST Map ： " + bstTime + " s");
        System.out.println();

        LinkedListMap<String, Integer> linkedListMap = new LinkedListMap<>();
        double linkedListTime = testMap(linkedListMap, filename);
        System.out.println("LinkedList Map : " + linkedListTime + " s");
        System.out.println();

        AVLTreeMap<String, Integer> avlTreeMap = new AVLTreeMap<>();
        double avlTreeTime = testMap(avlTreeMap, filename);
        System.out.println("AVLTree Map : " + avlTreeTime + " s");
        System.out.println();
    }
}
