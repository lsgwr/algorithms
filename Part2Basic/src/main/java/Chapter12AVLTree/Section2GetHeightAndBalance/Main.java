/***********************************************************
 * @Description : 基于BSTKV的平衡二叉树的检测
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2020/1/3 21:28
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter12AVLTree.Section2GetHeightAndBalance;

import Chapter07SetAndMap.Section1SetBasicAndBSTSet.FileOperation;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        System.out.println("Pride and Prejudice");

        ArrayList<String> words = new ArrayList<>();
        String filename = "src/main/java/Chapter07SetAndMap/Section1SetBasicAndBSTSet/pride-and-prejudice.txt";
        if (FileOperation.readFile(filename, words)) {
            // 排序后看看性能，这样最能测AVL的实现好不好，排序后加入节点后整个BST会蜕化成一条链表，新加入的节点都要遍历整个BST才能找到自己的位置
            // Collections.sort(words);
            System.out.println("Total words: " + words.size());

            BSTKV_AVL<String, Integer> map = new BSTKV_AVL<>();
            for (String word : words) {
                if (map.contains(word)) {
                    // 之前存在地话就词频+1
                    map.set(word, map.get(word) + 1);
                } else {
                    // 不存在就插入进去，词频初始化为1
                    map.add(word, 1);
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
 * 从下面的结果可以看到我们第6章实现的二分搜索树是很不平衡的~
 * <p>
 * Pride and Prejudice
 * Total words: 125901
 * 节点左右子树高度差超过1啦：3
 * 节点左右子树高度差超过1啦：4
 * 节点左右子树高度差超过1啦：7
 * ......
 * 节点左右子树高度差超过1啦：14
 * 节点左右子树高度差超过1啦：9
 * 节点左右子树高度差超过1啦：-16
 * 节点左右子树高度差超过1啦：-10
 * 节点左右子树高度差超过1啦：10
 * 节点左右子树高度差超过1啦：10
 * 节点左右子树高度差超过1啦：4
 * ......
 * Total different words: 6530
 * Frequency of PRIDE : 53
 * Frequency of PREJUDICE : 11
 */