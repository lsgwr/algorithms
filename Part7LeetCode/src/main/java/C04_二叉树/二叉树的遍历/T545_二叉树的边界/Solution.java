/***********************************************************
 * @Description : 二叉树的边界
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/2/4 19:24
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C04_二叉树.二叉树的遍历.T545_二叉树的边界;

import C04_二叉树.TreeNode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 这题主要是要理解左右边界的概念，左边界是从根到最左侧结点的路径，就是从根节点一直往左孩子走，左孩子为空，
 * 就往右孩子走一步，继续往这个节点的左孩子前进，再没有就继续右孩子走一步，直到有左孩子或者是叶子节点（有左孩子，就继续往左孩子走了），
 * 其实说白了，就是沿着最左子树路径一直走，直到找到第一个叶子节点为止，右视图的概念同理，理解了这个概念，做题目也就不难了。
 *
 * 关于叶子节点视图，因为要保证先左后右的顺序，找叶子节点只能采用先序/中序的遍历方式，才能保证有序。
 *
 * 右视图最后是倒序加入的，需要一个栈来辅助（这里我采用了双端队列来模拟，效果一样），最后加入到结果集中
 */
public class Solution {
    /**
     * 思路：二叉树的”三“视图，左视图+底视图（即全部叶子节点）+右视图，主要是要注意左视图和右视图的定义
     * 左视图的概念：
     * 从根节点一直往左孩子走，左孩子为空，就往右孩子走一步，继续往这个节点的左孩子前进，
     * 再没有就继续右孩子走一步，直到有左孩子或者是叶子节点（有左孩子，就继续往左孩子走了）
     *
     * @param root root
     * @return 边界视图
     */
    public List<Integer> boundaryOfBinaryTree(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        // 根节点不是叶子节点才加入，后面叶子节点视图冗余了全部叶子节点
        if (!isLeaf(root)) {
            res.add(root.val);
        }
        // 获取左视图
        getLeftView(root, res);
        // 获取叶子节点视图
        getLeafView(root, res);
        // 获取右视图
        getRightView(root, res);
        return res;
    }

    /**
     * 获取左视图
     * 左边界的定义是从根到最左侧结点的路径，所以左视图只要考虑最左路径即可
     * @param root root
     * @param res  结果集
     */
    private void getLeftView(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }
        // 左视图，自然往左子树走了
        TreeNode node = root.left;
        // 其实左视图的意思就是沿着最左子树路径一直走，直到找到第一个叶子节点为止
        while (node != null) {
            if (!isLeaf(node)) {
                // 不是叶子节点才加入，是为了避免重复，之后获取全部叶子节点的时候，叶子节点会加入
                res.add(node.val);
            }
            if (node.left != null) {
                // 左视图是根到最左侧节点的的路径，所以左孩子不空，就往左孩子走
                node = node.left;
            } else {
                // 否则往其右子树前进，右孩子不空，仍然会有最左节点的路径
                node = node.right;
            }
        }
    }

    /**
     * 获取叶子节点视图
     *
     * @param root root
     * @param res  结果集
     */
    private void getLeafView(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }
        TreeNode node = root;
        Deque<TreeNode> stack = new LinkedList<>();
        // 先序/中序遍历增加叶子节点均可
        while (node != null || !stack.isEmpty()) {
            if (node != null) {
                stack.push(node);
                node = node.left;
            } else {
                node = stack.pop();
                if (isLeaf(node)) {
                    res.add(node.val);
                }
                node = node.right;
            }
        }
    }

    /**
     * 获取右视图
     * 右边界的定义是从根到最右侧结点的路径，所以右视图只要考虑最右路径即可
     * @param root root
     * @param res  结果集
     */
    private void getRightView(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }
        // 右视图，自然往右子树走了
        TreeNode node = root.right;
        // 右视图是要倒序输出，这里用个栈来存储
        Deque<TreeNode> stack = new LinkedList<>();
        // 其实右视图的意思就是沿着最右子树路径一直走，直到找到第一个叶子节点为止
        while (node != null) {
            if (!isLeaf(node)) {
                // 不是叶子节点才加入，是为了避免重复，之前已经获取全部叶子节点了
                stack.push(node);
            }
            if (node.right != null) {
                // 右视图是根到最左侧节点的的路径，所以右孩子不空，就往右孩子走
                node = node.right;
            } else {
                // 否则往其左子树前进，左孩子不空，仍然会有最右节点的路径
                node = node.left;
            }
        }
        // 右视图加入到res结果集中
        while (!stack.isEmpty()) {
            res.add(stack.pop().val);
        }
    }

    /**
     * 判断节点是否叶子节点
     *
     * @param node 树节点
     * @return 是否叶子节点
     */
    private boolean isLeaf(TreeNode node) {
        return node.left == null && node.right == null;
    }
}
