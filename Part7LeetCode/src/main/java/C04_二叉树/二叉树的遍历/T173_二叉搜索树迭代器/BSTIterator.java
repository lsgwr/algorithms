/***********************************************************
 * @Description : 173.二叉搜索树迭代器
 * https://leetcode-cn.com/problems/binary-search-tree-iterator/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/30 20:24
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C04_二叉树.二叉树的遍历.T173_二叉搜索树迭代器;

import C04_二叉树.TreeNode;

import java.util.Stack;

/**
 * 考察非递归的中序遍历。 这道题本质上是写一个二叉树的中序遍历的迭代器。 内部设置一个栈， 初始化的
 * 时候， 存储从根节点到最左叶子节点的路径。 在遍历的过程中， 每次从栈中弹出一个元素， 作为当前的返
 * 回结果， 同时探测一下当前节点是否存在右孩子， 如果有， 则进入右孩子， 并把从该右孩子到最左叶子节
 * 点的所有节点入栈
 *
 * @author liangshanguang
 */
public class BSTIterator {
    private Stack<TreeNode> stack;

    public BSTIterator(TreeNode root) {
        stack = new Stack<>();
        while (root != null) {
            stack.push(root);
            root = root.left;
        }
    }

    /**
     * @return whether we have a next smallest number
     */
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    /**
     * @return the next smallest number
     */
    public int next() {
        final TreeNode node = stack.pop();
        if (node.right != null) {
            TreeNode p = node.right;
            while (p != null) {
                stack.push(p);
                p = p.left;
            }
        }
        return node.val;
    }
}
