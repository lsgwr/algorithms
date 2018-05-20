/***********************************************************
 * @Description : 平衡二叉树(二叉查找树加上平衡因子)
 * @date        : 2018/5/20 22:19
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter12AVLTree.Section2CalculateBalanceFactor;


import java.util.ArrayList;
import java.util.LinkedList;

/**
 * @param <Key>   作为树的索引关键词，必须是可比较地，所以需要继承Comparable
 * @param <Value> 不需要可比较
 */
public class AVLTree<Key extends Comparable<Key>, Value> {

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
         * 节点的高度
         */
        int height;

        /**
         * 构造函数
         */
        Node(Key key, Value value) {
            // key是唯一地，不能重复
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
            this.height = 1;
        }

        public Node(Node node) {
            // key是唯一地，不能重复
            this.key = node.key;
            this.value = node.value;
            this.left = node.left;
            this.right = node.right;
            this.height = node.height;
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

    public AVLTree() {
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

    /**
     * 判断该二叉树是否是一棵二分搜索树
     */
    public boolean isBST() {
        ArrayList<Key> keys = new ArrayList<>();
        // 二分搜索树的中序遍历可以用来升序排序，用这个性质来检查是否是一个二分搜索树
        inOrder(root, keys);
        for (int i = 1; i < keys.size(); i++) {
            if (keys.get(i - 1).compareTo(keys.get(i)) > 0) {
                // 升序排列中出现一个地方前面的元素大于后面的元素就可以否定整个二叉搜索树了
                return false;
            }
        }
        return true;
    }

    /**
     * 判断二叉树是否是一棵平衡二叉树
     */
    public boolean isBalanced() {
        return isBalanced(root);
    }

    //***************************************************
    //* 二分搜索树的辅助函数
    //**************************************************

    /**
     * 判断二叉树是否是一棵平衡二叉树
     *
     * @param node 递归开始的子树根节点，root时表示整个子树
     */
    private boolean isBalanced(Node node) {
        if (node == null) {
            // 递归到底仍未发现不平衡的左右子树，说明整个二叉树是平衡地
            return true;
        }
        int balancedFactor = getNodeBalanceFactor(node);
        if (Math.abs(balancedFactor) > 1) {
            return false;
        }
        // 递归查看左右子树
        return isBalanced(node.left) && isBalanced(node.right);
    }

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
        // AVL新增：维护节点的高度值,父节点的高度等于左右子树中高度较高的+1
        node.height = 1 + Math.max(getNodeHeight(node.left), getNodeHeight(node.right));
        // 计算平衡因子,用于下面进行二叉树的平衡维护
        int balanceFactor = getNodeBalanceFactor(node);
        /************************二叉搜索树的平衡维护************************/
        // 1.左子树的左子树高度过高,进行右旋转(LL)
        if (balanceFactor > 1 && getNodeBalanceFactor(node.left) >= 0) {
            return rotateRight(node);
        }
        // 2.右子树的右子树高度过高,进行左旋转(RR)
        if (balanceFactor < -1 && getNodeBalanceFactor(node.right) <= 0) {
            return rotateLeft(node);
        }
        // 3.左子树的右子树高度过高，先把左子节点左旋转，变成LL后再右旋转(LR)
        if (balanceFactor > 1 && getNodeBalanceFactor(node.left) < 0) {
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        }
        // 4.右子树的左子树高度过高，先把右子节点右旋转，变成RR后再左旋转(RL)
        if (balanceFactor < -1 && getNodeBalanceFactor(node.right) > 0) {
            node.right = rotateRight(node.right);
            return rotateLeft(node);
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
     * 对以node为根的二叉搜索树进行中序遍历,结果一直sout输出
     */
    private void inOrder(Node node) {
        if (node != null) {
            inOrder(node.left);
            System.out.println(node.key);
            inOrder(node.right);
        }
    }

    /**
     * 对以node为根的二叉搜索树进行中序遍历,结果存放到list中
     */
    private void inOrder(Node node, ArrayList<Key> keys) {
        if (node != null) {
            inOrder(node.left, keys);
            keys.add(node.key);
            inOrder(node.right, keys);
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
     * 返回指定节点的层高度值
     */
    private int getNodeHeight(Node node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }

    /**
     * 获取指点节点的平衡因子(即左右子树高度差地绝对值)
     *
     * @param node 指定的节点
     * @return >0表示左子树高度右子树；=0表示左右高度相等；<0表示左子树高度小于右子树
     */
    private int getNodeBalanceFactor(Node node) {
        if (node == null) {
            // 空节点没有左右子树，平衡因子(左右子树高度差)设置为0
            return 0;
        }
        return getNodeHeight(node.left) - getNodeHeight(node.right);
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

    /*****************************************************************************
     * 坐旋转、右旋转等多种旋转方式实现二叉搜索树的平衡，平衡函数都是在insert中调用地
     * ***************************************************************************/
    /*******************************************************
     * 对节点y进行向右旋转操作，返回旋转后新的根节点x
     *        y                              x
     *       / \                           /   \
     *      x   T4     向右旋转 (y)        z     y
     *     / \       - - - - - - - ->    / \   / \
     *    z   T3                       T1  T2 T3 T4
     *   / \
     * T1   T2
     *******************************************************/
    private Node rotateRight(Node y) {
        // 1.y以x为轴进行右旋转
        Node x = y.left;
        Node T3 = x.right;
        x.right = y;
        y.left = T3;
        // 2.更新height
        y.height = 1 + Math.max(getNodeHeight(y.left), getNodeHeight(y.right));
        x.height = 1 + Math.max(getNodeHeight(x.left), getNodeHeight(x.right));
        return x;
    }

    /*******************************************************
     * 对节点y进行向左旋转操作，返回旋转后新的根节点x
     *    y                             x
     *  /  \                          /   \
     * T1   x      向左旋转 (y)       y     z
     *     / \   - - - - - - - ->   / \   / \
     *   T2  z                     T1 T2 T3 T4
     *      / \
     *     T3 T4
     *******************************************************/
    private Node rotateLeft(Node y) {
        // 1.y以x为轴进行左旋转
        Node x = y.right;
        Node T2 = x.left;
        x.left = y;
        y.right = T2;
        // 2.更新height
        y.height = 1 + Math.max(getNodeHeight(y.left), getNodeHeight(y.right));
        x.height = 1 + Math.max(getNodeHeight(x.left), getNodeHeight(x.right));
        return x;
    }

    // private Node rotateLeftRight(Node )

}
