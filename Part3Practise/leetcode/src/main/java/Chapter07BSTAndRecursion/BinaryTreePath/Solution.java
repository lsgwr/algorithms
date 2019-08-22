package Chapter07BSTAndRecursion.BinaryTreePath;

import Chapter07BSTAndRecursion.TreeNode;

import java.util.ArrayList;
import java.util.List;

/***********************************************************
 *257. 二叉树的所有路径  给定一个二叉树，返回所有从根节点到叶子节点的路径。
 *
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 示例:
 *
 * 输入:
 *
 *    1
 *  /   \
 * 2     3
 *  \
 *   5
 *
 * 输出: ["1->2->5", "1->3"]
 *
 * 解释: 所有根节点到叶子节点的路径为: 1->2->5, 1->3
 *
 * @author    : l00379880 梁山广
 * @version   : V1.0 at 2019/8/22 19:37
 ***********************************************************/
public class Solution {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        if (root.left == null && root.right == null) {
            result.add(String.valueOf(root.val));
        }

        // 获取并拼接左子树字符串
        List<String> listL = binaryTreePaths(root.left);
        for (int i = 0; i < listL.size(); i++) {
            result.add(root.val + "->" + listL.get(i));
        }
        // 获取并拼接右子树字符串
        List<String> listR = binaryTreePaths(root.right);
        for (int i = 0; i < listR.size(); i++) {
            result.add(root.val + "->" + listR.get(i));
        }

        return result;
    }
}
