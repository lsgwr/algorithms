/***********************************************************
 * @Description : 基于BSTKV的平衡二叉树的检测
 * 经过了LL、RR、RL和RR的旋转，最终的树是平衡二叉树AVL了
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2020/1/4 15:59
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter12AVLTree.Section7DeleteNode;

import Chapter07SetAndMap.Section1SetBasicAndBSTSet.FileOperation;

import java.util.ArrayList;
import java.util.Collections;

public class Main {
    public static void main(String[] args) {
        System.out.println("Pride and Prejudice");

        ArrayList<String> words = new ArrayList<>();
        String filename = "src/main/java/Chapter07SetAndMap/Section1SetBasicAndBSTSet/pride-and-prejudice.txt";
        if (FileOperation.readFile(filename, words)) {
            // 排序后看看性能，这样最能测AVL的实现好不好，排序后加入节点后整个BST会蜕化成一条链表，新加入的节点都要遍历整个BST才能找到自己的位置
            Collections.sort(words);
            System.out.println("Total words: " + words.size());

            BSTKV_AVL<String, Integer> avl = new BSTKV_AVL<>();
            for (String word : words) {
                if (avl.contains(word)) {
                    // 之前存在地话就词频+1
                    avl.set(word, avl.get(word) + 1);
                } else {
                    // 不存在就插入进去，词频初始化为1
                    avl.add(word, 1);
                    if (!avl.isBalanced()) {
                        // 添加节点都会进行旋转再平衡，一次输出下面的内容就说明我们的算法有问题
                        System.out.println("二叉树是否是平衡二叉树：" + avl.isBalanced());
                    }
                }
            }
            System.out.println("Total different words: " + avl.getSize());
            // 出现pride和prejudice单词的次数
            System.out.println("Frequency of PRIDE : " + avl.get("pride"));
            System.out.println("Frequency of PREJUDICE : " + avl.get("prejudice"));
            System.out.println("二叉树是否是二分搜索树BST：" + avl.isBST());
            // 还没进行平衡处理，肯定是不平衡的
            System.out.println("二叉树是否是平衡二叉树：" + avl.isBalanced());

            // 下面进行删除操作，只要重要任何一个步骤不再是BST或者AVL了，就报错
            int count = 0;
            for (int i = 0; i < words.size(); i++) {
                String word = words.get(i);
                avl.remove(word);
                if (!avl.isBST()) {
                    System.out.println("不再是BST啦！");
                }
                if (!avl.isBalanced()) {
                    count++;
//                    System.out.println("不再是平衡二叉树啦！" + word);
                }
            }
            System.out.println(count);
        }
    }
}
/**
 * Pride and Prejudice
 * Total words: 125901
 * Total different words: 6530
 * Frequency of PRIDE : 53
 * Frequency of PREJUDICE : 11
 * 二叉树是否是二分搜索树BST：true
 * 二叉树是否是平衡二叉树：true
 */