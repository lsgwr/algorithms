/***********************************************************
 * @Description : 二分搜索树的实现，本章所有节的知识点汇总起来地最佳实现
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2019/12/29 19:45
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter06BST;

/**
 * @param <E> E表示Element，继承Comparable使得二叉树的元素是可比较的，可以用compareTo()方法进行比较
 * @author liangshanguang
 */
public class BST<E extends Comparable<E>> {
    /**
     * 二分搜索树每个节点的封装类。Node是BST的内部类，所以属性可以设置为public
     */
    private class Node {
        /**
         * 当前节点
         */
        public E e;
        /**
         * 节点e的左子节点和右子节点
         */
        public Node left, right;

        public Node(E e) {
            this.e = e;
            left = null;
            right = null;
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
        root = null;
        size = 0;
    }

    /**
     * 获取二分搜索树的节点总数
     */
    public int getSize() {
        return size;
    }

    /**
     * 判断二分搜索树是否为空
     *
     * @return 通过节点总数是否为0来进行判断
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 向以节点Node为根节点的二分搜索树树中添加新的元素e，递归实现
     *
     * @param node 二分搜索树的根节点
     * @param e    要加入地元素e
     */
    private Node add(Node node, E e) {
        // 只要碰到了为空的node，就一定要把我们的e作为节点添加到这里的，具体是作为左子树、右子树还是根节点到下面再进行设置
        if (node == null) {
            return new Node(e);
        }
        if (e.compareTo(node.e) < 0) {
            // e小于根节点，往node的左子树继续遍历
            node.left = add(node.left, e);
        } else if (e.compareTo(node.e) > 0) {
            // e大于根节点，往node的右子树继续遍历
            node.right = add(node.right, e);
        }
        // 如果和遍历到的节点相等即e.compareTo(node.e)==0则直接跳过，不做任何处理，因为我们实现的二分搜索树不允许有重复元素。

        // 当这个node是把e给new出来地就设置到子节点为空的上面去；如果不是new出来地相当于把已有的二分搜索树中的节点关系又设置一次
        return node;
    }

    /**
     * 向二分搜索树中添加元素e
     */
    public void add(E e) {
        root = add(root, e);
    }


}
