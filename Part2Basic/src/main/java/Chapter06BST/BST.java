/***********************************************************
 * @Description : 二分搜索树的实现，本章所有节的知识点汇总起来地最佳实现
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2019/12/29 19:45
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter06BST;

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

    /**
     * 寻找以node作为跟节点的二分搜索树的最大节点
     *
     * @param node 根节点
     */
    private Node maximum(Node node) {
        if (node.right == null) {
            return node;
        }
        return maximum(node.right);
    }

    /**
     * 寻找二分搜索树的最大元素
     */
    public E maximum() {
        if (size == 0) {
            throw new IllegalArgumentException("BST为空！无法找最小值");
        }
        return maximum(root).e;
    }

    /**
     * 从二分搜索树种删除最小元素并返回
     *
     * @return 删除的最小元素
     */
    public E removeMin() {
        // 这里先记录下最小值，防止下面removeMin()后树结构变了
        E min = minimum();
        // 这个递归函数返回地是新子树的根节点，不是我们要找地最小值
        root = removeMin(root);
        // 直接用我们前面找到的最小元素来返回
        return min;
    }

    /**
     * 删除以node作为根节点的二分搜索树中的最小节点，并返回删除节点后的新的二分搜索树的根节点
     *
     * @param node 根节点
     */
    private Node removeMin(Node node) {
        // 递归终止条件
        if (node.left == null) {
            // 递归遍历到左子树为空，说明找到了最小节点node
            // node.right是否为空都可以正常返回给上一级的父节点来设置父节点的左节点直接指向当前节点的右子树
            Node rightNode = node.right;
            node.right = null;
            size--;
            return rightNode;
        }

        // 递归组成逻辑
        // 当左节点不是null时就正常往下递归，返回当前节点给上一层节点设置下自己的左节点
        node.left = removeMin(node.left);
        return node;
    }

    /**
     * 从二分搜索树种删除最大元素并返回
     *
     * @return 删除的最大元素
     */
    public E removeMax() {
        // 这里先记录下最大值，removeMax()后树结构变了
        E max = maximum();
        // 这个递归函数返回地是新子树的根节点，不是我们要找地最大值
        root = removeMax(root);
        // 直接用我们前面找到的最大元素来返回
        return max;
    }

    /**
     * 删除以node作为根节点的二分搜索树中的最大节点，并返回删除节点后的新的二分搜索树的根节点
     *
     * @param node 根节点
     */
    private Node removeMax(Node node) {
        // 递归终止条件
        if (node.right == null) {
            // 递归遍历到右子树为空，说明找到了最大节点node
            // node.left是否为空都可以正常返回给上一级的父节点来设置父节点的右节点直接指向当前节点的左子树
            Node leftNode = node.left;
            node.left = null;
            size--;
            return leftNode;
        }

        // 递归组成逻辑
        // 当右节点不是null时就正常往下递归，返回当前节点给上一层节点设置下自己的右节点
        node.right = removeMax(node.right);
        return node;
    }

    /**
     * 删除指定值的结点
     *
     * @param e 节点的值
     */
    public void remove(E e) {
        root = remove(root, e);
    }

    /**
     * 删除
     *
     * @param node 二分搜索树的根节点
     * @param e    待删除节点的值
     * @return 要挂载到当前节点父节点的子树
     */
    private Node remove(Node node, E e) {
        // 递归终止条件
        if (node == null) {
            return null;
        }

        // 递归组成逻辑
        // 还没找到就接着往下找
        if (e.compareTo(node.e) < 0) {
            // 要找的值比当前节点小，向左递归
            node.left = remove(node.left, e);
            return node;
        } else if (e.compareTo(node.e) > 0) {
            // 要找的值比当前节点大，向右递归
            node.right = remove(node.right, e);
            return node;
        } else {
            // node.e == e 找到相等的节点了，下面删除指定值的节点
            if (node.left == null) {
                Node rightNode = node.right;
                // 释放引用
                node.right = null;
                size--;
                // 左节点为空，把node的右子树挂接到node的父亲节点即可
                return rightNode;
            }
            if (node.right == null) {
                Node leftNode = node.left;
                // 释放引用
                node.left = null;
                size--;
                // 右节点为空，把node的左子树挂接到node的父亲节点即可
                return leftNode;
            }
            // node的左右子树都不为空，就找node的右子树的最小值来代替node
            Node minimumRight = minimum(node.right);
            // 警告：下面两行代码一定不要颠倒，一定要先设置right再设置left，否则会出现迭代引用！因为下面那行改变了node.right的结构。参考问题:https://coding.imooc.com/learn/questiondetail/143936.html
            // 选出node右子树最小元素来代替node，那么右子树最小元素就要从原来位置删掉
            minimumRight.right = removeMin(node.right);
            // 替换当前节点node的左右子树
            minimumRight.left = node.left;
            // 释放node的引用
            node.left = node.right = null;
            // 返回给上一级来设置父节点
            return minimumRight;
        }
    }
}
