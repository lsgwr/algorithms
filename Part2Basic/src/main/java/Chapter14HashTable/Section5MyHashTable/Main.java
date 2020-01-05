/***********************************************************
 * @Description : 哈希表、红黑树、BST、AVL的性能比较
 * 分别测试
 * 没经过旋转的BST非平衡二叉树：Chapter12AVLTree.Section3isBSTandisBalanced.BSTKV_AVL
 * 经过了旋转的BST即平衡二叉树avl：Chapter12AVLTree.Section4to6RotateToReBalance.BSTKV_AVL
 * 绝对黑平衡的红黑树
 * 哈希表：空间换时间，性能最高
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2020/1/5 16:06
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter14HashTable.Section5MyHashTable;

import Chapter06BST.BSTKV;
import Chapter07SetAndMap.Section1SetBasicAndBSTSet.FileOperation;
import Chapter12AVLTree.Section7DeleteNode.BSTKV_AVL;
import Chapter13ReadBlackTree.Section5to7AddNode.BSTKV_RBTree;

import java.util.ArrayList;
import java.util.Collections;

public class Main {
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
            BSTKV<String, Integer> bstkv = new BSTKV<>();
            for (String word : words) {
                if (bstkv.contains(word)) {
                    // 之前存在地话就词频+1
                    bstkv.set(word, bstkv.get(word) + 1);
                } else {
                    // 不存在就插入进去，词频初始化为1
                    bstkv.add(word, 1);
                }
            }
            // 查询操作时最消耗性能的，最能看出不平衡的BST和平衡的BST(即AVL)的性能差异
            for (String word : words) {
                bstkv.contains(word);
            }
            endTime = System.nanoTime();
            System.out.println("未经旋转前的BST耗时：" + (endTime - startTime) / 1000000000.0 + "s");

            // 2.旋转后的二分搜索树即AVL
            startTime = System.nanoTime();
            BSTKV_AVL<String, Integer> avl = new BSTKV_AVL<>();
            for (String word : words) {
                if (avl.contains(word)) {
                    // 之前存在地话就词频+1
                    avl.set(word, avl.get(word) + 1);
                } else {
                    // 不存在就插入进去，词频初始化为1
                    avl.add(word, 1);
                }
            }
            // 查询操作时最消耗性能的，最能看出不平衡的BST和平衡的BST(即AVL)的性能差异
            for (String word : words) {
                avl.contains(word);
            }
            endTime = System.nanoTime();
            System.out.println("经过旋转后的BST(即AVL)耗时：" + (endTime - startTime) / 1000000000.0 + "s");

            // 3.旋转后保持绝对黑平衡的二分搜索树即红黑树
            startTime = System.nanoTime();
            BSTKV_RBTree<String, Integer> rbTree = new BSTKV_RBTree<>();
            for (String word : words) {
                if (rbTree.contains(word)) {
                    // 之前存在地话就词频+1
                    rbTree.set(word, rbTree.get(word) + 1);
                } else {
                    // 不存在就插入进去，词频初始化为1
                    rbTree.add(word, 1);
                }
            }
            // 查询操作时最消耗性能的，最能看出不平衡的BST和平衡的BST(即AVL)的性能差异
            for (String word : words) {
                rbTree.contains(word);
            }
            endTime = System.nanoTime();
            // 从结果中可以看到红黑树的效率确实最高，比平衡树还高一点，数据量越大效果会越明显
            System.out.println("保持绝对黑平衡的红黑树耗时：" + (endTime - startTime) / 1000000000.0 + "s");

            // 4.自己实现地哈希表(空间换时间，性能最高)
            startTime = System.nanoTime();
            HashTable<String, Integer> hashTable = new HashTable<>(131071);
            for (String word : words) {
                if (hashTable.contains(word)) {
                    // 之前存在地话就词频+1
                    hashTable.set(word, hashTable.get(word) + 1);
                } else {
                    // 不存在就插入进去，词频初始化为1
                    hashTable.add(word, 1);
                }
            }
            // 查询操作时最消耗性能的，最能看出不平衡的BST和平衡的BST(即AVL)的性能差异
            for (String word : words) {
                hashTable.contains(word);
            }
            endTime = System.nanoTime();
            // 从结果中可以看到红黑树的效率确实最高，比平衡树还高一点，数据量越大效果会越明显
            System.out.println("自己实现地哈希表：" + (endTime - startTime) / 1000000000.0 + "s");
        }
    }
}
/**
 * Pride and Prejudice
 * 未经旋转前的BST耗时：24.9147723s
 * 经过旋转后的BST(即AVL)耗时：0.0511122s
 * 保持绝对黑平衡的红黑树耗时：0.0465389s
 * 自己实现地哈希表：0.0370802s
 */