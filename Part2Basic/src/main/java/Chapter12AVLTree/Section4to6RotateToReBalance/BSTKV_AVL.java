/***********************************************************
 * @Description : 基于第6章“支持键值对的二分搜索树BSTKV.java”实现地二分平衡树
 * 6.2 实现了基本的节点高度和节点平衡因子的检测
 * 6.3 实现是否是BST和是否是平衡二叉树的判断函数
 * 6.4~6.6 新加入的节点会破坏6.3中的二叉树的性质，所以我们本部分通过各种旋转实现二叉树的再平衡
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/3 23:12
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter12AVLTree.Section4to6RotateToReBalance;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * @param <K> Key表示键值对的键，继承Comparable使得二叉树的元素是可比较的，可以用compareTo()方法进行比较。
 * @param <V> 即Val，是键值对的值即value
 * @author liangshanguang
 */
public class BSTKV_AVL<K extends Comparable<K>, V> {
    /**
     * 二分搜索树每个节点的封装类。Node是BST的内部类，所以属性可以设置为public
     */
    private class Node {
        /**
         * 当前节点的键
         */
        public K key;

        public V val;
        /**
         * 节点e的左子节点和右子节点
         */
        public Node left, right;

        /**
         * 节点的高度值
         */
        public int height;

        public Node(K key, V val) {
            this.key = key;
            this.val = val;
            left = null;
            right = null;
            // 初始当前节点还没被加入BST中，我们认为是叶子节点，高度认为是1
            // 后面添加节点时再更新height
            height = 1;
        }

        public Node(K key, V val, int height) {
            this.key = key;
            this.val = val;
            this.height = height;
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

    public BSTKV_AVL() {
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
     * 判断当前的二叉树是否仍然是一棵二分搜索树BST
     */
    public boolean isBST() {
        List<K> keys = new ArrayList<>();
        inOrder(root, keys);
        // BST的中序遍历结果的一个特殊性质就是遍历结果是升序的
        for (int i = 1; i < keys.size(); i++) {
            if (keys.get(i - 1).compareTo(keys.get(i)) > 0) {
                // 升序表明前面的节点应该小于后面的节点，当前面的节点大于后面的节点时，就说明二叉树不时BST的
                return false;
            }
        }
        return true;
    }

    /**
     * 判断当前的二叉树是否是平衡二叉树，每个节点的平衡因子balance值的绝对值不能大于1
     */
    public boolean isBalanced() {
        return isBalanced(root);
    }

    /**
     * 遍历当前二叉树的所有节点，看其balance值的绝对值是否大于1
     *
     * @param node 当前遍历到的子树的根节点
     * @return 是否是平衡二叉树
     */
    private boolean isBalanced(Node node) {
        // 1.递归终止条件
        if (node == null) {
            // 递归到底了，空子树可以看做是平衡二叉树
            return true;
        }
        if (Math.abs(calBalance(node)) > 1) {
            return false;
        }

        // 2.递归具体逻辑
        // 左右子树递归进行遍历，两个都为平衡二叉树，整体的二叉树才是平衡二叉树
        return isBalanced(node.left) && isBalanced(node.right);
    }

    /**
     * 获取指定节点的高度值
     *
     * @param node 要查询高度的节点
     * @return node节点的高度
     */
    private int getHeight(Node node) {
        if (node == null) {
            // 空间点的高度值我们认为是0
            return 0;
        }
        return node.height;
    }

    /**
     * 计算节点的高度
     * 节点的高度值= max(左子树高度值，右子树高度值) + 1
     *
     * @param node 当前高度
     * @return 根据左右子树计算得到的高度
     */
    private int calHeight(Node node) {
        // 不需要考虑node为null的情况，因为getHeight()已经考虑到了，node子节点为空直接返回0，不影响下面的计算
        return Math.max(getHeight(node.left), getHeight(node.right)) + 1;
    }

    /**
     * 计算节点node的平衡因子，即当前节点左子树高度减去右子树高度的差值
     *
     * @param node 当前节点
     * @return 平衡因子
     */
    private int calBalance(Node node) {
        // 不需要考虑node为null的情况，因为getHeight()已经考虑到了，node子节点为空直接返回0，不影响下面的计算
        return getHeight(node.left) - getHeight(node.right);
    }

    /**
     * 旋转情形1：不平衡发生在节点y左侧的左侧，需要进行右旋转
     * 向右旋转的核心代码：x.right = y; y.left = T3
     * // 对节点y进行向右旋转操作，返回旋转后新的根节点x
     * //        y                              x
     * //       / \                           /   \
     * //      x   T4     向右旋转 (y)        z     y
     * //     / \       - - - - - - - ->    / \   / \
     * //    z   T3                       T1  T2 T3 T4
     * //   / \
     * // T1   T2
     *
     * @param y 二叉树中首个发现平衡因子大于1的节点
     * @return 旋转后新的根节点x
     */
    private Node rotateRight(Node y) {
        Node x = y.left;
        Node T3 = x.right;
        // 右旋转的核心
        x.right = y;
        y.left = T3;
        // 更新节点的Height，从上面注释的图可以看到z及其子树不用更新，T3和T4也不需要，只需要更新y和x即可
        y.height = calHeight(y);
        x.height = calHeight(x);
        return x;
    }

    /**
     * 旋转情形2：不平衡发生在节点y右侧的右侧，需要进行左旋转
     * 向右旋转的核心代码：x.left = y; y.right = T3
     * // 对节点y进行向左旋转操作，返回旋转后新的根节点x
     * //    y                             x
     * //  /  \                          /   \
     * // T4   x      向左旋转 (y)       y     z
     * //     / \   - - - - - - - ->   / \   / \
     * //   T3  z                     T4 T3 T1 T2
     * //      / \
     * //     T1 T2
     *
     * @param y 二叉树中首个发现平衡因子小于-1的节点
     * @return 旋转后新的根节点x
     */
    private Node rotateLeft(Node y) {
        Node x = y.right;
        Node T3 = x.left;
        // 坐旋转过程
        x.left = y;
        y.right = T3;
        // 更新height
        y.height = calHeight(y);
        x.height = calHeight(x);
        return x;
    }

    /**
     * 根绝当前节点的平衡因子判断是否需要进行旋转
     *
     * @param node 当前节点
     * @return 旋转后新的根节点
     */
    private Node rotateToReBalance(Node node) {
        if (node == null) {
            return null;
        }
        // 更新节点的height，这个无比要做，要不会下面的平衡操作都是白搭~~！！！
        // 更新当前节点和其往上节点的高度。平衡二叉树某个节点的高度值=max(左子树高度值，右子树高度值) + 1
        // +1时因为父亲节点比子节点高一层。叶子节点的高度值认为是1，左右子树为空高度认为是0
        node.height = calHeight(node);
        // 获取节点的平衡因子，即node节点的左右子树的高度差的。子树为空平衡因子认为是0，即balance=左子树高度-右子树高度值
        int balance = calBalance(node);
        // 下面这3行可以打开用于观察旋转过程中平衡因子的变化
        // if (Math.abs(balance) > 1) {
        //     System.out.println(balance);
        // }
        // 旋转情形1：node左子树的左侧添加的节点导致node点不再平衡。两个左所以叫LL
        if (balance > 1 && calBalance(node.left) >= 0) {
            // 旋转后返回给上一层新的根节点，上面失衡的节点会继续按照旋转的流程使自己再次平衡，直到递归结束，整个二叉树也就再次平衡了
            return rotateRight(node);
        }
        // 旋转情形2：node右子树的右侧添加的节点导致node点不再平衡。两个右所以叫RR
        if (balance < -1 && calBalance(node.right) <= 0) {
            // 旋转后返回给上一层新的根节点，上面失衡的节点会继续按照旋转的流程使自己再次平衡，直到递归结束，整个二叉树也就再次平衡了
            return rotateLeft(node);
        }
        // 旋转情形3：node左子树的右侧添加的节点导致node点不再平衡。先左后右所以叫LR
        if (balance > 1 && calBalance(node.left) < 0) {
            // 旋转后返回给上一层新的根节点，上面失衡的节点会继续按照旋转的流程使自己再次平衡，直到递归结束，整个二叉树也就再次平衡了
            // 先对y的左孩子x执行一次左旋转，把问题转换成LL。node.left变成旋转后新的节点
            node.left = rotateLeft(node.left);
            // LL问题需要再对新的树执行一次右旋转
            return rotateRight(node);
        }
        // 旋转情形4：node右子树的左侧添加的节点导致node点不再平衡。先右后左所以叫RL
        if (balance < -1 && calBalance(node.right) > 0) {
            // 旋转后返回给上一层新的根节点，上面失衡的节点会继续按照旋转的流程使自己再次平衡，直到递归结束，整个二叉树也就再次平衡了
            // 先对y的右孩子x执行一次右旋转，把问题转换成RR。node.right变成旋转后新的节点
            node.right = rotateRight(node.right);
            // RR问题需要再对新的树执行一次左旋转
            return rotateLeft(node);
        }
        // 不需要维护平衡，直接返回原节点
        return node;
    }

    /**
     * 向以节点Node为根节点的二分搜索树树中添加新的元素键值对节点，递归实现
     *
     * @param node 二分搜索树的根节点
     * @param key  要加入地节点的键
     * @param val  要加入地节点的值
     */
    private Node add(Node node, K key, V val) {
        // 一、递归终止条件
        if (node == null) {
            // 只要碰到了为空的node，就一定要把我们的e作为节点添加到这里的，具体是作为左子树、右子树还是根节点到下面再进行设置
            size++;
            // 新加地节点刚开始都是叶子节点，所以Node的默认构造函数把height设置为1没问题，
            return new Node(key, val);
        }

        // 二、递归组成逻辑
        if (key.compareTo(node.key) < 0) {
            // key小于根节点的key，往node的左子树继续遍历
            node.left = add(node.left, key, val);
        } else if (key.compareTo(node.key) > 0) {
            // key大于根节点的key，往node的右子树继续遍历
            node.right = add(node.right, key, val);
        } else {
            // 如果和遍历到的节点相等即key.compareTo(node.key)==0，则进行节点值更新
            node.val = val;
        }
        // 加入新节点后通过旋转使得二叉树重新平衡。
        // 注意：添加节点后，高度的更新是在递归回退即从下往上遍历树的时候发生地，所以node节点失衡时，下面的节点肯定都更新过了高度
        // 当这个node是把key给new出来地就设置到子节点为空的上面去；如果不是new出来地相当于把已有的二分搜索树中的节点关系又设置一次
        return rotateToReBalance(node);
    }

    /**
     * 向二分搜索树中添加键值对节点(key, val)
     */
    public void add(K key, V val) {
        root = add(root, key, val);
    }

    /**
     * 在以节点node为根节点的二分搜索树树中查找是否包含键key的键值对(key, val)节点，有地话就把这个节点返回
     */
    private Node getNode(Node node, K key) {
        // 递归终止条件
        if (node == null) {
            // 遍历到到二叉树最底部了，还没找到，二分搜索树中肯定没有这个这个元素了
            return null;
        }

        // 递归组成逻辑
        if (key.compareTo(node.key) < 0) {
            // key小于当前节点的key，向左递归
            return getNode(node.left, key);
        } else if (key.compareTo(node.key) > 0) {
            // key大于当前节点的key，向右递归
            return getNode(node.right, key);
        } else {
            // key等于当前节点的key，返回当前节点node
            return node;
        }
    }

    /**
     * 看二分搜索树种是否包含键为key的键值对
     */
    public boolean contains(K key) {
        // 找不到指定的节点就返回null
        return getNode(root, key) != null;
    }

    /**
     * 根据指定的键查找对应的值
     *
     * @param key 键
     * @return 值
     */
    public V get(K key) {
        Node node = getNode(root, key);
        return node == null ? null : node.val;
    }

    /**
     * 更新树种的键值对
     *
     * @param key    键
     * @param valNew 值
     */
    public void set(K key, V valNew) {
        Node node = getNode(root, key);
        if (node == null) {
            throw new IllegalArgumentException("您要更新的键值对在BST中不存在！");
        }
        node.val = valNew;
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
        System.out.print(node.key + ":" + node.val + " ");
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
        System.out.print(node.key + ":" + node.val + " ");
        // 3.遍历右子树
        inOrder(node.right);
    }

    /**
     * 中序遍历以node作为根节点的二分搜索树,把遍历到的节点顺序加入到list中
     */
    private void inOrder(Node node, List<K> keys) {
        // 递归终止条件
        if (node == null) {
            // 遍历到null节点就返回上一层递归
            return;
        }

        // 递归组成逻辑
        // 2.遍历左子树
        inOrder(node.left, keys);
        // 1.访问当前节点。需要存储时可以放到list中
        // 访问节点可以是打印也可以是存储到list中
        // System.out.print(node.key + ":" + node.val + " ");
        keys.add(node.key);
        // 3.遍历右子树
        inOrder(node.right, keys);
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
        System.out.print(node.key + ":" + node.val + " ");
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
            System.out.print(node.key + ":" + node.val + " ");
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
    public Node minimum() {
        if (size == 0) {
            throw new IllegalArgumentException("BST为空！无法找最小值");
        }
        return minimum(root);
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
    public Node maximum() {
        if (size == 0) {
            throw new IllegalArgumentException("BST为空！无法找最小值");
        }
        return maximum(root);
    }

    /**
     * 从二分搜索树种删除最小元素并返回
     *
     * @return 删除的最小元素
     */
    public Node removeMin() {
        // 这里先记录下最小值，防止下面removeMin()后树结构变了
        Node min = minimum();
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
    public Node removeMax() {
        // 这里先记录下最大值，removeMax()后树结构变了
        Node max = maximum();
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
     * @param key 节点的键
     */
    public void remove(K key) {
        root = remove(root, key);
    }

    /**
     * 删除
     *
     * @param node 二分搜索树的根节点
     * @param key  待删除节点的键
     * @return 要挂载到当前节点父节点的子树
     */
    private Node remove(Node node, K key) {
        // 递归终止条件
        if (node == null) {
            return null;
        }

        // 递归组成逻辑
        // 还没找到就接着往下找
        if (key.compareTo(node.key) < 0) {
            // 要找的值比当前节点小，向左递归
            node.left = remove(node.left, key);
            return node;
        } else if (key.compareTo(node.key) > 0) {
            // 要找的值比当前节点大，向右递归
            node.right = remove(node.right, key);
            return node;
        } else {
            // node.key == key 找到相等的节点了，下面删除指定值的节点
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
            // 警告：下面两行代码一定不要颠倒，一定要先设置right再设置left，否则会出现迭代引用！
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
