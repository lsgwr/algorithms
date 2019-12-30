/***********************************************************
 * @Description : 二分搜索树的实现，本章所有节的知识点汇总起来地最佳实现
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2019/12/29 19:45
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter06BST;

import Chapter03StackAndQueues.Section5ArrayQueue.ArrayQueue;

import java.util.ArrayDeque;
import java.util.Queue;

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
        // 递归终止条件
        if (node == null) {
            // 只要碰到了为空的node，就一定要把我们的e作为节点添加到这里的，具体是作为左子树、右子树还是根节点到下面再进行设置
            size++;
            return new Node(e);
        }

        // 递归组成逻辑
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

    /**
     * 在以节点node为根节点的二分搜索树树中查找是否包含元素e
     */
    private boolean contains(Node node, E e) {
        // 递归终止条件
        if (node == null) {
            // 遍历到到二叉树最底部了，还没找到，二分搜索树中肯定没有这个这个元素了
            return false;
        }

        // 递归组成逻辑
        if (e.compareTo(node.e) < 0) {
            // e小于当前节点，向左递归
            return contains(node.left, e);
        } else if (e.compareTo(node.e) > 0) {
            // e大于当前节点，向右递归
            return contains(node.right, e);
        } else {
            // e等于当前节点，返回true
            return true;
        }
    }

    /**
     * 看二分搜索树种是否包含元素e
     */
    public boolean contains(E e) {
        return contains(root, e);
    }

    /**
     * 前序遍历以node作为根节点的二分搜索树
     */
    private void preOrder(Node node) {
        // 递归终止条件
        if (node == null) {
            // 遍历到null节点就返回上一层递归
            return;
        }

        // 递归组成逻辑
        // 1.访问当前节点。需要存储时可以放到list中
        System.out.print(node.e + " ");
        // 2.遍历左子树
        preOrder(node.left);
        // 3.遍历右子树
        preOrder(node.right);
    }

    /**
     * 前序遍历
     */
    public void preOrder() {
        preOrder(root);
    }

    /**
     * 中序遍历以node作为根节点的二分搜索树
     */
    private void inOrder(Node node) {
        // 递归终止条件
        if (node == null) {
            // 遍历到null节点就返回上一层递归
            return;
        }

        // 递归组成逻辑
        // 2.遍历左子树
        inOrder(node.left);
        // 1.访问当前节点。需要存储时可以放到list中
        System.out.print(node.e + " ");
        // 3.遍历右子树
        inOrder(node.right);
    }

    /**
     * 中序遍历
     */
    public void inOrder() {
        inOrder(root);
    }

    /**
     * 后序遍历以node作为根节点的二分搜索树
     */
    private void postOrder(Node node) {
        // 递归终止条件
        if (node == null) {
            // 遍历到null节点就返回上一层递归
            return;
        }

        // 递归组成逻辑
        // 2.遍历左子树
        postOrder(node.left);
        // 3.遍历右子树
        postOrder(node.right);
        // 1.访问当前节点。需要存储时可以放到list中
        System.out.print(node.e + " ");
    }

    /**
     * 后序遍历
     */
    public void postOrder() {
        postOrder(root);
    }

    /**
     * 层序遍历
     */
    public void levelOrder() {
        levelOrder(root);
    }

    /**
     * 层序遍历以node作为根节点的二分搜索树
     *
     * @param root 二分搜索树的根节点
     */
    private void levelOrder(Node root) {
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node node = queue.remove();
            // 每次弹出一个元素就打印下
            System.out.print(node.e + " ");
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }
    }

    /**
     * 寻找以node作为跟节点的二分搜索树的最小节点
     *
     * @param node 根节点
     */
    private Node minimum(Node node) {
        if (node.left == null) {
            return node;
        }
        return minimum(node.left);
    }

    /**
     * 寻找二分搜索树的最小元素
     */
    public E minimum() {
        if (size == 0) {
            throw new IllegalArgumentException("BST为空！无法找最小值");
        }
        return minimum(root).e;
    }


}
