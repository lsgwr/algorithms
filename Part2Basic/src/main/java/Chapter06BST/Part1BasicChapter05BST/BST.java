/***********************************************************
 * @Description : 本章和《程序猿的内功修炼，学好算法与数据结构》
 *                https://coding.imooc.com/learn/list/71.html
 *                第5章的前8节完全重复，此处直接那里代码拿过来复用。
 *                不在做本章的课了。代码来源链接：https://github.com/19920625lsg/liuyubobobo-algorithms/blob/master/Part1Basic/JAVA/src/main/java/Chapter5BinarySearchTree/Section8DeleteNode/BST.java
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/5/16 22:19
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter06BST.Part1BasicChapter05BST;

// package Chapter5BinarySearchTree.Section8DeleteNode;

import java.util.LinkedList;

/**
 * @param <Key>   作为树的索引关键词，必须是可比较地，所以需要继承Comparable
 * @param <Value> 不需要可比较
 */
public class BST<Key extends Comparable<Key>, Value> {

    /**
     * 节点类
     */
    private class Node {
        Key key;
        Value value;
        /**
         * 左子节点
         */
        Node left;
        /**
         * 右子节点
         */
        Node right;

        /**
         * 构造函数
         */
        Node(Key key, Value value) {
            // key是唯一地，不能重复
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
        }

        public Node(Node node) {
            // key是唯一地，不能重复
            this.key = node.key;
            this.value = node.value;
            this.left = node.left;
            this.right = node.right;
        }
    }

    /**
     * 根节点
     */
    private Node root;
    /**
     * 节点数
     */
    private int count;

    public BST() {
        root = null;
        count = 0;
    }


    public int size() {
        return count;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public void insert(Key key, Value value) {
        root = insert(root, key, value);
    }

    public boolean contain(Key key) {
        return contain(root, key);
    }

    public Value search(Key key) {
        return search(root, key);
    }


    /**
     * 寻找最小的键值
     */
    public Key min() {
        assert (count != 0);
        Node minNode = min(root);
        return minNode.key;
    }

    /**
     * 寻找最大的键值
     */
    public Key max() {
        assert (count != 0);
        Node maxNode = max(root);
        return maxNode.key;
    }

    /**
     * 删除最小值对应的节点
     */
    public void deleteMin() {
        if (root != null) {
            root = deleteMin(root);
        }
    }

    /**
     * 删除最大值对应的节点
     */
    public void deleteMax() {
        if (root != null) {
            root = deleteMax(root);
        }
    }

    /**
     * 从二叉树中删除键值为key的节点
     */
    public void deleteNode(Key key) {
        root = deleteNode(root, key);
    }
    //***************************************************
    //* 二分搜索树的辅助函数
    //**************************************************

    /**
     * 向以node为根节点的二叉树搜索树种，插入节点(key,value)
     * 返回插入新节点后的二叉搜索树的根
     */
    private Node insert(Node node, Key key, Value value) {
        // 处理最基本事件，即可以退出递归的事件
        if (node == null) {
            // 递归到没有子树了，就在这个位置插入。
            count++; // 元素数先+1
            // 把当前要插入的节点作为根节点返回，一层层递归后会跟新树路径上的节点关系地
            return new Node(key, value);
        }
        if (key.compareTo(node.key) < 0) {
            // 小于根节点的键，往左边子树插入
            node.left = insert(node.left, key, value);
        } else if (key.compareTo(node.key) > 0) {
            // 大于根节点的键，往右边子树插入
            node.right = insert(node.right, key, value);
        } else {
            // 如果key相等就直接更新(注意二叉搜索树的键是唯一地)
            node.value = value;
        }

        return node;
    }

    /**
     * 被递归调用来判断是否包含指定key的函数
     */
    private boolean contain(Node node, Key key) {
        // 递归退出条件.最后到达树底，下面没节点了(NULL),返回false
        if (node == null) {
            return false;
        }
        if (key.compareTo(node.key) > 0) {
            // 大于就在右边找
            return contain(node.right, key);
        } else if (key.compareTo(node.key) < 0) {
            // 小于就在左边找
            return contain(node.left, key);
        } else {
            // 等于就返回true,代表找到含有这个key地节点了
            return true;
        }
    }

    /**
     * 在以node为根的二叉搜索树种查找key对应的value
     */
    private Value search(Node node, Key key) {
        // 递归退出条件.最后到达树底，返回NULL
        if (node == null) {
            return null;
        }
        if (key.compareTo(node.key) > 0) {
            // 大于就在右边找
            return search(node.right, key);
        } else if (key.compareTo(node.key) < 0) {
            // 小于就在左边找
            return search(node.left, key);
        } else {
            // 等于就返回true,代表找到含有这个key地节点了,返回Value的地址
            return node.value;
        }
    }

    /*****************************************************************************
     * 前、中、后序遍历的私有函数：前中后序遍历的差别在于访问当前节点是在一开始、中间还是最后
     * ***************************************************************************/

    /**
     * 前序遍历
     */
    public void preOrder() {
        preOrder(root);
    }

    /**
     * 中序遍历
     */
    public void inOrder() {
        inOrder(root);
    }

    /**
     * 后序遍历
     */
    public void postOrder() {
        postOrder(root);
    }

    /**
     * 层序遍历(Level Order),又称广度优先遍历
     */
    public void levelOrder() {
        LinkedList<Node> queue = new LinkedList<>();
        // 先推入根节点
        queue.push(root);
        while (!queue.isEmpty()) {
            Node node = queue.remove();
            System.out.println(node.key);
            if (node.left != null) {
                queue.push(node.left);
            }
            if (node.right != null) {
                queue.push(node.right);
            }
        }
    }


    /*****************************************************************************
     * 前、中、后序遍历的私有辅助函数：前中后序遍历的差别在于访问当前节点是在一开始、中间还是最后
     * ***************************************************************************/

    /**
     * 对以node为根的二叉搜索树进行前序遍历
     */
    private void preOrder(Node node) {
        if (node != null) {
            System.out.println(node.key);
            preOrder(node.left);
            preOrder(node.right);
        }
    }


    /**
     * 对以node为根的二叉搜索树进行中序遍历
     */
    private void inOrder(Node node) {
        if (node != null) {
            inOrder(node.left);
            System.out.println(node.key);
            inOrder(node.right);
        }
    }

    /**
     * 对以node为根的二叉搜索树进行后序遍历
     */
    private void postOrder(Node node) {
        if (node != null) {
            postOrder(node.left);
            postOrder(node.right);
            System.out.println(node.key);
        }
    }

    /**
     * 在以node为根的二叉搜索树中，返回最小键值的点
     */
    private Node min(Node node) {
        // 没有左子节点的时候就到了最小点了
        if (node.left == null) {
            return node;
        }
        return min(node.left);
    }

    /**
     * 在以node为根的二叉搜索树中，返回最大键值的点
     */
    private Node max(Node node) {
        // 没有左子节点的时候就到了最小点了
        if (node.right == null) {
            return node;
        }
        return max(node.right);
    }

    /**
     * 删除以Node为根节点的子树中的最小值
     */
    private Node deleteMin(Node node) {
        if (node.left == null) {
            // 没有左节点了，就要看看右节点是否存在
            Node rightNode = node.right;
            // 删除最小节点node
            node.right = null;
            count--;
            // 不管rightNode是否为空，都可以直接返回地
            return rightNode;
        }
        // 好好体验递归
        node.left = deleteMin(node.left);
        return node;
    }

    /**
     * 删除以Node为根节点的子树中的最大值
     */
    private Node deleteMax(Node node) {
        if (node.right == null) {
            // 没有右节点了，就要看看左节点是否存在
            Node leftNode = node.left;
            // 删除最小节点node
            node.left = null;
            count--;
            // 不管rightNode是否为空，都可以直接返回地
            return leftNode;
        }
        // 好好体验递归
        node.right = deleteMax(node.right);
        return node;
    }


    /**
     * 删除指定节点作为树的根的二叉搜索树中键值为Key的节点
     * 返回删除节点后的新的二分搜索树的根。
     * 删除最小节点后的替换值实际从删除节点的右子树中找最小节点即可
     */
    private Node deleteNode(Node node, Key key) {
        // 到节点为NULL时，就可以退出了
        if (node == null) {
            return null;
        }
        if (key.compareTo(node.key) < 0) {
            node.left = deleteNode(node.left, key);
            return node;
        } else if (key.compareTo(node.key) > 0) {
            node.right = deleteNode(node.right, key);
            return node;
        } else {
            // 关键之处，循环到某阶段key = node->key了，最为复杂
            if (node.left == null) {
                // 左子树为空，看右子树，删除节点后，所有右子树节点前提一位
                Node rightNode = node.right;
                count--;
                // 右子树也为空的话也不影响，会返回NULL，不影响地
                return rightNode;
            }
            if (node.right == null) {
                // 右子树为空，看左子树，删除节点后，所有左子树节点前提一位
                Node leftNode = node.left;
                count--;
                return leftNode;
            }

            // 左右子树都不为空，找右子树的最小节点即可，然后把左右子树更新一下
            // 找到右子树的最小值.注意要新建一个Node,不然下面的
            Node successor = new Node(min(node.right));
            // 这里++，在下面的deleteMin会--地
            count++;
            // 更新右子树指针,注意deleteMin返回的node->right作为根节点的树的最小值的父亲节点
            successor.right = deleteMin(node.right);
            successor.left = node.left;
            // 删除指定节点
            count--;
            // 返回新的二叉搜索树的根节点
            return successor;
        }
    }


    /**
     * 打乱数组顺序
     */
    public static void shuffle(Object[] arr) {

        for (int i = arr.length - 1; i >= 0; i--) {
            int pos = (int) (Math.random() * (i + 1));
            Object t = arr[pos];
            arr[pos] = arr[i];
            arr[i] = t;
        }
    }

    public static void main(String[] args) {
        BST<Integer, Integer> bst = new BST<>();

        // 取n个取值范围在[0...m)的随机整数放进二分搜索树中
        int N = 10;
        int M = 100;
        for (int i = 0; i < N; i++) {
            Integer key = (int) (Math.random() * M);
            // 为了后续测试方便,这里value值取和key值一样
            bst.insert(key, key);
            System.out.print(key + " ");
        }
        System.out.println();

        // 测试二分搜索树的size()
        System.out.println("size: " + bst.size());
        System.out.println();

        // 测试二分搜索树的前序遍历 preOrder
        System.out.println("preOrder: ");
        bst.preOrder();
        System.out.println();

        // 测试二分搜索树的中序遍历 inOrder 中序排序可用于排序(升序)
        System.out.println("inOrder: ");
        bst.inOrder();
        System.out.println();

        // 测试二分搜索树的后序遍历 postOrder  后序遍历可用于释放二叉搜索树
        System.out.println("postOrder: ");
        bst.postOrder();
        System.out.println();

        // 层序遍历
        System.out.println("levelOrder: ");
        bst.levelOrder();
        System.out.println();

        // 测试 removeMin
        // 输出的元素应该是从小到大排列的
        System.out.println("Test removeMin: ");
        while (!bst.isEmpty()) {
            System.out.print("min: " + bst.min() + " , ");
            bst.deleteMin();
            System.out.println("After removeMin, size = " + bst.size());
        }
        System.out.println();


        for (int i = 0; i < N; i++) {
            Integer key = (int) (Math.random() * M);
            // 为了后续测试方便,这里value值取和key值一样
            bst.insert(key, key);
        }
        // 注意, 由于随机生成的数据有重复, 所以bst中的数据数量大概率是小于n的

        // 测试 removeMax
        // 输出的元素应该是从大到小排列的
        System.out.println("Test removeMax: ");
        while (!bst.isEmpty()) {
            System.out.print("max: " + bst.max() + " , ");
            bst.deleteMax();
            System.out.println("After removeMax, size = " + bst.size());
        }

        // order数组中存放[0...n)的所有元素
        Integer order[] = new Integer[N];
        for (int i = 0; i < N; i++) {
            order[i] = i;
        }

        for (int i = 0; i < N; i++) {
            Integer key = (int) (Math.random() * N);
            // 为了后续测试方便,这里value值取和key值一样.
            // 注意这里因为key是随机产生地，所以可能会出现key相同覆盖的情况,
            // 因此所有的键合并起来一定是order数组的子集，所以下面的删除最后一定是0
            bst.insert(key, key);
        }
        // 打乱order数组的顺序
        shuffle(order);
        System.out.println("Test delete: ");
        System.out.println(bst.size());
        // 乱序删除[0...n)范围里的所有元素
        for (int i = 0; i < N; i++) {
            if (bst.contain(order[i])) {
                bst.deleteNode(order[i]);
                System.out.println("After remove " + order[i] + " size = " + bst.size());
            }
        }

        // 最终整个二分搜索树应该为空
        System.out.println(bst.size());
    }
}
