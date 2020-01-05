/***********************************************************
 * @Description : 测试大量的顺序数据插入测试
 * ps:未经排序的BSTKV.java就不要测试了，那个测试有序数据就会完全蜕化成链表，能跑一天，哈哈哈
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/5 16:41
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter13ReadBlackTree.Section8PerformanceTest;

import Chapter12AVLTree.Section7DeleteNode.BSTKV_AVL;
import Chapter13ReadBlackTree.Section5to7AddNode.BSTKV_RBTree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Main3 {
    public static void main(String[] args) {
        int N = 20000000;
        Random random = new Random();
        List<Integer> testData = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            testData.add(random.nextInt(Integer.MAX_VALUE));
        }
        // 有序的大量数据最能测试性能
        Collections.sort(testData);

        // 2.经过旋转后的BST即平衡二叉树AVL
        long startTime = System.nanoTime();
        BSTKV_AVL<Integer, Integer> avl = new BSTKV_AVL<>();
        for (Integer x : testData) {
            avl.add(x, null);
        }
        long endTime = System.nanoTime();
        System.out.println("经过旋转后的BST即平衡二叉树AVL耗时：" + (endTime - startTime) / 1000000000.0 + "s");

        // 3.保持绝对黑平衡的BST即红黑树的测试
        startTime = System.nanoTime();
        BSTKV_RBTree<Integer, Integer> rbTree = new BSTKV_RBTree<>();
        for (Integer x : testData) {
            rbTree.add(x, null);
        }
        endTime = System.nanoTime();
        System.out.println("保持绝对黑平衡的BST即红黑树耗时：" + (endTime - startTime) / 1000000000.0 + "s");
    }
}
/**
 * 经过旋转后的BST即平衡二叉树AVL耗时：10.5240096s
 * 保持绝对黑平衡的BST即红黑树耗时：6.2289279s
 */
