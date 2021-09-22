/***********************************************************
 * @Description : BSTMap的测试,明显要比基于链表的LinkedListMap快地多
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/5/17 23:56
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter07SetAndMap.Section7BSTMap;

import Chapter07SetAndMap.Section1SetBasicAndBSTSet.FileOperation;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        System.out.println("Pride and Prejudice");

        ArrayList<String> words = new ArrayList<>();
        String filename = "src/main/java/Chapter07SetAndMap/Section1SetBasicAndBSTSet/pride-and-prejudice.txt";
        if (FileOperation.readFile(filename, words)) {
            System.out.println("Total words: " + words.size());

            BSTMap<String, Integer> map = new BSTMap<>();
            for (String word : words) {
                if (map.contains(word)) {
                    // 之前存在地话就词频+1
                    map.set(word, map.get(word) + 1);
                } else {
                    // 不存在就插入进去，词频初始化为1
                    map.set(word, 1);
                }
            }
            System.out.println("Total different words: " + map.getSize());
            // 出现pride和prejudice单词的次数
            System.out.println("Frequency of PRIDE : " + map.get("pride"));
            System.out.println("Frequency of PREJUDICE : " + map.get("prejudice"));
        }
        System.out.println();
    }
}
/**
 * Pride and Prejudice
 * Total words: 125901
 * Total different words: 6530
 * Frequency of PRIDE : 53
 * Frequency of PREJUDICE : 11
 */