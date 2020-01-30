/***********************************************************
 * @Description : 103.二叉树的锯齿形层次遍历
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/1/30 20:33
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package C04_二叉树.二叉树的遍历.T103_二叉树的锯齿形层次遍历;

import C04_二叉树.TreeNode;

import java.util.*;

class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            // 一次性把当前层的节点全部弹出
            List<Integer> levelList = new ArrayList<>();
            while (size-- > 0) {
                TreeNode node = queue.remove();
                levelList.add(node.val);
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            list.add(levelList);
        }
        for (int i = 0; i < list.size(); i++) {
            List<Integer> listCur = list.get(i);
            if (i % 2 == 1) {
                Collections.reverse(listCur);
            }
        }
        return list;
    }
}
