/***********************************************************
 * @Description : T958_判断一个二叉树是否是完全二叉树
 * https://leetcode-cn.com/problems/check-completeness-of-a-binary-tree/
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/2/4 17:56
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C04_二叉树.二叉树的遍历.T958_判断一个二叉树是否是完全二叉树;

import C04_二叉树.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class Solution {
    public boolean isCompleteTree(TreeNode root) {
        if (root == null) {
            return false;
        }
        // 1.层序遍历二叉树(BFS)，所以这里用到了队列，注意要用LinkedList，因为ArrayDeque不允许插入null
        Queue<TreeNode> queue = new LinkedList<>();
        // 是否也叶子节点
        boolean flag = false;
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.remove();
            // 一旦遇到null就停止遍历，下一次while再能进来，说明还有节点没被遍历
            // 结合完全二叉树的特点，可知此时二叉树肯定不是完全二叉树了
            if (node == null) {
                flag = true;
                continue;
            }
            if (flag) {
                return false;
            }
            queue.add(node.left);
            queue.add(node.right);
        }
        return true;
    }
}
