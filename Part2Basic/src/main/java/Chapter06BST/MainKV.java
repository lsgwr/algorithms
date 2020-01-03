/***********************************************************
 * @Description : 支持键值对的二分搜索树树基本方法的测试工具类
 *         /////////////////
 *         //      5      //
 *         //    /   \    //
 *         //   3    6    //
 *         //  / \    \   //
 *         // 2  4     8  //
 *         /////////////////
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/3 20:21
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter06BST;

public class MainKV {
    public static void main(String[] args) {
        BSTKV<Integer, String> bst = new BSTKV<>();
        int[] nums = {5, 3, 6, 8, 4, 2};
        for (int num : nums) {
            bst.add(num, num + "");
        }
        // 1.前序遍历测试
        System.out.print("前序遍历的结果为：");
        bst.preOrder();
        System.out.println();

        // 2.中序遍历测试
        System.out.print("中序遍历的结果为：");
        bst.inOrder();
        System.out.println();

        // 3.后序遍历测试
        System.out.print("后序遍历的结果为：");
        bst.postOrder();
        System.out.println();

        // 4.层序遍历测试
        System.out.print("层序遍历的结果为：");
        bst.levelOrder();
        System.out.println();

        // 5.查找二叉树的最小值
        System.out.print("二分搜索树的最小值为：");
        System.out.println(bst.minimum());

        // 6.查找二叉树的最大值
        System.out.print("二分搜索树的最大值为：");
        System.out.println(bst.maximum());

        // 7.刪除最小元素，可以debug看树的新结构，不断删除最小元素就实现了升序排列
        System.out.print("刪除最小元素：");
        System.out.println(bst.removeMin());

        // 8.删除最大元素，可以debug看树的新结构，不断删除最大元素就实现了降序排列
        System.out.print("刪除最大元素：");
        System.out.println(bst.removeMax());

        // 9.删除元素5，只剩6->3->4，6是根节点，3是6的左节点，4是3的右节点
        System.out.print("刪除元素5后：");
        bst.remove(5);
        bst.preOrder();
        System.out.println();

        // 10.获取元素
        System.out.println("key=6对应的val=" + bst.get(6));
        // 11.更新元素
        bst.set(6, "666");
        System.out.println("key=6对应的val=" + bst.get(6));
    }
}
/**
 * 前序遍历的结果为：5:5 3:3 2:2 4:4 6:6 8:8
 * 中序遍历的结果为：2:2 3:3 4:4 5:5 6:6 8:8
 * 后序遍历的结果为：2:2 4:4 3:3 8:8 6:6 5:5
 * 层序遍历的结果为：5:5 3:3 6:6 2:2 4:4 8:8
 * 二分搜索树的最小值为：Node{key=2, val=2}
 * 二分搜索树的最大值为：Node{key=8, val=8}
 * 刪除最小元素：Node{key=2, val=2}
 * 刪除最大元素：Node{key=8, val=8}
 * 刪除元素5后：6:6 3:3 4:4
 * key=6对应的val=6
 * key=6对应的val=666
 */