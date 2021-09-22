/***********************************************************
 * @Description : 199.二叉树的右视图
 * https://leetcode-cn.com/problems/binary-tree-right-side-view/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/30 20:12
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C04_二叉树.二叉树的遍历.T199_二叉树的右视图;

import C04_二叉树.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            int i = 0;
            while (i < size) {
                TreeNode node = queue.remove();
                if (i == size - 1) {
                    list.add(node.val);
                }
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
                i++;
            }
        }
        return list;
    }
}
