/***********************************************************
 * @Description : 测试大量的插入操作耗时
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/5 16:20
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter13ReadBlackTree.Section8PerformanceTest;

import Chapter06BST.BSTKV;
import Chapter12AVLTree.Section7DeleteNode.BSTKV_AVL;
import Chapter13ReadBlackTree.Section5to7AddNode.BSTKV_RBTree;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main2 {
    public static void main(String[] args) {
        int N = 20000000;
        Random random = new Random();
        List<Integer> testData = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            testData.add(random.nextInt(Integer.MAX_VALUE));
        }

        // 1.二分搜索树BST测试
        long startTime = System.nanoTime();
        BSTKV<Integer, Integer> bstkv = new BSTKV<>();
        for (Integer x : testData) {
            bstkv.add(x, null);
        }
        long endTime = System.nanoTime();
        System.out.println("未经旋转前的BST耗时：" + (endTime - startTime) / 1000000000.0 + "s");

        // 2.经过旋转后的BST即平衡二叉树AVL
        startTime = System.nanoTime();
        BSTKV_AVL<Integer, Integer> avl = new BSTKV_AVL<>();
        for (Integer x : testData) {
            avl.add(x, null);
        }
        endTime = System.nanoTime();
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
 * 未经旋转前的BST耗时：41.4811316s
 * 经过旋转后的BST即平衡二叉树AVL耗时：37.4551739s
 * 保持绝对黑平衡的BST即红黑树耗时：33.9333229s
 */
