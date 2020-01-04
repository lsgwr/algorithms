/***********************************************************
 * @Description : 基于BSTKV的平衡二叉树的检测
 * 分别测试
 * 没经过旋转的BST，非平衡二叉树：Chapter12AVLTree.Section3isBSTandisBalanced.BSTKV_AVL
 * 经过了旋转的BST，是平衡二叉树：Chapter12AVLTree.Section4to6RotateToReBalance.BSTKV_AVL
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2020/1/4 15:59
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter12AVLTree.Section4to6RotateToReBalance;

import Chapter07SetAndMap.Section1SetBasicAndBSTSet.FileOperation;

import java.util.ArrayList;
import java.util.Collections;

public class PerformanceTest {
    public static void main(String[] args) {
        System.out.println("Pride and Prejudice");
        long startTime, endTime;
        ArrayList<String> words = new ArrayList<>();
        String filename = "src/main/java/Chapter07SetAndMap/Section1SetBasicAndBSTSet/pride-and-prejudice.txt";
        if (FileOperation.readFile(filename, words)) {
            // 排序后看看性能，这样最能测AVL的实现好不好。未经旋转的BST加入节点后整个BST会蜕化成一条链表，新加入的节点都要遍历整个BST才能找到自己的位置
            // 旋转后的BST在加过节点后都会调整使得BST重新保持为平衡二叉树AVL，性能要比没旋转地高
            Collections.sort(words);
            // System.out.println("Total words: " + words.size());
            // 1.未经旋转前的二分搜索树
            startTime = System.nanoTime();
            Chapter12AVLTree.Section3isBSTandisBalanced.BSTKV_AVL<String, Integer> avlBeforeRotate = new Chapter12AVLTree.Section3isBSTandisBalanced.BSTKV_AVL<>();
            for (String word : words) {
                if (avlBeforeRotate.contains(word)) {
                    // 之前存在地话就词频+1
                    avlBeforeRotate.set(word, avlBeforeRotate.get(word) + 1);
                } else {
                    // 不存在就插入进去，词频初始化为1
                    avlBeforeRotate.add(word, 1);
                }
            }
            // 查询操作时最消耗性能的，最能看出不平衡的BST和平衡的BST(即AVL)的性能差异
            for (String word : words) {
                avlBeforeRotate.contains(word);
            }
            endTime = System.nanoTime();
            System.out.println("未经旋转前的BST耗时：" + (endTime - startTime) / 1000000000.0 + "s");

            // 2.旋转后的二分搜索树
            startTime = System.nanoTime();
            Chapter12AVLTree.Section4to6RotateToReBalance.BSTKV_AVL<String, Integer> avlAfterRotate = new Chapter12AVLTree.Section4to6RotateToReBalance.BSTKV_AVL<>();
            for (String word : words) {
                if (avlAfterRotate.contains(word)) {
                    // 之前存在地话就词频+1
                    avlAfterRotate.set(word, avlAfterRotate.get(word) + 1);
                } else {
                    // 不存在就插入进去，词频初始化为1
                    avlAfterRotate.add(word, 1);
                }
            }
            // 查询操作时最消耗性能的，最能看出不平衡的BST和平衡的BST(即AVL)的性能差异
            for (String word : words) {
                avlAfterRotate.contains(word);
            }
            endTime = System.nanoTime();
            System.out.println("经过旋转后的BST(即AVL)耗时：" + (endTime - startTime) / 1000000000.0 + "s");

//            System.out.println("Total different words: " + avlAfterRotate.getSize());
//            // 出现pride和prejudice单词的次数
//            System.out.println("Frequency of PRIDE : " + avlBeforeRotate.get("pride"));
//            System.out.println("Frequency of PREJUDICE : " + avlBeforeRotate.get("prejudice"));
//            System.out.println("二叉树是否是二分搜索树BST：" + avlBeforeRotate.isBST());
//            // 还没进行平衡处理，肯定是不平衡的
//            System.out.println("二叉树是否是平衡二叉树：" + avlBeforeRotate.isBalanced());
        }
    }
}
/**
 * Pride and Prejudice
 * 未经旋转前的BST耗时：25.2243362s
 * 经过旋转后的BST(即AVL)耗时：0.0496628s
 */