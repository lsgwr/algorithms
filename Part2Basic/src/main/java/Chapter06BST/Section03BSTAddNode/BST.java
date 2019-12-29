/***********************************************************
 * @Description : 二分搜索树的实现，本章所有节的知识点汇总起来地最佳实现
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2019/12/29 19:45
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter06BST.Section03BSTAddNode;

/**
 * @param <E> E表示Element，继承Comparable使得二叉树的元素是可比较的
 * @author liangshanguang
 */
public class BST<E extends Comparable<E>> {
    /**
     * 二分搜索树每个节点的封装类
     */
    private class Node {
        /**
         * 当前节点
         */
        public E e;
        /**
         * 节点e的左子节点和右子节点
         */
        private Node left, right;

        public Node(E e) {
            this.e = e;
            this.left = null;
            this.right = null;
        }
    }

    /**
     * 二分搜索树的根节点
     */
    private Node root;

    /**
     * 二分搜索树的节点个数
     */
    private int size;

    public BST() {
        this.root = null;
        this.size = 0;
    }

    /**
     * 获取二分搜索树的节点总数
     */
    public int getSize() {
        return this.size;
    }

    /**
     * 判断二分搜索树是否为空
     *
     * @return 通过节点总数是否为0来进行判断
     */
    public boolean isEmpty() {
        return this.size == 0;
    }
}
