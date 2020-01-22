/***********************************************************
 * @Description : 297. 二叉树的序列化与反序列化 实际是二叉树的层序遍历
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2019/8/22 08:16
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package Chapter07BSTAndRecursion.LeetCode297BST序列化和反序列化;


import Chapter07BSTAndRecursion.TreeNode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) {
            return "[]";
        }
        Queue<String> res = new LinkedList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        res.offer(String.valueOf(root.val));
        while(!queue.isEmpty()) {
            TreeNode node = queue.poll();
            // 有左节点就插入左节点，没有就插入null
            if (node.left != null) {
                queue.offer(node.left);
                res.offer(String.valueOf(node.left.val));
            } else {
                res.offer("null");
            }
            // 有右节点就插入右节点，没有就插入null
            if (node.right != null) {
                queue.offer(node.right);
                res.offer(String.valueOf(node.right.val));
            } else {
                res.offer("null");
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        while(!res.isEmpty()) {
            sb.append(res.poll());
            if (!res.isEmpty()) {
                sb.append(",");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        data = data.substring(1, data.length()-1);
        if (data.length() == 0) {
            return null;
        }
        Queue<String> ls = new LinkedList<>(Arrays.asList(data.split(",")));
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode root = null;
        while(!ls.isEmpty()) {
            String res = ls.poll();
            // 创建根节点
            if (root == null) {
                root = new TreeNode(Integer.parseInt(res));
                queue.offer(root);
                continue;
            }
            // 注意：ls的长度总是奇数的，所以除了根节点，其余节点创建时可以一次取两个ls中的元素
            TreeNode father = queue.poll();
            // 创建左节点
            if (!"null".equals(res)) {
                TreeNode curr = new TreeNode(Integer.parseInt(res));
                assert father != null;
                father.left = curr;
                queue.offer(curr);
            }
            // 创建右节点
            res = ls.poll();
            assert res != null;
            if (!"null".equals(res)) {
                TreeNode curr = new TreeNode(Integer.parseInt(res));
                assert father != null;
                father.right = curr;
                queue.offer(curr);
            }
        }
        return root;
    }
}
