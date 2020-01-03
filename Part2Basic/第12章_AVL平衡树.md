# 第12章 AVL平衡树
## 12.1 什么是AVL平衡树
> AVL是两个人的任命 `Adelson-Velsky`和`Landis`，；两个人都是俄罗斯人，是两人在1962年的论文中首次提出，是最早的自平衡二分搜索树
## 什么是平衡二叉树？
+ 对任意一个节点，其左子树和右子树的高度差不能超过1
+ 平衡二叉树的高度和节点数量之间的关系也是`O(logn)`的
+ 平衡二叉树某个节点的高度值=`max(左子树高度值，右子树高度值)` + 1。+1时因为父亲节点比子节点高一层。叶子节点的高度值认为是1，左右子树为空高度认为是0
  > 。在下图中用黑色表示
+ 平衡二叉树某个节点的平衡因子=`左子树高度-右子树高度值`，子树为空平衡因子认为是0
  > 在下图中用蓝色表示

![计算节点高度和平衡因子](images/第12章_AVL平衡树/计算节点高度和平衡因子.png)
### 平衡二叉树中"平衡"的含义
+ 如果说一棵树是“平衡”的，就隐含着“这棵树的高度和节点数量成 log(n) 的关系”这样的信息，这也就是“平衡”的意义所在。
+ 之所以要区分一棵树是否平衡，就是因为需要知道这棵树的操作复杂度是什么量级的。比如说“堆是一种平衡树”，实际上就是从操作复杂度说“堆的各种操作（insert、extract）的复杂度都是 O(logn)”。

## 12.2 结算节点的高度和平衡因子
> 只在add函数中更新节点高度height和节点平衡因为balance
```java
/**
     * 向以节点Node为根节点的二分搜索树树中添加新的元素e，递归实现
     *
     * @param node 二分搜索树的根节点
     * @param key  要加入地节点的键
     * @param val  要加入地节点的值
     */
private Node add(Node node, K key, V val) {
    // 递归终止条件
    if (node == null) {
        // 只要碰到了为空的node，就一定要把我们的e作为节点添加到这里的，具体是作为左子树、右子树还是根节点到下面再进行设置
        size++;
        // 新加地节点刚开始都是叶子节点，所以Node的默认构造函数把height设置为1没问题，
        return new Node(key, val);
    }

    // 递归组成逻辑
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
    // 更新当前节点和其往上节点的高度。平衡二叉树某个节点的高度值=max(左子树高度值，右子树高度值) + 1
    // +1时因为父亲节点比子节点高一层。叶子节点的高度值认为是1，左右子树为空高度认为是0
    node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
    // 获取节点的平衡因子，即node节点的左右子树的高度差的。子树为空平衡因子认为是0，即balance=左子树高度-右子树高度值
    node.balance = getHeight(node.left) - getHeight(node.right);
    if (Math.abs(node.balance) > 1) {
        // 如果左右子树的高度差超过了1(平衡二叉树任何一个节点的左右子树高度差不大于1)，说明不是平衡二叉树了
        System.out.println("节点左右子树高度差超过1啦：" + node.balance);
    }
    // 当这个node是把key给new出来地就设置到子节点为空的上面去；如果不是new出来地相当于把已有的二分搜索树中的节点关系又设置一次
    return node;
}
```