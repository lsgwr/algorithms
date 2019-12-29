/***********************************************************
 * @Description : 二叉树基本方法的测试工具类
 *         /////////////////
 *         //      5      //
 *         //    /   \    //
 *         //   3    6    //
 *         //  / \    \   //
 *         // 2  4     8  //
 *         /////////////////
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2019/12/30 0:37
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter06BST;

public class Main {
    public static void main(String[] args) {
        BST<Integer> bst = new BST<>();
        int[] nums = {5, 3, 6, 8, 4, 2};
        for (int num : nums) {
            bst.add(num);
        }
        System.out.print("前序遍历的结果为：");
        bst.preOrder();
        System.out.println();
    }
}
/**
 * 前序遍历的结果为：5 3 2 4 6 8
 */