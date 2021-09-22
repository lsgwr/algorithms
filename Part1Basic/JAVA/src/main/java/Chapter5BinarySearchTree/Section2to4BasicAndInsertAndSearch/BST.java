/***********************************************************
 * @Description : 二分搜索树
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/4/24 23:09
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter5BinarySearchTree.section2to4BasicAndInsertAndSearch;

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


    public static void main(String[] args) {
        int N = 1000000;

        // 创建一个数组，包含[0...N)的所有元素
        Integer[] arr = new Integer[N];
        for (int i = 0; i < N; i++) {
            arr[i] = new Integer(i);
        }

        // 打乱数组顺序
        for (int i = 0; i < N; i++) {
            int pos = (int) (Math.random() * (i + 1));
            Integer t = arr[pos];
            arr[pos] = arr[i];
            arr[i] = t;
        }
        // 由于我们实现的二分搜索树不是平衡二叉树，
        // 所以如果按照顺序插入一组数据，我们的二分搜索树会退化成为一个链表
        // 平衡二叉树的实现，我们在这个课程中没有涉及，
        // 有兴趣的同学可以查看资料自学诸如红黑树的实现
        // 以后有机会，我会在别的课程里向大家介绍平衡二叉树的实现的：）


        // 我们测试用的的二分搜索树的键类型为Integer，值类型为String
        // 键值的对应关系为每个整型对应代表这个整型的字符串
        BST<Integer, String> bst = new BST<Integer, String>();
        for (int i = 0; i < N; i++) {
            bst.insert(new Integer(arr[i]), Integer.toString(arr[i]));
        }

        // 对[0...2*N)的所有整型测试在二分搜索树中查找
        // 若i在[0...N)之间，则能查找到整型所对应的字符串
        // 若i在[N...2*N)之间，则结果为null
        for (int i = 0; i < 2 * N; i++) {
            String res = bst.search(new Integer(i));
            if (i < N) {
                assert res.equals(Integer.toString(i));
            } else {
                assert res == null;
            }
        }
    }
}
