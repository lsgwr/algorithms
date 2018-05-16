/***********************************************************
 * @Description : 利用集合来统计英文原版书中的不同单词数
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/5/17 00:18
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter07SetAndMap.Section1SetBasicAndBSTSet;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        System.out.println("Pride and Prejudice");

        ArrayList<String> words1 = new ArrayList<>();
        String filename = "/Users/liangshanguang/Program/Algorithm/liuyubobobo-algorithms/Part2Basic/src/main/java/Chapter07SetAndMap/Section1SetBasicAndBSTSet/pride-and-prejudice.txt";
        if (FileOperation.readFile(filename, words1)) {
            System.out.println("Total words: " + words1.size());

            BSTSet<String> set1 = new BSTSet<>();
            for (String word : words1) {
                set1.add(word);
            }
            System.out.println("Total different words: " + set1.getSize());
        }

        System.out.println();


        System.out.println("A Tale of Two Cities");

        ArrayList<String> words2 = new ArrayList<>();
        filename = "/Users/liangshanguang/Program/Algorithm/liuyubobobo-algorithms/Part2Basic/src/main/java/Chapter07SetAndMap/Section1SetBasicAndBSTSet/a-tale-of-two-cities.txt";
        if (FileOperation.readFile(filename, words2)) {
            System.out.println("Total words: " + words2.size());

            BSTSet<String> set2 = new BSTSet<>();
            for (String word : words2) {
                set2.add(word);
            }
            System.out.println("Total different words: " + set2.getSize());
        }
    }
}
